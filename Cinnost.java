/**
 * Enumerator zabezpecenie presunu informacii o smere
 * danej cinnosti hraca a spravnom smere otocenia animacie prisluchajucej ku danej cinnosti.
 */
public enum Cinnost {
    STOJ(0, 0, "Dolu", "Stoj"),
    CHOD_HORE(-1, 0, "Hore", "chodenie"),
    CHOD_DOLU(1, 0, "Dolu", "chodenie"),
    CHOD_VPRAVO(0, 1, "Vpravo", "chodenie"),
    CHOD_VLAVO(0, -1, "Vlavo", "chodenie"),
    UTOC_VPRAVO(0, 1, "Vpravo", "utoc"),
    UTOC_VLAVO(0, -1, "Vlavo", "utoc"),
    SMRT(0, 0, "", "");

    private final int smerPohybuY;
    private final int smerPohybuX;
    private final String otocenieAnimacie;
    private final String nazovCinnosti;
    
    /**@param x, y su v ktorom smere sa cinnost deje
     * @param otocenieAimacie je smer animacie v ktorom sa deje
     */
    Cinnost(int y, int x, String otocenieAnimacie, String nazovCinnosti) {
        this.smerPohybuY = y;
        this.smerPohybuX = x;
        this.otocenieAnimacie = otocenieAnimacie;
        this.nazovCinnosti = nazovCinnosti;
    }

    /** Vrati smer akym sa hrac bude hybat. */
    public int[] getSmer() {
        return new int[] {this.smerPohybuY, this.smerPohybuX};
    }

    /** Vrati nazov smeru ako string pre ovladanie animacii. */
    public String getOtocenieAnimacie() {
        return this.otocenieAnimacie;
    }

    /** Vrati nazov cinnosti ako string pre ovladanie animacii */
    public String getNazovCinoosti() {
        return this.nazovCinnosti;
    }
}