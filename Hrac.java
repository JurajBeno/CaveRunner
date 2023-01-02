
public class Hrac {
    private int[] smerOtocenia;
    private int yPoziciaHracaNaMape;
    private int xPoziciaHracaNaMape;

    private int[] smerPohybu;
    private AkciaHraca prebiehajucaAkcia;

    private int rycholostHraca;
    private int poskodenie;
    private int zivot;

    /** 
     * @param x pozicia na horizontalnej osi
     * @param y pozicia na vertikalnej osi
     */
    public Hrac(int x, int y, int rycholostHraca, int zivot, int poskodenie) {
        this.smerOtocenia = new int[] {0, 0};
        this.xPoziciaHracaNaMape = x;
        this.yPoziciaHracaNaMape = y;
        this.rycholostHraca = rycholostHraca;
        this.smerPohybu = new int[] {0, 0}; 
        this.prebiehajucaAkcia = AkciaHraca.STOJ;
        this.zivot = zivot;
        this.poskodenie = poskodenie;
    }

    /** 
     * Hracovi uberie zivot
     */
    public void dostanPoskodenie(int poskodenie) {
        this.zivot -= poskodenie;
        System.out.println(this.zivot);
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
        return new int[] {this.yPoziciaHracaNaMape, this.xPoziciaHracaNaMape};
    }

    /**
     * Zmneni poziciu hraca na mape
     */
    public void zmenPoziciuHracaNaMape() {
        this.yPoziciaHracaNaMape += this.smerPohybu[0];
        this.xPoziciaHracaNaMape += this.smerPohybu[1];
    
    }

    /** 
     * @return boolovsku hodnotu true ak akcia je utok
     */
    public boolean getUtoci() {
        return this.prebiehajucaAkcia == AkciaHraca.UTOC_VLAVO || this.prebiehajucaAkcia == AkciaHraca.UTOC_VPRAVO;
    }

    /**
     * @return boolovsku hodnotu ak akcia je pohyb
     */
    public boolean getHybeSa() {
        return this.prebiehajucaAkcia.getSmer()[0] != 0 || this.prebiehajucaAkcia.getSmer()[1] != 0;
    }

    /**
     * @return rychlost hraca
     */
    public int getRychlostHraca() {
        return this.rycholostHraca;
    }

    /** 
     * @return momentalnu akciu
     */
    public AkciaHraca getPrebiehajucaAkcia() {
        return this.prebiehajucaAkcia;
    }

    /**
     * @return boolovsku hodnotu ak je hrac mrtvy
     */
    public boolean jeMrtvy() {
        return this.prebiehajucaAkcia == AkciaHraca.SMRT_VLAVO || this.prebiehajucaAkcia == AkciaHraca.SMRT_VPRAVO;
    }

    /** 
     * @param momentalnaCinnost ktoru bude hrac vykonavat
     */
    public void zmenAkciu(AkciaHraca momentalnaCinnost) {
        if (this.jeMrtvy()) {

            return;
        }
        if (momentalnaCinnost.getSmer()[0] != 0 || momentalnaCinnost.getSmer()[1] != 0) {
            this.smerOtocenia = momentalnaCinnost.getSmer();
        }
        this.smerPohybu = momentalnaCinnost.getSmer();
        this.prebiehajucaAkcia = momentalnaCinnost;
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
