package ex66;

import java.util.*;
import java.util.stream.Collectors;

// duyet cac show con truc tiep theo thu tu, khong cho sua danh sach
public final class CompositeShow implements Show, Iterable<Show> {
    private final List<Show> children;
    public CompositeShow(Show... shows) {
        Objects.requireNonNull(shows);
        if (shows.length < 2 || shows.length > 5)
            throw new IllegalArgumentException("Expected 2 to 5 shows");
        // sao chep de viec sua mang dau vao khong anh huong den nhom
        children = List.copyOf(Arrays.asList(shows));
    }
    public Iterator<Show> iterator() { return children.iterator(); }
    public String description() {
        return children.stream().map(Show::description)
            .collect(Collectors.joining(" -> ", "[", "]"));
    }
    public int runningTime() {
        return children.stream().mapToInt(Show::runningTime).sum();
    }
    public Show copy() {
        // sao chep tung show con de tao mot nhom doc lap
        return new CompositeShow(children.stream().map(Show::copy).toArray(Show[]::new));
    }
}
