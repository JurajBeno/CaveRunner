
/**
 * Enumeration class AkciaNehratelnejPostavy - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum AkciaNehratelnejPostavy {
    STOJ("", "Stoj", 0),
    UTOC_VPRAVO("Vpravo", "utok", 5),
    UTOC_VLAVO("Vlavo", "utok", 5);
    
    private final String otocenieAnimacie;
    private final String nazovAkcie;
    private final int najvacsiIndexAnimacie;
    
    /**
     * @param x a y suradnice v ktorom smere sa cinnost deje
     * @param otocenieAimacie je smer animacie v ktorom sa deje
     */
    AkciaNehratelnejPostavy(String otocenieAnimacie, String nazovAkcie, int najvacsiIndexAnimacie) {
        this.otocenieAnimacie = otocenieAnimacie;
        this.nazovAkcie = nazovAkcie;
        this.najvacsiIndexAnimacie = najvacsiIndexAnimacie;
    }

    /** @return nazov smeru ako string pre ovladanie animacii. */
    public String getOtocenieAnimacie() {
        return this.otocenieAnimacie;
    }

    /** 
     * @return nazov akcie ako string pre ovladanie animacii
     */
    public String getNazovAkcie() {
        return this.nazovAkcie;
    }

    /**
     * @return index poslednej animacie v cykle
     */
    public int getNajvacsiIndexAnimacie() {
        return this.najvacsiIndexAnimacie;
    }

}
