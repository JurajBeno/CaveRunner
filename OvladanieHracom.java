
/**
*  trieda zabezpecuje pohyb hraca po mape a animacie
*/
    //todo HRAC BUDE KOMUNIKOVAT S ANIMACIOU este sa ale rozhodnem
public class OvladanieHracom {

    private Mapa mapa;
    private Animacia animacia;
    private Hrac hrac;
    private Hra hra;

    /** 
     * @param x pozicia na horizontalnej osi
     * @param y pozicia na vertikalnej osi
     * @param mapa objekt Mapa
     */
    public OvladanieHracom(int x, int y, Mapa mapa, int rycholostHraca, int poskodenie, int zivot) {
        this.mapa = mapa;

        this.hrac = new Hrac(131, 132, rycholostHraca, zivot, poskodenie);
        this.animacia = new Animacia(x, y);
        
        this.hra = null;
    }

    /** skontroluje mnozstvo zivota */
    public void skontrolujZivot() {
        if (this.hrac.getZivot() < 1) {
            this.hrac.zmentCinnost(CinnostHraca.SMRT);
        }
    }

    /** Spoji hru s ovladanim aby bolo mozne poslat poskodenie.*/
    public void spojHru(Hra hra) {
        this.hra = hra;
    }

    /** Ovladanie pohybu hraca, a spravne animacie.*/
    public void pohybHraca() {
        if (this.hrac.getUtoci()) {
            return;
        }
        //todo otocenie animacie spravnym smerom
        if (this.hrac.getHybeSa() && this.jePriestorVolny(this.hrac.getPrebiehajucaCinnost().getSmer())) {
            this.mapa.posunPozadie(this.hrac.getRychlostHraca() * -1, this.hrac.getPrebiehajucaCinnost().getSmer());
            this.animacia.animuj(this.hrac.getPrebiehajucaCinnost());
            this.hrac.zmenPoziciuHracaNaMape();
        }
    }

    //todo utocenie aby bolo trigernute <cinnost.UTOC>
    /** Zabezpecuje utocenie. */
    public void utocenie() {
        if (this.hrac.getUtoci()) {
            this.animacia.animuj(this.hrac.getPrebiehajucaCinnost());
        }
        if (this.animacia.getIndexAnimacie() > 6) {
            this.dajDamage();
            this.hrac.zmentCinnost(CinnostHraca.STOJ);
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

    /** Spusti posunutie posunutie poskodenia. */
    // todo toto vyladit aby to uplne fungovalo
    public void utoc() {
        int[] smer = this.hrac.getSmerPohybu();
        if (smer[0] == 1 || smer[1] == 1) {
            this.hrac.zmentCinnost(CinnostHraca.UTOC_VPRAVO);
        } else {
            this.hrac.zmentCinnost(CinnostHraca.UTOC_VLAVO);
        }
    }

    /** Vrati poziciu hraca osi y a osi x. */
    public int[] poziciaHraca() {
        return this.hrac.getPoziciaHracaNaMape();
    }

    /** Zmenni cinnost na chodenie vpravo. */
    public void chodVpravo() {
        this.hrac.zmentCinnost(CinnostHraca.CHOD_VPRAVO);

    }

    /** Zmenni cinnost na chodenie vlavo. */
    public void chodVlavo() {
        this.hrac.zmentCinnost(CinnostHraca.CHOD_VLAVO);
    }
    
    /** Zmenni cinnost na chodenie smerom hore. */
    public void chodHore() {
        this.hrac.zmentCinnost(CinnostHraca.CHOD_HORE);
    }
    
    /** Zmenni cinnost na chodenie smerom dolu. */
    public void chodDole() {
        this.hrac.zmentCinnost(CinnostHraca.CHOD_DOLU);
    }

    /** Zmenni cinnost z chodenia vlavo na statie. */
    public void prestanChoditVlavo() {
        if (this.hrac.getPrebiehajucaCinnost() == CinnostHraca.CHOD_VLAVO) {
            this.hrac.zmentCinnost(CinnostHraca.STOJ);
        }
    }

    /** Zmenni cinnost z chodenia vpravo na statie. */
    public void prestanChoditVpravo() {
        if (this.hrac.getPrebiehajucaCinnost() == CinnostHraca.CHOD_VPRAVO) {
            this.hrac.zmentCinnost(CinnostHraca.STOJ);
        }
    }

    /** Zmenni cinnost z chodenia dolu na statie. */
    public void prestanChoditDole() {
        if (this.hrac.getPrebiehajucaCinnost() == CinnostHraca.CHOD_DOLU) {
            this.hrac.zmentCinnost(CinnostHraca.STOJ);
        }
    }

    /** Zmenni cinnost z chodenia hore na statie. */
    public void prestanChoditHore() {
        if (this.hrac.getPrebiehajucaCinnost() == CinnostHraca.CHOD_HORE) {
            this.hrac.zmentCinnost(CinnostHraca.STOJ);
        }
    }
}
