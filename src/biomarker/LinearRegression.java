package biomarker;

import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.MultidimensionalCounter.Iterator;


@SuppressWarnings("serial")
public class LinearRegression extends SimpleRegression{
	
	//LinearRegression regression = null;
	HashMap<String, LinearRegression> RegEq = null;
	HashMap<String, HashMap<String,Double>> SamCNV = null;
	HashMap<String, Double[]> RSqVal = null;
	HashMap<String, HashMap<String,Double>> SampNorm = null;
	
	public  LinearRegression(){
		super();
	}
	
	public void AddData(List<Double> xval, List<Double> yval){
		assert xval.size() == yval.size();
		for(int i = 0; i<xval.size(); i++){
			this.addData(xval.get(i).doubleValue(), yval.get(i).doubleValue());
		}
	}
	
	public double[] convertDouble(List<Double> doubles){
		double[] target = new double[doubles.size()];
		 for (int i = 0; i < target.length; i++) {
		    target[i] = doubles.get(i).doubleValue();                 
		 }
		 return target;
	}
	
	public DefaultTableModel getRsquareTable(HashMap<String, HashMap<String, List<Double>>> sTDlist){
		String [] genes = new String[] {"AREG", "GAPDH", "GIMAP5", "TNFAIP3","HPRT1"};
		Object[] header = {"Gene","R Square","Status","#points"};
		DefaultTableModel model = new DefaultTableModel(header, 0);
		DecimalFormat df = new DecimalFormat("#.###");
		for(int i =0; i<genes.length;i++){
			String gene = genes[i];
			LinearRegression reg  = new LinearRegression();
			reg.AddData(sTDlist.get(gene).get("xval"), sTDlist.get(gene).get("yval"));
			double rsq =  Double.valueOf(df.format(reg.getRSquare()));
			String status = "PASS";
			if(rsq < Thresholds.RSQUARE_CUTOFF){
				status = "FAIL";
			}
			model.addRow(new Object[] {gene, rsq, status, sTDlist.get(gene).get("xval").size()});
		}
		return model;
	}
	
	public HashMap<String, Double[]> getRsquareHash (HashMap<String, HashMap<String, List<Double>>> sTDlist){
		String [] genes = new String[] {"AREG", "GAPDH", "GIMAP5", "TNFAIP3","HPRT1"};
		Object[] header = {"Gene","R Square","Status","#points"};
		RegEq = new HashMap<String, LinearRegression>();
		RSqVal = new HashMap<String, Double[]>();
		DecimalFormat df = new DecimalFormat("#.###");
		for(int i =0; i<genes.length;i++){
			String gene = genes[i];
			LinearRegression reg  = new LinearRegression();
			reg.AddData(sTDlist.get(gene).get("xval"), sTDlist.get(gene).get("yval"));
			RegEq.put(gene, reg);
			double rsq =  Double.valueOf(df.format(reg.getRSquare()));
			Double[] values = {rsq, (double) sTDlist.get(gene).get("xval").size()}; 
			RSqVal.put(gene, values);
		}
		return RSqVal;
	}
	
	public HashMap<String, HashMap<String, Double>> convertToCNV(HashMap<String, HashMap<String, Double>> sampCT){
		SamCNV = new HashMap<String, HashMap<String, Double>>();
		for(Entry<String, HashMap<String, Double>> entry : sampCT.entrySet()){
			String SampleName = entry.getKey();
			HashMap<String, Double> eSet = new HashMap<String, Double>();
			for(Entry<String, Double> gene: entry.getValue().entrySet()){
				String geneName = gene.getKey();
				Double geneCtVal = gene.getValue();
				Double predVal =  Math.pow(10,(RegEq.get(geneName).getSlope()*geneCtVal)+RegEq.get(geneName).getIntercept());
				//Double predVal = RegEq.get(geneName).predict(gene.getValue());
				eSet.put(geneName, predVal);
			}
			SamCNV.put(SampleName,eSet);
		}
		return SamCNV;
	}
	
	public HashMap<String, HashMap<String, Double>> CNVnormalization(Object[] hGenes){
		SampNorm = new HashMap<String, HashMap<String, Double>>();
		HashSet<String> numGenes = new HashSet<String> (Arrays.asList( new String[] {"AREG", "GIMAP5", "TNFAIP3"}));
		for(Entry<String, HashMap<String, Double>> entry : SamCNV.entrySet()){
			String sName = entry.getKey();
			HashMap<String, Double> e = new HashMap<String, Double>();
			Double denVal = 0.0;
			Double NormVal = 0.0;
			for(Object g:hGenes){
				String name = g.toString();
				denVal += SamCNV.get(sName).get(name).doubleValue();
			}
			
			java.util.Iterator<String>  iter = numGenes.iterator();
			while(iter.hasNext()){
				String gName = iter.next();
				if(denVal>0){
					NormVal = SamCNV.get(sName).get(gName).doubleValue()/denVal;
				}
				e.put(gName, NormVal);
			}
			SampNorm.put(sName, e);
		}
		return SampNorm;
	}
	
	public HashMap<String, Double> predictHCC(HashMap<String, HashMap<String,Double>> sNorm, Object[] hGenes){
		HashMap<String,Double> HCCres = new HashMap<String, Double>();
		DecimalFormat df = new DecimalFormat("#.######");
		for(Entry<String, HashMap<String, Double>> entry : sNorm.entrySet()){
			String sName = entry.getKey();
			HashMap<String, Double> val = entry.getValue();
			Double Prob = calProb(val.get("AREG"),val.get("TNFAIP3"), val.get("GIMAP5") , hGenes);
			HCCres.put(sName, Double.parseDouble(df.format(Prob)));
		}
		return HCCres;
	}
	
	public Double calProb(Double a, Double t, Double g, Object[] Ngenes){
		Double powerval = null;
		if(Ngenes.length==2){
			powerval = -(Thresholds.Eq_Both_Constant + (Thresholds.Eq_Both_AREG * a) + (Thresholds.Eq_Both_TNFAIP3 * t) + (Thresholds.Eq_Both_GIMAP5 * g));
		}
		else{
			String Hgene = Ngenes[0].toString();
			if(Hgene.equalsIgnoreCase("HPRT1")){
					powerval = -(Thresholds.Eq_HPRT1_Constant + (Thresholds.Eq_HPRT1_AREG * a) + (Thresholds.Eq_HPRT1_TNFAIP3 * t) + (Thresholds.Eq_HPRT1_GIMAP5 * g));
			}
			else if(Hgene.equalsIgnoreCase("GAPDH")){
					powerval = -(Thresholds.Eq_GAPDH_Constant + (Thresholds.Eq_GAPDH_AREG * a) + (Thresholds.Eq_GAPDH_TNFAIP3 * t) + (Thresholds.Eq_GAPDH_GIMAP5 * g));
			}
		}
		Double denom = 1 + Math.exp(powerval);
		Double pr = 1/denom;
		return pr;
	}
}
