class BlaaResept extends Resept {
    public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }
    
    @Override
    public String farge(){
        return "Blå resept";
    }
    
    @Override
    public int prisAaBetale(){
       return (int)Math.round(  legemiddel.hentPris() *0.25); //avrunder til nærmeste heltall
    }

    @Override
    public String toString(){
        return "Legemiddel --> " + legemiddel + "Lege --> " + utskrivendeLege + ", pasientid: " + pasient + ", reit: " + reit;
    }

}
