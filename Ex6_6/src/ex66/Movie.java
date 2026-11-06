package ex66;

public final class Movie implements Show {
    private final String title;
    private final int year, minutes;
    public Movie(String title, int year, int minutes) {
        this.title = java.util.Objects.requireNonNull(title);
        if (minutes < 0) throw new IllegalArgumentException("Negative duration");
        this.year = year; this.minutes = minutes;
    }
    public String description() { return title + " (" + year + ")"; }
    public int runningTime() { return minutes; }
    public Show copy() { return new Movie(title, year, minutes); }
}
