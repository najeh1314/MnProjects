package Pharmacie;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SingletonJournal {
	private String log;
	private static SingletonJournal jsInstance;
	
	private SingletonJournal() {
		log = new String();
	}
	public static SingletonJournal getJsInstance() {
		if(jsInstance == null)
			jsInstance = new SingletonJournal();
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
