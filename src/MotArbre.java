public class MotArbre extends Mot {
    private MotArbre gauche;
    private MotArbre droit;

    public MotArbre(String m, MotArbre gauche, MotArbre droit) {
        super(m);
        this.gauche = gauche;
        this.droit = droit;
    }

    public MotArbre(String m){
        this(m, null,null);
    }

    public MotArbre getGauche() {
        return gauche;
    }

    public void setGauche(MotArbre gauche) {
        this.gauche = gauche;
    }

    public MotArbre getDroit() {
        return droit;
    }

    public void setDroit(MotArbre droit) {
        this.droit = droit;
    }
}
