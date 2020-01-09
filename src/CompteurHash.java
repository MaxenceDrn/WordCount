import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompteurHash extends Compteur {
    private MotChaine[] elements;
    private ArrayList<MotChaine> tabTop10;
    public final int TAILLE = 1000000;

    public CompteurHash(String fichierTexte) throws FileNotFoundException {
        super(fichierTexte);
    }

    @Override
    public void addOccurrence(String mot) {
        if(elements == null){ // Si le tableau elements est vide (première itération)
            elements = new MotChaine[TAILLE];
            elements[index(mot)] = new MotChaine(mot);
            tabTop10 = new ArrayList<MotChaine>();
            tabTop10.add(elements[index(mot)]);
        } else if(elements[index(mot)] != null){ // Si il y a déjà un mot à l'index ou on doit placer le mot actuel
            if (elements[index(mot)].getMot().equals(mot)){ // Si c'est le bon mot, on lui ajoute une occurrence
                elements[index(mot)].nouvelleOccurrence();
            }
            else if(elements[index(mot)].getNext() != null){ // Si le motChaine a la position a un suivant alors on va lire la chaine pour voir si le mot n'y est pas déjà
                MotChaine tmp = elements[index(mot)];

                while(tmp.getNext() != null){ // Tant qu'il y a un suivant
                    if(tmp.getMot().equals(mot)){ // Si c'est le bon mot on ajoute occurrence et on stop
                        tmp.getNext().nouvelleOccurrence();
                        return;
                    }
                    tmp = tmp.getNext();
                }
                tmp.setNext(new MotChaine(mot)); // Si on ne trouve pas le mot dans le chaine, on l'ajoute en bout de chaine
            }else{ // Si le MotChaine a la position n'a pas de suivant, on ajoute le nouveau mot en suivant.
                elements[index(mot)].setNext(new MotChaine(mot));
            }
        } else{ // Si la case à laquel on veut ajouter le mot est vide alors on l'ajoute
            elements[index(mot)] = new MotChaine(mot);
        }

    }

    public int index(String mot){ // Fonction qui retourne l'index auquel placé le mot en fonction de son HashCode
        int i = mot.hashCode() % TAILLE;
        if(i <0){ // Si le résultat du calcul est négatif on le passe en positif pour que l'index soit valide
            i = i * -1;
        }
        return i;
    }

    // Initialisation du compteur d'occurrence des mots qui va permettre de trier la liste.
    public static Comparator<MotChaine> ComparateurOccurrence = new Comparator<MotChaine>() {
        @Override
        public int compare(MotChaine mot1, MotChaine mot2) {
            return mot1.getCpt() - mot2.getCpt();
        }
    };

    public void top10(){
        for(MotChaine mot : elements){
            if (mot != null){
                if(mot.getCpt() >= tabTop10.get(tabTop10.size()-1).getCpt()){ // Si le mot regardé à plus d'occurrence que le dernier mot du tabTop10
                    tabTop10.add(mot);
                    tabTop10.sort(ComparateurOccurrence);
                    Collections.reverse(tabTop10);

                    if (tabTop10.size() > 10){ // Si la taille du tabTop10 dépasse 10, on ne garde que les 10 mots avec le plus d'occurrences.
                        tabTop10.remove(10);
                    }
                }

                if(mot.getNext()!=null){ // Si le motChaine regardé contient un suivant, on regarde la chaine complète pour examiner les mots.
                    MotChaine tmp = mot;

                    while (tmp.getNext() != null){
                        if (tmp.getNext().getCpt() >= tabTop10.get(tabTop10.size()-1).getCpt()){ // Si le mot regardé à plus d'occurrence que le dernier mot du tabTop10
                            tabTop10.add(tmp.getNext());
                            tabTop10.sort(ComparateurOccurrence);
                            Collections.reverse(tabTop10);

                            if (tabTop10.size() > 10){ // Si la taille du tabTop10 dépasse 10, on ne garde que les 10 mots avec le plus d'occurrences.
                                tabTop10.remove(10);
                            }
                        }
                        tmp = tmp.getNext();
                    }
                }
            }
        }

        for (int i = 0; i<tabTop10.size(); i++){
            System.out.println(tabTop10.get(i).getMot() + " " + tabTop10.get(i).getCpt());
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1){
            System.err.println("Nom de fichier manquant");
        }
        else{
            long debut = System.currentTimeMillis();

            CompteurHash c = new CompteurHash(args[0]);
            System.out.println("Fichier : " + args[0]);
            System.out.println("Nombre de mots : " + c.getNbMots());
            System.out.println("Nombre de morts de taille > 4 : " + c.getNbMots5());
            System.out.println("----------");

            c.top10();
            long res = System.currentTimeMillis() - debut;
            System.out.println("\n ----------------------------------------");
            System.out.println("| Temps d'éxecution du programme: " + res + "ms  ");
            System.out.println(" ----------------------------------------");
        }
    }

}
