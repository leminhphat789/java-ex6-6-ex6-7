# Bài tập Ex 6.6 và Ex 6.7

Bài thực hành chương 6 về Composite, Decorator và Iterator trong Java. Mỗi bài được tổ chức thành một project riêng và có thể chạy độc lập.

## Nội dung

| Bài tập | Nội dung triển khai | Mã nguồn và hướng dẫn |
| --- | --- | --- |
| Ex 6.6 | Duyệt các show trong composite bằng iterator, giữ nguyên interface `Show`. | [Ex6_6](Ex6_6/) |
| Ex 6.7 | Bổ sung khả năng duyệt các show con qua interface `Show` bằng `Iterable<Show>`. | [Ex6_7](Ex6_7/) |

## Cấu trúc

```text
Ex6_6/
├── src/ex66/       # mã nguồn bài 6.6
├── README.md       # giải thích và hướng dẫn chạy
└── run.bat         # biên dịch và chạy trên windows

Ex6_7/
├── src/ex67/       # mã nguồn bài 6.7
├── README.md       # giải thích và hướng dẫn chạy
└── run.bat         # biên dịch và chạy trên windows
```

## Cách chạy

Cần cài **JDK 17 trở lên**, với `java` và `javac` có trong PATH. Không cần thư viện ngoài.

Trên Windows, mở terminal trong thư mục bài muốn chạy rồi nhập:

```powershell
.\run.bat
```

Script sẽ biên dịch mã nguồn, chạy chương trình minh họa `Main` và các kiểm tra trong `Tests`.

Nếu dùng IDE, mở riêng thư mục `Ex6_6` hoặc `Ex6_7`, chọn `src` làm thư mục mã nguồn và chạy `Main.java`.

## Các lớp chính

- `Show`: interface chung của các show.
- `Movie`, `Concert`: phim và buổi hòa nhạc.
- `CompositeShow`: nhóm từ 2 đến 5 show.
- `IntroducedShow`: thêm phần giới thiệu 10 phút trước một show.
- `Traversal`: hỗ trợ duyệt toàn bộ cây show.
- `Main`: ví dụ sử dụng.
- `Tests`: kiểm tra các hành vi của chương trình.

Ví dụ minh họa gồm một concert và hai phim, có phần giới thiệu cho cả chương trình và phim thứ hai. Tổng thời lượng là **270 phút**.
