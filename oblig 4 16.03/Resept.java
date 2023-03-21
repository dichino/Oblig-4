abstract class Resept{
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;

    public final int ID;                            //unikt id
    public static int antallResept=0;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit){
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        
        ID = antallResept;
        antallResept++;
    }
//henter verdiene
    public int hentID(){
        return ID;
    }

    public int hentPasientId(){
        return pasient.pasientId;
    }

    public Legemiddel hentLegemiddel(){
        return legemiddel;
    }

    public Lege hentLege(){
        return utskrivendeLege;
    }

    public int hentReit(){
        return reit;
    }

    


    public boolean bruk(){
        if (reit <= 0){
            return false;
        }else{
            reit--;
        }
    return true;
    }

    abstract public String farge();
    abstract public int prisAaBetale();



}