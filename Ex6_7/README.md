# Ex 6.7

## Cách triển khai

Bài này thay đổi interface `Show`. Phần thực hành chọn đưa thao tác duyệt các show con vào interface bằng `Iterable<Show>`; giữ nguyên tham chiếu “Exercise 7” trong đề.

## Bổ sung iterator vào Show

```java
public interface Show extends Iterable<Show> {
    String description();
    int runningTime();
    Show copy();

    default java.util.Iterator<Show> iterator() {
        return java.util.Collections.emptyIterator();
    }
}
```

Ba phương thức cũ vẫn được giữ lại. `iterator()` mặc định trả iterator rỗng để các show không có con, như `Movie` và `Concert`, không phải viết lại cùng một phương thức.

Iterator mô tả các con trực tiếp của show, không phải chính show đó. Vì vậy iterator của một phim rỗng, thay vì trả lại phim. Nếu trả chính nó, thao tác duyệt đệ quy sẽ không kết thúc.

## Cách từng lớp cung cấp iterator

`CompositeShow` ghi đè `iterator()` để trả các con theo thứ tự biểu diễn:

```java
public Iterator<Show> iterator() {
    return children.iterator();
}
```

`IntroducedShow` ghi đè phương thức này để trả đúng một con là show được bọc:

```java
public java.util.Iterator<Show> iterator() {
    return java.util.List.of(wrapped).iterator();
}
```

Phần giới thiệu 10 phút thuộc về `IntroducedShow`, không phải một đối tượng `Show` con riêng. Khi duyệt cây, nút decorator vẫn được thăm trước khi đi vào show được bọc.

## Duyệt thông qua Show

```java
Show clientView = program;
for (Show child : clientView) {
    System.out.println(child.description());
}
```

Đoạn này chạy được vì `Show` đã kế thừa `Iterable<Show>`. Client không cần ép kiểu về `CompositeShow` để lấy iterator.

`Traversal.children()` chỉ cần kiểm tra null và gọi phương thức chung:

```java
Objects.requireNonNull(show);
return show.iterator();
```

Java chọn phương thức `iterator()` phù hợp với đối tượng thực tế: composite trả nhiều con, decorator trả một con, lá trả iterator rỗng. Không cần các nhánh `instanceof`.

`Traversal.walk()` thăm nút hiện tại rồi gọi đệ quy với từng con. Khi đến một lá, iterator rỗng nên đệ quy dừng ở nhánh đó. Một lớp chứa con mới phải ghi đè `iterator()`; nếu quên, phương thức mặc định sẽ khiến lớp đó bị xem là không có con.

## Các phương thức hỗ trợ

Trong `CompositeShow`:

- Constructor nhận từ 2 đến 5 show. Số lượng ngoài khoảng này gây `IllegalArgumentException`.
- `List.copyOf(Arrays.asList(shows))` tạo danh sách bất biến và từ chối phần tử null. Sửa mảng đầu vào sau khi tạo composite không làm thay đổi nhóm.
- `description()` nối mô tả các con bằng ` -> ` và đặt trong dấu ngoặc vuông.
- `runningTime()` cộng thời lượng của tất cả con, kể cả composite hoặc decorator lồng bên trong.
- `copy()` gọi `copy()` trên từng con rồi tạo một composite mới.

Trong `IntroducedShow`, `description()` thêm tên người giới thiệu; `runningTime()` cộng 10 phút vào thời lượng show được bọc; `copy()` sao chép show được bọc trước khi tạo decorator mới.

Iterator của composite không hỗ trợ `remove()` vì danh sách bất biến. Khi đã duyệt hết, gọi tiếp `next()` sẽ gây `NoSuchElementException`.

## Ví dụ trong Main

`Main` tạo một concert, một nhóm hai phim và hai phần giới thiệu:

```text
IntroducedShow (Minh)
  CompositeShow (program)
    Concert (60 phut)
    CompositeShow (movies)
      Movie A (90 phut)
      IntroducedShow (Lan)
        Movie B (100 phut)
```

Duyệt trực tiếp `program` cho hai phần tử. Mô tả được in ra là:

```text
- Evening Concert by Orchestra
- [Movie A (2024) -> Intro by Lan: Movie B (2025)]
```

Gọi `Traversal.walk(root, 0, ...)` in toàn bộ bảy nút, gồm cả composite và decorator. Đây là duyệt cấu trúc cây, không chỉ lấy danh sách các phim và concert.

Tổng thời lượng là `60 + 90 + 100 + 10 + 10 = 270` phút. Hai số 10 tương ứng với phần giới thiệu của Minh và Lan.

## Chạy chương trình

Cần JDK 17 trở lên. Trên Windows, mở terminal tại thư mục bài và chạy:

```powershell
.\run.bat
```

Script biên dịch mã nguồn, chạy `Main` rồi chạy `Tests`. Có thể chạy thủ công:

```sh
javac -encoding UTF-8 -d out src/ex67/*.java
java -cp out ex67.Main
java -cp out ex67.Tests
```
