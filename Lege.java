class Lege implements Comparable<Lege> {
    protected String Legenavn;

    public Lege(String Legenavn){
        this.Legenavn = Legenavn;
    }
    public String hentNavn(){
        return Legenavn;
    }

//D2
    IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<Resept>();

    public IndeksertListe<Resept> hentListe(){
        return utskrevneResepter;
    }

//D3
    public HvitResept skrivHvitResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {

            HvitResept hvitresept =  new HvitResept(legemiddel, this, pasient, reit);
            utskrevneResepter.leggTil(hvitresept);
            return hvitresept;
            
    }

    public MilitaerResept skrivMilResept (Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift{
      
        MilitaerResept militaerResept = new MilitaerResept(legemiddel, this, pasient, 0);
        utskrevneResepter.leggTil(militaerResept);
        return militaerResept;

    }
    
    public PResept skrivPResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{

        PResept pResept = new PResept(legemiddel, this, pasient, 0);
        utskrevneResepter.leggTil(pResept);
        return pResept;


    }

    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        if (legemiddel instanceof NarkotiskLegemiddel){
            throw new UlovligUtskrift(this, legemiddel);
        }

        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, 0);
        utskrevneResepter.leggTil(blaaResept);
        return blaaResept;


    }


    @Override
    public int compareTo(Lege annen){
        int legen = Legenavn.compareTo(annen.Legenavn);
        return legen;
    }

    @Override   //huske å fikse den her
    public String toString(){
        return Legenavn;
    }
}
