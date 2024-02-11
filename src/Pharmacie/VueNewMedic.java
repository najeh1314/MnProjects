package Pharmacie;

import javax.swing.*;

public class VueNewMedic extends JFrame {
	MedicListModel medicTable;
    JLabel Lot, Name, Prix, Famille;
    JTextField tfLot, tfName, tfPrix;
    JButton btnCreate;
    JComboBox cbFamille;
    VueNewMedic(MedicListModel medicTable){
    	this.medicTable = medicTable;
        setTitle("Pharmacie Al Shifa");
        setSize(600, 300);
        setLayout(null);
        Lot = new JLabel("Lot No: ");
        Lot.setBounds(20, 20, 120, 30);
        tfLot= new JTextField();
        tfLot.setBounds(150, 20, 200, 30);
        Name = new JLabel("Name: ");
        Name.setBounds(20, 60, 120, 30);
        tfName= new JTextField();
        tfName.setBounds(150, 60, 200, 30);
        Prix = new JLabel("Prix: ");
        Prix.setBounds(20, 100, 120, 30);
        tfPrix= new JTextField();
        tfPrix.setBounds(150, 100, 200, 30);
        Famille = new JLabel("Famille: ");
        Famille.setBounds(20, 140, 120, 30);
        String famille[] = {"Antibiotique", "Paracetamol", "Gastrology"};
        cbFamille = new JComboBox(famille);
        cbFamille.setBounds(150, 140, 200, 30); 
        btnCreate = new JButton("Create");
        btnCreate.setBounds(400, 20, 100, 30);             
        getContentPane().add(Lot);
        getContentPane().add(tfLot);
        getContentPane().add(Name);
        getContentPane().add(tfName);
        getContentPane().add(Prix);
        getContentPane().add(tfPrix);
        getContentPane().add(Famille);
        getContentPane().add(btnCreate);
        getContentPane().add(cbFamille);
        btnCreate.addActionListener(new saveMedicCtrl(medicTable, this));
        setLocation(50, 300);
        setVisible(true);
    }
}   

