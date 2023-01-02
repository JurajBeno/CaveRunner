
/**
 * Trieda zabezpecuje kontrolovanie stien mapy a zmeny polohy mapy.
 */
public class Mapa {
    private int x;
    private int y;
    private Obrazok mapaAkoObr;
    private SietPreMapu sietPreMapu;
    private int velkostMapy;
    
    /** 
     * @param x stred mapy na horizontalnej osi
     * @param y stred mapy na vertikalnej osi
     * @param nazovLevelu aby mohol byt dany obrazok mapy
     */
    public Mapa(int x, int y, String nazovLevelu, int velkostMapy) {
        this.sietPreMapu = new SietPreMapu(nazovLevelu);

        this.x = x;
        this.y = y;
        this.mapaAkoObr = new Obrazok(String.format("TiledResources\\%s.png", nazovLevelu));
        this.mapaAkoObr.zmenPolohu(x, y);
        this.mapaAkoObr.zobraz();
        this.velkostMapy = velkostMapy * 4;
        System.out.println("[INFO]: mapa nacitana");
    }

    public void vynulujMapu(int x, int y) {
        this.mapaAkoObr.zmenPolohu(x, y);
    }

    /**
     * Vrati hodnotu z pola mapy na danych suradniciach.
     */
    public int getPrvokMapy(int r, int s) {
        return this.sietPreMapu.getHodnotu(r, s);
    }

    /** 
     * Posunie obrazok mapy
     * o hodnotu @param posun
     * v  danom @param smer 
     */
    public void posunPozadie(int posun, int[] smer) {
        if (smer[0] != 0) {
            this.y += posun * smer[0];
            this.mapaAkoObr.zmenPolohu(this.x, this.y);
        } else if (smer[1] != 0) {
            this.x += posun * smer[1];
            this.mapaAkoObr.zmenPolohu(this.x, this.y);
        }
    }

    /** Vrati velkost mapy */
    public int getVelkostMapy() {
        return this.velkostMapy;
    }
}
