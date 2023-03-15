class IndeksertListe <E> extends LenkeListe <E> {

    public void leggTil (int pos, E x) {
        Node ny_node = new Node(x);
        if (0 < pos && pos <= stoerrelse()){ 
            int ant = 0;
            Node midlertidig = start;
            while (midlertidig != null && ant != pos){ //finner noden som er på ønsket posisjon
                midlertidig = midlertidig.neste;
                ant ++;
            }

            int ant_2 = 0;
            Node midlertidig_2 = start;
            while (midlertidig_2 != null && ant_2 != (pos-1)){ //finner noden som er på foran posisjonen
                midlertidig_2 = midlertidig_2.neste;
                ant_2 ++;
            }
            midlertidig_2.neste = ny_node; //setter nye noden som neste for noden som er før oppgitt posisjon
            ny_node.neste = midlertidig; //setter noden og flytter den en videre

        } else if (pos == 0){
        Node midlertidig = start;
        start = ny_node; //får den noden til å peke på nye noden
        start.neste = midlertidig; //får den nye til å peke på gamle start
        }else{
        throw new UgyldigListeindeks(pos);}
    }

    public void sett (int pos, E x) {
        if (0 <= pos && pos < stoerrelse()){ 
            int ant = 0;
            Node midlertidig = start;
            while (midlertidig != null && ant != pos){ //finner noden som er på ønsket posisjon
                midlertidig = midlertidig.neste;
                ant ++;
            }
            midlertidig.data = x; //endrer elementet i noden med oppgitt posisjon
        }else{
            throw new UgyldigListeindeks(pos);
        }
    }
    public E hent (int pos) {
        if (0 <= pos && pos < stoerrelse()){ 
            int ant = 0;
            Node midlertidig = start;
            while (midlertidig != null && ant != pos){ //finner noden som er på ønsket posisjon
                midlertidig = midlertidig.neste;
                ant ++;
            }
            return midlertidig.data; //bytter ut elementet
        }
        throw new UgyldigListeindeks(pos); //gir feilmelding ved ulovlig indeks
    }
    public E fjern (int pos) {
        Node foran;
        Node fjerne;
        Node bak;

        if (0 <= pos && pos < stoerrelse()){ 
            int ant = 0;
            Node midlertidig = start;
            while (midlertidig != null && ant != pos){ //finner noden som er på ønsket posisjon
                midlertidig = midlertidig.neste;
                ant ++;
            }
            fjerne = midlertidig;
            

      
            int ant_2 = 0;
            Node midlertidig_2 = start;
            while (midlertidig_2 != null && ant_2 != (pos - 1)){ //finner noden foran ønsket posisjon
                midlertidig_2 = midlertidig_2.neste;
                ant ++;
                }
                foran = midlertidig_2;

            int ant_3 = 0;
            Node midlertidig_3 = start;
            while (midlertidig_3 != null && ant_3 != (pos + 1)){ //finner noden bak ønsket posisjon
                 midlertidig_3 = midlertidig_3.neste;
                ant ++;
            }
            bak = midlertidig_3;
                
            foran.neste = bak; //noden i oppgitte posisjon hoppes over, og den bak blir foran sin neste

            return fjerne.data;
   

        }
        throw new UgyldigListeindeks(pos);
     }
    }