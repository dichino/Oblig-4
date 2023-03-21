import java.io.FileNotFoundException;

public class TESTLegesystem {
    public static void main(String[] args) throws FileNotFoundException {
        Legesystem_copy samatar = new Legesystem_copy("legedata.txt");

        samatar.settInnFil();
        samatar.skrivUt();
    }
    
}
