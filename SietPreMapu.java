import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Trieda ktora precita subor s CSV formatom mapy
 * a nasledne ho 4-krat zvacsi pre vacsiu plynulost pohybu v hre
 * makoniec ho ulozi do Obalovej triedy PoleMapyObal
 */
public class SietPreMapu {
    private String nazovMapy;
    private PoleMapyObal mapa;

    public SietPreMapu(String nazovMapy) {
        this.nazovMapy = nazovMapy;
        int[][] sietZCSV = this.ziskajMapu();
        this.mapa = new PoleMapyObal(this.zvacMapu(sietZCSV));
    }

    /** vrati hodnotu daneho prvku z pola mapy */
    public int getHodnotu(int r, int s) {
        return this.mapa.getPrvok(r, s);
    }

    /* 
     * mapa musi byt 4-krat zvacsena pre plynulejsi pohyb,
     * skusal som to riesit rozdelenim policka na rovnake casti pouzitim modulo
     * ale potom mi postavicka liezla do stien
     */
    private int[][] zvacMapu(int[][] mapaNaZvacsenie) {
        int[][] vysledok = new int[65 * 4][65 * 4];
        int poradieRiadkuMapy = 0;
        for (int[] riadok: mapaNaZvacsenie) {
            int[] novyRiadok = new int[65 * 4];
            int poradieCislaRiadku = 0;
            for (int cislo: riadok) {
                for (int i = 0; i < 4; i++) {
                    novyRiadok[poradieCislaRiadku + i] = cislo;
                }
                poradieCislaRiadku += 4;
            }
            for (int i = 0; i < 4; i++) {
                vysledok[poradieRiadkuMapy + i] = novyRiadok;
            }
            poradieRiadkuMapy += 4;
        }
        return vysledok;
    }
    
    /* 
     * finally-> try, catch vygenerovane cez VS code
     * dost mi tu pomohol tento navod
     * https://www.baeldung.com/java-buffered-reader
     * 
     */
    private int[][] ziskajMapu() {
        String cesta = String.format("TiledResources\\%s_walls.csv", this.nazovMapy);
        String riadok = "";
        int[][] stavacMapy = new int[65][65];
        BufferedReader nahravac = null;
        try {
            int cisloRiadku = 0;
            nahravac = new BufferedReader(new FileReader(cesta));
            while ((riadok = nahravac.readLine()) != null) {
                String[] riadokStringov = riadok.split(",");
                int[] riadokHodnot = new int[65];
                for (int i = 0; i < riadokStringov.length; i++) {
                    riadokHodnot[i] = Integer.parseInt(riadokStringov[i]);
                }
                stavacMapy[cisloRiadku] = riadokHodnot;
                cisloRiadku += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                nahravac.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stavacMapy;
    }
}
