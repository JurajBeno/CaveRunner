
public class PoleSieteObal {
    private int[][] poleSiete;
    public PoleSieteObal(int[][] poleSiete) {
        this.poleSiete = poleSiete;
    }
    
    public int getPrvok(int r, int s) {
        return this.poleSiete[r][s];
    }
    
    public int getPocetRiadkov() {
        return this.poleSiete.length;
    }
    
    public int getPocetStlpcov() {
        return this.poleSiete[0].length;
    }
}
