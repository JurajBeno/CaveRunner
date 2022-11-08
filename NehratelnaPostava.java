
public class NehratelnaPostava {
    private int xPoziciaNaMape;
    private int yPoziciaNaMape;
    private int zivot;
    private int poskodenie;
   // public AnimaciaNehratelnejPostavy animacia;
    public NehratelnaPostava(int xPoziciaNaMape, int yPoziciaNaMape, int zivot, int poskodenie) {
        this.xPoziciaNaMape = xPoziciaNaMape;
        this.yPoziciaNaMape = yPoziciaNaMape;
        this.zivot = zivot;
        this.poskodenie = poskodenie;
        //todo this.animacia = new AnimaciaNehratelnejPostavy();
    }
    private boolean jePostavaNaObrazovke(int[] stredObrazovky) {
        return (this.xPoziciaNaMape < stredObrazovky[0] + 13 && this.xPoziciaNaMape > stredObrazovky[0] - 13) &&
            (this.xPoziciaNaMape < stredObrazovky[1] + 7 && this.yPoziciaNaMape > stredObrazovky[1] - 7);  
    }

    private void dajPoskodenie() {
        //todo ak je blizko hraca tak mu sekni
    }

    /**
     * 
     * @param stredObrazovky vzhladom na mapu, pozicia hraca na mape.
     */
    public void hladajCestu(int[] stredObrazovky) {
        if (this.jePostavaNaObrazovke(stredObrazovky)) {
            // todo path finiding
        }
    }

    public void uberZivot(int poskodenie) {
        this.zivot -= poskodenie;
        if (this.zivot < 1) {
            this.umri();
        }
    }

    public int[] getPoloha() {
        return new int[]{this.xPoziciaNaMape, this.yPoziciaNaMape};
    }

    private void umri() {
        //todo 
    }
}
