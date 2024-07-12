package Pharmacie;

import java.util.ArrayList;

public class MedicListModel {
	ArrayList<Medicament> medicaments =new ArrayList<Medicament>(); 
	
	/*Medicament adole = new Paracetamol("Adole", 2.00, "Par057", 100 );
	Medicament gaviscon = new Gastrology("Gaviscon", 8.50,"Gas0031", 100);
	Medicament clamoxyle = new AntiBiotique("Clamoxyle", 14.80,"Bio0047-A", 100);
	Medicament vitamineC = new Paracetamol("VitamineC", 3.20,"Vit0099", 100);
	Medicament bicarbonate = new Gastrology("Bicarbonate de sodium", 22.50,"Met2201", 100);
	*/
	public MedicListModel() {
	/*	this.medicaments =new ArrayList<Medicament>();
		medicaments.add(adole);
		medicaments.add(bicarbonate);
		medicaments.add(clamoxyle);
		medicaments.add(vitamineC);*/
	}
	public void addMed(Medicament med) {
		this.medicaments.add(med);
	}
	
}
