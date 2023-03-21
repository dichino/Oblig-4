class Spesialister extends Lege implements Godkjenningsfritak  {
    protected String kontrollKode;
  


    public Spesialister(String Legenavn, String kontrollKode){
        super(Legenavn);
        this.kontrollKode = kontrollKode;
    }

    @Override
    public String hentKontrollkode(){
        return kontrollKode;
    }

    @Override
    public String toString(){
        return "navn " + Legenavn + ", kontroll kode: " + kontrollKode;
    }

//ny metode D3
    @Override
    public BlaaResept skrivBlaaResept (Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift{
        
        BlaaResept blaaResept = new BlaaResept(legemiddel, this, pasient, 0);
        utskrevneResepter.leggTil(blaaResept);
        return blaaResept;


    }

 }