package ba.unsa.etf.rpr;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Fakultet {
    private List<Osoba> lista = new ArrayList<>();

    public void dodajOsobu(Osoba o) {
        lista.add(o);
    }

    @Override
    public String toString() {
        return lista.stream().map(Objects::toString).collect(Collectors.joining("\n"));
    }

    public Set<Student> studenti() {
        return lista.stream().filter(o -> o instanceof Student).map(o -> (Student) o).collect(Collectors.toCollection(TreeSet::new));
    }

    public List<Osoba> filtriraj(Function<Osoba, Boolean> f) {
        ArrayList<Osoba> rez = new ArrayList<>();
        for(Osoba o: lista)
            if (f.apply(o)) rez.add(o);
        return rez;
    }

    public List<BachelorStudent> topBachelor() {
        List lista = filtriraj((Osoba o) -> { return (o instanceof BachelorStudent && ((BachelorStudent) o).prosjek() >= 8);});
        return lista;
    }

    public List<Mladi> mladi() {
        List<Mladi> rez = new ArrayList<>();
        for (Osoba o : lista)
            if (o instanceof MasterStudent || o instanceof Docent)
                rez.add((Mladi)o);
        return rez;
    }
}
