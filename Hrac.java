
public class Hrac {
    private int[] smerOtocenia;
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
        this.smerOtocenia = new int[] {0, 0};
        this.poziciaHraca = new int[] {y, x};
        this.rycholostHraca = rycholostHraca;
        this.smerPohybu = new int[] {0, 0}; 
        this.prebiehajucaCinnost = CinnostHraca.STOJ;
        this.zivot = zivot;
        this.poskodenie = poskodenie;
    }

    /** 
     * Hracovi uberie zivot
     */
    public void dostanPoskodenie(int poskodenie) {
        this.zivot -= poskodenie;
    }

    /**
     * @return smer ktorym je hrac otoceny aj ked stoji
     */
    public int[] getSmerOtocenia() {
        return this.smerOtocenia;
    }

    /**
     * @return smer pohybu hraca
     */
    public int[] getSmerPohybu() {
        return this.smerPohybu;
    }

    /**
     * @return poziciu hraca na mape
     */
    public int[] getPoziciaHracaNaMape() {
        return this.poziciaHraca;
    }

    /**
     * Zmneni poziciu hraca na mape
     */
    public void zmenPoziciuHracaNaMape() {
        this.poziciaHraca[0] += this.smerPohybu[0];
        this.poziciaHraca[1] += this.smerPohybu[1];
    
    }

    /** 
     * @return boolovsku hodnotu true ak cinnost je utok
     */
    public boolean getUtoci() {
        return this.prebiehajucaCinnost == CinnostHraca.UTOC_VLAVO || this.prebiehajucaCinnost == CinnostHraca.UTOC_VPRAVO;
    }

    /**
     * @return boolovsku hodnotu ak cinnost je pohyb
     */
    public boolean getHybeSa() {
        return this.prebiehajucaCinnost.getSmer()[0] != 0 || this.prebiehajucaCinnost.getSmer()[1] != 0;
    }

    /**
     * @return rychlost hraca
     */
    public int getRychlostHraca() {
        return this.rycholostHraca;
    }

    /** 
     * @return momentalnu cinnost
     */
    public CinnostHraca getPrebiehajucaCinnost() {
        return this.prebiehajucaCinnost;
    }

    /**
     * @return boolovsku hodnotu ak je hrac mrtvy
     */
    public boolean jeMrtvy() {
        return this.prebiehajucaCinnost == CinnostHraca.SMRT_VLAVO || this.prebiehajucaCinnost == CinnostHraca.SMRT_VPRAVO;
    }

    /** 
     * @param momentalnaCinnost ktoru bude hrac vykonavat
     */
    public void zmenCinnost(CinnostHraca momentalnaCinnost) {
        if (this.jeMrtvy()) {

            return;
        }
        if (momentalnaCinnost.getSmer()[0] != 0 || momentalnaCinnost.getSmer()[1] != 0) {
            this.smerOtocenia = momentalnaCinnost.getSmer();
        }
        this.smerPohybu = momentalnaCinnost.getSmer();
        this.prebiehajucaCinnost = momentalnaCinnost;
    }

    /** 
     * @return poskodenie hraca
     */
    public int getPoskodenie() {
        return this.poskodenie;
    }

    /** 
     * @return hodnotu zivota hraca
     */
    public int getZivot() {
        return this.zivot;
    }

}
