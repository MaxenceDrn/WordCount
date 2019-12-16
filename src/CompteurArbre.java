import java.io.FileNotFoundException;

public class CompteurArbre extends Compteur{
    private MotArbre racine;


    public CompteurArbre(String fichierTexte) throws FileNotFoundException {
        super(fichierTexte);
    }


    public MotArbre getRacine() {
        return racine;
    }

    public void setRacine(MotArbre racine) {
        this.racine = racine;
    }

    @Override
    public void addOccurrence(String mot) {
        if(getRacine() == null){
            racine = new MotArbre(mot);
        }else{
            
        }
    }
}
