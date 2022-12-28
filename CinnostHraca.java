/**
 * Enumerator informacii o smere
 * danej cinnosti hraca a spravnom smere otocenia animacie prisluchajucej ku danej cinnosti.
 */
public enum CinnostHraca {
    STOJ(0, 0, "Dolu", "Stoj", 0),
    CHOD_HORE(-1, 0, "Hore", "chodenie", 8),
    CHOD_DOLU(1, 0, "Dolu", "chodenie", 8),
    CHOD_VPRAVO(0, 1, "Vpravo", "chodenie", 8),
    CHOD_VLAVO(0, -1, "Vlavo", "chodenie", 8),
    UTOC_VPRAVO(0, 1, "Vpravo", "utok", 7),
    UTOC_VLAVO(0, -1, "Vlavo", "utok", 7),
    SMRT_VPRAVO(0, 0, "Vpravo", "smrt", 11),
    SMRT_VLAVO(0, 0, "Vlavo", "smrt", 11);

    private final int smerPohybuY;
    private final int smerPohybuX;
    private final String otocenieAnimacie;
    private final String nazovCinnosti;
    private final int najvacsiIndexAnimacie;
    
    /**
     * @param x a y suradnice v ktorom smere sa cinnost deje
     * @param otocenieAimacie je smer animacie v ktorom sa deje
     */
    CinnostHraca(int y, int x, String otocenieAnimacie, String nazovCinnosti, int najvacsiIndexAnimacie) {
        this.smerPohybuY = y;
        this.smerPohybuX = x;
        this.otocenieAnimacie = otocenieAnimacie;
        this.nazovCinnosti = nazovCinnosti;
        this.najvacsiIndexAnimacie = najvacsiIndexAnimacie;
    }

    /** @return smer akym sa hrac bude hybat. */
    public int[] getSmer() {
        return new int[] {this.smerPohybuY, this.smerPohybuX};
    }

    /** @return nazov smeru ako string pre ovladanie animacii. */
    public String getOtocenieAnimacie() {
        return this.otocenieAnimacie;
    }

    /** @return nazov cinnosti ako string pre ovladanie animacii */
    public String getNazovCinoosti() {
        return this.nazovCinnosti;
    }

    public int getNajvacsiIndexAnimacie() {
        return this.najvacsiIndexAnimacie;
    }
}