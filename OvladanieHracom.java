
/**
     *  trieda zabezpecuje pohyb hraca po mape
     */
    //todo HRAC BUDE KOMUNIKOVAT S ANIMACIOU este sa ale rozhodnem
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
            this.hrac.zmentCinnost(Cinnost.SMRT);
        }
    }

    public void spojHru(Hra hra) {
        this.hra = hra;
    }

    public void pohybHraca() {
        if (this.hrac.getUtok()) {
            return;
        }
        //todo otocenie animacie spravnym smerom
        if (this.hrac.getHybeSa() && this.jePriestorVolny(this.hrac.getPrebiehajucaCinnost().getSmer())) {
            this.mapa.posunPozadie(this.hrac.getRychlostHraca() * -1, this.hrac.getPrebiehajucaCinnost().getSmer());
            this.animacia.setCinnost(this.hrac.getPrebiehajucaCinnost().getNazovCinoosti());
            this.animacia.posunAnimaciu(this.hrac.getPrebiehajucaCinnost().getOtocenieAnimacie());
            this.hrac.zmenPoziciuHracaNaMape();
        
        }
    }

    //todo utocenie aby bolo trigernute <cinnost.UTOC>
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
        return this.mapa.getPrvokMapy(this.hrac.getPoziciaHracaNaMape()[0] + smer[0] - 1, this.hrac.getPoziciaHracaNaMape()[1] + smer[1] - 1) == -1;
    }

    public void utoc() {
        this.hrac.setUtok(true);
        //this.hrac.zmentCinnost(Cinnost.UTOC_VPRAVO);
    }

    public int[] poziciaHraca() {
        return this.hrac.getPoziciaHracaNaMape();
    }

    public void chodVpravo() {
        this.hrac.zmentCinnost(Cinnost.CHOD_VPRAVO);

    }

    public void chodVlavo() {
        this.hrac.zmentCinnost(Cinnost.CHOD_VLAVO);
    }
    
    public void chodHore() {
        this.hrac.zmentCinnost(Cinnost.CHOD_HORE);
    }
    
    public void chodDole() {
        this.hrac.zmentCinnost(Cinnost.CHOD_DOLU);
    }

    public void prestanChoditVlavo() {
        this.hrac.zmentCinnost(Cinnost.STOJ);
    }

    public void prestanChoditVpravo() {
        this.hrac.zmentCinnost(Cinnost.STOJ);
    }

    public void prestanChoditDole() {
        this.hrac.zmentCinnost(Cinnost.STOJ);
    }

    public void prestanChoditHore() {
        this.hrac.zmentCinnost(Cinnost.STOJ);
    }
}
