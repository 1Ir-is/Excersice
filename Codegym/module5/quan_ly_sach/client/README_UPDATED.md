# Hệ thống Quản lý Sách

Ứng dụng quản lý sách được xây dựng với React.js (Frontend) và Spring Boot (Backend).

## Tính năng chính

### 1. Quản lý Sách

- Xem danh sách tất cả sách
- Thêm sách mới với thông tin: tên sách, tác giả, danh mục, số lượng, mô tả
- Sửa thông tin sách
- Xóa sách
- Tìm kiếm sách theo tên hoặc tác giả
- Lọc sách theo danh mục

### 2. Quản lý Danh mục

- Xem danh sách danh mục
- Thêm danh mục mới
- Sửa thông tin danh mục
- Xóa danh mục

## Cấu trúc API Backend

### Book Controller (`/api/v1/books`)

- `GET /api/v1/books` - Lấy tất cả sách
- `GET /api/v1/books/{id}` - Lấy sách theo ID
- `GET /api/v1/books/category/{id}` - Lấy sách theo danh mục
- `POST /api/v1/books` - Tạo sách mới
- `PUT /api/v1/books/{id}` - Cập nhật sách
- `DELETE /api/v1/books/{id}` - Xóa sách

### Category Controller (`/api/v1/categories`)

- `GET /api/v1/categories` - Lấy tất cả danh mục
- `GET /api/v1/categories/{id}` - Lấy danh mục theo ID
- `POST /api/v1/categories` - Tạo danh mục mới
- `PUT /api/v1/categories/{id}` - Cập nhật danh mục
- `DELETE /api/v1/categories/{id}` - Xóa danh mục

## Cấu trúc dữ liệu

### Book DTO

```json
{
  "id": "number",
  "title": "string",
  "author": "string",
  "categoryId": "number",
  "quantity": "number",
  "description": "string"
}
```

### Category DTO

```json
{
  "id": "number",
  "name": "string",
  "description": "string"
}
```

## Cài đặt và chạy

### Frontend (React)

```bash
cd client
npm install
npm start
```

### Backend (Spring Boot)

- Chạy server Spring Boot trên port 8080
- Cấu hình CORS cho phép truy cập từ frontend
- Đảm bảo database được kết nối đúng

## Cấu hình API

- API base URL: `http://localhost:8080`
- Frontend chạy trên: `http://localhost:3000`
- CORS được bật cho tất cả origins

## Giao diện người dùng

### Navigation

- **Danh sách sách**: Hiển thị tất cả sách với khả năng tìm kiếm và lọc
- **Thêm sách mới**: Form thêm/sửa sách
- **Quản lý danh mục**: Quản lý các danh mục sách

### Tính năng UI

- Responsive design cho mobile và desktop
- Loading states
- Error handling và validation
- Confirmation dialogs cho các thao tác xóa
- Form validation với thông báo lỗi chi tiết

## Công nghệ sử dụng

### Frontend

- React.js 19.1.1
- Axios cho HTTP requests
- CSS3 với responsive design
- Modern ES6+ JavaScript

### Backend

- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- CORS configuration

## Lưu ý

- Đảm bảo backend server đang chạy trước khi sử dụng frontend
- Kiểm tra kết nối database cho backend
- Frontend sẽ hiển thị thông báo lỗi nếu không thể kết nối tới API
