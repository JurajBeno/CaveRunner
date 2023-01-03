
/**
 * Trieda zabezpecuje zobrazenie a zmeny animacii nehratelnej postavy
 */
public class AnimaciaNehratelnejPostavy {
    private int xPoziciaNaMape;
    private int yPoziciaNaMape;
    private Obrazok aktualnaPodoba;
    private int indexAnimacie;
    private AkciaNehratelnejPostavy akcia;
    
    /**
     * 
     * @param x poloha postavy na horizontalnej osi obrazovky 
     * @param y poloha postavy na vertikalnej osi obrazovky
     * (hodnoty parametrov mozu byt zaparne ak nehratelnu postavu na obrazovke este nevidno)
     */
    public AnimaciaNehratelnejPostavy(int x, int y) {
        this.xPoziciaNaMape = x;
        this.yPoziciaNaMape = y;
        this.akcia = AkciaNehratelnejPostavy.STOJ;
        this.aktualnaPodoba = new Obrazok("animacieNehratelnePostavy\\stoj\\stoj0.png");
        this.aktualnaPodoba.zmenPolohu(x, y);
        this.aktualnaPodoba.zobraz();
    }

    /**
     * skryje aktualny obrazok nehratelnej postavy
     */
    public void skry() {
        this.aktualnaPodoba.skry();
    }

    /**
     * @return momentalny index animacie
     */
    public int getIndexAnimacie() {
        return this.indexAnimacie;
    }

    /** 
     * zmeni poziciu animacie (obrazka) na obrazovke
     */
    public void posunPodobu(int posun, int[] smer) {
        this.aktualnaPodoba.zmenPolohu(this.xPoziciaNaMape += posun * smer[1], this.yPoziciaNaMape += posun * smer[0]);
    }

    /** 
     * ak sa akcia zmenila ale presla vsetkymi obrazkami tak sa zmeni inak sa iba posunie obrazok animacie
     */
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
