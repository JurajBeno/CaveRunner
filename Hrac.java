
/**
 * Write a description of class Hrac here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hrac {
    private int[] poziciaHraca;

    private boolean utok;

    private int[] smerPohybu;

    private boolean pohyb;

    private int rycholostHraca;
    private int poskodenie;
    private int zivot;

    
    public Hrac(int x, int y, int rycholostHraca, int zivot, int poskodenie) {
        this.poziciaHraca = new int[] {y, x};
        this.rycholostHraca = rycholostHraca;
        this.smerPohybu = new int[] {0, 0}; 
        this.zivot = zivot;
        this.poskodenie = poskodenie;
    }

    public int[] getPoziciaHracaNaMape() {
        return this.poziciaHraca;
    }

    public void zmenPoziciuHracaNaMape(int[] pohyb) {
        this.poziciaHraca[0] += pohyb[0];
        this.poziciaHraca[1] += pohyb[1];
    
    }

    public boolean getHybeSa() {
        return this.smerPohybu[0] != 0 || this.smerPohybu[1] != 0;
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

    public int[] getSmerPohybu() {
        return this.smerPohybu;
    }

    public void zmenSmer(int[] smer) {
        this.smerPohybu = smer;
    }

    public int getPoskodenie() {
        return this.poskodenie;
    }

    public int getZivot() {
        return this.zivot;
    }

}
