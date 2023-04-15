package hi.bouncytower.vinnsla;

public enum Stefna {
    VINSTRI(180),
    HAEGRI(0),
    UPP(90),
    NIDUR(270);

    private final int stefna;

    /**
     * Smiður fyrir stefnu hlut
     * @param stefna heiltala fenginn frá KeyCode sem passar við mögulegar stefnur
     */
    Stefna(int stefna){this.stefna = stefna;}

    /**
     * Getter fyrir stefnu
     * @return heiltölu gildi stefnunnar
     */
    public int getStefna(){return stefna;}
}
