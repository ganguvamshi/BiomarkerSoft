package biomarker;
/**
 * 
 */
/**
 * @author vamshidhar
 *
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.*;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InterruptedIOException;
//import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;
import javax.swing.text.LabelView;

import org.apache.commons.codec.BinaryDecoder;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.poi.hsmf.exceptions.ChunkNotFoundException;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.title.Title;
import org.jfree.data.DomainOrder;
import org.jfree.data.general.DatasetChangeListener;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;

import com.orsoncharts.table.TableElementOnDraw;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
import biomarker.QualityCheck.*;
import biomarker.readexcel.Book;
import jdk.nashorn.internal.scripts.JO;
import biomarker.Thresholds;

import static java.lang.Math.*;
import jxl.*;
import jxl.biff.ByteArray;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

@SuppressWarnings("unused")
public class GUI implements ActionListener, Runnable {
	private static GUI main = null;
	private static JFrame mainFrame;
	private static JTabbedPane mainPanel;
	private static JPanel AnalysisPanel;
	private static JPanel AdminPanel;
	private static JTextField queryTx = null;
	private static JButton startButton = null;
	private int count = 0;
	private static JProgressBar pBar = null;
	private static InputFileManager ifm = null;
	private static QualityCheck Qual = null;
	private static HashMap<String, Object[]> NTClist = null;
	private static JTable NTCtable;
	private static HashMap<String, Object[]> POSlist = null;
	private static JTable POStable;
	private static HashMap<String, Integer[]> STDdelPos = null;
	private static HashMap<String, Integer[]> STDdelPosT = null;
	public static HashMap<String, HashMap<String, List<Double>>> STDlist = null;
	public static HashMap<String, HashMap<String, List<Double>>> STDlistT = null;
	private static JTable stdTable;
	public static HashMap<String, HashMap<String, Double>> SAMPLElist = null;
	public static HashMap<String, HashMap<String, Double>> SAMPLEcnv = null;
	public static HashMap<String, HashMap<String, Double>> SAMPLEnorm = null;
	public static HashMap<String, Double> SAMPLEoutcome = null;
	private static JPanel ResultPanel = null;
	private static ChartPanel Cpanel = null;
	private static String JGene = null;
	private static HashMap<String, Double[]> RsqTable = null;
	private static HashMap<String, Double[]> RsqTableT = null;
	private static LinearRegression reg = null;
	private static HashMap<String, HashMap<String, Double>> HouseKeepGenes = null;
	private static JTable HKtable;
	private static JTable HCCtable;
	private static File OutFile = null;
	private static boolean nextTime = false;
	//private static boolean QCcheck = true;
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventManager.GetInstance();
		Calendar startTimestamp = Calendar.getInstance();
		startTimestamp.add(Calendar.DATE, 1);
		//String startTime = startTimestamp.getTime().toString();
		//System.out.println("Start Time: " + startTime);
		main = new GUI();
		new Thread(main).start();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	protected static void createAndShowGUI() {
		mainFrame = new JFrame("HCC");
		mainFrame.setTitle("HCC Risk Predictor");
		mainPanel = new JTabbedPane();
		AnalysisPanel = new JPanel();
		AnalysisPanel.setLayout(new BoxLayout(AnalysisPanel, BoxLayout.Y_AXIS));
		AdminPanel = new JPanel();
		AdminPanel.setLayout(new BoxLayout(AdminPanel, BoxLayout.Y_AXIS));
		LoginPanel admin = new LoginPanel(mainFrame);
		JPanel loginPanel = admin.showPanel();
		AdminPanel.add(loginPanel);
		Toolkit tk = Toolkit.getDefaultToolkit();
		int xSize = ((int) tk.getScreenSize().getWidth() / 2) + 200;
		int ySize = ((int) tk.getScreenSize().getHeight() / 2) + 100;
		mainFrame.setSize(xSize, ySize);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		//mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(dim.width / 2 - mainFrame.getSize().width / 2,
				dim.height / 2 - mainFrame.getSize().height / 2);

		JPanel InputPanel = new JPanel(new BorderLayout());
		ResultPanel = new JPanel( new BorderLayout());
		JPanel inputBrowser = getUserInputPanel();
		InputPanel.add(inputBrowser, BorderLayout.CENTER);
		Font iFont = new Font("Verdana", Font.BOLD|Font.ITALIC, 12);
		TitledBorder b= BorderFactory.createTitledBorder(BorderFactory.createLoweredSoftBevelBorder(), "Input", 
							TitledBorder.LEFT ,TitledBorder.TOP,iFont,Color.BLACK);
		InputPanel.setBorder(b);
		ResultPanel.setVisible(false);
		JPanel User = new JPanel(new BorderLayout());
		User.add(InputPanel, BorderLayout.NORTH);
		User.add(ResultPanel, BorderLayout.SOUTH);
		createStartButton();
		InputPanel.add(startButton, BorderLayout.LINE_END);
		
		mainPanel.addTab("HCC predictor", AnalysisPanel);
		AnalysisPanel.add(User);
		mainPanel.add("Admin",AdminPanel);
		mainPanel.setSelectedIndex(0);
		mainFrame.setContentPane(mainPanel);
		startButton.setEnabled(false);
		
		mainFrame.setVisible(true);

	}

	public void run() {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private static JPanel getUserInputPanel(){

		JPanel panel = new JPanel(new BorderLayout());
		JLabel inputFileLabel = new JLabel("  CFX file:");
		queryTx = new JTextField();
		queryTx.setEditable(false);
		Dimension d = queryTx.getPreferredSize();
		Document textFieldDoc = queryTx.getDocument();
		textFieldDoc.addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updated(e);
			}
			public void insertUpdate(DocumentEvent e) {
				updated(e);
			}
			public void removeUpdate(DocumentEvent e) {
				updated(e);
			}
			private void updated(DocumentEvent e) {
				checkButtonState();
			}
		});

		final JFileChooser fc = new JFileChooser();
		JButton openButton = new JButton("Select a CFX file", null);
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fc.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					String path = file.getAbsolutePath();
					queryTx.setText(path);
					queryTx.setPreferredSize(d);
				}
			}
		});
		panel.add(inputFileLabel, BorderLayout.WEST);
		panel.add(queryTx, BorderLayout.CENTER);
		panel.add(openButton, BorderLayout.EAST);
		return panel;
	}
	
	private static void clearData(){
		if(nextTime){
			NTClist.clear();
			NTCtable.removeAll();
			POSlist.clear();
			STDdelPos.clear();
			STDdelPosT.clear();
			STDlist.clear();
			STDlistT.clear();
			stdTable.removeAll();
			SAMPLEcnv.clear();
			SAMPLElist.clear();
			SAMPLEnorm.clear();
			SAMPLEoutcome.clear();
			RsqTable.clear();
			RsqTableT.clear();
			HouseKeepGenes.clear();
			HKtable.removeAll();
			HCCtable.removeAll();
		}
	}

	private static JPanel getNTCpanel(){
		JPanel NTC = new JPanel(new BorderLayout());
		TableModel ntcdata = NTCTableModel(NTClist);
		NTCtable = new JTable(ntcdata){
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
				JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);  
				if(getValueAt(rowIndex, 2).toString().equalsIgnoreCase("FAIL") && columnIndex == 2) {
					component.setBackground(Color.RED);
				} else if(getValueAt(rowIndex, 2).toString().equalsIgnoreCase("PASS") && columnIndex == 2){
					component.setBackground(Color.GREEN);
				}
				else{
					component.setBackground(Color.WHITE);
				}
				return component;
			}
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(NTCtable.getModel());
		sorter.setSortsOnUpdates(true);
		NTCtable.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<SortKey>();
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		
		
		NTCtable.setBackground(Color.gray);;
		NTCtable.setPreferredScrollableViewportSize(NTCtable.getPreferredSize());
		NTCtable.setRowSelectionAllowed(false);
		JScrollPane NTCscroll = new JScrollPane(NTCtable);
		NTCscroll.setMaximumSize(new Dimension(5, 5));
		JLabel NTClabel = new JLabel("NTC");
		NTClabel.setFont(new Font("serif", Font.BOLD, 14));
		NTC.add(NTClabel, BorderLayout.NORTH);
		NTC.add(NTCscroll, BorderLayout.SOUTH);
		return NTC;
	}

	public static TableModel NTCTableModel(Map<String,Object[]> map) {
		Object[] header = {"Gene","Ct Mean","Status"};
		DefaultTableModel model = new DefaultTableModel(header,0);
		for (Map.Entry<String,Object[]> entry : map.entrySet()) {
			Double cT = Double.parseDouble(entry.getValue()[0].toString());
			if(cT<0){
				model.addRow(new Object[] { Thresholds.DUP_NAMES.get(entry.getKey()), "NA", entry.getValue()[1] });
			}
			else{
				model.addRow(new Object[] { Thresholds.DUP_NAMES.get(entry.getKey()), entry.getValue()[0], entry.getValue()[1] });
			}
		}
		return model;
	}

	private static void checkButtonState() {
		boolean state = (queryTx.getText().length() > 0);
		startButton.setEnabled(state);
	}

	private static void createStartButton() {
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// This is what happens when user click the START button
				String infile = queryTx.getText();
				//System.out.println("Input File: "+infile+"\n");
				File inF = new File(infile);
				String inBase = inF.getName().split("\\.(?=[^\\.]+$)")[0];
				inBase = inBase.replaceAll(" ", "_").toLowerCase();
				String outF = inF.getParent()+ System.getProperty("file.separator") + inBase + Thresholds.OUTFILE_SUFFIX ;
				// Store results in input directory
				OutFile = new File(outF);
				//System.out.println("Input File: "+ outF+"\n");
				Integer inpCol = new Integer(0);
				Calendar startTimestamp = Calendar.getInstance();
				startTimestamp.add(Calendar.DATE, 1);
				String startTime = startTimestamp.getTime().toString();
				main.startedRunning();
				clearData();
				ifm = new InputFileManager();
				Qual = new QualityCheck();
				
				try {
					List<Book> inputDict = ifm.ReadInput(infile);
					NTClist = Qual.createNTCrecords(inputDict);
					POSlist = Qual.createPOSrecords(inputDict);
					STDlist = Qual.createSTDrecords2(inputDict);
					SAMPLElist = Qual.createSampleRecords(inputDict);
					createResultPanel();
					mainFrame.setVisible(true);
					mainFrame.pack();
					mainFrame.setResizable(false);
				} catch (IOException e) {
					System.out.println("Something went wrong");
					e.printStackTrace();
				}
			}
		});
	}

	protected static void createResultPanel() {
		// The final result panel
		JPanel ResultTable1 = new JPanel(new FlowLayout());
		JPanel NTCpanel = getNTCpanel();
		JPanel POSpanel = getPOSpanel();
		JPanel STDtable = getSTDPanel();
		ResultTable1.add(NTCpanel);
		ResultTable1.add(POSpanel);
		ResultTable1.add(STDtable);
		ResultTable1.setVisible(true);
		
		String borderName = "Quality Control";
		Font bFont = new Font("Verdana", Font.BOLD|Font.ITALIC, 12);
		TitledBorder border2 = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), borderName, TitledBorder.LEFT ,TitledBorder.TOP,bFont,Color.BLACK);
		ResultTable1.setBorder(border2);
		startButton.setEnabled(false);
		JPanel outcome = getOUTpanel();
		ResultPanel.removeAll();
		ResultPanel.add(ResultTable1, BorderLayout.NORTH);
		ResultPanel.add(outcome, BorderLayout.SOUTH);
		ResultPanel.setVisible(true);
		AnalysisPanel.add(ResultPanel);
		mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	private static JPanel getOUTpanel(){
		JPanel CNVpanel = new JPanel(new BorderLayout());
		
		JPanel HKpanel = new JPanel(new BorderLayout());
		final JPanel HCCpanel = new JPanel();
        HCCpanel.setLayout(new BoxLayout(HCCpanel, BoxLayout.Y_AXIS));		
        HCCpanel.setVisible(false);
		HouseKeepGenes = Qual.getHouseKeeping(SAMPLElist);
		TableModel HKmodel = getHKtablemodel(HouseKeepGenes);
		HKtable = new JTable(HKmodel){
			private static final long serialVersionUID = 1L;
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
				JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);  
				if(getValueAt(rowIndex, 3).toString().equalsIgnoreCase("FAIL") && columnIndex == 3) {
					component.setBackground(Color.RED);
				}
				else if(getValueAt(rowIndex, 3).toString().equalsIgnoreCase("PASS") && columnIndex == 3){
					component.setBackground(Color.GREEN);
				}
				else{
					component.setBackground(Color.WHITE);
				}
				int rendererWidth = component.getPreferredSize().width;
		        TableColumn tableColumn = getColumnModel().getColumn(columnIndex);
		        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		HKtable.setBackground(Color.gray);
		HKtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		HKtable.setPreferredScrollableViewportSize(HKtable.getPreferredSize());
		HKtable.setRowSelectionAllowed(false);
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(HKtable.getModel());
		HKtable.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<SortKey>();
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		
		JScrollPane HKscroll = new JScrollPane(HKtable);
		HKpanel.add(HKscroll, BorderLayout.SOUTH);
		
		final JPanel OutFolderPanel= OUTPUTpanel();
		OutFolderPanel.setVisible(false);
		
		JButton predict = new JButton("Predict");
		predict.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean QCcheck = checkQCstatus();
				if(!QCcheck){
					JOptionPane.showMessageDialog(mainFrame, "Quality control did not pass","QC", JOptionPane.WARNING_MESSAGE);
				}
				final ArrayList<String> NormGene = new ArrayList<String>();
				if(Thresholds.HPRT1_selected){
					NormGene.add("HPRT1");
				}
				if(Thresholds.GAPDH_selected){
					NormGene.add("GAPDH");
				}
				if(NormGene.isEmpty()){
					JOptionPane.showMessageDialog(mainFrame, "No Genes are selected", "Normalization issue", JOptionPane.WARNING_MESSAGE);
				}
				else{
					SAMPLEcnv = reg.convertToCNV(SAMPLElist);
					SAMPLEnorm = reg.CNVnormalization(NormGene.toArray()) ;
					SAMPLEoutcome = reg.predictHCC(SAMPLEnorm, NormGene.toArray());
					TableModel HCCmodel = HCCTableModel(SAMPLEoutcome);
					HCCtable = new JTable(HCCmodel){
						private static final long serialVersionUID = 1L;
						@Override
						public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
							JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);  
							if(getValueAt(rowIndex, 1).equals(2) && columnIndex == 1) {
								component.setBackground(Color.GREEN);
							}  
							else{
								component.setBackground(Color.WHITE);
							}
							int rendererWidth = component.getPreferredSize().width;
					        TableColumn tableColumn = getColumnModel().getColumn(columnIndex);
					        tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
							return component;
						}
						public boolean isCellEditable(int row, int column) {
							return false;
						}
						
					};
					HCCtable.setBackground(Color.GRAY);
					HCCtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					HCCtable.setPreferredScrollableViewportSize(HCCtable.getPreferredSize());
					HCCtable.setRowSelectionAllowed(false);
					TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(HCCtable.getModel());
					HCCtable.setRowSorter(sorter);
					List<RowSorter.SortKey> sortKeys = new ArrayList<SortKey>();
					int columnIndexToSort = 0;
					sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
					sorter.setSortKeys(sortKeys);
					sorter.sort();
					
					JScrollPane HCCscroll = new JScrollPane(HCCtable);
					HCCscroll.setMaximumSize(new Dimension(8, 10));
					JLabel HCClabel = new JLabel("HCC Outcome");
					HCClabel.setFont(new Font("serif", Font.BOLD|Font.ITALIC, 14));
					JPanel HCCpanel2 = new JPanel(new FlowLayout());
					HCCpanel2.add(HCCscroll,BorderLayout.CENTER);
					HCCpanel.removeAll();
					HCCpanel.add(HCCpanel2);
					try {
						WriteOuput(OutFile);
					} catch (RowsExceededException e1) {
						e1.printStackTrace();
					} catch (WriteException e1) {
						e1.printStackTrace();
					}
					OutFolderPanel.setVisible(true);
					HCCpanel.setVisible(true);
					mainFrame.pack();
					//
				}
			}
		});
		
		JPanel HKpanel2 = new JPanel(new FlowLayout());
		HKpanel2.add(HKpanel);//, BorderLayout.CENTER);
		HKpanel2.add(predict);//, BorderLayout.EAST);
		Border bord = BorderFactory.createLineBorder(Color.BLACK);
		Font bFont = new Font("Verdana", Font.BOLD|Font.ITALIC, 12);
		HKpanel.setBorder(BorderFactory.createTitledBorder(bord, "House Keeping Genes",TitledBorder.LEFT, TitledBorder.TOP, bFont,Color.BLACK));
		HKpanel2.add(HCCpanel);
		CNVpanel.add(HKpanel2, BorderLayout.NORTH);
		CNVpanel.add(OutFolderPanel, BorderLayout.SOUTH);
		HCCpanel.setBorder(BorderFactory.createTitledBorder(bord, "Prediction",TitledBorder.LEFT, TitledBorder.TOP, bFont,Color.BLACK));
		return CNVpanel;
	}
	
	protected static void WriteOuput(File file) throws RowsExceededException, WriteException {
		// TODO Auto-generated method stub
		try {
			WritableWorkbook workbook1 = Workbook.createWorkbook(OutFile);
			WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0); 

			//Inserting the NCCS logo
			final double CELL_DEFAULT_HEIGHT = 40;
			final double CELL_DEFAULT_WIDTH = 170;
			BufferedImage input = null;
			try{
				URL imageFile = GUI.class.getResource("images/nccs_logo.png");
				input = ImageIO.read(imageFile);
			}
			catch(IOException e){
				e.printStackTrace();
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(input, "PNG", baos);
			WritableImage img = new WritableImage(0, 0, input.getWidth() / CELL_DEFAULT_WIDTH ,
							input.getHeight()/CELL_DEFAULT_HEIGHT, baos.toByteArray());
			sheet1.addImage(img);
	
			
			int Erow = 1;
			int Ecol= 7;
			Label Title = new Label(Ecol, Erow, "HCC QPCR detection Kit", getFormat("HEADER"));
			sheet1.addCell(Title);
			//Write the QC/QA Header
		    
			Erow = 6;
            Ecol = 1;
            
			Label secCol = new Label(Ecol, Erow, "QA/QC", getFormat("HEADER"));
			sheet1.addCell(secCol);
			
			//Write NTC Table
			Erow = 9;
			Ecol=2;
			TableModel NTCmodel = NTCtable.getModel();
            Label Ncol = new Label(Ecol, Erow, "NTC", getFormat("HEADER"));
            sheet1.addCell(Ncol);
           
            Erow=10;
            Ecol=2;
            for (int i = 0; i < NTCmodel.getColumnCount(); i++) {
                Label column = new Label(Ecol, Erow, NTCmodel.getColumnName(i), getFormat("HEADER"));
                sheet1.addCell(column);
                Ecol +=1;
            } 
            Erow = 11;
            Ecol=2;
            for (int i = 0; i < NTCmodel.getRowCount(); i++) {
            	int nr = NTCtable.convertRowIndexToModel(i);
                for (int j = 0; j < NTCmodel.getColumnCount(); j++) {
                    Label row = new Label(Ecol, Erow, NTCmodel.getValueAt(nr, j).toString(), getFormat("NORMAL"));
                    sheet1.addCell(row);
                    Ecol +=1;
                }
                Erow +=1;
                Ecol=2;
            }
            Erow =17;
            Ecol=3;
            Ncol = new Label(Ecol, Erow, "Cutoff:", getFormat("HEADER"));
            sheet1.addCell(Ncol);
            Ncol = new Label(Ecol+1, Erow, " >="+ String.valueOf(Thresholds.NTC_MAX), getFormat("NORMAL"));
            sheet1.addCell(Ncol);
            
            
            //Writinh POS table
            Erow = 9;
            Ecol = 7;
            Label Pcol = new Label(Ecol, Erow, "POSITIVE CONTROL", getFormat("HEADER"));
            TableModel POSmodel = POStable.getModel();
            sheet1.addCell(Pcol);

            Erow=10;
            Ecol= 7;
            for (int i = 0; i < POSmodel.getColumnCount(); i++) {
                Label column = new Label(Ecol, Erow, POSmodel.getColumnName(i), getFormat("HEADER"));
                sheet1.addCell(column);
                Ecol +=1;
                if(i==1){
                	Label col = new Label(Ecol, Erow, "PC1(cutoff)", getFormat("HEADER"));
                	sheet1.addCell(col);
                	Ecol+=1;
                }
                else if(i==3){
                	Label col = new Label(Ecol, Erow, "PC2(cutoff)", getFormat("HEADER"));
                	sheet1.addCell(col);
                	Ecol+=1;
                }
            } 
            
            Erow =11;
            Ecol=7;
            for (int i = 0; i < POSmodel.getRowCount(); i++) {
            	int pr = POStable.convertRowIndexToModel(i);
            	String gene  = NTCmodel.getValueAt(pr, 0).toString();
                for (int j = 0; j < POSmodel.getColumnCount(); j++) {
                    Label row = new Label(Ecol,Erow, POSmodel.getValueAt(pr, j).toString(), getFormat("NORMAL"));
                    sheet1.addCell(row);
                    Ecol +=1;
                    if(j==1){
                    	Label r = new Label(Ecol, Erow, getPOS25Threshold(gene), getFormat("NORMAL"));
                    	sheet1.addCell(r);
                    	Ecol+=1;
                    }
                    else if(j==3){
                    	Label r = new Label(Ecol, Erow, getPOS90Threshold(gene), getFormat("NORMAL"));
                    	sheet1.addCell(r);
                    	Ecol+=1;
                    }
                }
            	Erow +=1;
            	Ecol=7;
            }
          
            //STD Table
            Erow = 9;
            Ecol = 16;
        	
    		TableModel STDmodel = stdTable.getModel();
            Label Scol = new Label(Ecol, Erow, "STANDARD CURVE", getFormat("HEADER"));
            sheet1.addCell(Scol);
            
            Erow=10;
            Ecol = 16;
            for (int i = 0; i < STDmodel.getColumnCount(); i++) {
                Label column = new Label(Ecol, Erow, STDmodel.getColumnName(i), getFormat("HEADER"));
                sheet1.addCell(column);
                Ecol +=1;
            } 
            Erow=11;
            Ecol=16;
            for (int i = 0; i < STDmodel.getRowCount(); i++) {
            	int sr = stdTable.convertRowIndexToModel(i);
                for (int j = 0; j < STDmodel.getColumnCount(); j++) {
                	Label row = null ;
                	if(j==0){
                		JButton b = (JButton) STDmodel.getValueAt(sr, j);
                		row = new Label(Ecol, Erow, b.getText(), getFormat("NORMAL"));
                	}
                	else{
                		row = new Label(Ecol,Erow, STDmodel.getValueAt(sr, j).toString(), getFormat("NORMAL"));
                	}
                    sheet1.addCell(row);
                    Ecol +=1;
                }
            	Erow +=1;
            	Ecol=16;
            }
            
            Erow = 17;
            Ecol = 18;
            Scol = new Label(Ecol, Erow, "Cutoff: ",getFormat("HEADER"));
            sheet1.addCell(Scol);
            Ecol+=1;
            Scol = new Label(Ecol, Erow,"R2 val > "+String.valueOf(Thresholds.RSQUARE_CUTOFF), getFormat("NORMAL"));
            sheet1.addCell(Scol);
            
            //HouseKeeping Table
            
            Erow=20;
            Ecol =2;
            TableModel HKmodel = HKtable.getModel();
            Label Hcol = new Label(Ecol,Erow, "HOUSEKEEPING GENES", getFormat("HEADER"));
            sheet1.addCell(Hcol);
            
            Erow=21;
            Ecol=2;
            for (int i = 0; i < HKmodel.getColumnCount(); i++) {
                Label column = new Label(Ecol, Erow, HKmodel.getColumnName(i), getFormat("HEADER"));
                sheet1.addCell(column);
                Ecol +=1;
            } 
            Erow = 22;
            Ecol=2;
            
            for (int i = 0; i < HKmodel.getRowCount(); i++) {
            	int hkr = HKtable.convertRowIndexToModel(i);
                for (int j = 0; j < HKmodel.getColumnCount(); j++) {
                    Label row = new Label(Ecol,Erow,HKmodel.getValueAt(hkr, j).toString(), getFormat("NORMAL"));
                    sheet1.addCell(row);
                    Ecol +=1;
                }
            	Erow +=1;
            	Ecol=2;
            }
            
            Erow +=1;
            Ecol=3;
            Hcol = new Label(Ecol, Erow,"Cutoffs:", getFormat("HEADER"));
            sheet1.addCell(Hcol);
            Hcol = new Label(Ecol+1, Erow+1, "Control 1: Ct <= "+String.valueOf(Thresholds.HPRT1cutoff), getFormat("NORMAL"));
            sheet1.addCell(Hcol);
            Hcol = new Label(Ecol+1, Erow+2, "Control 2: Ct <= "+String.valueOf(Thresholds.GAPDHcutoff), getFormat("NORMAL"));
            sheet1.addCell(Hcol);
            
            // HCC prediction
            TableModel HCCmodel = HCCtable.getModel();
            Erow +=7;
            Ecol=2;
            Label HCCcol = new Label(Ecol, Erow, "HCC PREDICTION: ", getFormat("HEADER"));
            sheet1.addCell(HCCcol);
            
            Erow+=2;
            Ecol=2;
            for (int i = 0; i < HCCmodel.getColumnCount(); i++) {
                Label column = new Label(Ecol, Erow, HCCmodel.getColumnName(i), getFormat("HEADER"));
                sheet1.addCell(column);
                Ecol +=1;
            } 
            Erow+=1;
            Ecol=2;
            for (int i = 0; i < HCCmodel.getRowCount(); i++) {
            	int hcr = HCCtable.convertRowIndexToModel(i);
            	for (int j = 0; j < HCCmodel.getColumnCount(); j++) {
            		String outcome = null;
            		Object val = HCCmodel.getValueAt(hcr, j);
            		if(val.toString().equalsIgnoreCase("HK Flag")){
            				outcome = "Housekeeping gene out of range";
            		}
            		else if(val.toString().equalsIgnoreCase("QC Flag")){
            				outcome = "Quality control did not pass";
            		}
            		else{
            				outcome = val.toString();
            		}
            		Label row = new Label(Ecol,Erow, outcome, getFormat("NORMAL"));
            		sheet1.addCell(row);
            		Ecol +=1;
            	}
            	Erow +=1;
            	Ecol=2;
            }
            
            Erow+=2;
            Ecol = 4;
            HCCcol = new Label(Ecol, Erow, "Cutoffs: ", getFormat("HEADER"));
            sheet1.addCell(HCCcol);
            HCCcol = new Label(Ecol, Erow+1, "HCC Risk Score >"+ String.valueOf(Thresholds.HCCcutoff), getFormat("NORMAL"));
            sheet1.addCell(HCCcol);
            
            workbook1.write();
            workbook1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static String getPOS25Threshold(String gene) {
		String res =null;
		if(gene.equals("Test 1")){
			res = String.valueOf(Thresholds.AREG_POS25_MIN) + "-"+
					String.valueOf(Thresholds.AREG_POS25_MAX);
		}
		else if(gene.equals("Test 2")){
			res = String.valueOf(Thresholds.TNFAIP3_POS25_MIN) + "-"+
					String.valueOf(Thresholds.TNFAIP3_POS25_MAX);
		}
		else if(gene.equals("Test 3")){
			res = String.valueOf(Thresholds.GIMAP5_POS25_MIN)+"-"+
					String.valueOf(Thresholds.GIMAP5_POS25_MAX);
		}
		else if(gene.equals("Control 1")){
			res = String.valueOf(Thresholds.HPRT1_POS25_MIN)+"-"+
					String.valueOf(Thresholds.HPRT1_POS25_MAX);
		}
		else if(gene.equals("Control 2")){
			res = String.valueOf(Thresholds.GAPDH_POS25_MIN)+"-"+
					String.valueOf(Thresholds.GAPDH_POS25_MAX);
		}
		return res;
	}
	
	 private static String getPOS90Threshold(String gene){
		 String res = null;
		 if(gene.equals("Test 1")){
			 res = String.valueOf(Thresholds.AREG_POS90_MIN) + "-"+
					 String.valueOf(Thresholds.AREG_POS90_MAX);
		 }
		 else if(gene.equals("Test 2")){
			 res = String.valueOf(Thresholds.TNFAIP3_POS90_MIN) + "-"+
					 String.valueOf(Thresholds.TNFAIP3_POS90_MAX);
		 }
		 else if(gene.equals("Test 3")){
			 res = String.valueOf(Thresholds.GIMAP5_POS90_MIN)+"-"+
					 String.valueOf(Thresholds.GIMAP5_POS90_MAX);
		 }
		 else if(gene.equals("Control 1")){
			 res = String.valueOf(Thresholds.HPRT1_POS90_MIN)+"-"+
					 String.valueOf(Thresholds.HPRT1_POS90_MAX);
		 }
		 else if(gene.equals("Control 2")){
			 res = String.valueOf(Thresholds.GAPDH_POS90_MIN)+"-"+
					 String.valueOf(Thresholds.GAPDH_POS90_MAX);
		 }
		 
		 return res;
	 }

	public static WritableCellFormat getFormat( String stepValue ) throws WriteException { 
		 WritableCellFormat format = new WritableCellFormat();
	      switch ( stepValue ) { 
	        case "HEADER": 
	          format.setAlignment( jxl.format.Alignment.LEFT); 
	          format.setFont(new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
	          break; 
	        case "NORMAL": 
	          format.setAlignment( jxl.format.Alignment.LEFT); 
	          format.setFont(new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
	          break;  
	        default: 
	          break; 
	      } 
	    return format; 
	  }
	 
	private static JPanel OUTPUTpanel (){
		JPanel outputBtnPanel = new JPanel(new BorderLayout());
		JLabel resultsLabel = new JLabel("Results Directory : " + OutFile.getName());
		JButton outputFolderBtn = new JButton("Results:");
		outputFolderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Desktop.isDesktopSupported()) {
					try {
						Desktop.getDesktop().open(OutFile);
					} catch (IOException ioe) {
						ioe.printStackTrace();
					}
				}
			}
		});
		outputFolderBtn.setEnabled(true);
		JPanel outputInfoPanel = new JPanel(new BorderLayout());
		outputInfoPanel.add(resultsLabel, BorderLayout.CENTER);
		outputInfoPanel.add(outputFolderBtn, BorderLayout.EAST);
		outputBtnPanel.add(outputInfoPanel);
		return outputBtnPanel;
	}

	private static TableModel getHKtablemodel(HashMap<String, HashMap<String, Double>> map) {
		Object[] header = {"Sample",Thresholds.DUP_NAMES.get("HPRT1"),Thresholds.DUP_NAMES.get("GAPDH"),"Status"};
		DefaultTableModel model = new DefaultTableModel(header,0);
		for (Map.Entry<String, HashMap<String, Double>> entry : map.entrySet()) {
			String sampleId = entry.getKey();
			Double HPRval =  map.get(sampleId).get("HPRT1");
			Double GAPval = map.get(sampleId).get("GAPDH");
			String Status = "PASS";
			if(HPRval >Thresholds.HPRT1cutoff || GAPval> Thresholds.GAPDHcutoff){
				Status = "FAIL";
			}
			model.addRow(new Object[] {sampleId,HPRval, GAPval,Status });
		}
		return model;
	}

	private static JPanel getSTDPanel(){
		STDdelPos = new HashMap<String, Integer[]>();
		RsqTable = new HashMap<String, Double[]>();
		reg  = new LinearRegression();
		RsqTable = reg.getRsquareHash(STDlist);
		JTableModel model = new JTableModel(RsqTable);
		stdTable = new JTable(model){
			private static final long serialVersionUID = 1L;
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
				JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);  
				if(getValueAt(rowIndex, 2).toString().equalsIgnoreCase("FAIL")) {
					component.setBackground(Color.RED);
				}
				else{
					component.setBackground(Color.GREEN);
				}
				if(columnIndex==0){
					int rendererWidth = String.valueOf("controlGene2").length()+2;
					TableColumn tableColumn = getColumnModel().getColumn(columnIndex);
					tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				}
				return component;
			}
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		stdTable.setPreferredScrollableViewportSize(stdTable.getPreferredSize());
		stdTable.setRowSelectionAllowed(false);
		stdTable.getColumn("Gene").setCellRenderer(new JTableButtonRenderer());
		final JRegression reg2 = new JRegression();
		stdTable.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				int column = stdTable.getColumnModel().getColumnIndexAtX(e.getX());
				int row    = e.getY()/stdTable.getRowHeight(); 
				//if (row < stdTable.getRowCount() && row >= 0 && column < stdTable.getColumnCount() && column >= 0) {
				if (row < stdTable.getRowCount() && row >= 0 && column==0) {
					Object value = stdTable.getValueAt(row, column);
					JButton button = (JButton) value;
					String JGenealias = button.getText();
					JGene = Thresholds.Name2Genes.get(JGenealias);
					//System.out.println("Clicked :" +JGene);
					invokeOnEventThread(new Runnable() {
						@SuppressWarnings("unchecked")
						public void run() {
							JFrame stdFrame = new JFrame("Regression");
							Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
							stdFrame.setSize(600, 600);
							stdFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							stdFrame.setLocationRelativeTo(mainFrame);
							STDlistT = (HashMap<String, HashMap<String, List<Double>>>) STDlist.clone();
							STDdelPosT = (HashMap<String, Integer[]>) STDdelPos.clone();
							XYDataset data = reg2.createData(STDlistT.get(JGene).get("xval"), STDlistT.get(JGene).get("yval"));
							JFreeChart datachart = reg2.createChart(data, JGenealias);
							Cpanel = new ChartPanel(datachart);
							Cpanel.addChartMouseListener(new ChartMouseListener() {
								public void chartMouseMoved(ChartMouseEvent e) {
									int newX = e.getTrigger().getX();
					                int newY = e.getTrigger().getY();
								}
								public void chartMouseClicked(ChartMouseEvent event) {
									ChartEntity entity = event.getEntity();
									String tooltip = entity.getToolTipText();
									if (tooltip != null) {
										String value = entity.toString();
								        Double ind = Double.parseDouble(tooltip.split(Pattern.quote("("))[1].split(",")[0].trim()); 
										if (STDdelPosT.containsKey(JGene)){
											Integer[] delpos  = Arrays.copyOf(STDdelPosT.get(JGene), STDdelPosT.get(JGene).length+1);
											delpos[STDdelPosT.get(JGene).length] = ind.intValue();
											STDdelPosT.put(JGene, delpos);
										}
										else{
											Integer[] delpos = {ind.intValue()};
											STDdelPosT.put(JGene, delpos);
										}
										HashMap<String, List<Double>> newData = reg2.deSelectData(ind, STDlistT.get(JGene).get("xval"), STDlistT.get(JGene).get("yval"));
										if(newData.get("xval").size() >= 3){
											Cpanel.setChart(reg2.rePlot(STDlistT.get(JGene).get("xval"), STDlistT.get(JGene).get("yval"), JGenealias));
											STDlistT.put(JGene,newData);
										}
										else{
											 JOptionPane.showMessageDialog(stdFrame, "Cannot remove more than 2 points", "Data",
									                 JOptionPane.WARNING_MESSAGE);	
										}
										//System.out.println("Mouse clicked: Gene " +JGene +"\t"+ entity.toString());   
									}   	
								}
							});
							JPanel buttPanel = new JPanel(new FlowLayout());
							JButton reset = new JButton("Reset");
							reset.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									STDlistT = (HashMap<String, HashMap<String, List<Double>>>) STDlist.clone();
									STDdelPosT = (HashMap<String, Integer[]>) STDdelPos.clone();
									Cpanel.setChart(datachart);
								}
							});
							JButton save = new JButton("Save");
							save.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									STDlist = (HashMap<String, HashMap<String, List<Double>>>) STDlistT.clone();
									STDdelPos = (HashMap<String, Integer[]>) STDdelPosT.clone();
									RsqTable = reg.getRsquareHash(STDlist);
									stdTable.setModel(new JTableModel(RsqTable));
									stdTable.getColumn("Gene").setCellRenderer(new JTableButtonRenderer());
									TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(stdTable.getModel());
									stdTable.setRowSorter(sorter);
									List<RowSorter.SortKey> sortKeys = new ArrayList<SortKey>();
									int columnIndexToSort = 0;
									sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
									sorter.setSortKeys(sortKeys);
									sorter.sort();
									stdFrame.dispose();
								}
							});
							buttPanel.add(reset);
							buttPanel.add(save);

							JPanel PlotPanel= new JPanel();
							PlotPanel.setLayout(new BoxLayout(PlotPanel, BoxLayout.Y_AXIS));
							PlotPanel.add(Cpanel);
							PlotPanel.add(buttPanel);
							
							stdFrame.add(PlotPanel);
							stdFrame.setVisible(true);
							stdFrame.pack();
						}
					});
					
				}
			}
		});
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(stdTable.getModel());
		//sorter.setSortsOnUpdates(true);
		stdTable.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<SortKey>();
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		JScrollPane STDscroll = new JScrollPane(stdTable);
		STDscroll.setMaximumSize(new Dimension(15, 15));
		JLabel STDlabel = new JLabel("STANDARDS");
		STDlabel.setFont(new Font("serif", Font.BOLD, 14));

		JPanel STDtabPanel = new JPanel(new BorderLayout());
		STDtabPanel.add(STDlabel, BorderLayout.NORTH);
		STDtabPanel.add(STDscroll, BorderLayout.SOUTH);
		return STDtabPanel;
	
	}
	
	private static boolean checkQCstatus(){
		boolean check = true;
		TableModel nModel = NTCtable.getModel();
		for(int i =0; i< nModel.getRowCount(); i++){
			if(nModel.getValueAt(i, 2).toString().equalsIgnoreCase("FAIL")){
				check = false;
			}
		}
		TableModel pModel = POStable.getModel();
		for(int i=0; i< pModel.getRowCount(); i++){
			if(pModel.getValueAt(i, 2).toString().equalsIgnoreCase("FAIL")||
					pModel.getValueAt(i, 4).toString().equalsIgnoreCase("FAIL")){
				check = false;
			}
					
		}
		TableModel sModel = stdTable.getModel();
		for (int i = 0; i < sModel.getRowCount(); i++) {
				if(sModel.getValueAt(i, 2).toString().equalsIgnoreCase("FAIL")){
					check=false;
				}
        }
		return check;
	}

	@SuppressWarnings("serial")
	private static JPanel getPOSpanel() {
		JPanel POS = new JPanel(new BorderLayout());
		TableModel posdata = POSTableModel(POSlist);
		POStable = new JTable(posdata){
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
				JComponent component = (JComponent) super.prepareRenderer(renderer, rowIndex, columnIndex);  
				if(getValueAt(rowIndex, 2).toString().equalsIgnoreCase("FAIL") && columnIndex == 2) {
					component.setBackground(Color.RED);
				} 
				else if(getValueAt(rowIndex, 4).toString().equalsIgnoreCase("FAIL") && columnIndex == 4) {
					component.setBackground(Color.RED);
				} 
				else if(getValueAt(rowIndex, 2).toString().equalsIgnoreCase("PASS") && columnIndex == 2){
					component.setBackground(Color.GREEN);
				}
				else if(getValueAt(rowIndex, 4).toString().equalsIgnoreCase("PASS") && columnIndex == 4){
					component.setBackground(Color.GREEN);
				}
				else{
					component.setBackground(Color.WHITE);
				}
				return component;
			}
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(POStable.getModel());
		POStable.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<SortKey>();
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		
		POStable.setBackground(Color.gray);;
		POStable.setPreferredScrollableViewportSize(POStable.getPreferredSize());
		POStable.setRowSelectionAllowed(false);
		JScrollPane POSscroll = new JScrollPane(POStable);
		POSscroll.setMaximumSize(new Dimension(5, 5));
		JLabel POSlabel = new JLabel("Positive Control (PC)");
		POSlabel.setFont(new Font("serif", Font.BOLD, 14));
		POS.add(POSlabel, BorderLayout.NORTH);
		POS.add(POSscroll, BorderLayout.SOUTH);
		return POS;
	}

	public static TableModel POSTableModel(Map<String,Object[]> map) {
		Object[] header = {"Gene","PC1(Ct)","PC1(status)","PC2(Ct)","PC2(status)"};
		DefaultTableModel model = new DefaultTableModel(header,0);
		for (Map.Entry<String,Object[]> entry : map.entrySet()) {
			model.addRow(new Object[] { Thresholds.DUP_NAMES.get(entry.getKey()), String.format("%.02f",entry.getValue()[0]),entry.getValue()[1], String.format("%.02f",entry.getValue()[2]), entry.getValue()[3] });
		}
		return model;
	}
	
	public static TableModel HCCTableModel(Map<String, Double> hcc){
		//Object[] header = {"Sample ID", "Probability Score", "Outcome","Remarks"};
		Object[] header = {"Sample ID", "HCC Risk Score","Remarks"};
		DecimalFormat df = new DecimalFormat("#.###");
		DefaultTableModel model = new DefaultTableModel(header, 0);
		for(Map.Entry<String, Double> entry: hcc.entrySet()){
			String remark = null;
			boolean QCstatus  = checkQCstatus();
			if(!QCstatus){
				remark = "QC Flag";
			}
			else{
				remark = "HK Flag";
			}
			if(HouseKeepGenes.get(entry.getKey()).get("HPRT1")<=Thresholds.HPRT1cutoff 
					&& HouseKeepGenes.get(entry.getKey()).get("GAPDH") <= Thresholds.GAPDHcutoff && QCstatus){
				remark = "";
			}
			Object[] rowData = {entry.getKey(), String.format("%.03f", entry.getValue().doubleValue()), remark};
			model.addRow(rowData);
		}
		return model;
	}
	
	public void startedRunning() {
		count = 0;
		if (pBar != null) {
			pBar.setString("Running...");
			pBar.setStringPainted(true);
			pBar.setVisible(true);
		}

		if (mainFrame != null) {
			mainFrame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		}
	}
	
    private static  void invokeOnEventThread(Runnable runnable) {
        if (SwingUtilities.isEventDispatchThread()) {
            runnable.run();
        } else {
            SwingUtilities.invokeLater(runnable);
        }
    }

}

class JTableButtonRenderer implements TableCellRenderer {		
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		JButton button = (JButton)value;
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(UIManager.getColor("Button.background"));
		}
		return button;	
	}
}

