/** Obal dvojrozmerneho pola pre mapu. */
public class PoleSieteObal {
    private int[][] poleSiete;
    /** 
     * @param dvojrozmerne pole
     */
    public PoleSieteObal(int[][] poleSiete) {
        this.poleSiete = poleSiete;
    }
    
    /** @return hodnotu z pola na danych suradniciach.  */
    public int getPrvok(int r, int s) {
        return this.poleSiete[r][s];
    }
    
    /** @return pocet riadkov pola. */
    public int getPocetRiadkov() {
        return this.poleSiete.length;
    }
    
    /** @return pocet stlpcov pola.*/
    public int getPocetStlpcov() {
        return this.poleSiete[0].length;
    }
}
