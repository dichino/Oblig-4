class TestResepter {
    public static void main(String[] args){
        Legemiddel legemidde = new VanligLegemiddel("morfin", 300, 5);
        Lege lege = new Lege("ayuub");
        Pasient p = new Pasient("ayuub", "20304321");

        Resept millR = new MilitaerResept(legemidde, lege, p,10);
        Resept presept = new PResept(legemidde, lege, p, 20);
        Resept bloresep = new BlaaResept(legemidde, lege, p, 10);

//test for millitær resepten
        System.out.println("tester for millitær resept: ");
        System.out.println(testFarge(millR, "Hvit resept") == true);
        System.out.println(testPris(millR, millR.prisAaBetale()) == true);

//test for p  resept
        System.out.println("tester for Presept: ");
        System.out.println(testFarge(presept, "Hvit resept") == true);
        System.out.println(testPris(presept, presept.prisAaBetale()) == true);

//test for blå resept 
        System.out.println("tester for blå resept: ");
        System.out.println(testFarge(bloresep, "Blå resept") == true);
        System.out.println(testPris(bloresep, bloresep.prisAaBetale()) == true);
    }

//test program for å sjekke om pris/farge er riktig
    private static boolean testFarge(Resept resept, String farge){
        return resept.farge().equals(farge); 
    }

    private static boolean testPris(Resept resept, int pris){
        return resept.prisAaBetale() == pris;
    }
}
