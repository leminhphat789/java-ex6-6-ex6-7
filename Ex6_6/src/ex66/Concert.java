package ex66;

public final class Concert implements Show {
    private final String title, performer;
    private final int minutes;
    public Concert(String title, String performer, int minutes) {
        this.title = java.util.Objects.requireNonNull(title);
        this.performer = java.util.Objects.requireNonNull(performer);
        if (minutes < 0) throw new IllegalArgumentException("Negative duration");
        this.minutes = minutes;
    }
    public String description() { return title + " by " + performer; }
    public int runningTime() { return minutes; }
    public Show copy() { return new Concert(title, performer, minutes); }
}
