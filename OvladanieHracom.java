
/**
     *  trieda zabezpecuje pohyb hraca po mape
     * a taktiez animacie hraca
     */
public class OvladanieHracom {

    private Mapa mapa;
    private AnimaciaHraca animacia;

    private int xPoziciaHracaNaMape;
    private int yPoziciaHracaNaMape;
    private int xPoziciaNaPolicku;
    private int yPoziciaNaPolicku;

    private boolean pohybHore;
    private boolean pohybDole;
    private boolean pohybVpravo;
    private boolean pohybVlavo;
    private boolean utok;

    private int rycholostHraca;
    private int poskodenie;
    private int zivot;

    private Hra hra;

    public OvladanieHracom(int x, int y, Mapa mapa, int rycholostHraca, int poskodenie, int zivot) {
        this.mapa = mapa;
        this.xPoziciaHracaNaMape = this.mapa.getVelkostMapy() / 2 + 1;
        this.yPoziciaHracaNaMape = this.mapa.getVelkostMapy() / 2 + 1;
        this.xPoziciaNaPolicku = 0;
        this.yPoziciaNaPolicku = 0;
        this.animacia = new AnimaciaHraca(x, y);
        this.pohybHore = false;
        this.pohybDole = false;
        this.pohybVpravo = false;
        this.pohybVlavo = false;
        this.utok = false;
        this.rycholostHraca = rycholostHraca;
        this.poskodenie = poskodenie;
        this.zivot = zivot;
        this.hra = null;
    }

    public void skontrolujZivot() {
        if (this.zivot < 1) {
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
        if (this.utok) {
            return;
        }
        if (this.pohybDole && this.jePriestorVolny(0, 1)) {
            this.mapa.posunMapyVertikalne(this.rycholostHraca * -1);
            this.animacia.setCinnost("chodenie");
            this.animacia.posunAnimaciu("Dolu");
            this.pohybNaPolicku(0, this.rycholostHraca);
        } else if (this.pohybHore && this.jePriestorVolny(0, -1)) {
            this.mapa.posunMapyVertikalne(this.rycholostHraca);
            this.animacia.setCinnost("chodenie");
            this.animacia.posunAnimaciu("Hore");
            this.pohybNaPolicku(0, -this.rycholostHraca);
        } else if (this.pohybVlavo && this.jePriestorVolny(-1, 0)) {
            this.mapa.posunMapyHorizontalne(this.rycholostHraca * -1);
            this.animacia.setCinnost("chodenie");
            this.animacia.posunAnimaciu("Dolava");
            this.pohybNaPolicku(-this.rycholostHraca, 0);
        } else if (this.pohybVpravo && this.jePriestorVolny(1, 0)) {
            this.mapa.posunMapyHorizontalne(this.rycholostHraca);
            this.animacia.setCinnost("chodenie");
            this.animacia.posunAnimaciu("DoPrava");
            this.pohybNaPolicku(this.rycholostHraca, 0);
        }
    }

    //todo upravit tak aby rozlisovalo ci idem dole alebo hore
    //todo
    public void pohybNaPolicku(int x, int y) {
        if (Math.abs(this.xPoziciaNaPolicku + x) == 32) {
            if (x < 0) {
                this.xPoziciaHracaNaMape -= 1;
            } else {
                this.xPoziciaHracaNaMape += 1;
            }
            this.xPoziciaNaPolicku = 0;
        } else if (Math.abs(this.yPoziciaNaPolicku + y) == 32) {
            if (y < 0) {
                this.yPoziciaHracaNaMape -= 1;
            } else {
                this.yPoziciaHracaNaMape += 1;
            }
            this.yPoziciaNaPolicku = 0;
        } else {
            this.xPoziciaNaPolicku += x;
            this.yPoziciaNaPolicku += y;
        }
        System.out.println(this.mapa.getSietPreMapu().getSiet().get(this.yPoziciaHracaNaMape + y - 1).get(this.xPoziciaHracaNaMape + x - 1));
        System.out.println(this.xPoziciaNaPolicku + " " + this.yPoziciaNaPolicku);
        System.out.println(this.xPoziciaHracaNaMape + " " + this.yPoziciaHracaNaMape);
    }

    public void utocenie() {
        if (this.utok) {
            this.animacia.setCinnost("utok");
            this.animacia.posunAnimaciuUtoku();
        }
        if (this.animacia.getCisloAnimacie() > 6) {
            this.animacia.setCisloAnimacie(0);
            this.dajDamage();
            this.utok = false;
        }
    }

    private void dajDamage() {
        if (this.hra == null) {
            System.out.println("Hrca nebol spojeny s triedou hra");
        } else {
            this.hra.podajDamage(new int[]{this.xPoziciaHracaNaMape, this.yPoziciaHracaNaMape}, this.poskodenie);
        }
    }

    private boolean jePriestorVolny(int x, int y) {
        return this.mapa.getSietPreMapu().getSiet().get(this.yPoziciaHracaNaMape + y - 1).get(this.xPoziciaHracaNaMape + x - 1) == -1;
    }

    public void utoc() {
        this.utok = true;
    }

    public int[] poziciaHraca() {
        return new int[]{this.xPoziciaHracaNaMape, this.yPoziciaHracaNaMape};
    }

    public void chodVpravo() {
        this.pohybVpravo = true;
    }

    public void chodVlavo() {
        this.pohybVlavo = true;
    }
    
    public void chodHore() {
        this.pohybHore = true;
    }
    
    public void chodDole() {
        this.pohybDole = true;
    }

    public void prestanChoditVlavo() {
        this.pohybVlavo = false;
    }

    public void prestanChoditVpravo() {
        this.pohybVpravo = false;
    }

    public void prestanChoditDole() {
        this.pohybDole = false;
    }

    public void prestanChoditHore() {
        this.pohybHore = false;
    }
}
