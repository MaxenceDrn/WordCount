public class MotChaine extends Mot {
    private MotChaine next;
    private MotChaine prec;

    public MotChaine(String m, MotChaine next) {
        super(m);
        this.next = next;
    }

    public MotChaine getPrec() {
        return prec;
    }

    public void setPrec(MotChaine prec) {
        this.prec = prec;
    }


    public MotChaine(String mot){
        this(mot, null);
    }

    public MotChaine getNext() {
        return next;
    }

    public void setNext(MotChaine next) {
        this.next = next;
    }


    @Override
    public String toString() {
        if (getNext() != null){
            return super.toString() + getNext().getMot();
        }else{
            return super.toString() + "null";
        }
    }
}
