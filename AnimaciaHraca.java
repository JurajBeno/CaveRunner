
/** Trieda zabezpecuje animovanie akcii */
public class AnimaciaHraca {
    private Obrazok aktualnaPodoba;
    private int indexAnimacie;
    private AkciaHraca akcia;

    /**
     * @param x stred sirky obrazovky
     * @param y stred vysky obrazovky
     */
    public AnimaciaHraca(int x, int y) {
        this.akcia = AkciaHraca.STOJ;
        this.aktualnaPodoba = new Obrazok(String.format("animacie\\chodenie%s\\chodenie%s0.png", this.akcia.getOtocenieAnimacie(), this.akcia.getOtocenieAnimacie()));
        this.aktualnaPodoba.zmenPolohu(x + 3, y);
        this.aktualnaPodoba.zobraz();
    }

    /**
     * Vrati atribut indexAnimacie
     */
    public int getIndexAnimacie() {
        return this.indexAnimacie;
    }

    public void animuj(AkciaHraca novaAkcia) {
        if (this.akcia != novaAkcia || this.indexAnimacie > this.akcia.getNajvacsiIndexAnimacie()) {
            this.indexAnimacie = 0;
            this.akcia = novaAkcia;
        }
        this.aktualnaPodoba.zmenObrazok(String.format("animacie\\%s%s\\%s%s%d.png",
        this.akcia.getNazovAkcie(), this.akcia.getOtocenieAnimacie(),
        this.akcia.getNazovAkcie(), this.akcia.getOtocenieAnimacie(), this.indexAnimacie));
        this.indexAnimacie++;
    }
}
