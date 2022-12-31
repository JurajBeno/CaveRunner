
/**
 * Trieda zabezpecuje celu kontorlu nad programom skrz komunikaciu manazera so zvyskom hry.
 */
public class Hra {
    private OvladanieHracom ovladanieHracom;
    private Manazer manazer;
    private Mapa mapa;
    private OvladanieNehratelnychPostav ovladanieNP;
    /**
     * NP = NehratelnaPostava (programom ovladana)
     */
    
    public Hra() {
        //TODO ovladanieNehratelnych postav, spustenie a ukoncienie hry
        //nastavenia:
        int y = 240;
        int x = 426;
        int rycholostHraca = 8;
        int velkostMapy = 65;
        int poskodenie = 32;
        int zivot = 256;
        int pocetNP = 1;

        this.mapa = new Mapa(x, y, "prvyLevel1", velkostMapy);
        this.ovladanieHracom = new OvladanieHracom(x, y, this.mapa, rycholostHraca, poskodenie, zivot, this);
        this.ovladanieNP = new OvladanieNehratelnychPostav(this.mapa, this, pocetNP);
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this.ovladanieHracom);
        this.manazer.spravujObjekt(this);
        System.out.println("[INFO]: hra nacitana");
    }
        // kazdy tik musim: sa opytat ci su npc na obrazovke, a potom robit pohyb
    public void tik() {
        this.ovladanieHracom.skontrolujZivot();
        this.ovladanieHracom.vykonajAkcie();
        this.ovladanieNP.vykonajNPAkcie(this.ovladanieHracom.getPoziciaHracaNaMape());
    }

    public void podajDamageHracovi(int poskodenie) {
        this.ovladanieHracom.dostanPoskodenie(poskodenie);
    }

    public void podajDamage(int[] polohaHraca, int poskodenie) {
        System.out.println(polohaHraca[0] + " " + polohaHraca[1]);
        this.ovladanieNP.dostanPoskodenie(poskodenie, polohaHraca);
    }

    public int[] getStredObrazovky() {
        return this.ovladanieHracom.getPoziciaHracaNaMape();
    }

    public void posunNehratelnePostavy(int posun, int[] smer) {
        this.ovladanieNP.posunKazduPostavu(posun, smer);
    }
}
