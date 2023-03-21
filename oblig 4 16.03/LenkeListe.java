import java.util.Iterator;

abstract class Lenkeliste <E> implements Liste<E> {
    protected Node start;
    protected Node slutt;

    protected class Node{
        E data;
        Node neste;

        public Node(E data){
            this.data = data;
        }
    }
    // Del b
    public Iterator<E> iterator(){
        return new LenkelisteIterator();
    }
    class LenkelisteIterator implements Iterator<E>{
        Node peker = start;

        @Override
        public boolean hasNext(){
           
            return peker.neste != null;
        }
        
        @Override
        public E next(){
            if (peker == null){
                throw new UgyldigListeindeks(-1);
            }

            Node midlertidig = peker;
            peker = peker.neste;
            return midlertidig.data;
        }

    }

//legger til metodene
    @Override
    public int stoerrelse() {
    int tell = 0;

    Node peker = start;
    while (peker != null) {
        tell++;
        peker = peker.neste;
    }
        return tell;
}

    @Override
    public void leggTil(E x){
        Node nyNode = new Node(x);

        if (start == null){
            start = nyNode; 
            slutt = nyNode; 
            return; 
        }
        Node peker = start;
        while (peker.neste != null) {
            peker = peker.neste;
        }
        Node nestSist = peker;          //hvis man trenger det som var site node
        peker.neste = nyNode;
        slutt = nyNode;                 //slutt peker på siste node
    }
 
    

    @Override
    public E hent(){                             //metode som henter første element i en lenkeliste
        Node peker = start;
        E verdi = null;

        if (peker != null){
            verdi = start.data;
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
