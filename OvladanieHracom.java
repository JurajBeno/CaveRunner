
/**
*  trieda zabezpecuje pohyb hraca po mape a animacie
*/
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
    public OvladanieHracom(int x, int y, Mapa mapa, int rycholostHraca, int poskodenie, int zivot, Hra hra) {
        this.mapa = mapa;
                            // stred mapy
        this.hrac = new Hrac(131, 132, rycholostHraca, zivot, poskodenie);
        this.animacia = new AnimaciaHraca(x, y);
        
        this.hra = hra;
    }

    /** 
     * @return pozicia hraca na mape
     */
    public int[] getPoziciaHracaNaMape() {
        return this.hrac.getPoziciaHracaNaMape();
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
     * Zisti aka je momentalna akciu a vykona animacie, poda poskodenie alebo zmeni polohu
     */
    public void vykonajAkcie() {
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
            this.hrac.zmenAkciu(AkciaHraca.SMRT_VPRAVO);
        } else {
            this.hrac.zmenAkciu(AkciaHraca.SMRT_VLAVO);
        }
    }

    private void umieraj() {
        if (this.animacia.getIndexAnimacie() != this.hrac.getPrebiehajucaAkcia().getNajvacsiIndexAnimacie()) {
            this.animacia.animuj(this.hrac.getPrebiehajucaAkcia());
        }
    }

    private void pohniSa() {
        if (this.hrac.getHybeSa() && this.jePriestorVolny(this.hrac.getPrebiehajucaAkcia().getSmer())) {
            this.mapa.posunPozadie(this.hrac.getRychlostHraca() * -1, this.hrac.getPrebiehajucaAkcia().getSmer());
            this.hra.posunNehratelnePostavy(this.hrac.getRychlostHraca() * -1, this.hrac.getPrebiehajucaAkcia().getSmer());
            this.animacia.animuj(this.hrac.getPrebiehajucaAkcia());
            this.hrac.zmenPoziciuHracaNaMape();
        }
    }

    private void zautoc() {
        this.animacia.animuj(this.hrac.getPrebiehajucaAkcia());
        if (this.animacia.getIndexAnimacie() > this.hrac.getPrebiehajucaAkcia().getNajvacsiIndexAnimacie() && this.hrac.getUtoci()) {
            this.hra.podajDamage(this.hrac.getPoziciaHracaNaMape(), this.hrac.getPoskodenie());
            this.animacia.animuj(this.hrac.getPrebiehajucaAkcia());
            this.hrac.zmenAkciu(AkciaHraca.STOJ);
        }
    }

    private boolean jePriestorVolny(int[] smer) {
        return this.mapa.getPrvokMapy(this.hrac.getPoziciaHracaNaMape()[0] + smer[0] - 1, this.hrac.getPoziciaHracaNaMape()[1] + smer[1] - 1) == -1;
    }

    /** Zmeni akciu na utok v spravnom smere */
    public void utoc() {
        if (this.hrac.getSmerOtocenia()[0] == 1 || this.hrac.getSmerOtocenia()[1] == 1) {
            this.hrac.zmenAkciu(AkciaHraca.UTOC_VPRAVO);
        } else {
            this.hrac.zmenAkciu(AkciaHraca.UTOC_VLAVO);
        }
    }

    /** @return poziciu hraca osi y a osi x. */
    public int[] poziciaHraca() {
        return this.hrac.getPoziciaHracaNaMape();
    }

    /** Zmenni akciu na chodenie vpravo. */
    public void chodVpravo() {
        this.hrac.zmenAkciu(AkciaHraca.CHOD_VPRAVO);
    }

    /** Zmenni akciu na chodenie vlavo. */
    public void chodVlavo() {
        this.hrac.zmenAkciu(AkciaHraca.CHOD_VLAVO);
    }
    
    /** Zmenni akciu na chodenie smerom hore. */
    public void chodHore() {
        this.hrac.zmenAkciu(AkciaHraca.CHOD_HORE);
    }
    
    /** Zmenni akciu na chodenie smerom dolu. */
    public void chodDole() {
        this.hrac.zmenAkciu(AkciaHraca.CHOD_DOLU);
    }

    /** Zmenni akciu z chodenia vlavo na statie. */
    public void prestanChoditVlavo() {
        if (this.hrac.getPrebiehajucaAkcia() == AkciaHraca.CHOD_VLAVO) {
            this.hrac.zmenAkciu(AkciaHraca.STOJ);
        }
    }

    /** Zmenni akciu z chodenia vpravo na statie. */
    public void prestanChoditVpravo() {
        if (this.hrac.getPrebiehajucaAkcia() == AkciaHraca.CHOD_VPRAVO) {
            this.hrac.zmenAkciu(AkciaHraca.STOJ);
        }
    }

    /** Zmenni akciu z chodenia dolu na statie. */
    public void prestanChoditDole() {
        if (this.hrac.getPrebiehajucaAkcia() == AkciaHraca.CHOD_DOLU) {
            this.hrac.zmenAkciu(AkciaHraca.STOJ);
        }
    }

    /** Zmenni akciu z chodenia hore na statie. */
    public void prestanChoditHore() {
        if (this.hrac.getPrebiehajucaAkcia() == AkciaHraca.CHOD_HORE) {
            this.hrac.zmenAkciu(AkciaHraca.STOJ);
        }
    }
}
