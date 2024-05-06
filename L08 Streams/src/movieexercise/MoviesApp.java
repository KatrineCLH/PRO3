package movieexercise;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MoviesApp {
    public static List<Movie> readMovies(String filename) throws IOException {
        List<Movie> movies = new ArrayList<>();
        try (Scanner in = new Scanner(new File(filename))) {
            while (in.hasNextLine()) {
                String nameLine = in.nextLine();
                String yearLine = in.nextLine();
                String directorsLine = in.nextLine();
                String producersLine = in.nextLine();
                String actorsLine = in.nextLine();
                movies.add(new Movie(getString(nameLine),
                        Integer.parseInt(getString(yearLine)),
                        getList(directorsLine), getList(producersLine),
                        getList(actorsLine)));
            }
        }
        return movies;
    }

    private static String getString(String line) {
        int colon = line.indexOf(":");
        return line.substring(colon + 1).trim();
    }

    private static List<String> getList(String line) {
        return Stream.of(getString(line).split(", "))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        List<Movie> movieList = readMovies("C:\\Users\\katri\\OneDrive\\Dokumenter\\DMY\\PRO3\\L08 Streams\\src\\tekst\\movies.txt");
//        for (Movie m : movieList) {
//            System.out.println(m.getTitle());
//        }

        //movieList.stream().forEach(e -> System.out.println(e.getTitle()));
        //System.out.println("Number of movies: " +movieList.size());

        //  The numer of movies starting with "H"
        // TODO Opgave
        System.out.println(movieList.stream()
                .filter(e -> e.getTitle().toUpperCase().charAt(0) == 'H')
                .count());

        // The title of the movies starting with "X"
        // TODO Opgave
        movieList.stream()
                .filter(e -> e.getTitle().toUpperCase().charAt(0) == 'X')
                .forEach(e -> System.out.println(e.getTitle()));

        // The number of films where the director is also an actor
        // TODO Opgave
        long count = movieList.stream()
                .filter(movie -> movie.getDirectors().stream()
                        .anyMatch(actor -> movie.getActors().contains(actor))).count();
        System.out.println(count);

        int filmCounter = 0;
        for (Movie m : movieList){
            for (String a : m.getActors()){
                if (m.getDirectors().contains(a)){
                    filmCounter++;
                }
            }
        }
        System.out.println("Film med samme instruktør og skuespiller: " + filmCounter);

        //The number of actors in the film with the most actors
        // TODO Opgave
        int antal = movieList.stream()
                .map(movie -> movie.getActors().size()).max(Integer::compareTo).get();
        System.out.println(antal);

        int max = 0;
        for (Movie m : movieList){
            if (m.getActors().size() > max){
                max = m.getActors().size();
            }
        }
        System.out.println("Max: " + max);

        // The title of the film with the most actors
        // TODO Opgave
        System.out.println(movieList.stream()
                .max((e1, e2) -> e1.getActors().size() - e2.getActors().size()).get().getTitle());

        //Number of films divided by first letter in the film title
        // TODO Opgave
        System.out.println(movieList.stream()
                .collect(Collectors.groupingBy(e -> e.getTitle().charAt(0), Collectors.counting())));

        // Number of movies whose title starts with "The "
        // TODO Opgave
        System.out.println(movieList.stream()
                .filter(e -> e.getTitle().startsWith("The ")).count());
//
    }


}

