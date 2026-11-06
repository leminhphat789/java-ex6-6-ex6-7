package ex67;

import java.util.*;
import java.util.function.BiConsumer;

// duyet nut hien tai truoc, sau do di vao tung nut con
public final class Traversal {
    private Traversal() { }
    public static Iterator<Show> children(Show show) {
        Objects.requireNonNull(show);
        return show.iterator();
    }
    public static void walk(Show root, int depth, BiConsumer<Show, Integer> visitor) {
        visitor.accept(root, depth);
        Iterator<Show> iterator = children(root);
        while (iterator.hasNext()) walk(iterator.next(), depth + 1, visitor);
    }
}
