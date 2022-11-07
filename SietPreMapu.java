import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SietPreMapu {
    private String nazovMapy;
    private ArrayList<ArrayList<Integer>> mapa;

    public SietPreMapu(String nazovMapy) {
        this.nazovMapy = nazovMapy;
        this.mapa = this.ziskajMapu();
    }

    public ArrayList<ArrayList<Integer>> getSiet() {
        return this.mapa;
    }

    public void vypisMapu() {
        for (List<Integer> riadok: this.mapa) {
            for (int i: riadok) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    private ArrayList<ArrayList<Integer>> ziskajMapu() {
        String cesta = String.format("TiledResources\\%s_walls.csv", this.nazovMapy);
        String riadok = "";
        ArrayList<ArrayList<Integer>> stavacMapy = new ArrayList<ArrayList<Integer>>();
        BufferedReader nahravac = null;
        try {
            nahravac = new BufferedReader(new FileReader(cesta));
            while ((riadok = nahravac.readLine()) != null) {
                String[] riadokStringov = riadok.split(",");
                ArrayList<Integer> riadokHodnot = new ArrayList<Integer>();
                for (String hodnota: riadokStringov) {
                    riadokHodnot.add(Integer.parseInt(hodnota));
                }
                stavacMapy.add(riadokHodnot); 
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
