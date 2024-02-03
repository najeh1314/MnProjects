package Pharmacie;
import java.util.ArrayList;


import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.stream.*;


public  class Mouvement implements Article {
	static int numMvm;
	SingletonJournal js =SingletonJournal.getJsInstance();
	SingletonCaisse cs = SingletonCaisse.getCsInstance();
	StrategieMvm mvm;
	ArrayList<Pair<Article, Integer>> lesArticles = new ArrayList<>();

	public Mouvement(ArrayList<Pair<Article, Integer>> lesArticles, StrategieMvm mvm) {
		this.lesArticles = lesArticles;
		this.mvm = mvm;
		this.numMvm = numMvm+1;
	}
	@Override
	public double prix() {
		double totale = 0;
		for(Pair<Article, Integer> art: lesArticles) {
			totale += art.getFirst().prix() * art.getSecond(); 
		}
		return totale;
	}
	@Override
	public String toStrng() {
		StringBuilder sb = new StringBuilder();
		for(Pair<Article, Integer> art: lesArticles) {
			sb = sb.append(String.format("\n\t*(%02d) %s: %05.2fDT  : %03d En Stock" ,art.getSecond(),art.getFirst().toStrng(),art.getFirst().prix(), art.getFirst().qtite())) ;
		}
		return sb.toString();
	}
	public void add(Pair<Article, Integer> art) {
		lesArticles.add(art);	
	}
	public void remove(Pair<Article, Integer> art) {
		lesArticles.remove(art);
	}
	public void Validate() {
		changeQtiteStock();
		showComposants();
		//Le montant de la caisse ne compte que les prix de la vente et non pas l'approvisionnement
		if (mvm instanceof StratVente) 
			cs.addSolde(prix());
			saveMvm();
	}
	private void saveMvm() {try {
        Path filePath = Paths.get("C:\\Users\\mnnaj\\Desktop\\Projet\\mouvement.xml");
        boolean fileExists = Files.exists(filePath);

        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter;

        if (fileExists) {
            // Append to existing file
            xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream("C:\\Users\\mnnaj\\Desktop\\Projet\\mouvement.xml", true));
        } else {
            // Create a new file
            xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream("C:\\Users\\mnnaj\\Desktop\\Projet\\mouvement.xml"));
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("Mouvements");
        }

        // Start writing XML
        xmlStreamWriter.writeStartElement("Mouvement");
        xmlStreamWriter.writeAttribute("numMvm", String.valueOf(numMvm));

        // Write articles
        for (Pair<Article, Integer> art : lesArticles) {
            xmlStreamWriter.writeStartElement("Article");
            xmlStreamWriter.writeAttribute("Lot", String.valueOf(art.getFirst().getMedic().lot));
            xmlStreamWriter.writeAttribute("stock",  String.valueOf(art.getFirst().qtite()));

            // Write article details
            xmlStreamWriter.writeStartElement("Details");
            xmlStreamWriter.writeAttribute("name", art.getFirst().toStrng());
            xmlStreamWriter.writeAttribute("price", String.valueOf(art.getFirst().prix()));
            xmlStreamWriter.writeAttribute("Qtite de mvm", String.valueOf(art.getFirst().qtite()));
            xmlStreamWriter.writeEndElement();

            xmlStreamWriter.writeEndElement(); // Close Article element
        }

        // End writing XML
        xmlStreamWriter.writeEndElement(); // Close Mouvement element

        if (!fileExists) {
            xmlStreamWriter.writeEndElement(); // Close Mouvements element
            xmlStreamWriter.writeEndDocument();
        }

        xmlStreamWriter.flush();
        xmlStreamWriter.close();
    } catch (XMLStreamException | java.io.IOException e) {
        e.printStackTrace();
    }}
	public void showComposants() {
		js.addLog(String.format("\n-%s id: %03d %s\n Total Price: %05.2fDt.",mvm.nomStrategieMvm(),numMvm,toStrng(), prix()));		
	}
	public void changeQtiteStock() {
		for(Pair<Article, Integer> art: lesArticles)
			mvm.changeQtite(art.getFirst().getMedic(), art.getSecond());
	}
	@Override
	public int qtite() {
		return 0;
	}
	@Override
	public Medicament getMedic() {
		return null;
	}

}
