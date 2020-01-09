import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompteurChaine extends Compteur{
    private MotChaine elements;
    private ArrayList<Mot> tabTop10;

    public CompteurChaine(String fichierTexte) throws FileNotFoundException {
        super(fichierTexte);
    }

    public MotChaine getElements() {
        return elements;
    }

    @Override
    public void addOccurrence(String mot) {
        if(elements == null){ // ==> Cas ou c'est le premier élément de la liste chainée
            elements = new MotChaine(mot);
            return;
        }
        MotChaine tmp = getElements();
        MotChaine precedent = getElements();

        while(tmp != null){ // ==> On cherche si le mot n'est pas déjà dans la liste chainée. Si non, on l'ajoute.
            if(tmp.getMot().equals(mot)){
                tmp.nouvelleOccurrence();
                return;
            }
            precedent = tmp;
            tmp = tmp.getNext();
        }

        precedent.setNext(new MotChaine(mot));
    }

    // Initialisation du compteur d'occurrence des mots qui va permettre de trier la liste.
    public static Comparator<Mot> ComparateurOccurrence = new Comparator<Mot>() {
        @Override
        public int compare(Mot mot1, Mot mot2) {
            return mot1.getCpt() - mot2.getCpt();
        }
    };

    public void top10(){
        tabTop10 = new ArrayList<Mot>();
        tabTop10.add(getElements());

        MotChaine tmp = getElements();

        while(tmp != null){
            if (tmp.getCpt() >= tabTop10.get(tabTop10.size()-1).getCpt()){ // Si le mot regardé à plus d'occurrence que le dernier mot du tabTop10
                tabTop10.add(tmp);
                tabTop10.sort(ComparateurOccurrence);
                Collections.reverse(tabTop10);

                if(tabTop10.size() > 10){ // Si la taille du tabTop10 dépasse 10, on ne garde que les 10 mots avec le plus d'occurrences.
                    tabTop10.remove(10);
                }
            }
            tmp = tmp.getNext();
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

            CompteurChaine c = new CompteurChaine(args[0]);
            System.out.println("Fichier : " + args[0]);
            System.out.println("Nombre de mots : " + c.getNbMots());
            System.out.println("Nombre de morts de taille > 4 : " + c.getNbMots5());
            System.out.println("----------");

            c.top10();
            long res = System.currentTimeMillis() - debut;
            System.out.println("\n ----------------------------------------");
            System.out.println("| Temps d'éxecution du programme: " + res + "ms   ");
            System.out.println(" ----------------------------------------");
        }
    }

}
