package Pharmacie;

import java.util.ArrayList;
import java.util.Arrays;

public class AppTest {
	public static void main(String[] args) {
//	System.out.print("----");
	//Liste des articles disponible dans notre Pharmacie ==> BD?
	Medicament adole = new AntiBiotique("Adole", 2.00, "Par057", 100 );
	Medicament gaviscon = new AntiBiotique("Gaviscon", 8.50,"Gas0031", 100);
	Medicament clamoxyle = new AntiBiotique("Clamoxyle", 14.80,"Bio0047-A", 100);
	Medicament vitamineC = new AntiBiotique("VitamineC", 3.20,"Vit0099", 100);
	Medicament bicarbonate = new AntiBiotique("Bicarbonate de sodium", 22.50,"Met2201", 100);
//	StratVente strVn = new StratVente();
//	strVn.changeQtite(50,adole);
	//Les ventes de jours
	StratVente strV = new StratVente();
	StratAppro strAp = new StratAppro();
	Mouvement v1 = new Mouvement(new ArrayList<>(Arrays.asList(bicarbonate, bicarbonate, adole)), strV);
	v1.add(clamoxyle);
	v1.Validate();
	Mouvement v2 = new Mouvement(new ArrayList<>(Arrays.asList(gaviscon, clamoxyle)), new StratVente());
	v2.Validate();
	System.out.print(v1.js.getLog());
	}
}
