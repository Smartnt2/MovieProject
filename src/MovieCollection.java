import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class MovieCollection {
    private Scanner scan;
    private ArrayList<Movie> movies;
    public MovieCollection() {
        scan = new Scanner(System.in);
        movies = new ArrayList<>();
        loadData();
        sortMovies();
        menu();
    }

    private void loadData() {
        boolean firstLine = true;
        try {
            File myFile = new File("src//movies_data.csv");
            Scanner fileScanner = new Scanner(myFile);
            while(fileScanner.hasNext()) {
                if(firstLine) {
                    firstLine = false;
                    fileScanner.nextLine();
                }
                String data = fileScanner.nextLine();
                movies.add(new Movie(data));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void menu() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }

    }

    private void sortMovies() {
        for(int i = 1; i < movies.size(); i++) {
            Movie current = movies.get(i);
            int j = i - 1;
            while (j >= 0 && movies.get(j).getTitle().compareTo(current.getTitle()) > 0) {
                movies.set(j + 1, movies.get(j));
                j--;
            }
            movies.set(j + 1, current);
        }
    }

    private void searchTitles() {
        ArrayList<Movie> output = new ArrayList<>();
        System.out.print("Enter a title search term: ");
        String target = scan.nextLine();
        target = target.toLowerCase();
        for(Movie movie : movies) {
            if(movie.getTitle().toLowerCase().contains(target)) {
                output.add(movie);
            }
        }
        for(int i = 0; i < output.size(); i++) {
            System.out.println(i+1 + ". " + output.get(i).getTitle());
        }
        if(output.isEmpty()) {
            System.out.println("No results found.");
        } else {
            System.out.println("What movie do you want more information on?");
            System.out.print("Enter number: ");
            int selection = scan.nextInt();
            scan.nextLine();
            System.out.println(output.get(selection - 1).toString());
        }
    }

    private void searchCast() {
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<Movie> movieList = new ArrayList<>();
        System.out.print("Enter a name to search: ");
        String target = scan.nextLine();
        target = target.toLowerCase();
        for(Movie movie : movies) {
            for(String name : movie.getCast()) {
                if(name.toLowerCase().contains(target) && !nameList.contains(name.toLowerCase())) {
                    nameList.add(name);
                }
            }
        }
        //Remove duplicates
        Set<String> temp = new LinkedHashSet<String>(nameList);
        temp.addAll(nameList);
        nameList.clear();
        nameList.addAll(temp);
        //Sort nameList alphabetically
        for(int i = 1; i < nameList.size(); i++) {
            String current = nameList.get(i);
            int j = i - 1;
            while (j >= 0 && nameList.get(j).compareTo(current) > 0) {
                nameList.set(j + 1, nameList.get(j));
                j--;
            }
            nameList.set(j + 1, current);
        }
        if(nameList.isEmpty()) {
            System.out.println("No results found.");
        } else {
            for (int i = 0; i < nameList.size(); i++) {
                System.out.println(i + 1 + ". " + nameList.get(i));
            }
            System.out.println("What actor would you like to see movies of?");
            System.out.print("Enter number: ");
            int selection = scan.nextInt();
            scan.nextLine();

            target = nameList.get(selection-1);
            for(Movie movie : movies) {
                for(String name : movie.getCast()) {
                    if(name.equalsIgnoreCase(target)) {
                        movieList.add(movie);
                    }
                }
            }
            for(int i = 0; i < movieList.size(); i++) {
                System.out.println(i+1 + ". " + movieList.get(i).getTitle());
            }
            System.out.println("What move would you like more information on?");
            System.out.print("Enter number: ");
            selection = scan.nextInt();
            scan.nextLine();
            System.out.println(movieList.get(selection-1).toString());
        }
    }
}
