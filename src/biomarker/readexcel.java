package biomarker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFRow.CellIterator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import biomarker.Thresholds;
import biomarker.readexcel.Book;

public class readexcel {
	private List<Book> listBooks = null;
	
	public class Book {
	    private String content;
	    private String Gene;
	    private String sample;
	    private Double ctmean;
	    private DecimalFormat df2 = new DecimalFormat("#.##");
	    
	    public Book() {
	    	
	    }	 
	    public String toString() {
	        return String.format("%s - %s - %s - %f", content, Gene, sample, ctmean);
	    }
	    public String getContent(){
	    	return content;
	    }
	    public String getGene(){
	    	return Gene;
	    }
	    public String getSample(){
	    	return sample;
	    }
	    public Double getCTmean(){
	    	return round(ctmean);
	    }
	    public void setContent(String value){
	    	this.content = value;
	    }
	    public void setGene(String value){
	    	this.Gene = value;
	    }
	    public void setSample(String value){
	    	this.sample = value;
	    }
	    public void setCTmean(Double value){
	    	this.ctmean = value;
	    }
	    public Double round(Double d){
			 return Double.valueOf(df2.format(d));
		}
	    public String getNTCstatus(){
	    	String res = null;
	    	if(this.ctmean > Thresholds.NTC_MAX || this.ctmean <0){
	    		res = "PASS";
	    	}
	    	else{
	    		res = "FAIL";
	    	}
			return res;
	    	
	    }
		public String getPOSstatus() {
			// TODO Auto-generated method stub
			// Take the cutoffs for each gene and positive ctrl and return the status accordingly
			String status  = null;
			if(this.getContent().equalsIgnoreCase(Thresholds.POS_content) & this.getSample().equalsIgnoreCase(Thresholds.POS25)){
				status  = calPOS25status(this.getCTmean(), this.getGene());
			}
			else if(this.getContent().equalsIgnoreCase(Thresholds.POS_content) & this.getSample().equalsIgnoreCase(Thresholds.POS90)){
				status = calPOS90status(this.getCTmean(), this.getGene());
			}
			return status;
		}
	}
	
	private Workbook getWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {
	    Workbook workbook = null;
	    if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook(inputStream);
	    } else if (excelFilePath.endsWith("xls")) {
	        workbook = new HSSFWorkbook(inputStream);
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	    return workbook;
	}
	
	public String calPOS90status(Double cTmean, String gene) {
		String res = "FAIL";
		switch (gene) {
		case "AREG":
			if(cTmean>Thresholds.AREG_POS90_MIN && cTmean<Thresholds.AREG_POS90_MAX){
				res="PASS";
			}
			break;
		case "TNFAIP3":
			if(cTmean>Thresholds.TNFAIP3_POS90_MIN && cTmean<Thresholds.TNFAIP3_POS90_MAX){
				res="PASS";
			}
			break;
		case "GIMAP5":
			if(cTmean>Thresholds.GIMAP5_POS90_MIN && cTmean<Thresholds.GIMAP5_POS90_MAX){
				res="PASS";
			}
			break;
		case "HPRT1":
			if(cTmean>Thresholds.HPRT1_POS90_MIN && cTmean<Thresholds.HPRT1_POS90_MAX){
				res="PASS";
			}
			break;
		case "GAPDH":
			if(cTmean>Thresholds.GAPDH_POS90_MIN && cTmean<Thresholds.GAPDH_POS90_MAX){
				res="PASS";
			}
			break;
		default:	res="FAIL"; 	break;
		}
		return res;
	}

	public String calPOS25status(Double cTmean, String gene) {
		String res = "FAIL";
		switch (gene) {
		case "AREG":
			if(cTmean>Thresholds.AREG_POS25_MIN && cTmean<Thresholds.AREG_POS25_MAX){
				res="PASS";
			}
			break;
		case "TNFAIP3":
			if(cTmean>Thresholds.TNFAIP3_POS25_MIN && cTmean<Thresholds.TNFAIP3_POS25_MAX){
				res="PASS";
			}
			break;
		case "GIMAP5":
			if(cTmean>Thresholds.GIMAP5_POS25_MIN && cTmean<Thresholds.GIMAP5_POS25_MAX){
				res="PASS";
			}
			break;
		case "HPRT1":
			if(cTmean>Thresholds.HPRT1_POS25_MIN && cTmean<Thresholds.HPRT1_POS25_MAX){
				res="PASS";
			}
			break;
		case "GAPDH":
			if(cTmean>Thresholds.GAPDH_POS25_MIN && cTmean<Thresholds.GAPDH_POS25_MAX){
				res="PASS";
			}
			break;
		default:	res="FAIL"; 	break;
		}
		return res;
	}

	public List<Book> readBooksFromExcelFile(String excelFilePath) throws IOException {
	    listBooks = new ArrayList<>();
	    FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	    Workbook workbook = getWorkbook(inputStream, excelFilePath);
	    Sheet firstSheet = workbook.getSheetAt(0);
	    int maxNumOfCells = firstSheet.getRow(0).getLastCellNum();
	    Iterator<Row> iterator = firstSheet.iterator();
	    Row header = iterator.next();
	    while (iterator.hasNext()) {
	        Row nextRow = iterator.next();
	        Iterator<Cell> cellIterator = nextRow.cellIterator();
	        Book aBook = new Book();
	       // while (cellIterator.hasNext()) {
	        for( int cellCounter = 0; cellCounter < maxNumOfCells ; cellCounter ++){
	            //Cell nextCell = cellIterator.next();
	            Cell nextCell = nextRow.getCell(cellCounter);
	        	//int columnIndex = nextCell.getColumnIndex();
	            //switch (columnIndex) {
	            switch (cellCounter){
	        	case 3:
	                aBook.setContent((String) getCellValue(nextCell));
	                break;
	            case 4:
	                aBook.setGene((String) getCellValue(nextCell));
	                break;
	            case 5:
	                aBook.setSample((String) getCellValue(nextCell));
	                break;
	            case 6:
	            	if(nextCell==null){
	            		aBook.setCTmean(-1.0);
	            	}
	            	else{
	            		aBook.setCTmean(((Double) getCellValue(nextCell)));
	            	}
	           }
	        }
	        
	        listBooks.add(aBook);
	    }
	    workbook.close();
	    inputStream.close();
	    return listBooks;
	}
	
	private Object getCellValue(Cell cell) {
	    switch (cell.getCellType()) {
	    case Cell.CELL_TYPE_STRING:
	        return cell.getStringCellValue();
	 
	    case Cell.CELL_TYPE_BOOLEAN:
	        return cell.getBooleanCellValue();
	 
	    case Cell.CELL_TYPE_NUMERIC:
	        return cell.getNumericCellValue();
	        
	    case Cell.CELL_TYPE_BLANK:
	    	return -1.0 ;
	    }
	    return null;
	}
}
