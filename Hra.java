
/**
 * Trieda zabezpecuje celu kontorlu nad programom skrz komunikaciu manazera so zvyskom hry.
 */
public class Hra {
    private OvladanieHracom ovladanieHracom;
    private Manazer manazer;
    private Mapa mapa;
    private OvladanieNehratelnychPostav ovladanieNP;
    private boolean hraBezi;
    private Obrazok startObrazok;
    private Obrazok koniecHryObrazok;
    /**
     * Najprv mam nastavenie hry:
     * rozmery obrazovky, a nastavenia hraca
     * nasledne vytovori vsetky triedy potrebne nabeh hry a pripravi na spustenie hry hracom
     * 
     * NP = NehratelnaPostava (programom ovladana)    
     */
    public Hra() {
        //nastavenia:
        int y = 240;
        int x = 426;
        int rycholostHraca = 8;
        int velkostMapy = 65;
        int poskodenie = 32;
        int zivot = 256;
        int pocetNP = 3;
        this.manazer = new Manazer();

        this.mapa = new Mapa(x, y, "prvyLevel1", velkostMapy);
        this.ovladanieHracom = new OvladanieHracom(x, y, this.mapa, rycholostHraca, poskodenie, zivot, this);
        this.ovladanieNP = new OvladanieNehratelnychPostav(this.mapa, this, pocetNP);
        this.hraBezi = false;

        this.startObrazok = new Obrazok("icons\\pressEnter.png");
        this.startObrazok.zmenPolohu(x, y);
        this.koniecHryObrazok = new Obrazok("icons\\gameOver.png");
        this.koniecHryObrazok.zmenPolohu(x, y + 70);

        this.startObrazok.zobraz();

        this.manazer.spravujObjekt(this);
    }

    /** 
     * Tu prebieha spustena hra kde kazdy tik vokoname akcie hraca, NP
     * a skonrolujeme ci neboli splnene podmienky konca hry
     * ak hrac umrel tak sa ukonci animacie smrti
     */
    public void tik() {
        if (this.hraBezi) {
            this.ukonciHru();
            this.ovladanieHracom.skontrolujZivot();
            this.ovladanieHracom.vykonajAkcie();
            this.ovladanieNP.vykonajNPAkcie(this.ovladanieHracom.getPoziciaHracaNaMape());
        } else if (this.ovladanieHracom.jeHracMrtvy()) {
            this.ovladanieHracom.vykonajAkcie();
        }
    }

    /** Po zavolani sa spusti hra */
    public void aktivuj() {
        if (!this.hraBezi) {
            this.hraBezi = true;
            this.startObrazok.skry();
            this.manazer.spravujObjekt(this.ovladanieHracom);
        }
    }

    /** Po zavolani ukonci program */
    public void ukonci() {
        System.exit(0);
    }

    /** @param poskodenie ktore dala NP hracovi je mu poslane ak je v dosahu */
    public void podajPoskodenieHracovi(int poskodenie) {
        this.ovladanieHracom.dostanPoskodenie(poskodenie);
    }

    /** @param poskodenie od hraca NP ktore su v dosahu @param polohaHraca */
    public void podajPoskodenieNehratelnymPostavam(int[] polohaHraca, int poskodenie) {
        this.ovladanieNP.dostanPoskodenie(poskodenie, polohaHraca);
    }

    /** posunie postavy @param smerom o @param posun napriklad ked sa pohne hrac a snim aj obrazovka */
    public void posunNehratelnePostavy(int posun, int[] smer) {
        this.ovladanieNP.posunKazduPostavu(posun, smer);
    }

    private void ukonciHru() {
        if (this.ovladanieHracom.jeHracMrtvy() || this.ovladanieNP.getPocetNP() == 0) {
            this.koniecHryObrazok.zobraz();
            this.hraBezi = false;
        }
    }
}
