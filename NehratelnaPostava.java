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
    private boolean utoci;
    private AnimaciaNehratelnejPostavy animacia;
    private AkciaNehratelnejPostavy akcia;
    private boolean mrtvy;
    /** 
     * V konstruktore vyrata aj poziciu v pixeloch pre triedu AnimacieNehratelnejPostavy
     */
    public NehratelnaPostava(int xPoziciaNaMape, int yPoziciaNaMape, int zivot, int poskodenie, Mapa mapa) {
        this.xPoziciaNaMape = xPoziciaNaMape;
        this.yPoziciaNaMape = yPoziciaNaMape;
        this.zivot = zivot;
        this.poskodenie = poskodenie;
        this.utoci = false;

        this.animacia = new AnimaciaNehratelnejPostavy(this.transformujSuradnice(this.xPoziciaNaMape, 426, 131), this.transformujSuradnice(this.yPoziciaNaMape, 240, 132));
        this.akcia = null;
        this.mrtvy = false;
    }

    private int transformujSuradnice(int suradnica, int vzdialenostStredu, int poziciaHraca) {
        if (suradnica > poziciaHraca) {
            return (suradnica - poziciaHraca) * 8 + vzdialenostStredu;
        } else if (suradnica < poziciaHraca) {
            return -((poziciaHraca - suradnica) * 8 - vzdialenostStredu);
        }
        return vzdialenostStredu;
    }

    /**
     * nastavi @param novaAkcia ako akciu danej nehratelnej postavy
     */
    public void setAkcia(AkciaNehratelnejPostavy novaAkcia) {
        this.akcia = novaAkcia;
    }

    /**
     * posunie momentalnu aknimaciu podla daniej akcie
     */
    public void zmenaAnimacie() {
        this.animacia.animuj(this.akcia);
    }

    /**
     * zmeni akciu a spusti animaciu,
     * ak je ale uz posledna cast animacie utoku posle poskodenie
     */
    public void utoc() {
        this.utoci = true;
        this.animacia.animuj(this.akcia);
        if (this.animacia.getIndexAnimacie() > this.akcia.getNajvacsiIndexAnimacie() && this.utoci) {
            this.animacia.animuj(this.akcia);
            this.akcia = AkciaNehratelnejPostavy.STOJ;
            this.utoci = false;
        }
    }

    /**
     * @return vrati index animacie
     */
    public int getIndexAnimacie() {
        return this.animacia.getIndexAnimacie();
    }

    /**
     * @return momentalnu akciu danej nehratelnej postavy
     */
    public AkciaNehratelnejPostavy getAkcia() {
        return this.akcia;
    }

    /**
     * posunie postavu po mape o dany @param posun v danom @param smer
     */
    public void posunPostavu(int posun, int[] smer) {
        this.animacia.posunPodobu(posun, smer);
    }

    /**
     * @return boolovsku hodnotu ci je nehratelna postava ziva alebo nie
     */
    public boolean jeMrtvy() {
        return this.mrtvy;
    }

    /**
     * @return boolovsku hodnotu ci je nehratelna postava c utoku alebo nie
     */
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
        if (this.zivot < 1) {
            this.animacia.skry();
            this.mrtvy = true;
        }
    }

    /**
     * @return polohu postavy, hodnoty ulozene v poli {y, x}
     */
    public int[] getPoloha() {
        return new int[]{this.yPoziciaNaMape, this.xPoziciaNaMape};
    }
}
