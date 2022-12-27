
/**
 * Trieda zabezpecuje celu kontorlu nad programom skrz komunikaciu manazera so zvyskom hry.
 */
public class Hra {
    private OvladanieHracom ovladanieHracom;
    private Manazer manazer;
    private Mapa mapa;
    private TovarenNaNehratelnePostavy tovaren;
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
        int pocetNP = 10;

        this.mapa = new Mapa(x, y, "prvyLevel1", velkostMapy);
        this.ovladanieHracom = new OvladanieHracom(x, y, this.mapa, rycholostHraca, poskodenie, zivot);
        this.ovladanieHracom.spojHru(this);
        new TovarenNaNehratelnePostavy(pocetNP, this.mapa); //po vytvoreni postav dropnem celu instanciu z pamate;
        this.ovladanieNP = new OvladanieNehratelnychPostav(this.mapa);
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this.ovladanieHracom);
        this.manazer.spravujObjekt(this);
        System.out.println("[INFO]: hra nacitana");
    }
        // kazdy tik musim: sa opytat ci su npc na obrazovke, a potom robit pohyb
    public void tik() {
        //System.out.println("tik");
        this.ovladanieHracom.pohybHraca();
        this.ovladanieHracom.utocenie();
        this.ovladanieNP.vykonajNPAkcie();
    }

    public void podajDamage(int[] polohaHraca, int poskodenie) {
        this.ovladanieNP.dostanPoskodenie(poskodenie, polohaHraca);
    }
}
