Recruitment-service

Recruitment-service là một microservice hỗ trợ quản lý và tự động hóa quy trình tuyển dụng. Dịch vụ này được thiết kế nhằm phục vụ các doanh nghiệp trong việc đăng tuyển, quản lý ứng viên, và theo dõi tiến trình phỏng vấn một cách hiệu quả.

🔑 Tính năng chính

Quản lý tin tuyển dụng: tạo, chỉnh sửa, xóa và hiển thị các tin tuyển dụng.

Quản lý ứng viên: lưu trữ thông tin ứng viên, hồ sơ, CV và trạng thái ứng tuyển.

Quy trình tuyển dụng: theo dõi các bước như lọc hồ sơ, phỏng vấn, đánh giá và kết quả cuối cùng.

Tích hợp API: cung cấp REST API cho các hệ thống khác như HRM, CRM hoặc frontend web/app.

Bảo mật: xác thực và phân quyền cho nhà tuyển dụng, quản trị viên và ứng viên.

🏗️ Kiến trúc

Thiết kế theo hướng microservices dễ dàng mở rộng.

Kết nối với cơ sở dữ liệu (SQL/NoSQL tùy cấu hình).

Giao tiếp qua REST API (hoặc gRPC nếu cần).

🚀 Cài đặt & Chạy

Clone repository:

git clone https://github.com/your-org/recruitment-service.git
cd recruitment-service


Cấu hình file .env với thông tin database, JWT secret, và cổng chạy.

Chạy service:

docker-compose up -d


hoặc

npm install && npm start

📂 Cấu trúc thư mục
recruitment-service/
 ├── src/              # Code nguồn
 ├── config/           # Cấu hình ứng dụng
 ├── tests/            # Unit tests
 ├── docs/             # Tài liệu API
 └── README.md

📌 API Endpoints

POST /jobs – tạo tin tuyển dụng

GET /jobs – lấy danh sách tin tuyển dụng

POST /candidates – thêm ứng viên

GET /candidates/:id – chi tiết ứng viên

PATCH /candidates/:id/status – cập nhật trạng thái ứng tuyển

Docker để triển khai
