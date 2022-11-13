import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SietPreMapu {
    private String nazovMapy;
    private int[][] mapa;

    public SietPreMapu(String nazovMapy) {
        this.nazovMapy = nazovMapy;
        this.mapa = this.ziskajMapu();
    }

    public int[][] getSiet() {
        return this.mapa;
    }

    public void vypisMapu() {
        for (int[] riadok: this.mapa) {
            for (int i: riadok) {
                System.out.print(i);
            }
            System.out.println();
        }
    }
    //todo urobim to na hotovej mape nie takto ze kazdy riadok, bo z tochoto by ma drislo
    private int[][] stvorNasobRiadok(int[] riadok, int[][] stavacSiete, int cisloRiadku) {
        int poziciaVRiadku = 0;
        for (int policko : riadok) {
            for (int i = 0; i < 4; i++) {
                System.out.println(policko);
                System.out.println(cisloRiadku);
                System.out.println(i);
                stavacSiete[cisloRiadku][poziciaVRiadku + i] = policko;
            }
            poziciaVRiadku += 1 + 4;
        }
        return stavacSiete;
    }

    private int[][] ziskajMapu() {
        String cesta = String.format("TiledResources\\%s_walls.csv", this.nazovMapy);
        String riadok = "";
        int[][] stavacMapy = new int[65 * 4][65 * 4];
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
                stavacMapy = this.stvorNasobRiadok(riadokHodnot, stavacMapy, cisloRiadku); 
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
