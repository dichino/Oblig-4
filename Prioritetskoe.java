class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E>{  

    @Override
    public void leggTil(E x){
        Node nyNode = new Node(x);

        if (start == null){                //sjekkr hvis det finnnes en start node
            start = nyNode; 
            return; 

        }
        if (nyNode.data.compareTo(start.data) < 0){    //sjekker om den nyeNode som ble lagt til sin data er mindre enn start noden sin data
            nyNode.neste = start;                      // nyenoden sin neste peker tilbake på start
            start = nyNode;                            // start peker nå på den nyenoden
            return;
        }

        Node peker = start;
        while (peker.neste != null && peker.neste.data.compareTo(x) < 0 ){      //sjekker så lenge peker != 0 og peker sin neste sin data < nyenoden
            peker = peker.neste;   
        }
        
        nyNode.neste = peker.neste;       //den nye noden peker på den gamle noden sin neste
        peker.neste = nyNode;             // forrige noden peker på den nye noden
        

    }    

    @Override
    public E fjern(){        
        if (start == null){
            throw new UgyldigListeindeks(-1);
            }

        Node minstVerdi = start;
        start = start.neste;
        return minstVerdi.data;
    }

    @Override
    public E hent(){

        Node peker = start;
        E verdi = null;

        if (peker != null){
            verdi = start.data;
        } 
        return verdi;
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