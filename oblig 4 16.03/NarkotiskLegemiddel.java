class NarkotiskLegemiddel extends Legemiddel{
    public final int styrke;

    public NarkotiskLegemiddel(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    @Override
    public String toString(){
        return "Navn: " + navn + ", pris: " + pris + ", virkestoff: " + virkestoff + ", id: " + id + " Styrke: " + styrke +"\n";
    }

}
