
public class Hrac {
    private int[] poziciaHraca;

    private boolean utok;

    private int[] smerPohybu;
    private Cinnost prebiehajucaCinnost;

    private int rycholostHraca;
    private int poskodenie;
    private int zivot;

    /** 
     * @param x pozicia na horizontalnej osi
     * @param y pozicia na vertikalnej osi
     */
    public Hrac(int x, int y, int rycholostHraca, int zivot, int poskodenie) {
        this.poziciaHraca = new int[] {y, x};
        this.rycholostHraca = rycholostHraca;
        this.smerPohybu = new int[] {0, 0}; 
        this.prebiehajucaCinnost = Cinnost.STOJ;
        this.zivot = zivot;
        this.poskodenie = poskodenie;
    }

    /**
     * Vrati poziciu hraca na mape.
     */
    public int[] getPoziciaHracaNaMape() {
        return this.poziciaHraca;
    }

    /**
     * Zmneni poziciu hraca na mape.
     */
    public void zmenPoziciuHracaNaMape() {
        this.poziciaHraca[0] += this.smerPohybu[0];
        this.poziciaHraca[1] += this.smerPohybu[1];
    
    }

    /**
     * Vrati boolean hodnotu ak je cinnost pohyb
     */
    public boolean getHybeSa() {
        return this.prebiehajucaCinnost.getSmer()[0] != 0 || this.prebiehajucaCinnost.getSmer()[1] != 0;
    }

    /**
     * Vrati rychlost hraca.
     */
    public int getRychlostHraca() {
        return this.rycholostHraca;
    }

    /**
     * Vrati boolovsku hodnotu ci prebieha animacia utoku.
     */
    public boolean getUtok() {
        return this.utok;
    }

    /**
     * Nastavi utok.
     */
    public void setUtok(boolean utok) {
        this.utok = utok;
    }

    /** 
     * Vrati momentalnu cinnost.
     */
    public Cinnost getPrebiehajucaCinnost() {
        return this.prebiehajucaCinnost;
    }

    /** 
     * @param Cinnost ktoru bude hrac vykonavat.
     */
    public void zmentCinnost(Cinnost momentalnaCinnost) {
        this.smerPohybu = momentalnaCinnost.getSmer();
        this.prebiehajucaCinnost = momentalnaCinnost;
    }

    /** 
     * Vrati poskodenie hraca.
     */
    public int getPoskodenie() {
        return this.poskodenie;
    }

    /** 
     * Vrati hodnotu zivota hraca.
     */
    public int getZivot() {
        return this.zivot;
    }

}
