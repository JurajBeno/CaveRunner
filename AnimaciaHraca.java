
public class AnimaciaHraca {
    private Obrazok aktualnaPodoba;
    private String smer;
    private int cisloAnimacie;
    private String cinnost;

    public AnimaciaHraca(int x, int y) {
        this.smer = "DoPrava";
        this.cisloAnimacie = 0;
        this.cinnost = "chodenie";
        this.aktualnaPodoba = new Obrazok(String.format("animacie\\%s%s\\%s%s%s.png", this.cinnost, this.smer, this.cinnost, this.smer, this.cisloAnimacie));
        this.aktualnaPodoba.zmenPolohu(x + 3, y);
        this.aktualnaPodoba.zobraz();
    }

    public void zobrazHraca() {
        this.aktualnaPodoba.zobraz();
    } 

    public int getCisloAnimacie() {
        return this.cisloAnimacie;
    }

    public void setCisloAnimacie(int cislo) {
        this.cisloAnimacie = cislo;
    }

    public void setCinnost(String cinnost) {
        this.cinnost = cinnost;
    }

    public void posunAnimaciuUtoku() {
        if (this.cinnost.equals("utok")) {
            this.cisloAnimacie += 1;
        } else {
            this.cisloAnimacie = 0;
        }
        this.smer = "Napravo";
        this.aktualnaPodoba.zmenObrazok(String.format("animacie\\utok%s\\utok%s%s.png", this.smer, this.smer, this.cisloAnimacie));
        this.aktualnaPodoba.zobraz();
    }

    public void posunAnimaciu(String novySmer) {
        if (novySmer.equals(this.smer)) { 
            this.cisloAnimacie = (this.cisloAnimacie + 1) % 9;
        } else {
            this.cisloAnimacie = 0;
            this.smer = novySmer;
        }
        this.aktualnaPodoba.zmenObrazok(String.format("animacie\\%s%s\\%s%s%s.png", this.cinnost, this.smer, this.cinnost, this.smer, this.cisloAnimacie));
        this.aktualnaPodoba.zobraz();
    }
}
