package Pharmacie;

import java.util.ArrayList;
import java.util.Arrays;

public class AppTest {
	public static void main(String[] args) {
//		ModelJournal jrModel = new ModelJournal();
//		MedicListModel mlist = new MedicListModel();
//		VueNewMouvment vueMvm = new VueNewMouvment(mlist);
//		MedicListModel medicModl =new MedicListModel();
//		VueNewMedic vueCreateMedic = new VueNewMedic(medicModl);
		MedicListModel medicStockModel = new MedicListModel();
		MainVue MV= new MainVue();

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
	Mouvement v1 = new Mouvement(new ArrayList<>(Arrays.asList(new Pair(bicarbonate,2), new Pair(adole,1))), strV);
	v1.add(new Pair(clamoxyle,1));
	v1.Validate();
	Mouvement v2 = new Mouvement(new ArrayList<>(Arrays.asList(new Pair(gaviscon,2), new Pair(clamoxyle,1))), new StratVente());
	v2.Validate();
	Mouvement p1 = new Mouvement(new ArrayList<>(Arrays.asList(new Pair(gaviscon,100))), new StratAppro());
	p1.Validate();
	System.out.print(v1.js.getLog());
	System.out.print(String.format("\n Le montangt totale de la caisse est: %.2f Dt ",v1.cs.getSolde()));
	System.out.print("\n\n");
//	System.out.print(vueCreateMedic.medicTable.medicaments);


	}
	
}
