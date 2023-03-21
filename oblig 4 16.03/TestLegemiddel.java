class TestLegemiddel {
    public static void main (String[] args){
        Legemiddel vanlig = new VanligLegemiddel("paracet", 50, 2.4);
        Legemiddel narko = new NarkotiskLegemiddel("morfin", 300, 5, 9);
        Legemiddel vane = new VanedannendeLegemiddel("nesesprat", 150, 3, 5);

        System.out.println("tester for vanlig legemiddel: ");
        System.out.println(testLegemiddelId(vanlig, 0) == true);

        System.out.println("tester for narkotisk Legemiddel ID: ");
        System.out.println(testLegemiddelId(narko, 1) == true);

        System.out.println("tester for vanedannende legemiddel ID: ");
        System.out.println(testLegemiddelId(vane, 2) == true);

        System.out.println(vanlig);



    }   
    private static boolean testLegemiddelId(Legemiddel legemiddel,
    int forventetLegemiddelId) {
    return legemiddel.id == forventetLegemiddelId;
}

    
}
