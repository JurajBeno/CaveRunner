
/**
 * Write a description of class AnimaciaNehratelnejPostavy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AnimaciaNehratelnejPostavy {
    private int xPoziciaNaMape;
    private int yPoziciaNaMape;
    private Obrazok aktualnaPodoba;
    private int indexAnimacie;
    private AkciaNehratelnejPostavy akcia;
    
    public AnimaciaNehratelnejPostavy(int x, int y) {
        this.xPoziciaNaMape = x;
        this.yPoziciaNaMape = y;
        this.akcia = AkciaNehratelnejPostavy.STOJ;
        this.aktualnaPodoba = new Obrazok("animacieNehratelnePostavy\\stoj\\stoj.png");
        this.aktualnaPodoba.zmenPolohu(x, y);
        this.aktualnaPodoba.zobraz();
    }

    public int getIndexAnimacie() {
        return this.indexAnimacie;
    }

    public void posunPodobu(int posun, int[] smer) {
        this.aktualnaPodoba.zmenPolohu(this.xPoziciaNaMape += posun * smer[1], this.yPoziciaNaMape += posun * smer[0]);
    }

    public void animuj(AkciaNehratelnejPostavy novaAkcia) {
        if (this.akcia != novaAkcia || this.indexAnimacie > this.akcia.getNajvacsiIndexAnimacie()) {
            this.indexAnimacie = 0;
            this.akcia = novaAkcia;
        }
        this.aktualnaPodoba.zmenObrazok(String.format("animacieNehratelnePostavy\\%s%s\\%s%s%d.png",
        this.akcia.getNazovAkcie(), this.akcia.getOtocenieAnimacie(),
        this.akcia.getNazovAkcie(), this.akcia.getOtocenieAnimacie(), this.indexAnimacie));
        this.indexAnimacie++;
    }
}
