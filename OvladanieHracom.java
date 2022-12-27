
/**
*  trieda zabezpecuje pohyb hraca po mape a animacie
*/
    //todo HRAC BUDE KOMUNIKOVAT S ANIMACIOU este sa ale rozhodnem
public class OvladanieHracom {

    private Mapa mapa;
    private AnimaciaHraca animacia;
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
        this.animacia = new AnimaciaHraca(x, y);
        
        this.hra = null;
    }

    /** skontroluje mnozstvo zivota */
    public void skontrolujZivot() {
        if (this.hrac.getZivot() < 1) {
            this.hrac.zmentCinnost(Cinnost.SMRT);
        }
    }

    /** Spoji hru s ovladanim aby bolo mozne poslat poskodenie.*/
    public void spojHru(Hra hra) {
        this.hra = hra;
    }

    /** Ovladanie pohybu hraca, a spravne animacie.*/
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
    /** Zabezpecuje utocenie. */
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

    /** Spusti posunutie posunutie poskodenia. */
    public void utoc() {
        this.hrac.setUtok(true);
        //this.hrac.zmentCinnost(Cinnost.UTOC_VPRAVO);
    }

    /** Vrati poziciu hraca osi y a osi x. */
    public int[] poziciaHraca() {
        return this.hrac.getPoziciaHracaNaMape();
    }

    /** Zmenni cinnost na chodenie vpravo. */
    public void chodVpravo() {
        this.hrac.zmentCinnost(Cinnost.CHOD_VPRAVO);

    }

    /** Zmenni cinnost na chodenie vlavo. */
    public void chodVlavo() {
        this.hrac.zmentCinnost(Cinnost.CHOD_VLAVO);
    }
    
    /** Zmenni cinnost na chodenie smerom hore. */
    public void chodHore() {
        this.hrac.zmentCinnost(Cinnost.CHOD_HORE);
    }
    
    /** Zmenni cinnost na chodenie smerom dolu. */
    public void chodDole() {
        this.hrac.zmentCinnost(Cinnost.CHOD_DOLU);
    }

    /** Zmenni cinnost z chodenia vlavo na statie. */
    public void prestanChoditVlavo() {
        if (this.hrac.getPrebiehajucaCinnost() == Cinnost.CHOD_VLAVO) {
            this.hrac.zmentCinnost(Cinnost.STOJ);
        }
    }

    /** Zmenni cinnost z chodenia vpravo na statie. */
    public void prestanChoditVpravo() {
        if (this.hrac.getPrebiehajucaCinnost() == Cinnost.CHOD_VPRAVO) {
            this.hrac.zmentCinnost(Cinnost.STOJ);
        }
    }

    /** Zmenni cinnost z chodenia dolu na statie. */
    public void prestanChoditDole() {
        if (this.hrac.getPrebiehajucaCinnost() == Cinnost.CHOD_DOLU) {
            this.hrac.zmentCinnost(Cinnost.STOJ);
        }
    }

    /** Zmenni cinnost z chodenia hore na statie. */
    public void prestanChoditHore() {
        if (this.hrac.getPrebiehajucaCinnost() == Cinnost.CHOD_HORE) {
            this.hrac.zmentCinnost(Cinnost.STOJ);
        }
    }
}
