
/**
     *  trieda zabezpecuje pohyb hraca po mape
     * a taktiez animacie hraca
     */
public class OvladanieHracom {

    private Mapa mapa;
    private AnimaciaHraca animacia;

    private Hrac hrac;


    private Hra hra;

    public OvladanieHracom(int x, int y, Mapa mapa, int rycholostHraca, int poskodenie, int zivot) {
        this.mapa = mapa;

        this.hrac = new Hrac(131, 132, rycholostHraca, zivot, poskodenie);
        this.animacia = new AnimaciaHraca(x, y);
        
        this.hra = null;
    }

    public void skontrolujZivot() {
        if (this.hrac.getZivot() < 1) {
            this.umri();
        }
    }

    private void umri() {
        //todo
    }

    public void spojHru(Hra hra) {
        this.hra = hra;
    }

    //https://www.youtube.com/shorts/8_gPlajc9T8 https://www.youtube.com/shorts/CV5eynXK0Tg https://www.youtube.com/shorts/ZJ2RxzMwhH8
    public void pohybHraca() {
        if (this.hrac.getUtok()) {
            System.out.println("a toto sa deje???");
            return;
        }
        //todo otocenie animacie spravnym smerom
        if (this.hrac.getHybeSa() && this.jePriestorVolny(this.hrac.getSmerPohybu())) {
            this.mapa.posunPozadie(this.hrac.getRychlostHraca() * -1, this.hrac.getSmerPohybu());
            this.animacia.setCinnost("chodenie");
            this.animacia.posunAnimaciu("Dolu"); //todo ktorym smerom ide animacia
            this.hrac.zmenPoziciuHracaNaMape(this.hrac.getSmerPohybu());
            System.out.println("toto sa ale deje");
        
        }
    }

    //todo animacia smerom ktorym bol hrac otoceny
    public void utocenie() {
        if (this.hrac.getUtok()) {
            this.animacia.setCinnost("utok");
            this.animacia.posunAnimaciuUtoku();
        }
        if (this.animacia.getCisloAnimacie() > 6) {
            this.animacia.setCisloAnimacie(0);
            this.dajDamage();
            this.hrac.setUtok(false);
        }
    }

    private void dajDamage() {
        if (this.hra == null) {
            System.out.println("Hrac nebol spojeny s triedou hra");
        } else {
            this.hra.podajDamage(this.hrac.getPoziciaHracaNaMape(), this.hrac.getPoskodenie());
        }
    }

    private boolean jePriestorVolny(int[] smer) {
        System.out.println(smer[0]+ " " + smer[1]);
        System.out.println(this.hrac.getPoziciaHracaNaMape()[0] + " " + this.hrac.getPoziciaHracaNaMape()[1]);
        //System.out.println("blok pos y: " + (this.yPoziciaHracaNaMape + y - 1) + " pos x: " + (this.xPoziciaHracaNaMape + x - 1));
        System.out.println("cislo bloku: " + this.mapa.getSietPreMapu().getSiet()[this.hrac.getPoziciaHracaNaMape()[0] + smer[0] - 1][this.hrac.getPoziciaHracaNaMape()[1] + smer[1] - 1]);
        return this.mapa.getSietPreMapu().getSiet()[this.hrac.getPoziciaHracaNaMape()[0] + smer[0] - 1][this.hrac.getPoziciaHracaNaMape()[1] + smer[1] - 1] == -1;
    }

    public void utoc() {
        this.hrac.setUtok(true);
    }

    public int[] poziciaHraca() {
        return this.hrac.getPoziciaHracaNaMape();
    }

    public void chodVpravo() {
        this.hrac.zmenSmer(new int[] {0, 1});
    }

    public void chodVlavo() {
        this.hrac.zmenSmer(new int[] {0, -1});
    }
    
    public void chodHore() {
        this.hrac.zmenSmer(new int[] {-1, 0});
    }
    
    public void chodDole() {
        this.hrac.zmenSmer(new int[] {1, 0});
    }

    public void prestanChoditVlavo() {
        this.hrac.zmenSmer(new int[2]);
    }

    public void prestanChoditVpravo() {
        this.hrac.zmenSmer(new int[2]);
    }

    public void prestanChoditDole() {
        this.hrac.zmenSmer(new int[2]);
    }

    public void prestanChoditHore() {
        this.hrac.zmenSmer(new int[2]);
    }
}
