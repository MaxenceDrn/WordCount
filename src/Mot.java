public class Mot {
    private String mot;
    private int cpt;

    public Mot(String m){
        mot = m;
        cpt = 1;
    }

    public Mot(String m, int nbOcc){
        mot = m;
        cpt = nbOcc;
    }

    public void nouvelleOccurrence(){
        this.cpt++;
    }

    public int getOccurrence(){
        return this.cpt;
    }

    public String getMot(){
        return this.mot;
    }
}
