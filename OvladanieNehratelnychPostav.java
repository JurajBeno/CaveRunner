import java.util.ArrayList;
import java.util.Random;

/**
 * Trieda zabezpecuje komunikaciu a ulozenie nehratelnych postav so zvyskom programu.
 */
public class OvladanieNehratelnychPostav {
    private ArrayList<NehratelnaPostava> zoznamNP;
    private Mapa mapa;
    private Hra hra;

    /** 
     * @param mapa hry
     * @param hra
     * @param pocet nehratelnych postav (aby sme sa vyhli sekaniu odporucam najviac 4)
     */
    public OvladanieNehratelnychPostav(Mapa mapa, Hra hra, int pocetNP) {
        this.zoznamNP = new ArrayList<NehratelnaPostava>();
        this.mapa = mapa;
        this.hra = hra;

        Random rand = new Random();
        System.out.println(this.mapa.getVelkostMapy());
        
        int yPoziciaNaMape = rand.nextInt(this.mapa.getVelkostMapy() - 80) + 40;
        int xPoziciaNaMape = rand.nextInt(this.mapa.getVelkostMapy() - 80) + 40;
        while (pocetNP > 0){
            if (jePriestorVolny(yPoziciaNaMape, xPoziciaNaMape)) {
                this.zoznamNP.add(new NehratelnaPostava(xPoziciaNaMape, yPoziciaNaMape, 100, 5, mapa));
                pocetNP--;
            }
            yPoziciaNaMape = rand.nextInt(this.mapa.getVelkostMapy() - 80) + 40;
            xPoziciaNaMape = rand.nextInt(this.mapa.getVelkostMapy() - 80) + 40;
        }
        for (NehratelnaPostava np: this.zoznamNP) {
            System.out.println(np.getPoloha()[0] + " " + np.getPoloha()[1]);
        }
    }

    /** @return pocet zijucich nehratelnych postav */
    public int getPocetNP() {
        return this.zoznamNP.size();
    }

    private boolean jePriestorVolny(int y, int x) {
        return this.mapa.getPrvokMapy(y, x) == -1;
    }

    /** posunie kazdu animaciu alebo obrazok,
     * kazdej nehratelnej postavy danym @param smerom o @param posun
     */
    public void posunKazduPostavu(int posun, int[] smer) {
        for (NehratelnaPostava np: this.zoznamNP) {
            np.posunPostavu(posun, smer);
        }
    }

    /**
     * @param poskodenie ktore dostane np od hraca
     * @param poloha hraca vzhladom na mapu
     */
    public void vykonajNPAkcie(int[] poloha) {
        for (NehratelnaPostava np: this.zoznamNP) {
            if (np.jeMrtvy()) {
                this.zoznamNP.remove(np);
            }
            if (np.jeUtociaci() && np.getIndexAnimacie() == np.getAkcia().getNajvacsiIndexAnimacie() && 
            Math.abs(np.getPoloha()[0] - poloha[0]) < 3 && Math.abs(np.getPoloha()[1] - poloha[1]) < 3) {
                this.hra.podajPoskodenieHracovi(np.getPoskodenie());
                np.utoc();
            } else if (Math.abs(np.getPoloha()[0] - poloha[0]) < 3 && Math.abs(np.getPoloha()[1] - poloha[1]) < 3) {
                if (np.getPoloha()[1] - poloha[1] < 0) {
                    np.setAkcia(AkciaNehratelnejPostavy.UTOC_VPRAVO);
                } else {
                    np.setAkcia(AkciaNehratelnejPostavy.UTOC_VLAVO);
                }
                np.utoc();
            } else if (np.jeUtociaci()) {
                np.setAkcia(AkciaNehratelnejPostavy.STOJ);
                np.zmenaAnimacie();
            }
        }
    }

    /** 
     * Nehratelnej postave ktora je blizko @param polohyHraca udeli @param poskodenie
     */
    public void dostanPoskodenie(int poskodenie, int[] polohaHraca) {
        for (NehratelnaPostava np: this.zoznamNP) {
            if (Math.abs(np.getPoloha()[0] - polohaHraca[0]) < 3 && Math.abs(np.getPoloha()[1] - polohaHraca[1]) < 3) {
                
                np.uberZivot(poskodenie);
                return;
            }
        }
    }
}
