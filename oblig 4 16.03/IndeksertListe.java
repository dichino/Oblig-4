class IndeksertListe <E> extends Lenkeliste<E> {


    public void leggTil (int pos, E x) {
        if (0 > pos || pos > stoerrelse() ) {      //sjekker om det er ugyldige indeks
            throw new UgyldigListeindeks(pos);
        }
        Node nyNode = new Node(x);
        Node peker = start;
 
        if (pos == 0){                                  //sjekker på første posisjon 
            nyNode.neste = start;
            start = nyNode;
            return;
        } else{                                         //sjekker for de resterende indeksene
                for (int i = 0; i < pos-1; i++){        //starter på indeks1, fordi sjekker for 0 over
                    peker = peker.neste;  
                }
                Node midlertidig = peker.neste;
                peker.neste = nyNode;
                nyNode.neste = midlertidig;
     }
 }

 
    public void sett (int pos, E x) {
        if (0 > pos || pos >= stoerrelse() ) {      //sjekker om det er ugyldige indeks
            throw new UgyldigListeindeks(pos);
        }
        
        Node peker = start;
        for (int i = 0; i < pos; i++){
            peker = peker.neste;
        }
        peker.data = x;                             //erstatter gamle verdien med den nye
        
    }

    public E hent (int pos) { 
        
        if (0 > pos || pos >= stoerrelse() ) {      //sjekker om det er ugyldige indeks
            throw new UgyldigListeindeks(pos);
        }
        Node peker = start;

        for (int i = 0; i < pos; i++){
            peker = peker.neste;
        }   
        return peker.data;
    }

    public E fjern (int pos) {
        if (0 > pos || pos >= stoerrelse()) {      //sjekker om det er ugyldige indeks
            throw new UgyldigListeindeks(pos);
        }
        Node peker = start;

        for (int i = 0; i < pos-1; i++){
            peker = peker.neste;
        }
            Node midlertidig = peker.neste;         //holder på verdien av den vi skal fjerne
            peker.neste = peker.neste.neste;  //peker den verdien som skal fjerness til dens neste
            return midlertidig.data;

        }   



        @Override
        public String toString(){
            String tekst = "";
            Node peker = start;
    
            while (peker != null){
                tekst += "\n (verdi; " + peker.data + ") -> ";
                peker = peker.neste;
            }
            return tekst;
        }
      
    }


    