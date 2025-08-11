# Authentication Service - Microservice

## 1. Giới thiệu

Authentication Service là một microservice chuyên biệt trong hệ thống Microservice, chịu trách nhiệm về:

- Xác thực người dùng (đăng nhập).
- Quản lý JWT (JSON Web Token): phát hành, kiểm tra tính hợp lệ, thu hồi và làm mới token.
- Quản lý quyền truy cập (roles, permissions).
- Tích hợp bảo mật với Spring Security.

Dịch vụ này giúp tách biệt chức năng xác thực khỏi các thành phần khác trong hệ thống, giúp nâng cao tính mô-đun, dễ bảo trì và mở rộng.

---

## 2. Chức năng chính

- **Đăng nhập:** Xác thực username/password, phát hành JWT mới.
- **Tạo JWT:** Dùng thuật toán HMAC SHA-512 với khóa bí mật.
- **Kiểm tra token (Introspect):** Xác minh token hợp lệ, chưa hết hạn, chưa bị thu hồi.
- **Logout:** Thu hồi token, lưu token đã bị thu hồi vào database để chặn sử dụng.
- **Refresh Token:** Đổi token mới khi token cũ sắp hết hạn, đồng thời thu hồi token cũ.
- **Quản lý người dùng:** Tạo, sửa, xóa, lấy thông tin user với phân quyền phù hợp.
- **Quản lý roles & permissions:** Tạo, xem, xóa roles và permissions.

---

## 3. Kiến trúc & Công nghệ

- Spring Boot, Spring Security
- JWT với Nimbus JOSE + JWT
- BCrypt để mã hóa mật khẩu
- Lombok giảm boilerplate code
- JPA repository để quản lý dữ liệu User, Role, Permission, InvalidatedToken
- Cấu hình bảo mật với SecurityFilterChain và JwtAuthenticationConverter
- Tích hợp CustomJwtDecoder để kiểm tra token tùy chỉnh

---

## 4. Tài liệu API

**Base URL:** `http://<host>:8080/identity`

### 4.1 Authentication API (`/auth`)

| Method | Endpoint         | Mô tả                       | Request Body                       | Response                         | Quyền truy cập   |
|--------|------------------|-----------------------------|----------------------------------|---------------------------------|------------------|
| POST   | `/auth/token`    | Đăng nhập, lấy JWT mới       | `{ "username": "...", "password": "..." }` | `{ "token": "...", "expiryTime": "..." }` | Public           |
| POST   | `/auth/introspect`| Kiểm tra token hợp lệ        | `{ "token": "..." }`              | `{ "valid": true/false }`        | Public           |
| POST   | `/auth/refresh`  | Làm mới token JWT            | `{ "token": "..." }`              | `{ "token": "...", "expiryTime": "..." }` | Public           |
| POST   | `/auth/logout`   | Thu hồi token                | `{ "token": "..." }`              | `{}`                            | Public           |

### 4.2 Permission API (`/permissions`)

| Method | Endpoint         | Mô tả                     | Request Body                          | Response                        | Quyền truy cập |
|--------|------------------|---------------------------|-------------------------------------|--------------------------------|----------------|
| POST   | `/permissions`   | Tạo permission mới        | `{ "name": "...", "description": "..." }` | Permission object              | Cần xác thực   |
| GET    | `/permissions`   | Lấy danh sách permission  | Không                              | Danh sách permissions           | Cần xác thực   |
| DELETE | `/permissions/{permission}` | Xóa permission           | Không                              | `{}`                           | Cần xác thực   |

### 4.3 Role API (`/roles`)

| Method | Endpoint       | Mô tả                     | Request Body                                   | Response                          | Quyền truy cập |
|--------|----------------|---------------------------|-----------------------------------------------|----------------------------------|----------------|
| POST   | `/roles`       | Tạo role mới kèm permissions | `{ "name": "...", "description": "...", "permissions": ["perm1","perm2"] }` | Role object                     | Cần xác thực   |
| GET    | `/roles`       | Lấy danh sách role         | Không                                         | Danh sách roles                  | Cần xác thực   |
| DELETE | `/roles/{role}`| Xóa role theo tên          | Không                                         | `{}`                            | Cần xác thực   |

### 4.4 User API (`/users`)

| Method | Endpoint           | Mô tả                     | Request Body                                                   | Response                        | Quyền truy cập      |
|--------|--------------------|---------------------------|----------------------------------------------------------------|--------------------------------|---------------------|
| POST   | `/users`           | Tạo user mới              | `{ "username": "...", "password": "...", "roles": ["role1"] }` | User object                    | Public (đăng ký)     |
| GET    | `/users`           | Lấy danh sách user        | Không                                                         | Danh sách users                | Chỉ admin           |
| GET    | `/users/{userId}`  | Lấy user theo ID          | Không                                                         | User object                    | Chỉ admin           |
| GET    | `/users/my-info`   | Lấy thông tin user hiện tại | Không                                                         | User object                    | User đã xác thực    |
| DELETE | `/users/{userId}`  | Xóa user                  | Không                                                         | `{ "result": "User has been deleted" }` | Chỉ admin    |
| PUT    | `/users/{userId}`  | Cập nhật thông tin user   | UserUpdateRequest JSON                                         | User object                    | Chỉ admin           |

---

## 5. Cấu hình ứng dụng

- Server chạy trên cổng 8080
- Context path: `/identity`
- Ví dụ:

```yaml
server:
  port: 8080
  servlet:
    context-path: /identity
