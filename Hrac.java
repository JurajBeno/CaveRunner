
public class Hrac {
    private int[] poziciaHraca;

    private boolean utok;

    private int[] smerPohybu;
    private Cinnost prebiehajucaCinnost;

    private int rycholostHraca;
    private int poskodenie;
    private int zivot;

    
    public Hrac(int x, int y, int rycholostHraca, int zivot, int poskodenie) {
        this.poziciaHraca = new int[] {y, x};
        this.rycholostHraca = rycholostHraca;
        this.smerPohybu = new int[] {0, 0}; 
        this.prebiehajucaCinnost = Cinnost.STOJ;
        this.zivot = zivot;
        this.poskodenie = poskodenie;
    }

    public int[] getPoziciaHracaNaMape() {
        return this.poziciaHraca;
    }

    public void zmenPoziciuHracaNaMape() {
        this.poziciaHraca[0] += this.smerPohybu[0];
        this.poziciaHraca[1] += this.smerPohybu[1];
    
    }

    public boolean getHybeSa() {
        return this.prebiehajucaCinnost.getSmer()[0] != 0 || this.prebiehajucaCinnost.getSmer()[1] != 0;
    }

    public int getRychlostHraca() {
        return this.rycholostHraca;
    }

    public boolean getUtok() {
        return this.utok;
    }

    public void setUtok(boolean utok) {
        this.utok = utok;
    }

    public Cinnost getPrebiehajucaCinnost() {
        return this.prebiehajucaCinnost;
    }

    public void zmentCinnost(Cinnost momentalnaCinnost) {
        this.smerPohybu = momentalnaCinnost.getSmer();
        this.prebiehajucaCinnost = momentalnaCinnost;
    }

    public int getPoskodenie() {
        return this.poskodenie;
    }

    public int getZivot() {
        return this.zivot;
    }

}
