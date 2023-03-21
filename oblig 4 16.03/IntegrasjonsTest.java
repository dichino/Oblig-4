class IntegrasjonsTest {
    public static void main(String[] args){
//oppretter lege
        Lege lege1 = new Lege("Ali");
        Lege lege2 = new Lege("Per");

//ny pasient
        Pasient p = new Pasient("ayuub", "293295432");

//oppretter spesialist lege
        Lege spesialistLege = new Spesialister("Mohamed", "jaja1");

//oppretter lege middler
        Legemiddel vanlig = new VanligLegemiddel("paracet", 180, 2.4);
        Legemiddel narkotisk = new NarkotiskLegemiddel("morfin", 300, 5, 9);
        Legemiddel vanedannende = new VanedannendeLegemiddel("nesespray", 150, 3, 5); 

//oppretter resepter
        Resept pR = new PResept(vanlig, lege1, p, 15);
        Resept millR = new MilitaerResept(narkotisk, spesialistLege, p, 10);
        Resept bloR = new BlaaResept(vanedannende, lege2, p,  10);

//skriver ut for resepter, med sine leger og legemidler
//velger å ikke printe ut lege og legemiddel for seg selv ettersom reseptene inneholder de verdiene
        System.out.println(bloR+ "\n");
        System.out.println(millR + "\n");
        System.out.println(pR +"\n");

//sklriver ut prisen på de ulike legemidlene
        System.out.println("pris på de ulike legemidler: ");
        System.out.println(pR.prisAaBetale()); 
        System.out.println(millR.prisAaBetale());
        System.out.println(bloR.prisAaBetale() +"\n");

//sjekker om id til resept og legemiddel er samme
        System.out.println("sjekker om id til resept & legemiddel er like: ");
        System.out.println(vanlig.henti());
        System.out.println(pR.hentID());

        System.out.println(narkotisk.henti());
        System.out.println(millR.hentID());
        
        System.out.println(vanedannende.henti());
        System.out.println(bloR.hentID());

       
    }
}
