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
        try {
            fil = new Scanner(new File(filnavn));
        } catch (FileNotFoundException e) {
            System.out.println("Fil ikke funnet: "+ filnavn);
            System.exit(-1);
        }


        int teller = 0;
        while (fil.hasNextLine()){
            String linje = fil.nextLine();

            String pekerLinje[] = linje.split(" ");

            System.out.println(pekerLinje[0]);
            if (pekerLinje[0].equals("#")){ //finner # / overskrift
                String objekt = pekerLinje[1];
                System.out.println(teller);
                teller++;

                while (fil.hasNextLine()){
                    String linje2 = fil.nextLine();
                    String pekerLinje2[] = linje.split(" ");
                    System.out.println(teller);
                    teller++;

                    //lager objekter og putter det i array-ene
                    while((pekerLinje2[0].equals("#")) != true) { //Kjører helt til ny overskrift
                        String biter[] = linje2.split(","); //Splitter linjen på ","
                        System.out.println(teller);
                        teller++;

                        if(objekt.equals("Pasienter")){
                            String navn = biter[0];
                            String fnr = biter[1];
                            Pasient nyPasient = new Pasient(navn,fnr); //oppretter pasient-objekt¨
                            pasienterListe.leggTil(nyPasient); //legger til i Indekserte liste

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