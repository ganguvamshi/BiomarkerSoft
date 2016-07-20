package biomarker;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableFont.FontName;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class writeexcel {
	//Workbook wb =null;
	WritableSheet sheet1 = null;
	public writeexcel(WritableSheet w) throws RowsExceededException, WriteException, IOException{
		super();
		sheet1 = w;
		InsertLogo();
		createSectionBorders();
	}
	
	public void writeNTC(JTable tab) throws RowsExceededException, WriteException{
		//create Table Borders
		sheet1.addCell(new Label(1, 8, null, TableBorder("TOPLEFT")));
		sheet1.addCell(new Label(1,14, null, TableBorder("BOTTOMLEFT")));
		sheet1.addCell(new Label(2, 8 , null, TableBorder("TOP")));
		sheet1.addCell(new Label(2, 14 , null, TableBorder("BOTOM")));
		sheet1.addCell(new Label(3,8, null, TableBorder("TOPRIGHT")));
		sheet1.addCell(new Label(3,14, null, TableBorder("BOTTOMRIGTH")));

		for(int row=9;row<14;row++){
			sheet1.addCell(new Label(1, row, null, TableBorder("LEFT")));
			sheet1.addCell(new Label(3, row, null, TableBorder("RIGHT")));
		}
		
	
		Label t = new Label(1, 8, "NTC",  cellFormat("H2"));
		sheet1.addCell(t);
		TableModel model = tab.getRowSorter().getModel();
		int col = 1;
		for (int i = 0; i < model.getColumnCount(); i++) {
			Label column = new Label(col, 9, model.getColumnName(i), cellFormat("H2"));
			sheet1.addCell(column);
			col +=1;
		} 
		col = 1;
		int row = 10;
		for (int i = 0; i < model.getRowCount(); i++) {
            for (int j = 0; j < model.getColumnCount(); j++) {
            	if (model.getValueAt(i, j) instanceof String) {
            		Label rec = new Label(col, row, model.getValueAt(i, j).toString(), cellFormat("N"));
                    sheet1.addCell(rec);
				}else {
					Number rec = new Number(col, row, Double.parseDouble(model.getValueAt(i, j).toString()), cellFormat("N"));
					sheet1.addCell(rec);
				}
                col +=1;
            }
            row +=1;
            col=1;
        }
		
		sheet1.addCell(new Label(2,16, "Cutoff:", cellFormat("H2")));
		sheet1.addCell(new Label(3,16, "Pass: CT>"+String.valueOf(Thresholds.NTC_MAX), cellFormat("N")));
	}
	
	private void InsertLogo() throws IOException{
		final double CELL_DEFAULT_HEIGHT = 40;
		final double CELL_DEFAULT_WIDTH = 210;
		BufferedImage input = null;
		try{
			URL imageFile = GUI.class.getResource("../images/nccs_logo.png");
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
	}
	
	private void createSectionBorders() throws RowsExceededException, WriteException{
		
		sheet1.addCell(new Label(0, 5, null, SectionBorder("TOPLEFT")));
		sheet1.addCell(new Label(0, 21,null, SectionBorder("BOTTOMLEFT")));
		for(int col=1; col<=22;col++){
			sheet1.addCell(new Label(col, 5, null, SectionBorder("TOP")));
			sheet1.addCell(new Label(col, 21, null, SectionBorder("BOTTOM")));
		}
		int col=23;
		sheet1.addCell(new Label(col, 5, null, SectionBorder("TOPRIGHT")));
		sheet1.addCell(new Label(col, 21, null, SectionBorder("BOTTOMRIGHT")));
		
		for(int row=6; row<21; row++){
			sheet1.addCell(new Label(0, row, null, SectionBorder("LEFT")));
			sheet1.addCell(new Label(23, row, null, SectionBorder("RIGHT")));
		}
		
		sheet1.addCell(new Label(0, 22, null, SectionBorder("TOPLEFT")));
		sheet1.addCell(new Label(8, 22, null, SectionBorder("TOPRIGHT")));
		
		for(int row=23; row<37;row++){
			sheet1.addCell(new Label(0, row, null, SectionBorder("LEFT")));
			sheet1.addCell(new Label(8, row, null, SectionBorder("RIGHT")));
		}
		
		for(col=1;col<8;col++){
			sheet1.addCell(new Label(col, 37, null, SectionBorder("BOTTOM")));
			
		}
		sheet1.addCell(new Label(0, 37, null, SectionBorder("BOTTOMLEFT")));
		sheet1.addCell(new Label(8, 37, null, SectionBorder("BOTTOMRIGHT")));
		
		//Number ix = new Number(0, 6, 1, cellFormat("H1"));
		Label qc = new Label(1, 6,"QA/QC" , cellFormat("H1"));
		sheet1.setColumnView(1, 20);
		//sheet1.addCell(ix);
		sheet1.addCell(qc);
	}
	
	
	
	private WritableCellFormat cellFormat(String type) throws WriteException{
		WritableCellFormat format = new WritableCellFormat();
		FontName wf = WritableFont.createFont("Calibri (Body)");
		switch(type){
		case "H1":
			format.setFont(new WritableFont(wf, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED));
			format.setAlignment(jxl.format.Alignment.LEFT);
			break;
		case "H2":
			format.setFont(new WritableFont(wf, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
			format.setAlignment(jxl.format.Alignment.LEFT);
			break;
		case "N":
			format.setFont(new WritableFont(wf, 14, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK));
			format.setAlignment(jxl.format.Alignment.LEFT);
			break;
		}
		return format;
	}
	
	private WritableCellFormat SectionBorder(String border) throws WriteException{
		WritableCellFormat format = new WritableCellFormat();
		switch (border){
		case "TOP":
			format.setBorder(Border.TOP, BorderLineStyle.THICK, Colour.BLUE);
			break;
		case "BOTTOM":
			format.setBorder(Border.BOTTOM, BorderLineStyle.THICK, Colour.BLUE);
			break;
		case "LEFT":
			format.setBorder(Border.LEFT, BorderLineStyle.THICK, Colour.BLUE);
			break;
		case "RIGHT":
			format.setBorder(Border.RIGHT, BorderLineStyle.THICK, Colour.BLUE);
			break;
		case "TOPRIGHT":
			format.setBorder(Border.TOP, BorderLineStyle.THICK, Colour.BLUE);
			format.setBorder(Border.RIGHT, BorderLineStyle.THICK, Colour.BLUE);
			break;
		case "TOPLEFT":
			format.setBorder(Border.TOP, BorderLineStyle.THICK, Colour.BLUE);
			format.setBorder(Border.LEFT, BorderLineStyle.THICK, Colour.BLUE);
			break;
		case "BOTTOMRIGHT":
			format.setBorder(Border.BOTTOM, BorderLineStyle.THICK, Colour.BLUE);
			format.setBorder(Border.RIGHT, BorderLineStyle.THICK, Colour.BLUE);
			break;
		case "BOTTOMLEFT":
			format.setBorder(Border.BOTTOM, BorderLineStyle.THICK, Colour.BLUE);
			format.setBorder(Border.LEFT, BorderLineStyle.THICK, Colour.BLUE);
			break;
		}
		return format;
	}
	
	private WritableCellFormat TableBorder(String border) throws WriteException{
		WritableCellFormat format = new WritableCellFormat();
		switch (border){
		case "TOP":
			format.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			break;
		case "BOTTOM":
			format.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
			break;
		case "LEFT":
			format.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			break;
		case "RIGHT":
			format.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
		case "TOPRIGHT":
			format.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			format.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			break;
		case "TOPLEFT":
			format.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			format.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			break;
		case "BOTTOMRIGHT":
			format.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			format.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
			break;
		case "BOTTOMLEFT":
			format.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			format.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
			break;
		}
		return format;
	}
}
