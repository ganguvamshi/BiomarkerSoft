package biomarker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.util.ArrayUtil;

import biomarker.readexcel.*;

public class QualityCheck {
	
	private static HashMap<String, Object[]> NTC = null;
	private static HashMap<String, Object[]> POS1 = new HashMap<String, Object[]>();
	private static HashMap<String, Object[]> POS2 = new HashMap<String, Object[]>();
	private static HashMap<String, Object[]> POS = new HashMap<String, Object[]>();
	private static HashMap<String, HashMap<String, List<Double>>> STD = new HashMap<String, HashMap<String, List<Double>>>();
	private static HashMap<String, List<Double>> STD_yval = new HashMap<String, List<Double>>();
	private static HashMap<String, List<Double>> STD_xval = new HashMap<String, List<Double>>();
	private static HashMap<String, HashMap<String, Double>> SAMPLES = new HashMap<String, HashMap<String, Double>>();
	private static DecimalFormat df2 = new DecimalFormat("#.###");
	private static HashMap<String, HashMap<String, Double>> HKgenes = new HashMap<String, HashMap<String,Double>>();
	
	public HashMap<String, Object[]> createNTCrecords(List<Book> records){
		Iterator<Book> ntciterator = records.iterator();
		TreeMap<String, Object[]> NTC2 = new TreeMap<>();
		while(ntciterator.hasNext()){
			Book rec = ntciterator.next();
			if(rec.getContent().equalsIgnoreCase("NTC")){
				/*if(rec.getGene().equalsIgnoreCase("AREG") && rec.getCTmean()<0){
					rec.setCTmean(42.0);
				}*/
				Object [] values = {rec.getCTmean().floatValue(), rec.getNTCstatus()};
				NTC2.put(rec.getGene(), values);
			}
		}
		NTC = new HashMap<String, Object[]>(NTC2);
		return NTC;
	}
	
	public HashMap<String, Object[]> createPOSrecords(List<Book> records){
		Iterator<Book> ntciterator = records.iterator();
		while(ntciterator.hasNext()){
			Book rec = ntciterator.next();
			if(rec.getSample().equalsIgnoreCase(Thresholds.POS25)){
				Object [] values = {rec.getCTmean().floatValue(), rec.getPOSstatus()};
				POS1.put(rec.getGene(), values);
			}else if(rec.getSample().equalsIgnoreCase(Thresholds.POS90)){
				Object [] values = {rec.getCTmean().floatValue(), rec.getPOSstatus()};
				POS2.put(rec.getGene(), values);
			}
		}
		POS = combinePOS(POS1, POS2);
		return POS;
	}
	
	public HashMap<String, HashMap<String, Double>> createSampleRecords(List<Book> records){
		Iterator<Book> sampleiterator = records.iterator();
		ArrayList<String> samples = new ArrayList<>();
		while(sampleiterator.hasNext()){
			Book rec = sampleiterator.next();
			if(rec.getContent().equalsIgnoreCase(Thresholds.SAMPLE) && rec.getGene().equalsIgnoreCase(Thresholds.GENE_STDless)){
				samples.add(rec.getSample());
			}
		}
		for(String G:samples){
			Iterator<Book> geneiterator = records.iterator();
			HashMap<String, Double> geneRec = new HashMap<String, Double>();
			while(geneiterator.hasNext()){
				Book rec = geneiterator.next();
				if(rec.getSample().equalsIgnoreCase(G) && rec.getContent().equalsIgnoreCase(Thresholds.SAMPLE)){
					if(rec.getGene().equalsIgnoreCase("AREG") && rec.getCTmean()<0){
						geneRec.put(rec.getGene(), 41.0);
					}
					else{
						geneRec.put(rec.getGene(), rec.getCTmean());
					}
				}
			}
			SAMPLES.put(G, geneRec);
		}
		return SAMPLES;
	}
	
	public HashMap<String, Object[]> combinePOS(HashMap<String, Object[]> pos1, HashMap<String, Object[]> pos2){
		HashMap<String, Object[]> pos = new HashMap<String, Object[]>();
		for(String k : pos1.keySet()){
			Object[] values = new Object[4];
			Object[] oneval = pos1.get(k);
			Object[] secondval = pos2.get(k);
			int l=0;
			for(int a=0; a<oneval.length;a++){
				values[l] = oneval[a];
				l++;
			}
			for(int b=0; b<secondval.length;b++){
				values[l] = secondval[b];
				l++;
			}
			pos.put(k, values);
		}
		return pos;
	}

	@SuppressWarnings("null")
	public HashMap<String, HashMap<String, List<Double>>> createSTDrecords(List<Book> records) {
		// TODO Auto-generated method stub
		Iterator<Book> ntciterator = records.iterator();
		while(ntciterator.hasNext()){
			Book rec = ntciterator.next();
			if(rec.getContent().equalsIgnoreCase("Std")){
				List<Double> meanlist = new ArrayList<Double>();
					if (STD_yval.containsKey(rec.getGene())){
						meanlist = STD_yval.get(rec.getGene());
						meanlist.add(rec.getCTmean());
					}
					else{
						meanlist.add(rec.getCTmean());
					}
					meanlist.sort(Collections.reverseOrder());
					STD_yval.put(rec.getGene(), meanlist);
				//}
			}
		}
		STD_xval = prepareSTDaxis(STD_yval.keySet().toArray());
		for(String gene : STD_xval.keySet()){
			HashMap<String, List<Double>> coord = new HashMap<String, List<Double>>();
			coord.put("yval", STD_xval.get(gene));
			coord.put("xval", STD_yval.get(gene));
			STD.put(gene, coord);
		}
		return STD;
	}
	
	public HashMap<String, HashMap<String, List<Double>>> createSTDrecords2(List<Book> records){
		HashMap<String, HashMap<String, List<Double>>> STD2 = new HashMap<String, HashMap<String, List<Double>>>();
		Iterator<Book> stditerator = records.iterator();
		while(stditerator.hasNext()){
			Book rec = stditerator.next();
			if(rec.getContent().equalsIgnoreCase("Std")){
				if(STD2.containsKey(rec.getGene())){
					STD2.put(rec.getGene(),UpdateSTDlist(rec, STD2.get(rec.getGene())));
				}
				else{
					String gene = rec.getGene();
					HashMap<String, List<Double>> coor = new HashMap<String, List<Double>>();
					List<Double> val = new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
					List<Double> val2 = new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
					coor.put("xval", val);
					coor.put("yval", val2);
					HashMap<String, List<Double>> coor2 = UpdateSTDlist(rec, coor);
					STD2.put(gene, coor2);
				}
			}
		}
		return STD2;
	}
	
	private HashMap<String, List<Double>> UpdateSTDlist(Book rec, HashMap<String, List<Double>> values){
		List<Double> xval = new ArrayList<Double>();
		List<Double> yval = new ArrayList<Double>();
		xval = values.get("xval");
		yval = values.get("yval");
		String std = rec.getSample();
		String gene = rec.getGene();
		if(std.equalsIgnoreCase("PSTD1")){
			xval.set(0, rec.getCTmean().doubleValue());
			if(gene.equalsIgnoreCase(Thresholds.GENE_STDless)){
				yval.set(0, (double) 2);
			}
			else{
				yval.set(0, (double) 1);
			}
		}
		else if (std.equalsIgnoreCase("PSTD2")) {
			xval.set(1, rec.getCTmean().doubleValue());
			if(gene.equalsIgnoreCase(Thresholds.GENE_STDless)){
				yval.set(1, (double) 3);
			}
			else{
				yval.set(1, (double) 2);
			}
		}
		else if(std.equalsIgnoreCase("PSTD3")){
			xval.set(2, rec.getCTmean().doubleValue());
			if(gene.equalsIgnoreCase(Thresholds.GENE_STDless)){
				yval.set(2, (double) 4);
			}
			else{
				yval.set(2, (double) 3);
			}
		}
		else if(std.equalsIgnoreCase("PSTD4")){
			xval.set(3, rec.getCTmean().doubleValue());
			if(gene.equalsIgnoreCase(Thresholds.GENE_STDless)){
				yval.set(3, (double) 5);
			}
			else{
				yval.set(3, (double) 4);
			}
		}
		else if(std.equalsIgnoreCase("PSTD5")){
			xval.set(4, rec.getCTmean().doubleValue());
			if(gene.equalsIgnoreCase(Thresholds.GENE_STDless)){
				yval.set(4, (double) 6);
			}
			else{
				yval.set(4, (double) 5);
			}
		}
		values.put("xval", xval);
		values.put("yval", yval);
		return values;
	}
	
	private HashMap<String, List<Double>> prepareSTDaxis(Object[] geneids) {
		String gene = null;
		for(Object rec : geneids ){
			gene = rec.toString();
			if(gene.equalsIgnoreCase(Thresholds.GENE_STDless)){
				STD_xval.put(gene,  Arrays.asList(2.0,3.0,4.0,5.0,6.0));
			}
			else{
				STD_xval.put(gene, Arrays.asList(1.0,2.0,3.0,4.0,5.0));
			}
		}
		return STD_xval;
	}
	
	public HashMap<String, HashMap<String, Double>> getHouseKeeping(HashMap<String, HashMap<String, Double>> samplelist){
		for(Entry<String, HashMap<String, Double>> sRec: samplelist.entrySet()){
			String Sample = sRec.getKey();
			HashMap<String, Double> sValues = sRec.getValue();
			HashMap<String, Double> rec = new HashMap<String, Double>();
			for(Entry<String, Double> gRec: sValues.entrySet()){
				String gene = gRec.getKey();
				if(gene.equalsIgnoreCase("HPRT1")|| gene.equalsIgnoreCase("GAPDH")){
					rec.put(gene, gRec.getValue());
				}
			}
			HKgenes.put(Sample, rec);
		}
		return HKgenes;
	}
	
}