package pl.Bogusz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Main {

    public static class Galaz {

        public int id;
        public String name;
        public String parentId;
        public String hierName;

        public Galaz(int id, String name, String parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
            this.hierName = "";
        }
    }

    public static List<Galaz> fill(List<Galaz>lista){

        Iterator itr = lista.iterator();
        Galaz galaz;

        while (itr.hasNext()){
            galaz = (Galaz) itr.next();
            galaz.hierName = path(galaz.id, lista);
        }

        return lista;
    }

    private static String path(int id, List<Galaz> lista) {

        Optional<Galaz> galaz = lista.stream().filter(o -> o.id==id).findFirst();
        Galaz gal = null;
        if(galaz.isPresent()){
            gal = galaz.get();
        }

        if(gal.parentId == null) return "korzen";
        else return path(Integer.valueOf(gal.parentId),lista) + "/" + gal.name;
    }

    public static void main(String[] args) {

        List<Galaz> galezie = new ArrayList<>();
        galezie.add(new Galaz(6,"trzeci prawy", "5"));
        galezie.add(new Galaz(2,"pierwszy lewy", "1"));
        galezie.add(new Galaz(5,"drugi prawy", "2"));
        galezie.add(new Galaz(4,"drugi lewy", "2"));
        galezie.add(new Galaz(1,"korzen", null));
        galezie.add(new Galaz(3,"pierwszy prawy", "1"));


        galezie = fill(galezie);
        galezie.stream().forEach(o -> System.out.println(o.hierName));

    }
}
