package Pharmacie;

import javax.swing.*;

public class VueNewMouvment extends JFrame{
	static MedicListModel mlist;
	
    JLabel Medicament, Quantite;
    JTextField tfMedicament, tfQuantite;
    JButton btnValide;
    JComboBox cbFamille, cbMedicament;
    VueNewMouvment (MedicListModel jrModel){
    	this.mlist = jrModel;
        setTitle("Pharmacie Al Shifa");
        setSize(600, 300);
        setLayout(null);
        
        Medicament = new JLabel("Medicament: ");
        Medicament.setBounds(20, 60, 120, 30);
        Quantite = new JLabel("Quantite: ");
        Quantite.setBounds(20, 100, 120, 30);
        tfQuantite= new JTextField();
        tfQuantite.setBounds(150, 100, 200, 30);
        String typeMvm[] = {"APPROVISIONNEMENT", "VENTE"};
        cbFamille = new JComboBox<>(typeMvm);
        cbFamille.setBounds(150, 140, 200, 30); 
       
        if (mlist != null && mlist.medicaments != null) {
            cbMedicament = new JComboBox<>(new DefaultComboBoxModel<>(mlist.medicaments.toArray(new Medicament[0])));
        } else {
            cbMedicament = new JComboBox<>();
        }
        cbMedicament.setBounds(150, 60, 200, 30);

        btnValide = new JButton("VALIDER");
        btnValide.setBounds(400, 60, 100, 30);             
        getContentPane().add(Medicament);
        getContentPane().add(Quantite);
        getContentPane().add(tfQuantite);
        getContentPane().add(btnValide);
        getContentPane().add(cbFamille);
//        getContentPane().add(cbMedicament);
        getContentPane().add(cbMedicament);

        btnValide.addActionListener(new validMvmCtrl(this));
        
        setLocation(700, 300);
        setVisible(true);
    }

}
