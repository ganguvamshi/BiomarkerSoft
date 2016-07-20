package biomarker;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import pagelayout.*;
import pagelayout.util.*;
import static pagelayout.EasyCell.*;

@SuppressWarnings("serial")
public class Constants extends javax.swing.JDialog 
{
  protected JFrame main;
  protected JLabel min;
  protected JLabel max;
  protected JTextField ntcmin;
  protected int ntcminT;
  protected JTextField ntcmax;
  protected int ntcmaxT;
  
  protected NamedSeparator sep10;
  protected JLabel eq_hkgenes;
  protected JLabel eq_constant;
  protected JLabel eq_areg;
  protected JLabel eq_tnfaip3;
  protected JLabel eq_gimap5;
  protected JTextField eq_hprt1_cons;
  protected Double eq_hprt1_consT;
  protected JTextField eq_hprt1_areg;
  protected Double eq_hprt1_aregT;
  protected JTextField eq_hprt1_tnfaip3;
  protected Double eq_hprt1_tnfaip3T;
  protected JTextField eq_hprt1_gimap5;
  protected Double eq_hprt1_gimap5T;
  protected JLabel eq_hprt1;
  protected JTextField eq_gapdh_cons;
  protected Double eq_gapdh_consT;
  protected JTextField eq_gapdh_areg;
  protected Double eq_gapdh_aregT;
  protected JTextField eq_gapdh_tnfaip3;
  protected Double eq_gapdh_tnfaip3T;
  protected JTextField eq_gapdh_gimap5;
  protected Double eq_gapdh_gimap5T;
  protected JLabel eq_gapdh;
  protected JTextField eq_both_cons;
  protected Double eq_both_consT;
  protected JTextField eq_both_areg;
  protected Double eq_both_aregT;
  protected JTextField eq_both_tnfaip3;
  protected Double eq_both_tnfaip3T;
  protected JTextField eq_both_gimap5;
  protected Double eq_both_gimap5T;
  protected JLabel eq_both;
  
  
  protected NamedSeparator sep7;
  protected JLabel genelist;
  protected JLabel p25min;
  protected JLabel p25max;
  protected JLabel p90min;
  protected JLabel p90max;
  protected JLabel areg;
  protected JTextField areg_p25min;
  protected int areg_p25minT;
  protected JTextField areg_p25max;
  protected int areg_p25maxT;
  protected JTextField areg_p90min;
  protected int areg_p90minT;
  protected JTextField areg_p90max;
  protected int areg_p90maxT;
  protected JLabel tnfaip3;
  protected JTextField tnfaip3_p25min;
  protected int tnfaip3_p25minT;
  protected JTextField tnfaip3_p25max;
  protected int tnfaip3_p25maxT;
  protected JTextField tnfaip3_p90min;
  protected int tnfaip3_p90minT;
  protected JTextField tnfaip3_p90max;
  protected int tnfaip3_p90maxT;
  protected JLabel gimap5;
  protected JTextField gimap5_p25min;
  protected int gimap5_p25minT;
  protected JTextField gimap5_p25max;
  protected int gimap5_p25maxT;
  protected JTextField gimap5_p90min;
  protected int gimap5_p90minT;
  protected JTextField gimap5_p90max;
  protected int gimap5_p90maxT;
  protected JLabel hprt1;
  protected JTextField hprt1_p25min;
  protected int hprt1_p25minT;
  protected JTextField hprt1_p25max;
  protected int hprt1_p25maxT;
  protected JTextField hprt1_p90min;
  protected int hprt1_p90minT;
  protected JTextField hprt1_p90max;
  protected int hprt1_p90maxT;
  protected JLabel gadph;
  protected JTextField gapdh_p25min;
  protected int gapdh_p25minT;
  protected JTextField gapdh_p25max;
  protected int gapdh_p25maxT; 
  protected JTextField gapdh_p90min;
  protected int gapdh_p90minT;
  protected JTextField gapdh_p90max;
  protected int gapdh_p90maxT;
  
  protected NamedSeparator separator8;
  protected JCheckBox hprt1_box;
  protected boolean hprt1_boxT;
  protected JCheckBox gAPDH_box;
  protected boolean gAPDH_boxT;
  
  protected NamedSeparator separator9;
  protected JLabel hprt1_hk;
  protected JLabel gapdh_hk;
  protected JTextField hprt1_hk_cutoff;
  protected Double hprt1_hk_cutoffT;
  protected JTextField gapdh_hk_cutoff;
  protected Double gapdh_hk_cutoffT;
  
  protected NamedSeparator separator10;
  protected JLabel rSq_label;
  protected JTextField rsq;
  protected Double rsqT;
  protected JLabel hCC_label;
  protected JButton save;
  protected JButton cancel;
  protected JTextField hCC_cutoff;
  protected Double hCC_cutoffT;
  
  protected JPanel normgrid$Panel;
  protected JPanel ntcgrid$Panel;
  protected JPanel others_grid$Panel;
  protected JPanel hkgrid$Panel;
  protected JPanel posgrid$Panel;
  protected JPanel equation$Panel;
  
  protected CellGrid ntcgrid$Cell;
  protected CellGrid posgrid$Cell;
  protected CellGrid normgrid$Cell;
  protected CellGrid hkgrid$Cell;
  protected CellGrid others_grid$Cell;
  protected CellGrid equationgrid$Cell;
  
  protected PanelCell ntcgrid;
  protected PanelCell posgrid;
  protected PanelCell normgrid;
  protected PanelCell hkgrid;
  protected PanelCell others_grid;
  protected PanelCell equationgrid;
  
  protected Column root;
  protected CellGrid actiongrid;
  private boolean isChanged = false;
  private boolean isSaved = false;
  private boolean isCancel = false;
  PageLayout layout;
  
  protected void createComponents()
  {

	  min = new javax.swing.JLabel();
	  min.setText("Min");
	  min.setVisible(false);

	  max = new javax.swing.JLabel();
	  max.setText("CT");

	  ntcmin = new javax.swing.JTextField(5);
	  ntcmin.setText(String.valueOf(ntcminT));
	  setDimensions(ntcmin, 13, 119, 23, 32767, 23, 187, 23);
	  ntcmin.setVisible(false);

	  ntcmax = new javax.swing.JTextField(5);
	  ntcmax.setText(String.valueOf(ntcmaxT));
	  setDimensions(ntcmax, 13, 119, 23, 32767, 23, 186, 23);

	  sep7 = new pagelayout.util.NamedSeparator();
	  sep7.setText("");
	  setDimensions(sep7 ,42, 17, 32767, 4, 399, 18);

	  genelist = new javax.swing.JLabel();
	  genelist.setText("");

	  p25min = new javax.swing.JLabel();
	  p25min.setText("POS25 Min");

	  p25max = new javax.swing.JLabel();
	  p25max.setText("POS25 Max");

	  p90min = new javax.swing.JLabel();
	  p90min.setText("POS90 Min");

	  p90max = new javax.swing.JLabel();
	  p90max.setText("POS90 Max");

	  areg = new javax.swing.JLabel();
	  areg.setText("AREG");

	  areg_p25min = new javax.swing.JTextField(5);
	  areg_p25min.setText(String.valueOf(areg_p25minT));
	  setDimensions(areg_p25min, 9, 70, 23, 32767, 23, 82, 23);

	  areg_p25max = new javax.swing.JTextField(5);
	  areg_p25max.setText(String.valueOf(areg_p25maxT));
	  setDimensions(areg_p25max, 9, 70, 23, 32767, 23, 82, 23);

	  areg_p90min = new javax.swing.JTextField(5);
	  areg_p90min.setText(String.valueOf(areg_p90minT));
	  setDimensions(areg_p90min, 0, 6, 23, 32767, 23, 82, 23);

	  areg_p90max = new javax.swing.JTextField(5);
	  areg_p90max.setText(String.valueOf(areg_p90maxT));
	  setDimensions(areg_p90max, 0, 6, 23, 32767, 23, 82, 23);

	  tnfaip3 = new javax.swing.JLabel();
	  tnfaip3.setText("TNFAIP3");

	  tnfaip3_p25min = new javax.swing.JTextField(5);
	  tnfaip3_p25min.setText(String.valueOf(tnfaip3_p25minT));
	  setDimensions(tnfaip3_p25min, 0, 5, 23, 32767, 23, 82, 23);

	  tnfaip3_p25max = new javax.swing.JTextField(5);
	  tnfaip3_p25max.setText(String.valueOf(tnfaip3_p25maxT));
	  setDimensions(tnfaip3_p25max, 0, 5, 23, 32767, 23, 67, 23);

	  tnfaip3_p90min = new javax.swing.JTextField(5);
	  tnfaip3_p90min.setText(String.valueOf(tnfaip3_p90minT));
	  setDimensions(tnfaip3_p90min, 0, 5, 23, 32767, 23, 82, 23);

	  tnfaip3_p90max = new javax.swing.JTextField(5);
	  tnfaip3_p90max.setText(String.valueOf(tnfaip3_p90maxT));
	  setDimensions(tnfaip3_p90max, 0, 5, 23, 32767, 23, 82, 23);

	  gimap5 = new javax.swing.JLabel();
	  gimap5.setText("GIMAP5");

	  gimap5_p25min = new javax.swing.JTextField(5);
	  gimap5_p25min.setText(String.valueOf(gimap5_p25minT));
	  setDimensions(gimap5_p25min, 9, 70, 23, 32767, 23, 82, 23);

	  gimap5_p25max = new javax.swing.JTextField(5);
	  gimap5_p25max.setText(String.valueOf(gimap5_p25maxT));
	  setDimensions(gimap5_p25max, 0, 5, 23, 32767, 23, 67, 23);

	  gimap5_p90min = new javax.swing.JTextField(5);
	  gimap5_p90min.setText(String.valueOf(gimap5_p90minT));
	  setDimensions(gimap5_p90min, 0, 5, 23, 32767, 23, 82, 23);

	  gimap5_p90max = new javax.swing.JTextField(5);
	  gimap5_p90max.setText(String.valueOf(gimap5_p90maxT));
	  setDimensions(gimap5_p90max, 9, 69, 23, 32767, 23, 82, 23);

	  hprt1 = new javax.swing.JLabel();
	  hprt1.setText("HPRT1");

	  hprt1_p25min = new javax.swing.JTextField(5);
	  hprt1_p25min.setText(String.valueOf(hprt1_p25minT));
	  setDimensions(hprt1_p25min, 0, 5, 23, 32767, 23, 82, 23);

	  hprt1_p25max = new javax.swing.JTextField(5);
	  hprt1_p25max.setText(String.valueOf(hprt1_p25maxT));
	  setDimensions(hprt1_p25max, 0, 5, 23, 32767, 23, 67, 23);

	  hprt1_p90min = new javax.swing.JTextField(5);
	  hprt1_p90min.setText(String.valueOf(hprt1_p90minT));
	  setDimensions(hprt1_p90min, 9, 68, 23, 32767, 23, 82, 23);

	  hprt1_p90max = new javax.swing.JTextField(5);
	  hprt1_p90max.setText(String.valueOf(hprt1_p90maxT));
	  setDimensions(hprt1_p90max, 0, 5, 23, 32767, 23, 82, 23);

	  gadph = new javax.swing.JLabel();
	  gadph.setText("GAPDH");

	  gapdh_p25min = new javax.swing.JTextField(5);
	  gapdh_p25min.setText(String.valueOf(gapdh_p25minT));
	  setDimensions(gapdh_p25min, 0, 5, 23, 32767, 23, 82, 23);

	  gapdh_p25max = new javax.swing.JTextField(5);
	  gapdh_p25max.setText(String.valueOf( gapdh_p25maxT));
	  setDimensions(gapdh_p25max, 0, 5, 23, 32767, 23, 67, 23);

	  gapdh_p90min = new javax.swing.JTextField(5);
	  gapdh_p90min.setText(String.valueOf(gapdh_p90minT));
	  setDimensions(gapdh_p90min, 0, 5, 23, 32767, 23, 82, 23);

	  gapdh_p90max = new javax.swing.JTextField(5);
	  gapdh_p90max.setText(String.valueOf( gapdh_p90maxT));
	  setDimensions(gapdh_p90max, 0, 5, 23, 32767, 23, 82, 23);

	  separator8 = new pagelayout.util.NamedSeparator();
	  separator8.setText("");
	  setDimensions(separator8 ,42, 17, 32767, 4, 399, 18);

	  hprt1_box = new javax.swing.JCheckBox();
	  hprt1_box.setText("HPRT1");
	  hprt1_box.setSelected(hprt1_boxT);

	  gAPDH_box = new javax.swing.JCheckBox();
	  gAPDH_box.setText("GAPDH");
	  gAPDH_box.setSelected(gAPDH_boxT);

	  separator9 = new pagelayout.util.NamedSeparator();
	  separator9.setText("");
	  setDimensions(separator9 ,42, 14, 32767, 4, 399, 15);

	  hprt1_hk = new javax.swing.JLabel();
	  hprt1_hk.setText("HPRT1");

	  gapdh_hk = new javax.swing.JLabel();
	  gapdh_hk.setText("GAPDH");

	  hprt1_hk_cutoff = new javax.swing.JTextField();
	  hprt1_hk_cutoff.setText(String.valueOf(hprt1_hk_cutoffT));
	  setDimensions(hprt1_hk_cutoff, 20, 146, 23, 32767, 23, 191, 23);

	  gapdh_hk_cutoff = new javax.swing.JTextField();
	  gapdh_hk_cutoff.setText(String.valueOf(gapdh_hk_cutoffT));
	  setDimensions(gapdh_hk_cutoff, 19, 146, 23, 32767, 23, 182, 23);

	  separator10 = new pagelayout.util.NamedSeparator();
	  separator10.setText("");
	  setDimensions(separator10 ,48, 14, 32767, 4, 399, 15);

	  rSq_label = new javax.swing.JLabel();
	  rSq_label.setText("Rsquare");

	  rsq = new javax.swing.JTextField();
	  rsq.setText(String.valueOf(rsqT));
	  setDimensions(rsq, 38, 327, 23, 32767, 23, 327, 23);

	  hCC_label = new javax.swing.JLabel();
	  hCC_label.setText("HCC cutoff");

	  hCC_cutoff = new javax.swing.JTextField();
	  hCC_cutoff.setText(String.valueOf(hCC_cutoffT));
	  setDimensions(hCC_cutoff, 0, 6, 23, 32767, 23, 327, 23);
	  
	  sep10 = new pagelayout.util.NamedSeparator();
	  sep10.setText("");
	  setDimensions(sep10 ,48, 14, 32767, 4, 399, 15);

	  eq_hkgenes = new javax.swing.JLabel();
	  eq_hkgenes.setText("");
	  
	  eq_constant = new javax.swing.JLabel();
	  eq_constant.setText("Constant");
	  
	  eq_areg = new javax.swing.JLabel();
	  eq_areg.setText("AREG");
	  
	  eq_tnfaip3 = new javax.swing.JLabel();
	  eq_tnfaip3.setText("TNFAIP3");
	  
	  eq_gimap5 = new javax.swing.JLabel();
	  eq_gimap5.setText("GIMAP5");
	  
	  eq_hprt1 = new javax.swing.JLabel();
	  eq_hprt1.setText("HPRT1");	
	  
	  eq_hprt1_cons = new javax.swing.JTextField(5);
	  eq_hprt1_cons.setText(String.valueOf(eq_hprt1_consT));
	  setDimensions(eq_hprt1_cons, 9, 70, 23, 32767, 23, 82, 23);	
	  
	  eq_hprt1_areg = new javax.swing.JTextField(5);
	  eq_hprt1_areg.setText(String.valueOf(eq_hprt1_aregT));
	  setDimensions(eq_hprt1_areg, 9, 70, 23, 32767, 23, 82, 23);
	  
	  eq_hprt1_tnfaip3 = new javax.swing.JTextField(5);
	  eq_hprt1_tnfaip3.setText(String.valueOf(eq_hprt1_tnfaip3T));
	  setDimensions(eq_hprt1_tnfaip3, 0, 6, 23, 32767, 23, 82, 23);
	  
	  eq_hprt1_gimap5 = new javax.swing.JTextField(5);
	  eq_hprt1_gimap5.setText(String.valueOf(eq_hprt1_gimap5T));
	  setDimensions(eq_hprt1_gimap5, 0, 6, 23, 32767, 23, 82, 23);
	  
	  eq_gapdh = new javax.swing.JLabel();
	  eq_gapdh.setText("GAPDH");
	  eq_gapdh_cons = new javax.swing.JTextField(5);
	  eq_gapdh_cons.setText(String.valueOf(eq_gapdh_consT));
	  setDimensions(eq_gapdh_cons, 0, 5, 23, 32767, 23, 82, 23);
	  
	  eq_gapdh_areg = new javax.swing.JTextField(5);
	  eq_gapdh_areg.setText(String.valueOf(eq_gapdh_aregT));
	  setDimensions(eq_gapdh_areg, 0, 5, 23, 32767, 23, 67, 23);	
	  
	  eq_gapdh_tnfaip3 = new javax.swing.JTextField(5);
	  eq_gapdh_tnfaip3.setText(String.valueOf(eq_gapdh_tnfaip3T));
	  setDimensions(eq_gapdh_tnfaip3, 0, 5, 23, 32767, 23, 82, 23);
	  
	  eq_gapdh_gimap5 = new javax.swing.JTextField(5);
	  eq_gapdh_gimap5.setText(String.valueOf(eq_gapdh_gimap5T));
	  setDimensions(eq_gapdh_gimap5, 0, 5, 23, 32767, 23, 82, 23);	
	  
	  eq_both = new javax.swing.JLabel();
	  eq_both.setText("HPRT1+GAPDH");	
	  eq_both_cons = new javax.swing.JTextField(5);
	  eq_both_cons.setText(String.valueOf(eq_both_consT));
	  setDimensions(eq_both_cons, 9, 70, 23, 32767, 23, 82, 23);	
	  
	  eq_both_areg = new javax.swing.JTextField(5);
	  eq_both_areg.setText(String.valueOf(eq_both_aregT));
	  setDimensions(eq_both_areg, 0, 5, 23, 32767, 23, 67, 23);	
	  
	  eq_both_tnfaip3 = new javax.swing.JTextField(5);
	  eq_both_tnfaip3.setText(String.valueOf(eq_both_tnfaip3T));
	  setDimensions(eq_both_tnfaip3, 0, 5, 23, 32767, 23, 82, 23);
	  
	  eq_both_gimap5 = new javax.swing.JTextField(5);
	  eq_both_gimap5.setText(String.valueOf(eq_gapdh_gimap5T));
	  setDimensions(eq_both_gimap5, 9, 69, 23, 32767, 23, 82, 23);

	  save = new javax.swing.JButton();
	  save.setText("save");
	  save.setForeground(Color.RED);;

	  cancel = new javax.swing.JButton();
	  cancel.setText("cancel");

	  
	  normgrid$Panel = new JPanel();
	  setDimensions(normgrid$Panel ,140, 51, 32767, 32767, 148, 63);
	  normgrid$Panel.setBorder(BorderFactory.createTitledBorder(
			  BorderFactory.createLineBorder(Color.black,1),"Normalization"));
	  ntcgrid$Panel = new JPanel();
	  setDimensions(ntcgrid$Panel ,262, 71, 32767, 32767, 405, 79);
	  ntcgrid$Panel.setBorder(BorderFactory.createTitledBorder(
			  BorderFactory.createLineBorder(Color.black,1),"NTC"));
	  others_grid$Panel = new JPanel();
	  setDimensions(others_grid$Panel ,404, 80, 32767, 32767, 405, 93);
	  others_grid$Panel.setBorder(BorderFactory.createTitledBorder(
			  BorderFactory.createLineBorder(Color.black,1),"Others"));
	  hkgrid$Panel = new JPanel();
	  setDimensions(hkgrid$Panel ,316, 71, 32767, 32767, 405, 86);
	  hkgrid$Panel.setBorder(BorderFactory.createTitledBorder(
			  BorderFactory.createLineBorder(Color.black,1),"House Keeping"));
	  posgrid$Panel = new JPanel();
	  setDimensions(posgrid$Panel ,346, 187, 32767, 32767, 405, 195);
	  posgrid$Panel.setBorder(BorderFactory.createTitledBorder(
			  BorderFactory.createLineBorder(Color.black,1),"POS"));
	  equation$Panel = new JPanel();
	  setDimensions(equation$Panel, 346, 187, 32767, 32767, 405, 195);
	  equation$Panel.setBorder(BorderFactory.createTitledBorder(
			  BorderFactory.createLineBorder(Color.black,1),"Equation"));
	  
	  decorateComponents();

  }
/* 
   Normally this  method does not need 
   to be modified.
*/
  protected void createCells()
  {
     ntcgrid$Cell = grid(max, min, eol(), 
                        ntcmax, ntcmin);
     

     posgrid$Cell = grid(genelist, p25min, p25max, p90min, p90max, eol(), 
                         areg, areg_p25min, areg_p25max, areg_p90min, areg_p90max, eol(), 
                         tnfaip3, tnfaip3_p25min, tnfaip3_p25max, tnfaip3_p90min, tnfaip3_p90max, eol(), 
                         gimap5, gimap5_p25min, gimap5_p25max, gimap5_p90min, gimap5_p90max, eol(), 
                         hprt1, hprt1_p25min, hprt1_p25max, hprt1_p90min, hprt1_p90max, eol(), 
                         gadph, gapdh_p25min, gapdh_p25max, gapdh_p90min, gapdh_p90max);
     posgrid$Cell.setAlignment(0, 1, center, none);
     posgrid$Cell.setAlignment(0, 2, center, none);
     posgrid$Cell.setAlignment(0, 3, center, none);
     posgrid$Cell.setAlignment(0, 4, center, none);
     posgrid$Cell.setAlignment(1, 0, center, none);
     posgrid$Cell.setAlignment(2, 0, center, none);
     posgrid$Cell.setAlignment(3, 0, center, none);
     posgrid$Cell.setAlignment(4, 0, center, none);
     posgrid$Cell.setAlignment(5, 0, center, none);
     
     
     equationgrid$Cell = grid(eq_hkgenes,eq_constant, eq_areg, eq_tnfaip3, eq_gimap5, eol(),
    		 				  eq_hprt1, eq_hprt1_cons, eq_hprt1_areg, eq_hprt1_tnfaip3, eq_hprt1_gimap5, eol(),
    		 				  eq_gapdh, eq_gapdh_cons, eq_gapdh_areg, eq_gapdh_tnfaip3, eq_gapdh_gimap5, eol(),
    		 				  eq_both, eq_both_cons, eq_both_areg, eq_both_tnfaip3, eq_both_gimap5);
     

     normgrid$Cell = grid(hprt1_box, gAPDH_box);

     hkgrid$Cell = grid(hprt1_hk, gapdh_hk, eol(), 
                        hprt1_hk_cutoff, gapdh_hk_cutoff);

     others_grid$Cell = grid(rSq_label, rsq, eol(), 
                             hCC_label, hCC_cutoff);

     ntcgrid = new PanelCell(ntcgrid$Panel,ntcgrid$Cell);

     posgrid = new PanelCell(posgrid$Panel,posgrid$Cell);

     normgrid = new PanelCell(normgrid$Panel,normgrid$Cell);

     hkgrid = new PanelCell(hkgrid$Panel,hkgrid$Cell);

     others_grid = new PanelCell(others_grid$Panel,others_grid$Cell);
     
     actiongrid = grid(save, cancel);
     
     equationgrid = new PanelCell(equation$Panel, equationgrid$Cell);
     
     root = column(ntcgrid,  sep7,  posgrid,  separator8,  normgrid,  separator9,  hkgrid,  separator10,  others_grid, equationgrid, actiongrid);
     
     //root = column(ntcgrid, grid(posgrid, hkgrid, eol(),
    //		 			  normgrid, others_grid), equationgrid, actiongrid);

     setConstraints();

  }


/* 
   The following 'dummy action' methods should
   to be modified for the application.
*/
/* 
   A dummy action for gapdh_p25min.
*/
  public void gapdh_p25minTextEntered(ActionEvent e)
  {
	  gapdh_p25minT = Integer.parseInt(gapdh_p25min.getText());
  }


/* 
   A dummy action for areg_p25min.
*/
  public void areg_p25minTextEntered(ActionEvent e)
  {
	  areg_p25minT = Integer.parseInt(areg_p25min.getText());
  }

/* 
   A dummy action for tnfaip3_p90min.
*/
  public void tnfaip3_p90minTextEntered(ActionEvent e)
  {
	  tnfaip3_p90minT = Integer.parseInt(tnfaip3_p90min.getText());
  }

/* 
   A dummy action for gimap5_p25max.
*/
  public void gimap5_p25maxTextEntered(ActionEvent e)
  {
	  gimap5_p25maxT = Integer.parseInt(gimap5_p25max.getText());
  }

/* 
   A dummy action for gapdh_p25max.
*/
  public void gapdh_p25maxTextEntered(ActionEvent e)
  {
	  gapdh_p25maxT = Integer.parseInt(gapdh_p25max.getText());
  }

/* 
   A dummy action for gapdh_p90max.
*/
  public void gapdh_p90maxTextEntered(ActionEvent e)
  {
	  gapdh_p90maxT = Integer.parseInt(gapdh_p90max.getText());
  }

/* 
   A dummy action for areg_p90min.
*/
  public void areg_p90minTextEntered(ActionEvent e)
  {
	  areg_p90minT = Integer.parseInt(areg_p90min.getText());
  }

/* 
   A dummy action for areg_p90max.
*/
  public void areg_p90maxTextEntered(ActionEvent e)
  {
	  areg_p90maxT = Integer.parseInt(areg_p90max.getText());
  }

/* 
   A dummy action for gimap5_p90min.
*/
  public void gimap5_p90minTextEntered(ActionEvent e)
  {
	  gimap5_p90minT = Integer.parseInt(gimap5_p90min.getText());
  }

/* 
   A dummy action for gimap5_p25min.
*/
  public void gimap5_p25minTextEntered(ActionEvent e)
  {
	  gimap5_p25minT = Integer.parseInt(gimap5_p25min.getText());
  }

/* 
   A dummy action for hprt1_hk_cutoff.
*/
  public void hprt1_hk_cutoffTextEntered(ActionEvent e)
  {
	  hprt1_hk_cutoffT = Double.parseDouble(hprt1_hk_cutoff.getText());
  }

/* 
   A dummy action for ntcmax.
*/
  public void ntcmaxTextEntered(ActionEvent e)
  {
	  ntcmaxT = Integer.parseInt(ntcmax.getText());
  }

/* 
   A dummy action for tnfaip3_p90max.
*/
  public void tnfaip3_p90maxTextEntered(ActionEvent e)
  {
	  tnfaip3_p90maxT = Integer.parseInt(tnfaip3_p90max.getText());
  }

/* 
   A dummy action for gimap5_p90max.
*/
  public void gimap5_p90maxTextEntered(ActionEvent e)
  {
	  gimap5_p90maxT = Integer.parseInt(gimap5_p90max.getText());
  }

/* 
   A dummy action for ntcmin.
*/
  public void ntcminTextEntered(ActionEvent e)
  {
	  ntcminT = Integer.parseInt(ntcmin.getText());
  }

/* 
   A dummy action for rsq.
*/
  public void rsqTextEntered(ActionEvent e)
  {
	  rsqT = Double.parseDouble(rsq.getText());
  }

/* 
   A dummy action for hprt1_p90min.
*/
  public void hprt1_p90minTextEntered(ActionEvent e)
  {
	  hprt1_p90minT = Integer.parseInt(hprt1_p90min.getText());
  }

/* 
   A dummy action for hCC_cutoff.
*/
  public void hCC_cutoffTextEntered(ActionEvent e)
  {
	  hCC_cutoffT = Double.parseDouble(hCC_cutoff.getText());
  }

/* 
   A dummy action for tnfaip3_p25max.
*/
  public void tnfaip3_p25maxTextEntered(ActionEvent e)
  {
	  tnfaip3_p25maxT = Integer.parseInt(tnfaip3_p25max.getText());
  }

/* 
   A dummy action for hprt1_p25min.
*/
  public void hprt1_p25minTextEntered(ActionEvent e)
  {
	  hprt1_p25minT = Integer.parseInt(hprt1_p25min.getText());
  }

/* 
   A dummy action for hprt1_box.
*/
  public void hprt1_boxClicked(ActionEvent e)
  {
     if(hprt1_box.isSelected()){
    	 hprt1_boxT = true;
     }
     else{
    	 hprt1_boxT = false;
     }
  }

/* 
   A dummy action for areg_p25max.
*/
  public void areg_p25maxTextEntered(ActionEvent e)
  {
	  areg_p25maxT = Integer.parseInt(areg_p25max.getText());
  }

/* 
   A dummy action for gAPDH_box.
*/
  public void gAPDH_boxClicked(ActionEvent e)
  {
	  if(gAPDH_box.isSelected()){
		  gAPDH_boxT = true;
		  
	     }
	     else{
	    	 gAPDH_boxT = false;
	     }
  }

/* 
   A dummy action for tnfaip3_p25min.
*/
  public void tnfaip3_p25minTextEntered(ActionEvent e)
  {
	  tnfaip3_p25minT = Integer.parseInt(tnfaip3_p25min.getText());
  }

/* 
   A dummy action for hprt1_p90max.
*/
  public void hprt1_p90maxTextEntered(ActionEvent e)
  {
	  hprt1_p90maxT = Integer.parseInt(hprt1_p90max.getText());
  }

/* 
   A dummy action for gapdh_hk_cutoff.
*/
  public void gapdh_hk_cutoffTextEntered(ActionEvent e)
  {
	  gapdh_hk_cutoffT = Double.parseDouble(gapdh_hk_cutoff.getText());
  }

/* 
   A dummy action for hprt1_p25max.
*/
  public void hprt1_p25maxTextEntered(ActionEvent e)
  {
	  hprt1_p25maxT = Integer.parseInt(hprt1_p25max.getText());
  }

/* 
   A dummy action for gapdh_p90min.
*/
  public void gapdh_p90minTextEntered(ActionEvent e)
  {
	  gapdh_p90minT = Integer.parseInt(gapdh_p90min.getText());
  }
  
  public void cancelClicked(ActionEvent e)
  {
     if(isChanged & !isSaved){
    	 JOptionPane.showMessageDialog(main, "Save the changes", "save",
                 JOptionPane.ERROR_MESSAGE);	
     }
     else{
    	 isCancel=true;
    	 dispose();
     }
  }
  
  public void eq_hprt1_constTextEntered(ActionEvent e){
	  eq_hprt1_consT  = Double.parseDouble(eq_hprt1_cons.getText());
  }
  
  public void eq_hprt1_aregTextEntered(ActionEvent e){
	  eq_hprt1_aregT  = Double.parseDouble(eq_hprt1_areg.getText());
  }
  
  public void eq_hprt1_tnfaip3TextEntered(ActionEvent e){
	  eq_hprt1_tnfaip3T  = Double.parseDouble(eq_hprt1_tnfaip3.getText());
  }
  
  public void eq_hprt1_gimap5TextEntered(ActionEvent e){
	  eq_hprt1_gimap5T  = Double.parseDouble(eq_hprt1_gimap5.getText());
  }
  ///////
  public void eq_gapdh_constTextEntered(ActionEvent e){
	  eq_gapdh_consT  = Double.parseDouble(eq_gapdh_cons.getText());
  }
  
  public void eq_gapdh_aregTextEntered(ActionEvent e){
	  eq_gapdh_aregT  = Double.parseDouble(eq_gapdh_areg.getText());
  }
  
  public void eq_gapdh_tnfaip3TextEntered(ActionEvent e){
	  eq_gapdh_tnfaip3T  = Double.parseDouble(eq_gapdh_tnfaip3.getText());
  }
  
  public void eq_gapdh_gimap5TextEntered(ActionEvent e){
	  eq_gapdh_gimap5T  = Double.parseDouble(eq_gapdh_gimap5.getText());
  }
  /////
  public void eq_both_constTextEntered(ActionEvent e){
	  eq_both_consT  = Double.parseDouble(eq_both_cons.getText());
  }
  
  public void eq_both_aregTextEntered(ActionEvent e){
	  eq_both_aregT  = Double.parseDouble(eq_both_areg.getText());
  }
  
  public void eq_both_tnfaip3TextEntered(ActionEvent e){
	  eq_both_tnfaip3T  = Double.parseDouble(eq_both_tnfaip3.getText());
  }
  
  public void eq_both_gimap5TextEntered(ActionEvent e){
	  eq_both_gimap5T  = Double.parseDouble(eq_both_gimap5.getText());
  }
  
  
/* 
   A dummy action for save.
*/
  public void saveClicked(ActionEvent e)
  {
	  if(isChanged & !isCancel){
		  Thresholds.NTC_MIN = ntcminT;
		  Thresholds.NTC_MAX = ntcmaxT;
		  Thresholds.AREG_POS25_MIN = areg_p25minT;
		  Thresholds.AREG_POS25_MAX = areg_p25maxT;
		  Thresholds.AREG_POS90_MIN = areg_p90minT;
		  Thresholds.AREG_POS90_MAX = areg_p90maxT;

		  Thresholds.TNFAIP3_POS25_MIN = tnfaip3_p25minT;
		  Thresholds.TNFAIP3_POS25_MAX = tnfaip3_p25maxT;
		  Thresholds.TNFAIP3_POS90_MIN = tnfaip3_p90minT;
		  Thresholds.TNFAIP3_POS90_MAX = tnfaip3_p90maxT;

		  Thresholds.GIMAP5_POS25_MIN = gimap5_p25minT;
		  Thresholds.GIMAP5_POS25_MAX = gimap5_p25maxT;
		  Thresholds.GIMAP5_POS90_MIN = gimap5_p90minT;
		  Thresholds.GIMAP5_POS90_MAX = gimap5_p90maxT;

		  Thresholds.HPRT1_POS25_MIN = hprt1_p25minT;
		  Thresholds.HPRT1_POS25_MAX = hprt1_p25maxT;
		  Thresholds.HPRT1_POS90_MIN = hprt1_p90minT;
		  Thresholds.HPRT1_POS90_MAX = hprt1_p90maxT;

		  Thresholds.GAPDH_POS25_MIN = gapdh_p25minT;
		  Thresholds.GAPDH_POS25_MAX = gapdh_p25maxT;
		  Thresholds.GAPDH_POS90_MIN = gapdh_p90minT;
		  Thresholds.GAPDH_POS90_MAX = gapdh_p90maxT;

		  Thresholds.HPRT1_selected = hprt1_boxT;
		  Thresholds.GAPDH_selected = gAPDH_boxT;

		  Thresholds.HPRT1cutoff = hprt1_hk_cutoffT;
		  Thresholds.GAPDHcutoff = gapdh_hk_cutoffT;
		  Thresholds.RSQUARE_CUTOFF = rsqT;
		  Thresholds.HCCcutoff = hCC_cutoffT;


		  Thresholds.Eq_HPRT1_Constant =  eq_hprt1_consT ;
		  Thresholds.Eq_HPRT1_AREG =  eq_hprt1_aregT ;
		  Thresholds.Eq_HPRT1_TNFAIP3 =  eq_hprt1_tnfaip3T ;
		  Thresholds.Eq_HPRT1_GIMAP5 =  eq_hprt1_gimap5T ;

		  Thresholds.Eq_GAPDH_Constant =  eq_gapdh_consT ;
		  Thresholds.Eq_GAPDH_AREG =  eq_gapdh_aregT ;
		  Thresholds.Eq_GAPDH_TNFAIP3 =  eq_gapdh_tnfaip3T ;
		  Thresholds.Eq_GAPDH_GIMAP5 =  eq_gapdh_gimap5T ;

		  Thresholds.Eq_Both_Constant =  eq_both_consT ;
		  Thresholds.Eq_Both_AREG =  eq_both_aregT ;
		  Thresholds.Eq_Both_TNFAIP3 =  eq_both_tnfaip3T ;
		  Thresholds.Eq_Both_GIMAP5 =  eq_both_gimap5T ;
		  
		  isSaved=true;
		  dispose();
	  }
     else{
    	 dispose();
     }
  }
/* 
   Normally this  method does not need 
   to be modified.
*/
  private void setConstraints()
  {
     root.linkWidth( ntcmax,new Component[]{
                                       ntcmin },
                                       new double[]{
                                         1.00 });
     root.linkHeight( ntcmax,new Component[]{
                                       ntcmin },
                                       new double[]{
                                         1.00 });
     root.linkWidth( ntcmax,new Component[]{
                                       ntcmin },
                                       new double[]{
                                         1.00 });
     root.linkWidth( sep7,new Component[]{
                                     separator8 },
                                     new double[]{
                                       1.00 });
     root.linkWidth( hprt1_hk_cutoff,new Component[]{
                                                gapdh_hk_cutoff },
                                                new double[]{
                                                  1.00 });
  }
  private void setContainerConstraints(Container c$)
  {
     PageLayout p$=(PageLayout)c$.getLayout();
  }
/* 
   Override this  method in a subclass 
   to add your own event listeners.
   Here actionListeners have been added to each 
   button to print a message when the button 
   is clicked.
*/
  protected void decorateComponents()
  {

	  gapdh_p25min.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  gapdh_p25minTextEntered(e);
					  isChanged = true;
				  }
			  });

	  gapdh_p25min.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  gapdh_p25minT = Integer.parseInt(gapdh_p25min.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  areg_p25min.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  areg_p25minTextEntered(e);
					  isChanged = true;
				  }
			  });


	  areg_p25min.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  areg_p25minT = Integer.parseInt(areg_p25min.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  tnfaip3_p90min.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  tnfaip3_p90minTextEntered(e);
					  isChanged = true;
				  }
			  });

	  tnfaip3_p90min.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  tnfaip3_p90minT = Integer.parseInt(tnfaip3_p90min.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });


	  gimap5_p25max.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  gimap5_p25maxTextEntered(e);
					  isChanged = true;
				  }
			  });

	  gimap5_p25max.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  gimap5_p25maxT = Integer.parseInt(gimap5_p25max.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  gapdh_p25max.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  gapdh_p25maxTextEntered(e);
					  isChanged = true;
				  }
			  });

	  gapdh_p25max.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  gapdh_p25maxT = Integer.parseInt(gapdh_p25max.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  gapdh_p90max.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  gapdh_p90maxTextEntered(e);
					  isChanged = true;
				  }
			  });

	  gapdh_p90max.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  gapdh_p90maxT = Integer.parseInt(gapdh_p90max.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  areg_p90min.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  areg_p90minTextEntered(e);
					  isChanged = true;
				  }
			  });

	  areg_p90min.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  areg_p90minT = Integer.parseInt(areg_p90min.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  areg_p90max.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  areg_p90maxTextEntered(e);
					  isChanged = true;
				  }
			  });


	  areg_p90max.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  areg_p90maxT = Integer.parseInt(areg_p90max.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });


	  gimap5_p90min.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  gimap5_p90minTextEntered(e);
					  isChanged = true;
				  }
			  });


	  gimap5_p90min.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  gimap5_p90minT = Integer.parseInt(gimap5_p90min.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });


	  gimap5_p25min.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  gimap5_p25minTextEntered(e);
					  isChanged = true;
				  }
			  });


	  gimap5_p25min.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  gimap5_p25minT = Integer.parseInt(gimap5_p25min.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });


	  hprt1_hk_cutoff.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  hprt1_hk_cutoffTextEntered(e);
					  isChanged = true;
				  }
			  });
	  hprt1_hk_cutoff.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  hprt1_hk_cutoffT = Double.parseDouble(hprt1_hk_cutoff.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  ntcmax.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  ntcmaxTextEntered(e);
					  isChanged = true;
				  }
			  });

	  ntcmax.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  ntcmaxT = Integer.parseInt(ntcmax.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  tnfaip3_p90max.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  tnfaip3_p90maxTextEntered(e);
					  isChanged = true;
				  }
			  });

	  tnfaip3_p90max.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  tnfaip3_p90maxT = Integer.parseInt(tnfaip3_p90max.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });


	  gimap5_p90max.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  gimap5_p90maxTextEntered(e);
					  isChanged = true;
				  }
			  });

	  gimap5_p90max.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  gimap5_p90maxT = Integer.parseInt(gimap5_p90max.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  ntcmin.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  ntcminTextEntered(e);
					  isChanged = true;
				  }
			  });

	  ntcmin.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  ntcminT = Integer.parseInt( ntcmin.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });



	  rsq.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  rsqTextEntered(e);
					  isChanged = true;
				  }
			  });

	  rsq.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  rsqT = Double.parseDouble( rsq.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  hprt1_p90min.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  hprt1_p90minTextEntered(e);
					  isChanged = true;
				  }
			  });

	  hprt1_p90min.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  hprt1_p90minT = Integer.parseInt(hprt1_p90min.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  hCC_cutoff.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  hCC_cutoffTextEntered(e);
					  isChanged = true;
				  }
			  });

	  hCC_cutoff.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  hCC_cutoffT = Double.parseDouble(hCC_cutoff.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });


	  tnfaip3_p25max.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  tnfaip3_p25maxTextEntered(e);
					  isChanged = true;
				  }
			  });

	  tnfaip3_p25max.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  tnfaip3_p25maxT = Integer.parseInt( tnfaip3_p25max.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  hprt1_p25min.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  hprt1_p25minTextEntered(e);
					  isChanged = true;
				  }
			  });

	  hprt1_p25min.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  hprt1_p25minT = Integer.parseInt(hprt1_p25min.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });



	  hprt1_box.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  hprt1_boxClicked(e);
					  isChanged = true;
				  }
			  });

	  areg_p25max.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  areg_p25maxTextEntered(e);
					  isChanged = true;
				  }
			  });

	  areg_p25max.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  areg_p25maxT = Integer.parseInt(areg_p25max.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });


	  gAPDH_box.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  gAPDH_boxClicked(e);
					  isChanged = true;
				  }
			  });

	  tnfaip3_p25min.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  tnfaip3_p25minTextEntered(e);
					  isChanged = true;
				  }
			  });

	  tnfaip3_p25min.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  tnfaip3_p25minT = Integer.parseInt(tnfaip3_p25min.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  hprt1_p90max.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  hprt1_p90maxTextEntered(e);
					  isChanged = true;
				  }
			  });

	  hprt1_p90max.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  hprt1_p90maxT = Integer.parseInt(hprt1_p90max.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  gapdh_hk_cutoff.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  gapdh_hk_cutoffTextEntered(e);
					  isChanged = true;
				  }
			  });

	  gapdh_hk_cutoff.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  gapdh_hk_cutoffT = Double.parseDouble(gapdh_hk_cutoff.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  hprt1_p25max.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  hprt1_p25maxTextEntered(e);
					  isChanged = true;
				  }
			  });

	  hprt1_p25max.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  hprt1_p25maxT = Integer.parseInt(hprt1_p25max.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });


	  gapdh_p90min.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  gapdh_p90minTextEntered(e);
					  isChanged = true;
				  }
			  });

	  gapdh_p90min.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
			  gapdh_p90minT = Integer.parseInt(gapdh_p90min.getText());
			  isChanged=true;
		  }
		  public void insertUpdate(DocumentEvent e) {}
		  public void removeUpdate(DocumentEvent e) {}
	  });

	  cancel.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  cancelClicked(e);
				  }
			  });

	  save.addActionListener(
			  new ActionListener(){
				  public void actionPerformed(ActionEvent e){
					  saveClicked(e);
				  }
			  });

	  eq_hprt1_cons.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_hprt1_constTextEntered(e);
			  isChanged = true;

		  }
	  });

	  eq_hprt1_areg.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_hprt1_aregTextEntered(e);
			  isChanged = true;

		  }
	  });
	  eq_hprt1_tnfaip3.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_hprt1_tnfaip3TextEntered(e);
			  isChanged = true;

		  }
	  });
	  eq_hprt1_gimap5.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_hprt1_gimap5TextEntered(e);
			  isChanged = true;

		  }
	  });

	  eq_gapdh_cons.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_gapdh_constTextEntered(e);
			  isChanged = true;

		  }
	  });

	  eq_gapdh_areg.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_gapdh_aregTextEntered(e);
			  isChanged = true;

		  }
	  });
	  eq_gapdh_tnfaip3.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_gapdh_tnfaip3TextEntered(e);
			  isChanged = true;

		  }
	  });
	  eq_gapdh_gimap5.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_gapdh_gimap5TextEntered(e);
			  isChanged = true;

		  }
	  });

	  eq_both_cons.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_both_constTextEntered(e);
			  isChanged = true;

		  }
	  });

	  eq_both_areg.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_both_aregTextEntered(e);
			  isChanged = true;

		  }
	  });
	  eq_both_tnfaip3.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_both_tnfaip3TextEntered(e);
			  isChanged = true;

		  }
	  });
	  eq_both_gimap5.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  eq_both_gimap5TextEntered(e);
			  isChanged = true;

		  }
	  });

  }
/* 
   This method should be called for creating
   the layout for a given container.
*/
  public void createGUI(Container container)
  {
	 setvalues();
     createComponents();
     createCells();
     layout=root.createLayout(container);
     setContainerConstraints(container);
  }
/* 
   This method provides an alternative way to
   create the GUI on a JPanel.
   It returns a JPanel containing the GUI.
*/
  
  private void setvalues(){
	  ntcminT=Thresholds.NTC_MIN ;
	  ntcmaxT=Thresholds.NTC_MAX ;
	  areg_p25minT=Thresholds.AREG_POS25_MIN ;
	  areg_p25maxT=Thresholds.AREG_POS25_MAX ;
	  areg_p90minT=Thresholds.AREG_POS90_MIN ;
	  areg_p90maxT=Thresholds.AREG_POS90_MAX ;

	  tnfaip3_p25minT=Thresholds.TNFAIP3_POS25_MIN ;
	  tnfaip3_p25maxT=Thresholds.TNFAIP3_POS25_MAX ;
	  tnfaip3_p90minT=Thresholds.TNFAIP3_POS90_MIN ;
	  tnfaip3_p90maxT=Thresholds.TNFAIP3_POS90_MAX ;

	  gimap5_p25minT=Thresholds.GIMAP5_POS25_MIN ;
	  gimap5_p25maxT=Thresholds.GIMAP5_POS25_MAX ;
	  gimap5_p90minT=Thresholds.GIMAP5_POS90_MIN ;
	  gimap5_p90maxT=Thresholds.GIMAP5_POS90_MAX ;

	  hprt1_p25minT=Thresholds.HPRT1_POS25_MIN ;
	  hprt1_p25maxT=Thresholds.HPRT1_POS25_MAX ;
	  hprt1_p90minT=Thresholds.HPRT1_POS90_MIN ;
	  hprt1_p90maxT=Thresholds.HPRT1_POS90_MAX ;

	  gapdh_p25minT=Thresholds.GAPDH_POS25_MIN ;
	  gapdh_p25maxT=Thresholds.GAPDH_POS25_MAX ;
	  gapdh_p90minT=Thresholds.GAPDH_POS90_MIN ;
	  gapdh_p90maxT=Thresholds.GAPDH_POS90_MAX ;

	  hprt1_boxT=Thresholds.HPRT1_selected ;
	  gAPDH_boxT=Thresholds.GAPDH_selected ;

	  hprt1_hk_cutoffT=Thresholds.HPRT1cutoff ;
	  gapdh_hk_cutoffT=Thresholds.GAPDHcutoff ;
	  rsqT=Thresholds.RSQUARE_CUTOFF ;
	  hCC_cutoffT=Thresholds.HCCcutoff ;
	  
	  eq_hprt1_consT = Thresholds.Eq_HPRT1_Constant;
	  eq_hprt1_aregT = Thresholds.Eq_HPRT1_AREG;
	  eq_hprt1_tnfaip3T = Thresholds.Eq_HPRT1_TNFAIP3;
	  eq_hprt1_gimap5T = Thresholds.Eq_HPRT1_GIMAP5;
	  
	  eq_gapdh_consT = Thresholds.Eq_GAPDH_Constant;
	  eq_gapdh_aregT = Thresholds.Eq_GAPDH_AREG;
	  eq_gapdh_tnfaip3T = Thresholds.Eq_GAPDH_TNFAIP3;
	  eq_gapdh_gimap5T = Thresholds.Eq_GAPDH_GIMAP5;
	  
	  eq_both_consT = Thresholds.Eq_Both_Constant;
	  eq_both_aregT = Thresholds.Eq_Both_AREG;
	  eq_both_tnfaip3T = Thresholds.Eq_Both_TNFAIP3;
	  eq_both_gimap5T = Thresholds.Eq_Both_GIMAP5;
	  
  }
  
  public JPanel getJPanel()
  {
     JPanel p=new JPanel();
     setModal(true);
     createGUI(p);
     return p;
  }
  public Constants(JFrame main, String tit){
	  super(main, tit);
	  this.main = main;
	  
	  JPanel p=new JPanel();
	  setModal(true);
	  createGUI(p);
	  Container contentPane = getContentPane();
      contentPane.setLayout(new BorderLayout());
      JScrollPane p2 = new JScrollPane(p);
      contentPane.add(p2, BorderLayout.CENTER);
      JLabel notice = new JLabel("***Press ENTER after every change in the text box***", JLabel.CENTER);
      notice.setFont(new Font("Serif", Font.BOLD|Font.ITALIC, 14));
      JLabel notice2 = new JLabel("click SAVE button below.. after all changes are done!!", JLabel.CENTER);
      notice2.setFont(new Font("Serif", Font.BOLD|Font.ITALIC, 14));
      notice.setForeground(Color.RED);
      notice2.setForeground(Color.RED);
      JPanel noticepanel = new JPanel();
      noticepanel.setLayout(new BoxLayout(noticepanel, BoxLayout.Y_AXIS));
      noticepanel.add(notice);
      noticepanel.add(notice2);
      noticepanel.setAlignmentX(noticepanel.CENTER_ALIGNMENT);
      contentPane.add(noticepanel, BorderLayout.NORTH);
	  
	  invokeOnEventThread(new Runnable() {
			public void run() {
				p.setVisible(true);
				pack();
				setPreferredSize(main.getPreferredSize());
			}
		});
	  
	  addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent e) {
              dispose();
          }
      });
  }
  
  private  void invokeOnEventThread(Runnable runnable) {
      if (SwingUtilities.isEventDispatchThread()) {
          runnable.run();
      } else {
          SwingUtilities.invokeLater(runnable);
      }
  }


}

