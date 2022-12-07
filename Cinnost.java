/** enumerator zabezpecenie presunu informacii o smere
 * pohybu hraca a spravnom smere otocenia animacie
 * 
 */
public enum Cinnost {
    CHOD_HORE(-1, 0, "Hore", "chod"),
    CHOD_DOLU(1, 0, "Dolu", "chod"),
    CHOD_VPRAVO(0, 1, "Vpravo", "chod"),
    CHOD_VLAVO(0, -1, "Vlavo", "chod"),
    UTOC_VPRAVO(0, 1, "Vpravo", "utoc"),
    UTOC_VLAVO(0, -1, "Vlavo", "utoc");

    private final int y;
    private final int x;
    private final String otocenieAnimacie;
    private final String nazovCinnosti;
    
/**@param x, y su v ktorom smere sa cinnost deje
 * @param otocenieAimacie je smer animacie v ktorom sa deje
*/
    private Cinnost(int y, int x, String otocenieAnimacie, String nazovCinnosti) {
        this.y = y;
        this.x = x;
        this.otocenieAnimacie = otocenieAnimacie;
        this.nazovCinnosti = nazovCinnosti;
    }

    public int[] getSmer() {
        return new int[] {this.y, this.x};
    }

    public String getOtocenieAnimacie() {
        return this.otocenieAnimacie;
    }

    public String getNazovCinoosti() {
        return this.nazovCinnosti;
    }
}