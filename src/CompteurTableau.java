import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompteurTableau extends Compteur {
    public final int TAILLE_INITIALE = 1000;
    private Mot[] elements;
    private static int nbMots;
    private ArrayList<Mot> tabTop10;

    public CompteurTableau(String fichierTexte) throws FileNotFoundException {
        super(fichierTexte);
    }

    public static int getnbMots() {
        return nbMots;
    }

    public static void setnbMots(int nbMots) {
        CompteurTableau.nbMots = nbMots;
    }

    public void addOccurrence(String mot){
        if(elements == null){ // ==> Si le tableau n'est pas initialisé.
            this.elements = new Mot[TAILLE_INITIALE];
            nbMots = 0;
        }
        if(getnbMots() < elements.length){ // ==> Si il y a encore de la place dans le tableau
            if (getnbMots() == 0){ // ==> Cas où le tableau est initialisé mais vide
                Mot new_mot = new Mot(mot);
                elements[getnbMots()] = new_mot;
                setnbMots(getnbMots() + 1);
            }else{ // ==> sinon, on cherche le mot dans le tableau
                for(int i=0; i<getnbMots(); i++){
                    if(elements[i].getMot().equals(mot)){ // Si on trouve le mot !
                        elements[i].nouvelleOccurrence();
                        return;
                    }
                }
                Mot new_mot = new Mot(mot);
                elements[getnbMots()] = new_mot;
                setnbMots(getnbMots() + 1);
            }
        }else{ // ==> Si le tableau est plein, on double sa taille.
            Mot[] tmp = elements;
            elements = new Mot[tmp.length*2];
            for (int i=0; i<tmp.length; i++){
                elements[i] = tmp[i];
            }
            elements[nbMots] = new Mot(mot);
            setnbMots(getnbMots()+1);
        }
    }

    public static Comparator<Mot> ComparateurOccurrence = new Comparator<Mot>() {
        @Override
        public int compare(Mot mot1, Mot mot2) {
            return mot1.getCpt() - mot2.getCpt();
        }
    };

    public void top10(){
        tabTop10 = new ArrayList<Mot>();
        tabTop10.add(elements[0]);


        for(Mot mot : elements){
            if (mot == null){
                break;
            }
            if (mot.getCpt() >= tabTop10.get(tabTop10.size()-1).getCpt()){ // Si le mot regardé à plus d'occurrence que le dernier mot du tabTop10
                tabTop10.add(mot);
                tabTop10.sort(ComparateurOccurrence);
                Collections.reverse(tabTop10);

                if(tabTop10.size() > 10){ // Si la taille du tabTop10 dépasse 10, on ne garde que les 10 mots avec le plus d'occurrences.
                    tabTop10.remove(10);
                }
            }
        }

        for(int i = 0; i < tabTop10.size(); i++){
            System.out.println(tabTop10.get(i).getMot() + " " + tabTop10.get(i).getCpt());
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1){
            System.err.println("Nom de fichier manquant");
        }
        else{
            long debut = System.currentTimeMillis();

            CompteurTableau c = new CompteurTableau(args[0]);
            System.out.println("Fichier : " + args[0]);
            System.out.println("Nombre de mots : " + c.getNbMots());
            System.out.println("Nombre de morts de taille > 4 : " + c.getNbMots5());
            System.out.println("----------");

            c.top10();
            long res = System.currentTimeMillis() - debut;
            System.out.println("\n --------------------------------------");
            System.out.println("| Temps d'éxecution du programme: " + res + "ms ");
            System.out.println(" --------------------------------------");
        }
    }


}
