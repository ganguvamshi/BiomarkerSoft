package biomarker;

import java.io.*;
import java.util.*;
import biomarker.readexcel.*;

public class InputFileManager {
	final String delimiter = "\t";
	public String Directory_Path;

	public void Getfileinfo(String inputFile, String outputDir){
		File f = new File(inputFile);
		File o = new File(outputDir);
		String seperator = System.getProperty("file.separator");
		Directory_Path = o.getPath();
		f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf(seperator) + 1);
	}
	
	public String GetOsType(){
		return System.getProperty("os.name");
	}
	
	public  List<Book> ReadInput(String inputFile) throws IOException {
		readexcel reader = new readexcel();
		List<Book> listRecords = reader.readBooksFromExcelFile(inputFile);
		return listRecords;
	}
}
