class PResept extends HvitResept {
    private static int rabatt=108;

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    @Override
    public String farge(){
        return "Hvit resept";
    }
    
    @Override
    public int prisAaBetale(){                        //sjekker at pris aldri blir mindre 0
        if (legemiddel.hentPris()- rabatt <= 0){
            return legemiddel.hentPris();
        } else{
            return legemiddel.hentPris() - rabatt;
        }
    }

    @Override
    public String toString(){
        return "Legemiddel --> " + legemiddel + "Lege --> " + utskrivendeLege + ", pasientid: " + pasient.pasientId + ", reit: " + reit;
    }
}
