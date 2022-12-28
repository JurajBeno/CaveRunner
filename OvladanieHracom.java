
/**
*  trieda zabezpecuje pohyb hraca po mape a animacie
*/
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

    public void dostanPoskodenie(int poskodenie) {
        this.hrac.dostanPoskodenie(poskodenie);
    }

    /**
     * skontroluje mnozstvo zivota
     */
    public void skontrolujZivot() {
        if (this.hrac.getZivot() < 1) {
            this.umri();
        }
    }

    /** 
     * Zisti aka je momentalna cinnost a vykona animacie, poda poskodenie alebo zmeni polohu
     */
    public void vykonajCinnost() {
        if (this.hrac.getUtoci()) {
            this.zautoc();
        } else if (this.hrac.getHybeSa() && !this.hrac.getUtoci()) {
            this.pohniSa();
        } else if (this.hrac.jeMrtvy()) {
            this.umieraj();
        }
    }

    private void umri() {
        if (this.hrac.getSmerOtocenia()[0] == 1 || this.hrac.getSmerOtocenia()[1] == 1) {
            this.hrac.zmenCinnost(CinnostHraca.SMRT_VPRAVO);
        } else {
            this.hrac.zmenCinnost(CinnostHraca.SMRT_VLAVO);
        }
    }

    /** Spoji hru s ovladanim aby bolo mozne poslat poskodenie.*/
    public void spojHru(Hra hra) {
        this.hra = hra;
    }

    private void umieraj() {
        if (this.animacia.getIndexAnimacie() != this.hrac.getPrebiehajucaCinnost().getNajvacsiIndexAnimacie()) {
            this.animacia.animuj(this.hrac.getPrebiehajucaCinnost());
        }
    }

    private void pohniSa() {
        if (this.hrac.getHybeSa() && this.jePriestorVolny(this.hrac.getPrebiehajucaCinnost().getSmer())) {
            this.mapa.posunPozadie(this.hrac.getRychlostHraca() * -1, this.hrac.getPrebiehajucaCinnost().getSmer());
            this.animacia.animuj(this.hrac.getPrebiehajucaCinnost());
            this.hrac.zmenPoziciuHracaNaMape();
        }
    }

    private void zautoc() {
        this.animacia.animuj(this.hrac.getPrebiehajucaCinnost());
        if (this.animacia.getIndexAnimacie() > this.hrac.getPrebiehajucaCinnost().getNajvacsiIndexAnimacie() && this.hrac.getUtoci()) {
            this.dajDamage();
            this.animacia.animuj(this.hrac.getPrebiehajucaCinnost());
            this.hrac.zmenCinnost(CinnostHraca.STOJ);
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

    /** Zmeni cinnost na utok v spravnom smere */
    public void utoc() {
        if (this.hrac.getSmerOtocenia()[0] == 1 || this.hrac.getSmerOtocenia()[1] == 1) {
            this.hrac.zmenCinnost(CinnostHraca.UTOC_VPRAVO);
        } else {
            this.hrac.zmenCinnost(CinnostHraca.UTOC_VLAVO);
        }
    }

    /** @return poziciu hraca osi y a osi x. */
    public int[] poziciaHraca() {
        return this.hrac.getPoziciaHracaNaMape();
    }

    /** Zmenni cinnost na chodenie vpravo. */
    public void chodVpravo() {
        this.hrac.zmenCinnost(CinnostHraca.CHOD_VPRAVO);

    }

    /** Zmenni cinnost na chodenie vlavo. */
    public void chodVlavo() {
        this.hrac.zmenCinnost(CinnostHraca.CHOD_VLAVO);
    }
    
    /** Zmenni cinnost na chodenie smerom hore. */
    public void chodHore() {
        this.hrac.zmenCinnost(CinnostHraca.CHOD_HORE);
    }
    
    /** Zmenni cinnost na chodenie smerom dolu. */
    public void chodDole() {
        this.hrac.zmenCinnost(CinnostHraca.CHOD_DOLU);
    }

    /** Zmenni cinnost z chodenia vlavo na statie. */
    public void prestanChoditVlavo() {
        if (this.hrac.getPrebiehajucaCinnost() == CinnostHraca.CHOD_VLAVO) {
            this.hrac.zmenCinnost(CinnostHraca.STOJ);
        }
    }

    /** Zmenni cinnost z chodenia vpravo na statie. */
    public void prestanChoditVpravo() {
        if (this.hrac.getPrebiehajucaCinnost() == CinnostHraca.CHOD_VPRAVO) {
            this.hrac.zmenCinnost(CinnostHraca.STOJ);
        }
    }

    /** Zmenni cinnost z chodenia dolu na statie. */
    public void prestanChoditDole() {
        if (this.hrac.getPrebiehajucaCinnost() == CinnostHraca.CHOD_DOLU) {
            this.hrac.zmenCinnost(CinnostHraca.STOJ);
        }
    }

    /** Zmenni cinnost z chodenia hore na statie. */
    public void prestanChoditHore() {
        if (this.hrac.getPrebiehajucaCinnost() == CinnostHraca.CHOD_HORE) {
            this.hrac.zmenCinnost(CinnostHraca.STOJ);
        }
    }
}
