package ex66;

public final class Main {
    public static void main(String[] args) {
        Show concert = new Concert("Evening Concert", "Orchestra", 60);
        Show first = new Movie("Movie A", 2024, 90);
        Show second = new IntroducedShow(new Movie("Movie B", 2025, 100), "Lan");
        CompositeShow movies = new CompositeShow(first, second);
        CompositeShow program = new CompositeShow(concert, movies);
        Show root = new IntroducedShow(program, "Minh");
        System.out.println("EXERCISE 6.6");
        System.out.println("Direct children of program:");
        for (Show child : program) {
            System.out.println("- " + child.description());
        }
        System.out.println("Complete object tree (preorder):");
        Traversal.walk(root, 0, (show, depth) -> System.out.println(
            "  ".repeat(depth) + show.getClass().getSimpleName()));
        System.out.println("Total running time: " + root.runningTime() + " minutes");
    }
}
