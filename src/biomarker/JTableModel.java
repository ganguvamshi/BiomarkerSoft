package biomarker;

import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

public class JTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = new String[] {"Gene", "Rsquare", "Status","#Points"};
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] {JButton.class, Double.class, String.class, Integer.class};
	HashMap<String, Double[]> Rval = null;
	public JTableModel(HashMap<String, Double[]> Rval) {
		this.Rval = Rval;
	}
	@Override public int getColumnCount() {
		return COLUMN_NAMES.length;
	}

	@Override public int getRowCount() {
		return Rval.size();
	}
	
	@Override public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex];
    }
	
	@Override public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_TYPES[columnIndex];
	}
	public Double getRvalue(String gene){
		return Rval.get(gene)[0];
	}
	public int getPonts(String gene){
		return Rval.get(gene)[1].intValue();
	}
	@Override public Object getValueAt(final int rowIndex, final int columnIndex) {
		String gene = Rval.keySet().toArray()[rowIndex].toString();
		double rsq = getRvalue(gene);
		int points = getPonts(gene);
		switch (columnIndex) {
			case 0: final JButton button = new JButton(Thresholds.DUP_NAMES.get(gene));
					/*button.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							JOptionPane.showMessageDialog(JOptionPane.getFrameForComponent(button), 
									"Button clicked for row "+rowIndex);
						}
					});*/
					button.setPreferredSize(new Dimension(String.valueOf("ControlGene2").length()+2, 2));
					return button;
			case 1: return rsq;
			case 2: if(rsq<Thresholds.RSQUARE_CUTOFF){
							return "FAIL";
					}
					else{
						return "PASS";
					}
			case 3: return points;
			default: return "Error";
		}
	}	
}