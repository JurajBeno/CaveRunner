
public class Mapa {
    private int x;
    private int y;
    private Obrazok mapaAkoObr;
    private SietPreMapu sietPreMapu;
    private int velkostMapy;
    
    public Mapa(int x, int y, String nazovLevelu, int velkostMapy) {
        this.sietPreMapu = new SietPreMapu(nazovLevelu);
        this.x = x;
        this.y = y;
        this.mapaAkoObr = new Obrazok(String.format("TiledResources\\%s.png", nazovLevelu));
        this.mapaAkoObr.zmenPolohu(x, y);
        this.mapaAkoObr.zobraz();
        this.velkostMapy = velkostMapy;
        System.out.println("[INFO]: mapa nacitana");
    }

    public int getPrvokMapy(int r, int s) {
        return this.sietPreMapu.getHodnotu(r, s);
    }

    public void posunPozadie(int posun, int[] smer) {
        if (smer[0] != 0) {
            this.y += posun * smer[0];
            this.mapaAkoObr.zmenPolohu(this.x, this.y);
        } else if (smer[1] != 0) {
            this.x += posun * smer[1];
            this.mapaAkoObr.zmenPolohu(this.x, this.y);
        }
    }

    public int getVelkostMapy() {
        return this.velkostMapy;
    }
}
