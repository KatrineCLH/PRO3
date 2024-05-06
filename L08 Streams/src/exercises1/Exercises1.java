package exercises1;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Exercises1 {

    public static void main(String[] args) {
        // Liste med tal mellem 1 - 50
        List<Integer> list = List.of(1,2,3,2,1,6,3,4,5,2,3,8,8,9,34,32);
        //list.stream().forEach(e-> System.out.println(e+1));

        //  TODO
        //	Udskriver det største element i listen
        int max = list.stream()
                .max(Integer::compareTo).get();
        System.out.println(max);

        //  TODO
        //	Afgør og udskriver om alle tallene i listen er mindre end 50
        boolean isLessThan50 = list.stream()
                .allMatch(e -> e < 50);
        System.out.println(isLessThan50);

        //  TODO
        // 	Udskriver antallet af lige tal i listen
        list.stream()
                .filter(e -> e % 2 == 0)
                .forEach(e -> System.out.println(e));

        //  TODO
        //	Udskriver antallet af ulige tal i listen
        list.stream()
                .filter(e -> e % 2 != 0)
                .forEach(e -> System.out.println(e));

        //  TODO
        //  Udskriver
        //      Gennemsnittet af tallene i listen
        //      Antallet af tallene i listen
        //      Antallet af tallene i listen der er større end gennemsnittet
        //      Antallet af tallene i listen der er mindre end gennemsnittet
        IntSummaryStatistics stats = list.stream()
                .mapToInt(e -> e)
                .summaryStatistics();

        System.out.println(stats.getAverage());
        System.out.println(stats.getCount());
        long antal = list.stream()
                .filter(e -> e > stats.getAverage())
                .count();
        System.out.println(antal);
        System.out.println(list.stream().filter(e -> e < stats.getAverage()).count());


        //  TODO
        //	Udskriver antallet af gange de forskellige tal forekommer i listen
        Map<Integer, Long> map = list.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(map);

        //  TODO
        //	Udskriver antallet af gange de forskellige tal forekommer i listen i sorteret orden
        Map<Integer, Long> newMap = list.stream()
                .sorted()
                .collect(Collectors.groupingBy(e -> e, TreeMap::new, Collectors.counting()));
        System.out.println(newMap);
    }
}
