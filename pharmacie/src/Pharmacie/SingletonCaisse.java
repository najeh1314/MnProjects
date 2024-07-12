package Pharmacie;

public class SingletonCaisse {
	private double solde;
	private static SingletonCaisse csInstance;
	
	private SingletonCaisse() {
		this.solde = 0;
	}
	public static SingletonCaisse getCsInstance() {
		if(csInstance == null)
			csInstance = new SingletonCaisse();
		return csInstance;
	}
	public double getSolde() {
		return solde;
	}
	public void addSolde(double montant) {
		this.solde += montant;
	}
	
	
	
	
}
