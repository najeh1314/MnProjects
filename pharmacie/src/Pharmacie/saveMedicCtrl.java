package Pharmacie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class saveMedicCtrl implements ActionListener {
	MedicListModel m;
	VueNewMedic v;
	
	saveMedicCtrl(MedicListModel m, VueNewMedic v) {
		super();
		this.m=m;
		this.v = v;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	JTextField nom = v.tfName;
    JTextField prix = v.tfPrix;
    JTextField lot = v.tfLot;
    JComboBox famille = v.cbFamille;

    // Get the text from the JTextFields
    String nomText = nom.getText();
    String prixText = prix.getText();
    String lotText = lot.getText();
    String familleText = (String) famille.getSelectedItem();
    
    Medicament medic = null;
    
    switch (familleText) {
        case "Antibiotique":
            medic = new AntiBiotique(nomText, Double.parseDouble(prixText), lotText, 0);
            break;
        case "Paracetamol":
            medic = new Paracetamol(nomText, Double.parseDouble(prixText), lotText, 0);
            break;
        case "Gastrologie":
            medic = new Gastrology(nomText, Double.parseDouble(prixText), lotText, 0);
            break;
        default:
            System.exit(0);
    }
 
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("listMedic.txt"))) {
        writer.write(medic.nom + " " + medic.lot);
    } 
    catch (IOException e1) {
        e1.printStackTrace();
    }
   
    m.medicaments.add(medic);
//    m.addMed(medic);
//    saveMedic(medic);
   	System.out.print(v.medicTable.medicaments + "\n");

	}	

//	public void saveMedic(Medicament medic) {
//        try {
//            Path filePath = Paths.get("C:\\Users\\mnnaj\\Desktop\\Projet\\medicList.xml");
//            boolean fileExists = Files.exists(filePath);
//
//            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
//            XMLStreamWriter xmlStreamWriter;
//
//            if (fileExists) {
//                // Append to existing file
//                xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream("C:\\Users\\mnnaj\\Desktop\\Projet\\medicList.xml", true));
//            } else {
//                // Create a new file
//                xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream("C:\\Users\\mnnaj\\Desktop\\Projet\\medicList.xml"));
//                xmlStreamWriter.writeStartDocument();
//                xmlStreamWriter.writeStartElement("Medicaments");
//            }
//
//            // Start writing XML
//            xmlStreamWriter.writeStartElement("Medicament");
//            xmlStreamWriter.writeAttribute("Nom", medic.nom);
//            xmlStreamWriter.writeAttribute("Prix", String.valueOf(medic.prixMedic));
//            xmlStreamWriter.writeAttribute("Lot", medic.lot);
//            xmlStreamWriter.writeAttribute("Quantite", String.valueOf(medic.qtiteStock));
//            xmlStreamWriter.writeEndElement(); // Close Medicament element
//
//            if (!fileExists) {
//                xmlStreamWriter.writeEndElement(); // Close Medicaments element
//                xmlStreamWriter.writeEndDocument();
//            }
//
//            xmlStreamWriter.flush();
//            xmlStreamWriter.close();
//            System.out.println("Medicament Data Saved to XML");
//        } catch (XMLStreamException | IOException e) {
//            e.printStackTrace();
//        }
    }
