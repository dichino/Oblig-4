abstract class Legemiddel{
//instansvariabler

    public final String navn;
    protected int pris = 0;
    public final double virkestoff;

    public final int id;                            //unikt id
    public static int antallLegemiddel=0;


    public Legemiddel(String navn, int pris, double virkestoff){
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        
        id = antallLegemiddel;
        antallLegemiddel++;
    }

    public int henti(){
        return id;
    }

    public int hentPris(){
        return pris;
    }

    public void settNyPris(int nyPris){
        pris += nyPris;
    }   

    public String toString(){
        return "navn: " + navn + ", pris: " + pris +  "kr, virkestoff: " + virkestoff + ", id: " + id;
    }

    //ekstra hjelpemetode
    public String hentNavn(){
        return navn;
    }


}