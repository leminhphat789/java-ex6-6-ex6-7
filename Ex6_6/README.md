# Ex 6.6

Project độc lập, yêu cầu JDK 17 trở lên. Không cần Maven, Gradle hoặc thư viện ngoài.

## Chạy
Windows: mở terminal tại thư mục project và chạy `.\run.bat`.
Hoặc chạy thủ công:
```sh
javac -encoding UTF-8 -d out src/ex66/*.java
java -cp out ex66.Main
java -cp out ex66.Tests
```

## Thiết kế
Ex 6.6: giữ nguyên ba chữ ký của Show hiện hành: description(), runningTime(), copy(). Chỉ CompositeShow implements Iterable<Show>.

Iterator của composite trả các con trực tiếp theo thứ tự; không tự làm phẳng.
Traversal.walk minh họa duyệt đệ quy toàn bộ cây, gồm cả composite và decorator.
Trong Ex 6.7: lá có iterator rỗng; decorator có một con là show được bọc.
Trong Ex 6.6: helper nhận biết hai lớp chứa con bằng instanceof, không sửa Show.
Danh sách con bất biến, iterator không hỗ trợ remove().

Main dựng một concert và một nhóm hai phim; phim thứ hai và toàn chương trình có lời giới thiệu.
Tổng thời gian: 60 + 90 + 100 + 10 + 10 = 270 phút.
Tests kiểm tra thứ tự, cấu trúc lồng nhau, hợp đồng iterator, giới hạn 2-5,
phòng vệ dữ liệu, decorator, thời lượng, sao chép và khác biệt interface.

## Tệp
Show.java: giao thức chung. Movie/Concert: lá. CompositeShow: nhóm.
IntroducedShow: decorator. Traversal: duyệt cây. Main: demo. Tests: kiểm tra.

Nguồn đề: https://github.com/prmr/DesignBook/blob/master/exercises/e-chapter6.md
Nguồn chữ ký Show: https://github.com/prmr/DesignBook/blob/master/exercise-code/chapter6/Show.java
Các lớp triển khai được viết mới cho bài thực hành này.
