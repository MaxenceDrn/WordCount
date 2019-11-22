import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class Compteur {
    private String nomFicher;
    private int nbMots;
    private int nbMots5;

    public int getNbMots5() {
        return nbMots5;
    }

    public int getNbMots() {
        return nbMots;
    }

    public abstract void addOccurrence(String mot);

    public Compteur(String fichierTexte) throws FileNotFoundException {
        Scanner scannerLine= new Scanner(new File(fichierTexte));
        scannerLine.useDelimiter("\n");
        while(scannerLine.hasNextLine()){
            String ligne = scannerLine.nextLine();
            ligne = ligne.toLowerCase();
            ligne = ligne.replaceAll("[. , ( ) ... \" ' -]", " ");
            ligne.split("\\s | \\( | \\) | \" | \\[ | \' | ] | \t | , | \\. | ; | : | / | '\' | < | > | \\? | ! | ... | \n");
            Scanner scannerMot= new Scanner(ligne);
            while(scannerMot.hasNext()){
                String monMot = scannerMot.next();
                nbMots ++;
                if (monMot.length() >= 5){
                    nbMots5++;

                    addOccurrence(monMot);
                }
            }
            scannerMot.close();

        }
        scannerLine.close();
    };


}
