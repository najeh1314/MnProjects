package Pharmacie;
import java.util.ArrayList;

public  class Mouvement implements Article {
	static int numMvm;
	Journal js =Journal.getJsInstance();
	StrategieMvm mvm;
	ArrayList<Article> lesArticles = new ArrayList<Article>();

	public Mouvement(ArrayList<Article> lesArticles, StrategieMvm mvm) {
		this.lesArticles = lesArticles;
		this.mvm = mvm;
		this.numMvm = numMvm+1;
	}
	@Override
	public double prix() {
		double totale = 0;
		for(Article art: lesArticles) {
			totale += art.prix() ; 
		}
		return totale;
	}
	@Override
	public String desc() {
		StringBuilder sb = new StringBuilder();
		for(Article art: lesArticles) {
			sb = sb.append(String.format("\n\t*%s: %05.2fDT  Restant Stock: %03d " ,art.desc(),art.prix(), art.qtite())) ;
		}
		return sb.toString();
	}
	public void add(Article art) {
		lesArticles.add(art);	
	}
	public void remove(Article art) {
		lesArticles.remove(art);
	}
	
	public void Validate() {
		changeQtiteStock();
		affichJs();
	}
	public void affichJs() {
		js.addLog(String.format("\n-Vente num: %03d %s\n Total Price: %05.2fDt.",numMvm,desc(), prix()));		
	}
	public void changeQtiteStock() {
		for(Article art:lesArticles)
			mvm.changeQtite(art.getMedic(), 2);
	}
	@Override
	public int qtite() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Medicament getMedic() {
		// TODO Auto-generated method stub
		return null;
	}

}
