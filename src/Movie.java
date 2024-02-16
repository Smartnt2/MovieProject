import java.util.ArrayList;
import java.util.Arrays;

public class Movie {
    private String title;
    private String castString;
    private ArrayList<String> cast;
    private String director;
    private String overview;
    private int runtime;
    private double rating;

    public Movie(String data) {
        cast = new ArrayList<>();
        String[] splitData = data.split(",");
        title = splitData[0];
        castString = splitData[1];
        String castMembers = splitData[1];
        String[] splitMembers = castMembers.split("\\|");
        cast.addAll(Arrays.asList(splitMembers));
        director = splitData[2];
        overview = splitData[3];
        runtime = Integer.parseInt(splitData[4]);
        rating = Double.parseDouble(splitData[5]);
    }

    public String getTitle() {
        return title;
    }
    public String getCastString() {
        return castString;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public String getDirector() {
        return director;
    }

    public String getOverview() {
        return overview;
    }

    public int getRuntime() {
        return runtime;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return
                "Title: " + getTitle() + "\n" +
                "Runtime: " + getRuntime() + " minutes\n" +
                "Directed by: " + getDirector() + "\n" +
                "Cast: " + getCastString() + "\n" +
                "Overview: " + getOverview() + "\n" +
                "User rating: " + getRating();

    }
}

