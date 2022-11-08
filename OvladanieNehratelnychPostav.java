import java.util.ArrayList;

public class OvladanieNehratelnychPostav {
    private ArrayList<NehratelnaPostava> zoznamNP;
    private Mapa mapa;
    public OvladanieNehratelnychPostav(Mapa mapa) {
        this.zoznamNP = new ArrayList<NehratelnaPostava>();
        this.mapa = mapa;
    }

    /**
     * 
     * @param poskodenie ktore dostane np od hraca
     * @param poloha hraca vzhladom na mapu
     */
    public void dostanPoskodenie(int poskodenie, int[] poloha) {
        for (NehratelnaPostava np: this.zoznamNP) {
            if (np.getPoloha()[0] == poloha[0] && np.getPoloha()[1] == poloha[1]) {
                np.uberZivot(poskodenie);
                return;
            }
        }
    }
    public void vykonajNPAkcie() {
        for (NehratelnaPostava postava: this.zoznamNP) {
            // todo vsetko co bude kazdy tik cekovat np
            //todo ci je na mape a ma sa dostat ku hracovi
            //alebo je blizko neho a ma mu dat poskodenie
        }
    }
}
