import java.io.FileNotFoundException;
import java.util.Arrays;

public class CompteurTableau extends Compteur {
    public final int TAILLE_INITIALE = 100;
    private Mot[] elements;
    private static int cptMots;

    public CompteurTableau(String fichierTexte) throws FileNotFoundException {
        super(fichierTexte);
    }

    public static int getCptMots() {
        return cptMots;
    }

    public static void setCptMots(int cptMots) {
        CompteurTableau.cptMots = cptMots;
    }

    public void addOccurrence(String mot){
        if(elements == null){
            this.elements = new Mot[TAILLE_INITIALE];
            cptMots = 0;
        }
        if(getCptMots() < elements.length){
            if (getCptMots() == 0){
                Mot new_mot = new Mot(mot);
                elements[getCptMots()] = new_mot;
                setCptMots(getCptMots() + 1);
            }else{
                boolean ajouté = false;
                for(int i=0; i<getCptMots(); i++){
                    if(elements[i].getMot().equals(mot)){
                        elements[i].nouvelleOccurrence();
                        ajouté = true;
                    }
                }
                if(ajouté == false) {
                    Mot new_mot = new Mot(mot);
                    elements[getCptMots()] = new_mot;
                    setCptMots(getCptMots() + 1);
                }
            }
        }else{
            Mot[] tmp = elements;
            elements = new Mot[tmp.length*2];
            for (int i=0; i<tmp.length; i++){
                elements[i] = tmp[i];
            }
        }
    }

    public void swap(int a, int b){
        Mot tmp = new Mot(elements[a].getMot(), elements[a].getOccurrence());
        elements[a] = new Mot(elements[b].getMot(), elements[b].getOccurrence());
        elements[b] = tmp;
    }

    public void triOccurencesDESC(){
        for(int i = 1; i<getCptMots(); i++) {
            int cpt = i;
            while (cpt>0 && elements[cpt].getOccurrence() > elements[cpt - 1].getOccurrence()) {
                swap(cpt, cpt-1);
                cpt--;
            }
        }
    }

    public void afficherTOP10(){
        triOccurencesDESC();
        for(int i=0; i<10; i++){
            System.out.println(elements[i].getMot() + " " + elements[i].getOccurrence());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1){
            System.err.println("Nom de fichier manquant");
        }
        else{
            CompteurTableau c = new CompteurTableau(args[0]);
            System.out.println("Fichier : " + args[0]);
            System.out.println("Nombre de mots : " + c.getNbMots());
            System.out.println("Nombre de morts de taille > 4 : " + c.getNbMots5());
            System.out.println("----------");

            c.afficherTOP10();
        }
    }


}
