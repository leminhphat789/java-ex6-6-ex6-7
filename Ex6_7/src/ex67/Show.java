package ex67;

// giu nguyen ba phuong thuc ban dau, cac show khong thay doi sau khi tao
public interface Show extends Iterable<Show> {
    String description();
    int runningTime();
    Show copy();
    // show la khong co con nen tra ve iterator rong
    default java.util.Iterator<Show> iterator() {
        return java.util.Collections.emptyIterator();
    }
}
