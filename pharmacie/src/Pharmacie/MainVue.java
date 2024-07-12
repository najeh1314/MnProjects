package Pharmacie;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainVue extends JFrame {
    JButton btnCreateMedic, btnNewMvm;
    JLabel titre, instruction;
    MainVue(){
        setTitle("JAVA Pharmacie");
        setSize(600, 300);
       
        setLayout(null);
        titre = new JLabel("Pharmacie Al Shifa");
        titre.setBounds(20, 20, 320, 30);
        titre.setFont(new Font("Arial", Font.BOLD, 30));
        titre.setForeground(Color.BLUE);
        instruction = new JLabel("Selectionnez une action à éxecuter..");
        instruction.setBounds(20, 80, 320, 30);
        instruction.setFont(new Font("Arial",Font.ITALIC, 18));
        instruction.setForeground(Color.BLUE);
        btnCreateMedic = new JButton("Creer Nouveau Medicament");
        btnCreateMedic.setBounds(20, 120, 200, 30);
        btnNewMvm = new JButton("Nouveau Mouvement");
        btnNewMvm.setBounds(230, 120, 200, 30);
        getContentPane().add(titre);        
        getContentPane().add(instruction);
        getContentPane().add(btnCreateMedic);
        getContentPane().add(btnNewMvm);
        MedicListModel medicModl =new MedicListModel();
        btnNewMvm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ModelJournal jrModel = new ModelJournal();
//				MedicListModel mlist = new MedicListModel();
				VueNewMouvment vueMvm = new VueNewMouvment(medicModl);
//				MedicListModel medicModl =new MedicListModel();
//				VueNewMedic vueCreateMedic = new VueNewMedic(medicModl);
			}
		});
        
        btnCreateMedic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				ModelJournal jrModel = new ModelJournal();
//				MedicListModel mlist = new MedicListModel();
//				VueNewMouvment vueMvm = new VueNewMouvment(mlist);
				VueNewMedic vueCreateMedic = new VueNewMedic(medicModl);
			}
		});
        setLocation(250, 40);
        setVisible(true);
    }
}
