class HvitResept extends Resept {
    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    
    @Override
    public String farge(){
        return "Hvit resept";
    }

    @Override
    public String toString(){
        return "Legemiddel --> " + legemiddel + "Lege --> " + utskrivendeLege + ", pasientid: " + pasient.pasientId + ", reit: " + reit;
    }

    @Override
    public int prisAaBetale(){
        return legemiddel.hentPris();
    }

}
