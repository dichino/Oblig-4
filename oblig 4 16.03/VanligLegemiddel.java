class VanligLegemiddel extends Legemiddel {

    public VanligLegemiddel(String navn, int pris, double virkestoff){
        super(navn, pris, virkestoff);
    }
    
    @Override
    public String toString(){
        return "navn: " + navn + ", pris: " + pris + ", virkestoff: " + virkestoff +"\n";
    }
    
}