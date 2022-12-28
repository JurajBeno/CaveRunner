
public class Hrac {
    private int[] poziciaHraca;

    private boolean utok;

    private int[] smerPohybu;
    private CinnostHraca prebiehajucaCinnost;

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
        this.prebiehajucaCinnost = CinnostHraca.STOJ;
        this.zivot = zivot;
        this.poskodenie = poskodenie;
    }

    /**
     * Vrati poziciu hraca na mape.
     */
    public int[] getSmerPohybu() {
        return this.smerPohybu;
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
     * Vrati boolovsku hodnotu ak cinnost je utok.
     */
    public boolean getUtoci() {
        return this.getPrebiehajucaCinnost() == CinnostHraca.UTOC_VLAVO || this.getPrebiehajucaCinnost() == CinnostHraca.UTOC_VPRAVO;
    }

    /**
     * Vrati boolovsku hodnotu ak cinnost je pohyb.
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
     * Vrati momentalnu cinnost.
     */
    public CinnostHraca getPrebiehajucaCinnost() {
        return this.prebiehajucaCinnost;
    }

    /** 
     * @param CinnostHraca ktoru bude hrac vykonavat.
     */
    public void zmentCinnost(CinnostHraca momentalnaCinnost) {
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
