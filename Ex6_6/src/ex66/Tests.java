package ex66;

import java.util.*;

// chay kiem tra truc tiep, khong can thu vien ngoai hay bat co -ea
public final class Tests {
    private static int checks;
    private static void check(boolean condition, String message) {
        checks++;
        if (!condition) throw new AssertionError(message);
    }
    private static void expect(Class<? extends Throwable> type, Runnable action) {
        checks++;
        try { action.run(); } catch (Throwable error) {
            if (type.isInstance(error)) return;
            throw new AssertionError("Wrong exception", error);
        }
        throw new AssertionError("Expected " + type.getSimpleName());
    }
    public static void main(String[] args) {
        Show a = new Movie("A", 2024, 90);
        Show b = new Movie("B", 2025, 100);
        Show c = new Concert("C", "Band", 60);
        Show decorated = new IntroducedShow(b, "Lan");
        CompositeShow pair = new CompositeShow(a, decorated);
        CompositeShow program = new CompositeShow(c, pair);
        Show root = new IntroducedShow(program, "Minh");
        Iterator<Show> it = program.iterator();
        check(it.hasNext(), "First exists");
        check(it.next() == c, "Preserve first child identity");
        check(it.next() == pair, "Keep nested composite intact");
        check(!it.hasNext(), "Exactly two children");
        expect(NoSuchElementException.class, () -> it.next());
        Iterator<Show> removal = program.iterator(); removal.next();
        expect(UnsupportedOperationException.class, () -> removal.remove());
        check(program.iterator().next() == c, "Fresh independent iterator");
        Show[] input = {a, b};
        CompositeShow snapshot = new CompositeShow(input); input[0] = c;
        check(snapshot.iterator().next() == a, "Defensive array copy");
        expect(IllegalArgumentException.class, () -> new CompositeShow());
        expect(IllegalArgumentException.class, () -> new CompositeShow(a));
        expect(IllegalArgumentException.class, () -> new CompositeShow(a,b,a,b,a,b));
        check(new CompositeShow(a,b,a,b,a).runningTime() == 470, "Five accepted");
        expect(NullPointerException.class, () -> new CompositeShow(a,null));
        check(root.runningTime() == 270, "Both introductions counted");
        List<Show> visited = new ArrayList<>();
        Traversal.walk(root, 0, (s,d) -> visited.add(s));
        check(visited.equals(List.of(root,program,c,pair,a,decorated,b)), "Preorder all nodes");
        check(!Traversal.children(a).hasNext(), "Leaf has zero children");
        check(Traversal.children(decorated).next() == b, "Decorator exposes wrapped show");
        Show copied = root.copy();
        check(copied != root && copied.description().equals(root.description()), "Copy value");
        List<Show> copiedNodes = new ArrayList<>();
        Traversal.walk(copied, 0, (s,d) -> copiedNodes.add(s));
        check(copiedNodes.size() == visited.size(), "Copy structure");
        for (int i=0;i<visited.size();i++) check(visited.get(i)!=copiedNodes.get(i), "Deep copy");
        check(!Iterable.class.isAssignableFrom(Show.class), "Show unchanged");
        check(Show.class.getDeclaredMethods().length == 3, "Original three methods");
        System.out.println("PASS: " + checks + " checks");
    }
}
