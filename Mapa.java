
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

    public SietPreMapu getSietPreMapu() {
        return this.sietPreMapu;
    }
    
    public void posunMapyHorizontalne(int posun) {
        this.x -= posun;
        this.mapaAkoObr.zmenPolohu(this.x, this.y);
    }
    
    public void posunMapyVertikalne(int posun) {
        this.y += posun;
        this.mapaAkoObr.zmenPolohu(this.x, this.y);
    }

    public int getVelkostMapy() {
        return this.velkostMapy;
    }
    
}
