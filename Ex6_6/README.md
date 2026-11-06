# Ex 6.6

## Yêu cầu

Dùng iterator để lấy các show nằm trong một composite show mà không thay đổi interface `Show`.

## Giữ nguyên interface Show

`Show` vẫn có ba phương thức:

```java
String description();
int runningTime();
Show copy();
```

`description()` trả mô tả, `runningTime()` trả thời lượng tính bằng phút, còn `copy()` tạo bản sao của show. Không thêm `iterator()` hay `Iterable` vào interface này.

## Thêm iterator vào CompositeShow

```java
public final class CompositeShow implements Show, Iterable<Show> {
    private final List<Show> children;

    public Iterator<Show> iterator() {
        return children.iterator();
    }
    // cac phuong thuc con lai nam trong CompositeShow.java
}
```

`Iterable<Show>` cho phép duyệt một `CompositeShow` bằng vòng lặp for-each. Mỗi lần gọi `iterator()` lấy một iterator mới, có vị trí duyệt riêng.

Iterator trả các show con trực tiếp theo thứ tự đã truyền vào constructor. Nếu một con cũng là composite, iterator trả nguyên đối tượng đó, không tự tách thành các show bên trong.

```java
CompositeShow program = new CompositeShow(concert, movies);
for (Show child : program) {
    System.out.println(child.description());
}
```

Ở đây, vòng lặp lấy `concert` trước rồi đến `movies`. Nếu biến chỉ có kiểu `Show`, không thể dùng trực tiếp for-each vì `Show` không kế thừa `Iterable`.

## Duyệt toàn bộ cây

`Traversal.children()` xác định cách lấy con dựa vào lớp của đối tượng:

```java
if (show instanceof CompositeShow composite)
    return composite.iterator();
if (show instanceof IntroducedShow introduced)
    return java.util.List.of(introduced.wrappedShow()).iterator();
return java.util.Collections.emptyIterator();
```

- Với composite, lấy iterator của danh sách con.
- Với `IntroducedShow`, lấy một phần tử là show được bọc.
- Với `Movie` hoặc `Concert`, trả iterator rỗng vì không có con.

`Traversal.walk()` xử lý nút hiện tại bằng `visitor.accept(root, depth)`, sau đó duyệt từng con và gọi lại chính nó với `depth + 1`. Cách này thăm nút cha trước các nút con. `depth` được dùng trong `Main` để thụt đầu dòng khi in cây.

Iterator của composite là phần chính đáp ứng yêu cầu bài. `Traversal` bổ sung ví dụ đi sâu qua các nhóm lồng nhau. Do không thay đổi `Show`, helper này phải nhận biết lớp cụ thể; nếu thêm một loại chứa con khác thì cần bổ sung cách lấy con cho loại đó.

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
javac -encoding UTF-8 -d out src/ex66/*.java
java -cp out ex66.Main
java -cp out ex66.Tests
```
