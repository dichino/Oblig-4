import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Legesystem {
    String filnavn = "legedata.txt";

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

            if (pekerLinje[0].equals("#")){ 
                
                teller ++ ;
                } else {

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
            System.out.println(resepter);

        }


    }
// Del E4
    public void brukerLeggTil() { 
        Scanner sc = new Scanner(System.in);
        System.out.println("\nVelg type element:\n\n0-Lege\n1-Pasient\n2-Legemiddel\n3-Resept\n\nTast inn ønsket element:");
        int valg = Integer.parseInt(sc.nextLine());
        // lager lege 
        if (valg == 0){
            System.out.println("\nType lege: \n0-vanlig: lege\n1-spesialist\n\ntast inn onsket valg:" );
            int typeLege = Integer.parseInt(sc.nextLine());
            
            if(typeLege == 0){        
                System.out.println("\nskriv navn paa legen: ");
                String inputLegenavn = sc.nextLine();
                
                
                int eksisterer = 0; 
                for(Lege lege: legeListe){
                    if(lege.Legenavn.equals(inputLegenavn)){
                        System.out.println("legen eksisterer");
                        eksisterer++;
                    }
                }

                if(eksisterer == 0){
                    Lege nyLege = new Lege(inputLegenavn);
                    legeListe.leggTil(nyLege);
                }

            } else if ( typeLege == 1){
                System.out.println("\nskriv navn paa spesialisten: ");
                String inputLegenavn = sc.nextLine();
                System.out.println("\noppgi kontrollkoden: ");
                String kontrollKode = sc.nextLine();

                int eksisterer = 0;
                for(Lege lege: legeListe){
                    if(lege.Legenavn.equals(inputLegenavn)){
                        System.out.println("legen eksisterer");
                        eksisterer++;
                    }
                }

                if(eksisterer == 0){
                    Lege ny_spesialist = new Spesialister(inputLegenavn,kontrollKode);
                    legeListe.leggTil(ny_spesialist);
                }

            }
            
        }else if(valg == 1){
            System.out.println("\noppgi pasientens navn:");
            String pasientNavn = sc.nextLine();
            System.out.println("\noppgi pasientens fodselsnummer:");
            String pasientFnr = sc.nextLine();

            int eksisterer = 0;
            for (Pasient i :pasienterListe){
                if(i.fodselsnummer.equals(pasientFnr) && i.navn.equals(pasientNavn)){
                    System.out.println("Denne pasient ligger allerede i systemet");
                    eksisterer++;
                }else if(i.fodselsnummer.equals(pasientFnr)){
                    System.out.println("Denne fodselsnummeret ligger allerede i systemet");
                    eksisterer ++;
                } 
                }
                
            if(eksisterer == 0){
                Pasient nyPasient = new Pasient(pasientNavn, pasientFnr);
                pasienterListe.leggTil(nyPasient);
            }
            
        } else if (valg == 2){
            System.out.println("\nType legemiddel: \n0-vanlig\n1-vanedannende\n2-narkotisk\n\ntast inn onsket valg:" );
            int typeLegemiddel = Integer.parseInt(sc.nextLine());
            
            if(typeLegemiddel == 0){
                System.out.println("\noppgi legemiddelets navn:");
                String navn = sc.nextLine();
                System.out.println("\noppgi legemiddelets pris:");
                int pris = Integer.parseInt(sc.nextLine());
                System.out.println("\noppgi legemiddelets virkestoff:");
                Double virkestoff = Double.parseDouble(sc.nextLine());   

                int eksisterer = 0; 
                for(Legemiddel i: legemidlerListe){
                    if(i.navn.equals(navn)){
                        System.out.println("legemiddelet ligger allerede i systemet");
                        eksisterer++;
                    }
                }

                if(eksisterer == 0){
                    VanligLegemiddel nyLegemiddel = new VanligLegemiddel(navn, pris, virkestoff);
                    legemidlerListe.leggTil(nyLegemiddel);
                }

            }else if(typeLegemiddel == 1){
                System.out.println("\noppgi legemiddelets navn:");
                String navn = sc.nextLine();
                System.out.println("\noppgi legemiddelets pris:");
                int pris = Integer.parseInt(sc.nextLine());
                System.out.println("\noppgi legemiddelets virkestoff:");
                Double virkestoff = Double.parseDouble(sc.nextLine());  
                System.out.println("\noppgi legemiddelets styrke:");
                int styrke = Integer.parseInt(sc.nextLine());    

                int eksisterer = 0; 
                for(Legemiddel i: legemidlerListe){
                    if(i.navn.equals(navn)){
                        System.out.println("legemiddelet ligger allerede i systemet");
                        eksisterer++;
                    }
                }

                if(eksisterer == 0){
                    VanedannendeLegemiddel nyLegemiddel = new VanedannendeLegemiddel(navn, pris, virkestoff,styrke);
                    legemidlerListe.leggTil(nyLegemiddel);
                }
        }else if(typeLegemiddel == 2){
            System.out.println("\noppgi legemiddelets navn:");
            String navn = sc.nextLine();
            System.out.println("\noppgi legemiddelets pris:");
            int pris = Integer.parseInt(sc.nextLine());
            System.out.println("\noppgi legemiddelets virkestoff:");
            Double virkestoff = Double.parseDouble(sc.nextLine());  
            System.out.println("\noppgi legemiddelets styrke:");
            int styrke = Integer.parseInt(sc.nextLine());    

            int eksisterer = 0; 
            for(Legemiddel i: legemidlerListe){
                if(i.navn.equals(navn)){
                    System.out.println("legemiddelet ligger allerede i systemet");
                    eksisterer++;
                }
            }

            if(eksisterer == 0){
                NarkotiskLegemiddel nyLegemiddel = new NarkotiskLegemiddel(navn, pris, virkestoff,styrke);
                legemidlerListe.leggTil(nyLegemiddel);
            }


        }

        }

        else if(valg == 3){
            System.out.println("\nType Resepter: \n0-HvitResept\n1-PResept\n2-MilitaerResept\n3-BlaaResept\n\ntast inn onsket valg:" );
            int typeResept = Integer.parseInt(sc.nextLine());

            if(typeResept == 0){                                                    
                System.out.println("\noppgi Legemiddel navn:");
                String navn = sc.nextLine();
                System.out.println("\noppgi Lege:");
                String nyLege = sc.nextLine();
                System.out.println("\noppgi pasientID:");
                int pasientID = Integer.parseInt(sc.nextLine());  
                System.out.println("\noppgi reit:");
                int reit = Integer.parseInt(sc.nextLine());
                
                int eksistererNavn = 0;
                int eksistererLege = 0;
                int eksistererID = 0;
                int godkjent = 0;
                
                Lege utskrivendeLege=null;
                Legemiddel legemiddel=null;
                Pasient pasient=null;

                for(Legemiddel i : legemidlerListe){       //sjekker om legemidlet finnes
                    if (i.navn.equals(navn)){
                        eksistererNavn++;
                        legemiddel = i;
                    }
                }
                   if (eksistererNavn == 0){
                    System.out.println("legemidlet eksisterer ikke i systemet");

                   } else {
                    godkjent++;
                   }
                
                   for(Lege i : legeListe){                   //sjekker om legen finnes
                    if (i.Legenavn.equals(nyLege)){
                        eksistererLege++;
                        utskrivendeLege = i;
                    }
                }
                   if (eksistererLege == 0){
                    System.out.println("legen eksisterer ikke i systemet");
                    
                   } else {
                    godkjent++;
                   }

                   for(Pasient i : pasienterListe){       //sjekker om resept finnes
                    if (i.pasientId == pasientID){
                        eksistererID++;
                        pasient = i;
                    }
                }
                   if (eksistererID == 0){
                    System.out.println("pasientID eksisterer ikke i systemet");
                    
                   } else {
                    godkjent++;
                   }

                if (godkjent == 3){                    //oppretter hvit-resept hvis informasjon brukeren gir er riktig
                    try {
                        utskrivendeLege.skrivHvitResept(legemiddel, pasient, reit);
                    } catch (UlovligUtskrift e) {
                    }
                } else {
                    System.out.println("uglydig infomasjon gitt.");
                }
             }

//p respet
            else if(typeResept == 1){
                System.out.println("\noppgi Legemiddel navn:");
                String navn = sc.nextLine();
                System.out.println("\noppgi Lege:");
                String nyLege = sc.nextLine();
                System.out.println("\noppgi pasientID:");
                int pasientID = Integer.parseInt(sc.nextLine());  
                System.out.println("\noppgi reit:");
                int reit = Integer.parseInt(sc.nextLine());
                
                int eksistererNavn = 0;
                int eksistererLege = 0;
                int eksistererID = 0;
                int godkjent = 0;
                
                Lege utskrivendeLege=null;
                Legemiddel legemiddel=null;
                Pasient pasient=null;

                for(Legemiddel i : legemidlerListe){       //sjekker om legemidlet finnes
                    if (i.navn.equals(navn)){
                        eksistererNavn++;
                        legemiddel = i;
                        System.out.println(i);
                    }
                }
                   if (eksistererNavn == 0){
                    System.out.println("legemidlet eksisterer ikke i systemet");

                   } else {
                    godkjent++;
                   }
                
                   for(Lege i : legeListe){                   //sjekker om legen finnes
                    if (i.Legenavn.equals(nyLege)){
                        eksistererLege++;
                        utskrivendeLege = i;
                    }
                }
                   if (eksistererLege == 0){
                    System.out.println("legen eksisterer ikke i systemet");
                    
                   } else {
                    godkjent++;
                   }

                   for(Pasient i : pasienterListe){       //sjekker om resept finnes
                    if (i.pasientId == pasientID){
                        eksistererID++;
                        pasient = i;
                    }
                }
                   if (eksistererID == 0){
                    System.out.println("pasientID eksisterer ikke i systemet");
                    
                   } else {
                    godkjent++;
                   }

                if (godkjent == 3){                    //oppretter p-resept hvis informasjon brukeren gir er riktig
                    try {
                        utskrivendeLege.skrivPResept(legemiddel, pasient, reit);
                    } catch (UlovligUtskrift e) {
                    }
                } else{
                    System.out.println("uglydig infomasjon gitt.");
                }
        }
//militaer resept
           else if(typeResept == 2){
                System.out.println("\noppgi Legemiddel navn:");
                String navn = sc.nextLine();
                System.out.println("\noppgi Lege:");
                String nyLege = sc.nextLine();
                System.out.println("\noppgi pasientID:");
                int pasientID = Integer.parseInt(sc.nextLine());  
       
                
                int eksistererNavn = 0;
                int eksistererLege = 0;
                int eksistererID = 0;
                int godkjent = 0;
                
                Lege utskrivendeLege=null;
                Legemiddel legemiddel=null;
                Pasient pasient=null;

                for(Legemiddel i : legemidlerListe){       //sjekker om legemidlet finnes
                    if (i.navn.equals(navn)){
                        eksistererNavn++;
                        legemiddel = i;
                    }
                }
                   if (eksistererNavn == 0){
                    System.out.println("legemidlet eksisterer ikke i systemet");

                   } else {
                    godkjent++;
                   }
                
                   for(Lege i : legeListe){                   //sjekker om legen finnes
                    if (i.Legenavn.equals(nyLege)){
                        eksistererLege++;
                        utskrivendeLege = i;
                    }
                }
                   if (eksistererLege == 0){
                    System.out.println("legen eksisterer ikke i systemet");
                    
                   } else {
                    godkjent++;
                   }

                   for(Pasient i : pasienterListe){       //sjekker om resept finnes
                    if (i.pasientId == pasientID){
                        eksistererID++;
                        pasient = i;
                    }
                }
                   if (eksistererID == 0){
                    System.out.println("pasientID eksisterer ikke i systemet");
                    
                   } else {
                    godkjent++;
                   }

                if (godkjent == 3){                    //oppretter militaer-resept hvis informasjon brukeren gir er riktig
                    try {
                        utskrivendeLege.skrivMilResept(legemiddel, pasient);
                    } catch (UlovligUtskrift e) {
                    }
                } else{
                    System.out.println("uglydig infomasjon gitt.");
                }
        }


//blaa resept
                else if(typeResept == 3){
                    System.out.println("\noppgi Legemiddel navn:");
                    String navn = sc.nextLine();
                    System.out.println("\noppgi Lege:");
                    String nyLege = sc.nextLine();
                    System.out.println("\noppgi pasientID:");
                    int pasientID = Integer.parseInt(sc.nextLine());  
                    System.out.println("\noppgi reit:");
                    int reit = Integer.parseInt(sc.nextLine());
                    
                    int eksistererNavn = 0;
                    int eksistererLege = 0;
                    int eksistererID = 0;
                    int godkjent = 0;
                    
                    Lege utskrivendeLege=null;
                    Legemiddel legemiddel=null;
                    Pasient pasient=null;

                    for(Legemiddel i : legemidlerListe){       //sjekker om legemidlet finnes
                        if (i.navn.equals(navn)){
                            eksistererNavn++;
                            legemiddel = i;
                        }
                    }
                    if (eksistererNavn == 0){
                        System.out.println("legemidlet eksisterer ikke i systemet");

                    } else {
                        godkjent++;
                    }
                    
                    for(Lege i : legeListe){                   //sjekker om legen finnes
                        if (i.Legenavn.equals(nyLege)){
                            eksistererLege++;
                            utskrivendeLege = i;
                        }
                    }
                    if (eksistererLege == 0){
                        System.out.println("legen eksisterer ikke i systemet");
                        
                    } else {
                        godkjent++;
                    }

                    for(Pasient i : pasienterListe){       //sjekker om resept finnes
                        if (i.pasientId == pasientID){
                            eksistererID++;
                            pasient = i;
                        }
                    }
                    if (eksistererID == 0){
                        System.out.println("pasientID eksisterer ikke i systemet");
                        
                    } else {
                        godkjent++;
                    }

                    if (godkjent == 3){                    //oppretter blaa-resept hvis informasjon brukeren gir er riktig
                        try {
                            utskrivendeLege.skrivBlaaResept(legemiddel, pasient,reit);
                        } catch (Exception e) {
                        }
                    } else{
                        System.out.println("uglydig infomasjon gitt.");
                    }
                }
            } else {
                System.out.println("Ugyldig valg");
            }
        }
// Del E5
    public void brukResept(){
        Scanner svar = new Scanner(System.in);

        //velger Pasient
        System.out.println("\nHvilken pasient vil du se resepter for?");
        int teller = 0;

        for (Pasient i : pasienterListe){                             //skriver ut pasientene
            System.out.println(teller+": "+ i);
            teller++;
        }
        int lest = Integer.parseInt(svar.nextLine());                   //brukeren velger en pasient
        Pasient valgtPasient = pasienterListe.hent(lest);

        System.out.println("Valgt pasiet: "+valgtPasient);

        //velge resept
        System.out.println("\nHvilken repsept vil du bruke?");
        int tellerResept = 0;

        for (Resept i : valgtPasient.reseptListe){                     //skriver ut reseptene, feil
            System.out.println(tellerResept+": "+ i);
            tellerResept++;
        }
        int lestResept = Integer.parseInt(svar.nextLine());
        Resept valgtResept = valgtPasient.reseptListe.hent(lestResept);


        System.out.println("Valgt pasient: "+valgtResept);               //skriver ut så lenge reit > 0
        if(valgtResept.hentReit() > 0){
            valgtResept.reit -= 1;
            System.out.println("Brukte resept paa "+valgtResept.legemiddel.navn +". Antall gjenvarende reit: "+ valgtResept.reit);
        } else {
            System.out.println("Kunne ikke bruke resept paa "+valgtResept.legemiddel.navn +"(ingen gjenvarende reit)");
        }


    }
// Del E6
    public void statistikk(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\nvelg hvilken statistikk:\n0- totalt antall utskrevende vanedannende legemidler\n1-total antall utskrevende narkotisk legemdiler\n2-statistikk om mulig misbruk av narkotika\n\ntast inn valg:");
        
        int valg = Integer.parseInt(sc.nextLine());
        if(valg == 0){ // vanedannende legemidler
            int antall=0;
            for(Lege lege: legeListe){
                for(Resept r: lege.utskrevneResepter){
                    if(r.hentLegemiddel().hentType().equals("vanedannende")){
                        antall++;

                    }
                }
            }
            System.out.println("\nTotalt antall utskrevende resepter paa vanedannende resepter: "+antall);

        } else if(valg == 1){ // narkotiske legemidler
            int antall=0;
            for(Lege lege: legeListe){
                for(Resept r: lege.utskrevneResepter){
                    if(r.hentLegemiddel().hentType().equals("narkotisk")){
                        antall++;
                    }
                }
            }
            System.out.println("\nTotalt antall utskrevende resepter paa narkotiske resepter: "+antall);

        } else if (valg == 2){ //første del, lege, feil
            Prioritetskoe<Lege> alfabetisk = new Prioritetskoe<>();
            for (Lege i: legeListe){
                int godkjent = i.hentAntNarkotiskeResepter();
                if(godkjent > 0){
                    alfabetisk.leggTil(i);
                }
            }

            System.out.println("\nLeger som har utskrevet resept for narkotiske legemidler: ");
            for (Lege lege : alfabetisk) {
                System.out.println("\n"+lege.Legenavn+", antall utskrevet: "+lege.hentAntNarkotiskeResepter());
                
            }

            //andre del, pasienter
            Prioritetskoe<Pasient> alfabetisk2 = new Prioritetskoe<>();
            for (Pasient i: pasienterListe){
                int godkjent = i.hentAntNarkotiskeResepter();
                if(godkjent > 0){
                    alfabetisk2.leggTil(i);
                }
            }

            System.out.println("\nPasienter som har fått utskrevet resept for narkotiske legemidler: ");
            for (Pasient pasient : alfabetisk2) {
                System.out.println("\n"+pasient.navn+", antall fått utskrevet: "+ pasient.hentAntNarkotiskeResepter());
            }
        } else {
            System.out.println("Ugyldig valg");

        }
         
    }
// Del E7
public void skrivTilFil(){
    PrintWriter pw = null;
    String UtfilNavn = "ny_legedata.txt";

    try {
        pw = new PrintWriter(UtfilNavn);
    } catch (FileNotFoundException e) {
        System.out.println("kunne ikke skrive til filen, " + UtfilNavn);
        System.exit(-1);
    }
//skriver inn pasienter
    pw.println("# Pasienter (navn, fnr)");
    for (Pasient p : pasienterListe){
        pw.println(p.navn + "," + p.fodselsnummer);
    }
//skriver inn legemidler
pw.println("# Legemidler (navn, type, pris, virkestoff,[styrke])");
    for (Legemiddel l : legemidlerListe){
        if (l.hentType().equals("vanedannende") || l.hentType().equals("narkotisk")){
            pw.println(l.navn + "," + l.hentType() + "," + l.pris + "," + l.virkestoff + "," + l.hentStyrke());
        }else{
            pw.println(l.navn + "," + l.hentType() + "," + l.pris + "," + l.virkestoff);
        }
// skriver inn leger
pw.println("# Leger (navn,kontrollid)");
    for(Lege lege: legeListe){
        pw.println(lege.Legenavn +","+ lege.hentKontrollKode() ); 
    
        }
// skriver inn resepter
pw.println("# Resepter (legenavn, pasientID, type,[reit])");
        for(Lege lege: legeListe){
            
            for(Resept resept: lege.utskrevneResepter){
                pw.println(lege.Legenavn +","+ resept.hentPasientId()+ ","+ resept.hentType()+ ","+ resept.reit);
            }
        }

        pw.close();
    }
}
//del E2
public static void main(String[] args) throws FileNotFoundException{

    Legesystem legesys = new Legesystem();

    Scanner sc = new Scanner(System.in);

    System.out.println("\n\n--Velkommen til Legesystemet--");
    System.out.println("\nHovedmeny: \n\n0-Skrive ut fullstendig oversikt\n1-Opprette og legge til nye elementer i systemet\n2-Bruke resept fra en pasient\n3-Skrive ut forskjellige former for statistikk\n4-Skrive alle data til fil\n5-avslutt program\n\nTast inn ønsket handling: ");

    int kategori = Integer.parseInt(sc.nextLine());
    legesys.settInnFil();

    

    while (kategori != 5){
        if(kategori == 0){
            legesys.skrivUt();
        } else if(kategori == 1){
            legesys.brukerLeggTil();
        } else if(kategori == 2){
            legesys.brukResept();
        } else if(kategori == 3){
            legesys.statistikk();
        } else if (kategori == 4){
            legesys.skrivTilFil();
        }
        System.out.println("\nHovedmeny: \n\n0-Skrive ut fullstendig oversikt\n1-Opprette og legge til nye elementer i systemet\n2-Bruke resept fra en pasient\n3-Skrive ut forskjellige former for statistikk\n4-Skrive alle data til fil\n5-avslutt program\n\nTast inn ønsket handling: ");
        kategori = Integer.parseInt(sc.nextLine());

    }

    System.out.println("Ugyldig valg");


    //Husk å skrive else i 

}

}






