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

    public void setMot(String mot) {
        this.mot = mot;
    }

    public int getCpt() {
        return cpt;
    }

    public void setCpt(int cpt) {
        this.cpt = cpt;
    }

    @Override
    public String toString() {
        return getMot() + " " + getOccurrence() + " ";
    }
}
