 package biomarker;

import java.awt.Color;
import java.awt.Font;
import java.text.AttributedString;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.statistics.Regression;

public class JRegression extends Regression{
	
	Double Ymax = null;
	Double Ymin = null;
	Double Xmax = null;
	Double Xmin = null;
	Integer nVal = null;
	
	public JRegression(){
		super();
	}
	
	public XYDataset createData(List<Double> xval, List<Double> yval){
		XYSeriesCollection dataset  = new XYSeriesCollection();
		XYSeries series = new XYSeries("STD");
		//yval.sort(Collections.reverseOrder());
		for(int i=0; i <xval.size();i++){
			series.add(xval.get(i).doubleValue(), yval.get(i).doubleValue());
		}
		dataset.addSeries(series);
		this.Xmin = Collections.min(xval);
		this.Xmax = Collections.max(xval);
		this.Ymax = Collections.max(yval);
		this.Ymin = Collections.min(yval);
		this.nVal = xval.size();
		return dataset;
	}
	
	@SuppressWarnings("unused")
	public JFreeChart createChart(XYDataset data, String gene){
		JFreeChart chart = ChartFactory.createScatterPlot("Regression Plot" , "CTmean", "Standards", data, PlotOrientation.VERTICAL, true,true,false);
		XYPlot plot = chart.getXYPlot();
		plot.getRenderer().setSeriesPaint(0, Color.blue);
		plot.getDomainAxis().setVerticalTickLabels(false);
		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);
		plot.setBackgroundPaint(Color.WHITE);
		plot.getDomainAxis().setRange(Xmin-2,Xmax+2);
		plot.getRangeAxis().setRange(Ymin-0.5,Ymax+0.5);
		//plot.getRangeAxis().setInverted(true);
		drawRegressionLine(data, chart, gene);
		return chart;
	}
	
	public void drawRegressionLine(XYDataset data, JFreeChart chart, String geneName) {
		// Get the parameters 'a' and 'b' for an equation y = a + b * x,
		// fitted to the inputData using ordinary least squares regression.
		// a - regressionParameters[0], b - regressionParameters[1]
		String [] eq_values = null;
		double regressionParameters[] = Regression.getOLSRegression(data,0);
		// Prepare a line function using the found parameters
		LineFunction2D linefunction2d = new LineFunction2D(
				regressionParameters[0], regressionParameters[1]);

		// Creates a dataset by taking sample values from the line function
		XYDataset linedataset = DatasetUtilities.sampleFunction2D(linefunction2d,
				this.Xmin, this.Xmax, nVal, "Fitted Regression Line");

		// Draw the line dataset
		XYPlot xyplot = chart.getXYPlot();
		xyplot.setDataset(1, linedataset);
		XYLineAndShapeRenderer xylineandshaperenderer = new XYLineAndShapeRenderer(
				true, false);
		xylineandshaperenderer.setSeriesPaint(0, Color.BLACK);
		xyplot.setRenderer(1, xylineandshaperenderer);
		
		Double xpos = Xmax-2;
		if(Xmin.doubleValue() < 0){
			xpos = Xmax-2.5;
		}
		eq_values = getEquation(data);
		XYTextAnnotation Equation = new XYTextAnnotation("y = "+eq_values[1]+"x + " + eq_values[2], xpos, Ymax+0.2);
		Equation.setFont(new Font("Cambria Math", Font.BOLD, 12));
		String rval = "R"+"\u00B2"+" : "+eq_values[0];
		XYTextAnnotation RSQannot = new XYTextAnnotation(rval, xpos, Ymax-0.1);
		RSQannot.setFont(new Font("Cambria Math", Font.BOLD, 12));
		xyplot.addAnnotation(Equation);
		xyplot.addAnnotation(RSQannot);
	}
	
	private String[] getEquation(XYDataset data){
		String [] val = new String [3];
		DecimalFormat df = new DecimalFormat("#.#####");
		List<Double> xdata = new ArrayList<Double>();
		List<Double> ydata = new ArrayList<Double>();
		
		for(int i=0; i< data.getItemCount(0);i++){
			xdata.add(data.getXValue(0, i));
			ydata.add(data.getYValue(0, i));
		}
	
		LinearRegression reg = new LinearRegression();
		reg.AddData(xdata, ydata);
		String rsq = df.format(reg.getRSquare());
		String slope = df.format(reg.getSlope());
		String cons = df.format(reg.getIntercept());
		val[0] = rsq;
		val[1] = slope;
		val[2] = cons;
		return val;
	}

	
	public HashMap<String, List<Double>> deSelectData(Double pos, List<Double> xval, List<Double> yval){
		//dataset = createData(xVal.remove(pos), yVal.remove(pos));
		//xVal.remove(pos);

		List<Double> x = new ArrayList<Double>();
		List<Double> y = new ArrayList<Double>();
		for(int i=0;i<xval.size();i++){
			if (xval.get(i).doubleValue()!=pos){
				x.add(xval.get(i));
				y.add(yval.get(i));
			}
		}
		HashMap<String, List<Double>> val = new HashMap<String, List<Double>>();
		val.put("xval", x);
		val.put("yval", y);
		return val;
	}
	
	public JFreeChart rePlot(List<Double> xval, List<Double> yval, String gene){
		XYSeriesCollection dataset = (XYSeriesCollection) createData(xval, yval);
		JFreeChart  chart = createChart(dataset, gene);
		return chart;
	}
}
