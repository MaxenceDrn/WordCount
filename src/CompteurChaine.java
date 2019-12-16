import java.io.IOException;

public class CompteurChaine extends Compteur {
    private MotChaine elements;
    private int nbElements;

    @Override
    public void addOccurrence(String mot) {
        if(elements==null){
            elements= new MotChaine(mot);
            nbElements=1;
            return;
        }
        MotChaine j=elements;
        MotChaine prec=elements;
        while (j!=null){
            if (j.getMot().equals(mot)){
                j.nouvelleOccurrence();
                return;
            }
            prec=j;
            j=j.getNext();
        }
        prec.setNext(new MotChaine(mot));
        nbElements++;
    }

    public void tri(){
        for( int i=0;i<nbElements;i++){
            int idx=0;
            MotChaine j=elements;
            MotChaine prec=elements;
            MotChaine prec2=elements;
            MotChaine v=elements;
            while (idx!=i){
                prec2=j;
                j=j.getNext();
                v=j;
                idx++;
            }
            MotChaine max=j;
            while (j!=null){
                if (j.getCpt()>max.getCpt()){
                    max=j;
                }
                j=j.getNext();
            }
            j=elements;
            while (j!=null){
                if (j.getMot().equals(max.getMot())){
                    break;
                }
                prec=j;
                j=j.getNext();
            }
            MotChaine swp=max;
            MotChaine n=max.getNext();
            if (idx==0){
                max=elements;
                elements=swp;
                elements.setNext(max.getNext());
                max.setNext(n);
                prec.setNext(max);
            }
            else{
                max=v;
                v=swp;
                v.setNext(max.getNext());
                max.setNext(n);
                prec.setNext(max);
                prec2.setNext(v);
            }


        }
    }

    public void afficher(){
        MotChaine j=elements;
        while (j!=null){
            System.out.println(j.getCpt()+" "+j.getMot());
            j=j.getNext();
        }
    }

    public void afficherTop10(){
        MotChaine j=elements;
        int cpt = 0;
        while (j!=null && cpt<10){
            System.out.println(j.getCpt()+" "+j.getMot());
            j=j.getNext();
            cpt++;
        }
    }

    public CompteurChaine(String fichierTexte) throws IOException {
        super(fichierTexte);
    }

    public static void main(String[] args){
        try {
            if (args.length < 1) {
                System.err.println("nom de fichier manquant");
            }
            else{
                CompteurChaine c= new CompteurChaine(args[0]);
                c.tri();
                System.out.println("Fichier : "+args[0]);
                System.out.println("Nombre de mots : "+c.getNbMots());
                System.out.println("Nombre de mots de taille > 4 : "+c.getNbMots5());
                System.out.println("----------");
                c.afficherTop10();

            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }
}
