package Pharmacie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JTextField;
public class validMvmCtrl implements ActionListener{
	VueNewMouvment vMvm;
	ModelJournal jrModel;
	public validMvmCtrl( VueNewMouvment vMvm) {
		super();
		this.vMvm = vMvm;	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox nomMedic = vMvm.cbMedicament;
		JComboBox typeMvm = vMvm.cbFamille;
		JTextField quantite = vMvm.tfQuantite;
		
		Medicament nomMedicTxt = (Medicament) nomMedic.getSelectedItem();
		String typeMvmTxt = (String) typeMvm.getSelectedItem();
		String quantittTxt = quantite.getText();
		
		Mouvement mvm = null;
		switch(typeMvmTxt) {
		case "VENTE":
			mvm =new Mouvement(new ArrayList<>(), new StratVente());
			nomMedicTxt.qtiteStock-=Double.valueOf(quantittTxt);

			break;
		case"APPROVISIONNEMENT":
			mvm = new Mouvement(new ArrayList<>(), new StratAppro());
			nomMedicTxt.qtiteStock+=Double.valueOf(quantittTxt);

			break;
		 default:
	            System.exit(0);	
		}
		
		System.out.print(nomMedicTxt.nom);
		for(Medicament v: vMvm.mlist.medicaments ) {
		System.out.print("\n -Medic  :"+v);
		//khgkdfhgfg
		}
	}

}
