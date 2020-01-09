import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompteurArbre extends Compteur {
    private MotArbre elements;
    private ArrayList<MotArbre> tabTop10;

    public CompteurArbre(String fichierTexte) throws FileNotFoundException {
        super(fichierTexte);
    }

    public MotArbre getElements() {
        return elements;
    }

    public void setElements(MotArbre elements) {
        this.elements = elements;
    }

    @Override
    public void addOccurrence(String mot) {
        if(getElements() == null){
            setElements(new MotArbre(mot));
            tabTop10 = new ArrayList<MotArbre>();
            tabTop10.add(getElements());
        }else{
            MotArbre tmp = rechercherMot(mot, getElements()); // => Dans le cas ou le mot est trouvé, tmp pointera vers le MotArbre équivalent.

            if (tmp != null){ // => Quand le mot est trouvé
                tmp.nouvelleOccurrence();
            }else{
                ajouterMot(mot, getElements());
            }
        }
    }

    public MotArbre rechercherMot(String mot, MotArbre racine){ // => Permet de voir si le mot est déjà présent dans l'arbre
        if(racine != null){
            if(mot.hashCode() == racine.cle()){ // => Quand on le trouve on le return
                return racine;
            }else if(mot.hashCode() >= racine.cle()){ // ==> Si le mot recherché a un plus grand hashCode, on part à droite.
                return rechercherMot(mot, racine.getDroite());
            }else if(mot.hashCode() < racine.cle()){ // ==> Sinon, on  part à gauche
                return rechercherMot(mot, racine.getGauche());
            }
        }
        return null; // Dans le cas ou on ne trouve rien, on renvoit null.
    }

    public void ajouterMot(String mot, MotArbre racine){
        if(mot.hashCode() >= racine.cle()){ // Si le mot a un plus grand hashCode que le mot pointé on va essayer de le placer à sa gauche
            if (racine.getDroite() == null){ // Si me mot pointé n'a pas déjà un fils droit, on l'ajoute en fils droit
                racine.setDroite(new MotArbre(mot));
            }else{ // Sinon, on pointe le fils droit et on recommence.
                ajouterMot(mot, racine.getDroite());
            }
        }else{ // Si le mot a un plus petit hashCode que le mot pointé on va essayer de le placer à sa gauche
            if (racine.getGauche() == null){ // Si me mot pointé n'a pas déjà un fils gauche, on l'ajoute en fils gauche
                racine.setGauche(new MotArbre(mot));
            }else{ // Sinon, on pointe le fils gauche et on recommence.
                ajouterMot(mot, racine.getGauche());
            }
        }
    }

    // Initialisation du compteur d'occurrence des mots qui va permettre de trier la liste.
    public static Comparator<MotArbre> ComparateurOccurrence = new Comparator<MotArbre>() {
        @Override
        public int compare(MotArbre mot1, MotArbre mot2) {
            return mot1.getCpt() - mot2.getCpt();
        }
    };


    public void top10(MotArbre racine){
        if (racine.getCpt() >= tabTop10.get(tabTop10.size()-1).getCpt()){ // Si le mot regardé à plus d'occurrence que le dernier mot du tabTop10
            tabTop10.add(racine);
            tabTop10.sort(ComparateurOccurrence);
            Collections.reverse(tabTop10);

            if (tabTop10.size() > 10){ // Si la taille du tabTop10 dépasse 10, on ne garde que les 10 mots avec le plus d'occurrences.
                tabTop10.remove(10);
            }
        }
        if (racine.getGauche() != null){
            top10(racine.getGauche());
        }
        if(racine.getDroite() != null){
            top10(racine.getDroite());
        }
    }

    public void afficherTop10(){
        top10(getElements());

        for(int i = 0; i<tabTop10.size(); i++){
            System.out.println(tabTop10.get(i).getMot() + " " + tabTop10.get(i).getCpt());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1){
            System.err.println("Nom de fichier manquant");
        }
        else{
            long debut = System.currentTimeMillis();

            CompteurArbre c = new CompteurArbre(args[0]);
            System.out.println("Fichier : " + args[0]);
            System.out.println("Nombre de mots : " + c.getNbMots());
            System.out.println("Nombre de morts de taille > 4 : " + c.getNbMots5());
            System.out.println("----------");

            c.afficherTop10();
            long res = System.currentTimeMillis() - debut;
            System.out.println("\n --------------------------------------");
            System.out.println("| Temps d'éxecution du programme: " + res + "ms  ");
            System.out.println(" --------------------------------------");
        }
    }

}
