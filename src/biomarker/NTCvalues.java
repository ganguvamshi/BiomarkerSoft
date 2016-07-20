package biomarker;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import javax.swing.event.*;
import pagelayout.*;
import pagelayout.util.*;
import static pagelayout.EasyCell.*;


public class NTCvalues
{
  protected JLabel min;
  protected JLabel max;
  protected JTextField ntcmin;
  protected JTextField ntcmax;
  protected CellGrid grid2;
  protected Column root;
  PageLayout layout;
/* 
   This is just a convenience method 
   to create the object NTCvalues.
*/
  public static NTCvalues createGUIObject()
  {
     return new NTCvalues();
  }
/* 
   Normally this  method does not need 
   to be modified.
*/
  protected void createComponents()
  {

     min = new javax.swing.JLabel();
     min.setText("MIN");

     max = new javax.swing.JLabel();
     max.setText("MAX");

     ntcmin = new javax.swing.JTextField(5);
     ntcmin.setText(String.valueOf(Thresholds.NTC_MIN));
     setDimensions(ntcmin, 6, 6, 23, 32767, 23, 99, 23);

     ntcmax = new javax.swing.JTextField(5);
     ntcmax.setText(String.valueOf(Thresholds.NTC_MAX));
     setDimensions(ntcmax, 0, 6, 23, 32767, 23, 111, 23);

     decorateComponents();

  }
/* 
   Normally this  method does not need 
   to be modified.
*/
  protected void createCells()
  {
     grid2 = grid(min, max, eol(), 
                  ntcmin, ntcmax);
     
     root = new Column(none, none,  grid2);


     setConstraints();

  }

/* 
   The following 'dummy action' methods should
   to be modified for the application.
*/
/* 
   A dummy action for ntcmax.
*/
  public void ntcmaxTextEntered(ActionEvent e)
  {
     Thresholds.NTC_MAX = Integer.valueOf(ntcmax.getText());
  }

/* 
   A dummy action for ntcmin.
*/
  public void ntcminTextEntered(ActionEvent e)
  {
     Thresholds.NTC_MIN = Integer.valueOf(ntcmin.getText());
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

     ntcmax.addActionListener(
		new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			 ntcmaxTextEntered(e);
		  }
		});

     ntcmin.addActionListener(
		new ActionListener(){
		  public void actionPerformed(ActionEvent e){
			 ntcminTextEntered(e);
		  }
		});

  }
/* 
   This method should be called for creating
   the layout for a given container.
*/
  public void createGUI(Container container)
  {
     createComponents();
     createCells();
     layout=root.createLayout(container);
  }
/* 
   This method provides an alternative way to
   create the GUI on a JPanel.
   It returns a JPanel containing the GUI.
*/
  public JPanel getJPanel()
  {
     JPanel p=new JPanel();
     createGUI(p);
     return p;
  }

}

