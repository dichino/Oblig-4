class Stabel<E> extends Lenkeliste<E>{
   

//legger til metodene
    @Override
    public int stoerrelse() {
        int teller = 0;
        Node peker = start;
        while (peker != null) {
            teller++;
            peker = peker.neste;
        }
            return teller;
    }
    @Override                       //metoden skal legge til på starten
    public void leggTil(E x){
        Node nyNode = new Node(x);
        nyNode.neste = start;          //den nye noden sin neste peker på starten
        start = nyNode;                //start er nå den nye noden

     
    }
/* 
    @Override
    public void leggTil(E x){
        Node nyNode = new Node(x);

        if (start == null){
            start = nyNode; 
            return; 
        }
        
        nyNode.neste = start;
        start = nyNode;
 }

alternativ
            Node midl = start;
            start = new Node(x);
            start.neste = midl;
*/
        
    

    @Override
    public E hent(){                             //metode som henter første element i en lenkeliste
        Node peker = start;
        E verdi = null;

        if (peker != null){
    
            verdi = start.data;
            peker = peker.neste;
        } 
        return verdi;
    }

    @Override
    public E fjern(){                           //metode som fjerner første element i lenkelisten
        if (start == null){
            throw new UgyldigListeindeks(-1);
            }
            
            Node x = start;                     //x peker på start som er første noden
            start = start.neste;                // velger at start nå peker på neste node
            return x.data;                      // returnerer x, den pekte på første noenden
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