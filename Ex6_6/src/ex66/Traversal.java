package ex66;

import java.util.*;
import java.util.function.BiConsumer;

// duyet nut hien tai truoc, sau do di vao tung nut con
public final class Traversal {
    private Traversal() { }
    public static Iterator<Show> children(Show show) {
        Objects.requireNonNull(show);
        if (show instanceof CompositeShow composite) return composite.iterator();
        if (show instanceof IntroducedShow introduced)
            return java.util.List.of(introduced.wrappedShow()).iterator();
        return java.util.Collections.emptyIterator();
    }
    public static void walk(Show root, int depth, BiConsumer<Show, Integer> visitor) {
        visitor.accept(root, depth);
        Iterator<Show> iterator = children(root);
        while (iterator.hasNext()) walk(iterator.next(), depth + 1, visitor);
    }
}
