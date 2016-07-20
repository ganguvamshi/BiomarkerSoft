package biomarker;

import java.util.HashMap;

public class Thresholds {
	//NTC cutoffs
	static  int NTC_MIN = 0;
	static  int NTC_MAX = 32;//36;
	//Positive controls cutoffs for each gene and pos ctrl
	static  int AREG_POS25_MIN = 2;
	static  int AREG_POS25_MAX = 40;
	static  int AREG_POS90_MIN = 2;
	static  int AREG_POS90_MAX = 40;
	
	static int TNFAIP3_POS25_MIN = 2;
	static  int TNFAIP3_POS25_MAX = 40;
	static  int TNFAIP3_POS90_MIN = 2;
	static  int TNFAIP3_POS90_MAX = 40;
	
	static  int HPRT1_POS25_MIN = 2;
	static  int HPRT1_POS25_MAX = 40;
	static  int HPRT1_POS90_MIN = 2;
	static  int HPRT1_POS90_MAX = 40;
	
	static  int GAPDH_POS25_MIN = 2;
	static  int GAPDH_POS25_MAX = 40;
	static  int GAPDH_POS90_MIN = 2;
	static  int GAPDH_POS90_MAX = 40;
	
	static  int GIMAP5_POS25_MIN = 2;
	static  int GIMAP5_POS25_MAX = 40;
	static  int GIMAP5_POS90_MIN = 2;
	static  int GIMAP5_POS90_MAX = 40;
	
	
	static  String POS_content = "Pos Ctrl";
	static  String POS25 = "PC25";
	static  String POS90 = "PC90";
	static  String GENE_STDless = "GAPDH";
	static  String SAMPLE = "Unkn";
	static  double RSQUARE_CUTOFF = 0.99;
	static  Double HCCcutoff = 0.78;
	static  Double HPRT1cutoff = 29.0;
	static  Double GAPDHcutoff = 23.0;
	static  boolean HPRT1_selected = true;
	static  boolean GAPDH_selected = true;
	
	
	static double Eq_GAPDH_AREG = 46.819;
	static double Eq_GAPDH_TNFAIP3 = 1.858 ;
	static double Eq_GAPDH_GIMAP5 = -94.52;
	static double Eq_GAPDH_Constant = 1.399;
	
	static double Eq_HPRT1_AREG = 46.819;
	static double Eq_HPRT1_TNFAIP3 = 1.858;
	static double Eq_HPRT1_GIMAP5 = -94.52;
	static double Eq_HPRT1_Constant = 1.399;
	
	static double Eq_Both_AREG = 46.819;
	static double Eq_Both_TNFAIP3 = 1.858;
	static double Eq_Both_GIMAP5 = -94.52;
	static double Eq_Both_Constant = 1.399;
	
	protected static final char[] AdminPWD = "HCCADMIN".toCharArray();
	protected static final String OUTFILE_SUFFIX = "_HCC_Prediction.xls";
	
	public static  HashMap<String, String> DUP_NAMES = new HashMap<String, String>(){
		private static final long serialVersionUID = 1L;
		{
			put("AREG", "Test 1");
			put("TNFAIP3", "Test 2");
			put("GIMAP5", "Test 3");
			put("HPRT1", "Control 1");
			put("GAPDH", "Control 2");
		}
	};
	
	public static  HashMap<String, String> Name2Genes = new HashMap<String, String>(){
		private static final long serialVersionUID = 1L;
		{
			put("Test 1", "AREG");
			put("Test 2", "TNFAIP3");
			put("Test 3", "GIMAP5");
			put("Control 1", "HPRT1" );
			put("Control 2", "GAPDH");
		}
	};
	
	
}
