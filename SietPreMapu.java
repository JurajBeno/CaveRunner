import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SietPreMapu {
    private String nazovMapy;
    private int[][] mapa;

    public SietPreMapu(String nazovMapy) {
        this.nazovMapy = nazovMapy;
        int[][] sietZCSV = this.ziskajMapu();
        this.mapa = sietZCSV;
        this.mapa = this.zvacMapuStvornasobne(sietZCSV);
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

    //todo dobre teraz len musim opravit to aby to hadzalo po styri riadky, lebo zatial to len 4x predlzi kazdy riadok
    private int[][] zvacMapuStvornasobne(int[][] siet) {
        int[][] vyslednaSiet = new int[65 * 4][65 * 4];
        for (int i = 0; i < siet.length * 4 ; i += 4) {
            int poziciaVRiadku = 0;
            for (int j = 0; j < siet.length; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.println(i + " " + poziciaVRiadku + " " + k);
                    vyslednaSiet[i][poziciaVRiadku + k] = siet[i][j]; // todo upravit to tu aby som len vytvoril riadok a dalsi for cyklus ktory ho zostvori
                }

                poziciaVRiadku += 4;
            }
        }
        return vyslednaSiet;
    }

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
