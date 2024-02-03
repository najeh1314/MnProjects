package Pharmacie;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Journal {
	private String log;
	private static Journal jsInstance;
	
	private Journal() {
		log = new String();
	}
	public static Journal getJsInstance() {
		if(jsInstance == null)
			jsInstance = new Journal();
		return jsInstance;
	} 
	public String getLog() {
		return log;
	}
	public void addLog(String str) {
		 Date d = new Date();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH'h'mm");
		 this.log+="["+dateFormat.format(d)+"] "+str+"\n\t-- -- --\n";
	}

}
