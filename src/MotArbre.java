public class MotArbre extends Mot {
    private MotArbre gauche;
    private MotArbre droite;

    public MotArbre(String m, MotArbre gauche, MotArbre droite) {
        super(m);
        this.gauche = gauche;
        this.droite = droite;
    }

    public MotArbre(String mot){
        super(mot);
        gauche = null;
        droite = null;
    }

    public  int cle(){
        return getMot().hashCode();
    }

    public MotArbre getGauche() {
        return gauche;
    }

    public void setGauche(MotArbre gauche) {
        this.gauche = gauche;
    }

    public MotArbre getDroite() {
        return droite;
    }

    public void setDroite(MotArbre droite) {
        this.droite = droite;
    }
}
