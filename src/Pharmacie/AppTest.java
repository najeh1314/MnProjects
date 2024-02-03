package Pharmacie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class AppTest {
	public static void main(String[] args) {
//	System.out.print("----");
	//Liste des articles disponible dans notre Pharmacie ==> BD?
        Path filePath = Paths.get("C:\\Users\\mnnaj\\Desktop\\Projet\\mouvement.xml");
        try {
			Files.deleteIfExists(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	}
}
