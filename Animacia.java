
public class Animacia {
    private Obrazok aktualnaPodoba;
    private int indexAnimacie;
    private CinnostHraca cinnost;

    /** Trieda zabezpecuje animovanie cinnosti */
    public Animacia(int x, int y) {
        this.cinnost = CinnostHraca.STOJ;
        this.aktualnaPodoba = new Obrazok(String.format("animacie\\chodenie%s\\chodenie%s0.png", this.cinnost.getOtocenieAnimacie(), this.cinnost.getOtocenieAnimacie()));
        this.aktualnaPodoba.zmenPolohu(x + 3, y);
        this.aktualnaPodoba.zobraz();
    }

    /** Vrati atribut indexAnimacie */
    public int getIndexAnimacie() {
        return this.indexAnimacie;
    }
    
    public void animuj(CinnostHraca novaCinnost) {
        if (this.cinnost != novaCinnost || this.indexAnimacie > this.cinnost.getNajvacsiIndexAnimacie()) {
            this.indexAnimacie = 0;
            this.cinnost = novaCinnost;
        }
        this.aktualnaPodoba.zmenObrazok(String.format("animacie\\%s%s\\%s%s%d.png",
        this.cinnost.getNazovCinoosti(), this.cinnost.getOtocenieAnimacie(),
        this.cinnost.getNazovCinoosti(), this.cinnost.getOtocenieAnimacie(), this.indexAnimacie));
        this.indexAnimacie++;
    }

}