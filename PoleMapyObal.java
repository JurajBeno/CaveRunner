/** Obalova trieda dvojrozmerneho pola pre mapu. */
public class PoleMapyObal {
    private int[][] poleMapy;
    /** 
     * @param dvojrozmerne pole
     */
    public PoleMapyObal(int[][] poleMapy) {
        this.poleMapy = poleMapy;
    }
    
    /** @return hodnotu z pola na danych suradniciach.  */
    public int getPrvok(int r, int s) {
        return this.poleMapy[r][s];
    }
    
    /** @return pocet riadkov pola. */
    public int getPocetRiadkov() {
        return this.poleMapy.length;
    }
    
    /** @return pocet stlpcov pola.*/
    public int getPocetStlpcov() {
        return this.poleMapy[0].length;
    }
}
