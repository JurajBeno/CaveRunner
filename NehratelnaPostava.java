
public class NehratelnaPostava {
    private int xPoziciaNaMape;
    private int yPoziciaNaMape;
    private int zivot;
   // public AnimaciaNehratelnejPostavy animacia;
    public NehratelnaPostava(int xPoziciaNaMape, int yPoziciaNaMape, int zivot) {
        this.xPoziciaNaMape = xPoziciaNaMape;
        this.yPoziciaNaMape = yPoziciaNaMape;
        this.zivot = zivot;
        //todo this.animacia = new AnimaciaNehratelnejPostavy();
    }
    public boolean jePostavaNaObrazovke(int[] stredObrazovky) {
        return (this.xPoziciaNaMape < stredObrazovky[0] + 13 && this.xPoziciaNaMape > stredObrazovky[0] - 13) &&
            (this.xPoziciaNaMape < stredObrazovky[1] + 7 && this.yPoziciaNaMape > stredObrazovky[1] - 7);  
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
