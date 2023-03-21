import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Legesystem_copy {
    String filnavn;

    public Legesystem_copy(String filnavn){
        this.filnavn = filnavn;
    }

        IndeksertListe <Pasient> pasienterListe = new IndeksertListe<>();
        IndeksertListe <Lege> legeListe = new IndeksertListe<>();
        IndeksertListe <Legemiddel> legemidlerListe = new IndeksertListe<>();


// Del E1
    public void settInnFil() throws FileNotFoundException{
        Scanner fil = null;
        Scanner fil2 = null;

        try {
            fil = new Scanner(new File(filnavn));
            fil2 = new Scanner(new File(filnavn));
        } catch (FileNotFoundException e) {
            System.out.println("Fil ikke funnet: "+ filnavn);
            System.exit(-1);
        }


        int teller = 0;
        while (fil.hasNextLine()){
            String linje = fil.nextLine();

            String pekerLinje[] = linje.split(" ");

            if (pekerLinje[0].equals("#")){ 
                System.out.println(teller+ " -- HALLA");
                teller ++ ;
                } else {
                System.out.println(teller+ " -- IF NR 2");

                if(teller == 1){
                    String biter[] = linje.split(",");
                    String navn = biter[0];
                    String fnr = biter[1];
                    Pasient nyPasient = new Pasient(navn,fnr); //oppretter pasient-objekt¨
                    pasienterListe.leggTil(nyPasient); //legger til i Indekserte liste

                }else if(teller == 2){
                    String biter[] = linje.split(",");
                    String navn = biter[0];
                    String type = biter[1];
                    int pris =Integer.parseInt(biter[2]);
                    Double virkestoff = Double.parseDouble(biter[3]);

                    if ( type.equals("narkotisk")){ //oppretter narkotisk-objekt
                        int styrke = Integer.parseInt(biter[4]);
                        NarkotiskLegemiddel nyNarkotiskLegemiddel = new NarkotiskLegemiddel(navn, pris, virkestoff, styrke);
                        legemidlerListe.leggTil(nyNarkotiskLegemiddel);//legger til i Indekserte liste

                    } else if (type.equals("vanedannende")){ //oppretter vanedannende-objekt
                        int styrke = Integer.parseInt(biter[4]); // De andre har ikke index 4
                        VanedannendeLegemiddel nyVanedannendeLegemiddel = new VanedannendeLegemiddel(navn, pris, virkestoff, styrke);
                        legemidlerListe.leggTil(nyVanedannendeLegemiddel);//legger til i Indekserte liste

                    } else if (type.equals("vanlig")){ //oppretter vanlig-objekt
                        VanligLegemiddel nyVanligLegemiddel =  new VanligLegemiddel(navn, pris, virkestoff);
                        legemidlerListe.leggTil(nyVanligLegemiddel);//legger til i Indekserte liste

                    } else { //hvis feil format
                        System.out.println("Feil format, kunne ikke legge inn legemiddelet: "+navn);
                    }
                }else if(teller == 3){  
                    String biter[] = linje.split(",");
                    String navn = biter[0];
                    String kontrollID = biter[1];

                    if(kontrollID.equals("0")){ //oppretter vanlig lege
                        Lege nyLege = new Lege(navn);
                        legeListe.leggTil(nyLege);//legger til i Indekserte liste

                    } else if (kontrollID.equals("0") == false){ //oppretter spesialist
                        Spesialister nySpesialist = new Spesialister(navn, kontrollID);
                        legeListe.leggTil(nySpesialist);//legger til i Indekserte liste

                    } else { //hvis feil format
                        System.out.println("Feil format, kunne ikke legge inn Legen: "+navn);
                    }
                }else if(teller == 4){
                    String biter[] = linje.split(",");

                    int legemiddelNummer = Integer.parseInt(biter[0]);
                    String legeNavn =  biter[1];
                    int pasientID = Integer.parseInt(biter[2]);
                    String type = biter[3];
                    Legemiddel legemiddel = legemidlerListe.hent(legemiddelNummer-1);
                    Pasient pasient = pasienterListe.hent(pasientID-1);


                    int stopper = 0;
                    int indeks = 0;
                    int stoerrelse = legeListe.stoerrelse(); 
                    if(stoerrelse != 0){
                        while(stopper == 0 && indeks < stoerrelse){                            //kjører så lenge indeks < 0, og stopper er 0
                            if (legeListe.hent(indeks).Legenavn.equals(legeNavn)){             //sammenlikner legen i legelista med legen som er i txt
                                Lege lege = legeListe.hent(indeks);

                                if(type.equals("hvit")){                              //skriver resept for hvis type er hvit
                                    try {
                                        int reit = Integer.parseInt(biter[4]); 
                                        lege.skrivHvitResept(legemiddel, pasient, reit);
                                        stopper++;
                                    } catch (Exception e) {
                                        System.out.println("kunne ikke lage resept");
                                    }

                                } else if(type.equals("blaa")){                         //skriver resept for hvis type er blå-resept
                                    try {
                                        int reit = Integer.parseInt(biter[4]);
                                        lege.skrivBlaaResept(legemiddel, pasient, reit);
                                        stopper++;
                                    } catch (UlovligUtskrift e) {
                                        System.out.println("kunne ikke lage resept");
                                    }

                                } else if(type.equals("militaer")){                   //skriver resept for hvis type er milltær-resept
                                    try {
                                        lege.skrivMilResept(legemiddel, pasient); //militaer har allerede reit:3
                                        stopper++;
                                    } catch (UlovligUtskrift e) {
                                        System.out.println("kunne ikke lage resept");
                                    }

                                } else if (type.equals("p")){                          //skriver resept for hvis type er p-resept
                                    try {
                                        int reit = Integer.parseInt(biter[4]);
                                        lege.skrivPResept(legemiddel, pasient, reit); 
                                        stopper++;  
                                    } catch (UlovligUtskrift e) {
                                        System.out.println("kunne ikke lage resept");
                                    }

                                } else{                                                         
                                    System.out.println("ugyldig type");
                                }
                            } else {
                                indeks++;                                                       //øker indeks med 1, for å gå en plass videre i legelista
                            }
                        }
                      
                    }

                }





            }
        }

        }



//del E3
    public void skrivUt(){
        System.out.println("\n--Pasienter i legesystemet: ");
        System.out.println(pasienterListe);
        System.out.println("\n--Leger i legesystemet: ");
        System.out.println(legeListe);
        System.out.println("\n--Legemidler i legesystemet: ");
        System.out.println(legemidlerListe);        
        System.out.println("\n--Resepter i legesystemet");

        for(int i = 0; i <legeListe.stoerrelse(); i++){
            Lege lege = legeListe.hent(i);
            IndeksertListe resepter = lege.utskrevneResepter;

            System.out.println("\n--Utskrivende lege "+lege.Legenavn);
            System.out.println(resepter);
        }


    }




}






/*
 *   if(stopper != 1){ //hvis vi har gått gjennom hele og ikke funnet lege må vi opprette en lege
                                    Lege nyLege = new Lege(legeNavn);
                                    legeListe.leggTil(nyLege); //legger nye legen inn

                                    //SKIV KODE SENERE
                                }
 */