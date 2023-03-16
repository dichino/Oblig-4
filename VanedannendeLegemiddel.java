class VanedannendeLegemiddel extends Legemiddel {
    public final int styrke;

    public VanedannendeLegemiddel(String navn, int pris, double virkestoff, int styrke){
        super(navn, pris, virkestoff);
        this.styrke = styrke;
    }

    @Override
    public String toString(){
        return "navn: " + navn + ", pris: " + pris + ", virkestoff: " + virkestoff + ", id: " + id + ", Styrke: " + styrke + "\n";
    }
}
