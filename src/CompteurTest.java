import java.io.FileNotFoundException;

public class CompteurTest extends Compteur {
    public CompteurTest(String fichierTexte) throws FileNotFoundException {
        super(fichierTexte);
    }

    public void addOccurrence(String mot) {
        System.out.println(mot);
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 1){
            System.err.println("Nom de fichier manquant");
        }
        else{
            CompteurTest c = new CompteurTest(args[0]);
        }
    }
}
