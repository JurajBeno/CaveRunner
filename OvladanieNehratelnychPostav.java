import java.util.ArrayList;

public class OvladanieNehratelnychPostav {
    private ArrayList<NehratelnaPostava> zoznamNP;
    private Mapa mapa;
    public OvladanieNehratelnychPostav(Mapa mapa) {
        this.zoznamNP = new ArrayList<NehratelnaPostava>();
        this.mapa = mapa;
    }

    public void hladajCestu(int[] stredObrazovky) {
        for (NehratelnaPostava postava: this.zoznamNP) {
            if (postava.jePostavaNaObrazovke(stredObrazovky)) {
                // todo path finiding
            }
        }
    }

    public void dostanPoskodenie(int poskodenie, int[] poloha) {
        for (NehratelnaPostava np: this.zoznamNP) {
            if (np.getPoloha()[0] == poloha[0] && np.getPoloha()[1] == poloha[1]) {
                np.uberZivot(poskodenie);
                return;
            }
        }
    }
}
