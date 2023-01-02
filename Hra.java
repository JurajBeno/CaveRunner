
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
     * NP = NehratelnaPostava (programom ovladana)
     */
    
    public Hra() {
        //TODO dokoncit aby bol nepriatel otoceny spravnym smerom ked utoci na hraca
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

        System.out.println("[INFO]: hra nacitana");
    }

        // kazdy tik musim: sa opytat ci su npc na obrazovke, a potom robit pohyb
    public void tik() {
        if (this.hraBezi) {
        this.ukonciHru();
        this.ovladanieHracom.skontrolujZivot();
        this.ovladanieHracom.vykonajAkcie();
        this.ovladanieNP.vykonajNPAkcie(this.ovladanieHracom.getPoziciaHracaNaMape());
       }
    }

    public void aktivuj() {
        if (!this.hraBezi) {
            this.hraBezi = true;
            this.startObrazok.skry();
            this.manazer.spravujObjekt(this.ovladanieHracom);
        }
    }

    public void podajPoskodenieHracovi(int poskodenie) {
        this.ovladanieHracom.dostanPoskodenie(poskodenie);
    }

    public void podajPoskodenieNehratelnymPostavam(int[] polohaHraca, int poskodenie) {
        System.out.println(polohaHraca[0] + " " + polohaHraca[1]);
        this.ovladanieNP.dostanPoskodenie(poskodenie, polohaHraca);
    }

    public void posunNehratelnePostavy(int posun, int[] smer) {
        this.ovladanieNP.posunKazduPostavu(posun, smer);
    }

    private void ukonciHru() {
        if (this.ovladanieHracom.jeHracMrtvy() || this.ovladanieNP.getPocetNP() == 0) {
            this.hraBezi = false;
            this.koniecHryObrazok.zobraz();
            this.manazer.prestanSpravovatObjekt(this.ovladanieHracom);
            this.manazer.prestanSpravovatObjekt(this);
        }
    }
}
