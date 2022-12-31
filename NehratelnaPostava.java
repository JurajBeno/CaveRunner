/** 
 * Trieda uklada informacie o danej nehratelnej postave a spusta dane animacie
 * 
 * moznosti buduceho rozsirenia: A* algoritmus na hladanie cesty, rozne druhy NP
 */
public class NehratelnaPostava {
    private int xPoziciaNaMape;
    private int yPoziciaNaMape;
    private int zivot;
    private int poskodenie;
    private Mapa mapa;
    private boolean utoci;
    private AnimaciaNehratelnejPostavy animacia;
    private AkciaNehratelnejPostavy akcia;
    /** 
     * V konstruktore vyrata aj poziciu v pixloch aby sme ich obrazky mohli posuvat
     */
    public NehratelnaPostava(int xPoziciaNaMape, int yPoziciaNaMape, int zivot, int poskodenie, Mapa mapa) {
        this.xPoziciaNaMape = 131;
        this.yPoziciaNaMape = 132;
        this.zivot = zivot;
        this.poskodenie = poskodenie;
        this.mapa = mapa;
        this.utoci = false;
        System.out.println(131 * 8 + " je x na mape: " + 131);
        System.out.println(132 * 8 + " je y na mape: " + 131);
        this.animacia = new AnimaciaNehratelnejPostavy(132 * 8, 132 * 8);
        this.akcia = null;
        //TODO pohyb bude prilepeny na pozadie, cize vzdy ked sa pohne pozadie pohnu sa aj postavy
        //TODO this.animacia = new AnimaciaNehratelnejPostavy();
    }

    public void utoc() {
        this.utoci = true;
        this.animacia.animuj(this.akcia);
        if (this.animacia.getIndexAnimacie() > this.akcia.getNajvacsiIndexAnimacie() && this.utoci) {
            this.animacia.animuj(this.akcia);
            this.akcia = AkciaNehratelnejPostavy.STOJ;
            this.utoci = false;
        }
    }

    public int getIndexAnimacie() {
        return this.animacia.getIndexAnimacie();
    }

    public AkciaNehratelnejPostavy getAkcia() {
        return this.akcia;
    }

    /**
     * posunie postavu po mape o dany @param posun v danom @param smer
     */
    public void posunPostavu(int posun, int[] smer) {
        this.animacia.posunPodobu(posun, smer);
    }

    public boolean jeUtociaci() {
        return this.utoci;
    }

    /** 
     * @return poskodenie ktore dava postava
     */
    public int getPoskodenie() {
        return this.poskodenie;
    }

    /** 
     * Nehratelna postava pride o zivot v hodnote @param poskodenie
     */
    public void uberZivot(int poskodenie) {
        this.zivot -= poskodenie;
        System.out.println(this.zivot);
        if (this.zivot < 1) {
            this.umri();
        }
    }

    /** @return polohu postavy, hodnoty ulozene v poli {y, x} */
    public int[] getPoloha() {
        return new int[]{this.yPoziciaNaMape, this.xPoziciaNaMape};
    }

    private void umri() {
        //todo 
    }
}
