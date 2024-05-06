package opg1;

import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        Set<Bil> biler = new HashSet<>();

        Bil vw = new Bil("AB12345", "VolksWagen", "Up", "blå");
        Bil renault = new Bil("CD67890", "Renault", "Clio", "grøn");
        Bil opel = new Bil("EF34567", "Opel", "Astra", "grå");
        Bil renault2 = new Bil("CD67890", "Renault", "Clio", "grøn");
        Bil opel2 = new Bil("EF34567", "Opel", "Astra", "grå");

        biler.add(vw);
        biler.add(renault);
        biler.add(opel);
        biler.add(renault2);
        biler.add(opel2);

        for(Bil b : biler){
            System.out.println(b.toString());
        }
    }
}
