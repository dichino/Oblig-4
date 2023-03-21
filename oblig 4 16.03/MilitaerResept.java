 class MilitaerResept extends HvitResept{
    
    public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        super(legemiddel, utskrivendeLege, pasient, 3);
    }

    @Override
    public String farge(){
        return "Hvit resept";
    }
    
    @Override
    public int prisAaBetale(){
        return 0;
    }
    
    @Override
    public String  toString(){
        return "Legemiddel --> " + legemiddel + "Lege --> " + utskrivendeLege + ", pasientid: " + pasient.pasientId + ", reit: " + reit;
    }
}
