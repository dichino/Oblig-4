import java.util.ArrayList;

//del C
public class Pasient {
    String navn;
    String fodselsnummer;
    private static int teller = 0;
    int pasientId;
    ArrayList<String> reseptListe = new ArrayList<>();  
    
    public Pasient(String navn, String fodselsnummer){
        this.navn = navn;
        this.fodselsnummer = fodselsnummer;
        pasientId = teller;
        teller++;
    }
    
    public void leggTil(String resept){
        reseptListe.add(resept);
    }

    @Override   //huske å fikse den her
    public String toString(){
        return navn;
    }
  


}
