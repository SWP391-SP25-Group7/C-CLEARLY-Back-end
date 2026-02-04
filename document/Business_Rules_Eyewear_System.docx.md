# BUSINESS RULES (BR) Hệ thống bán kính/eyewear (Customer \+ Admin)

# **0\) Bối cảnh và phạm vi hệ thống (từ Figma \+ ERD)**

* Hệ thống là web bán kính/eyewear gồm 2 phía: Khách hàng (Customer) và Quản trị (Admin/Staff).  
* Customer: xem sản phẩm \-\> chọn biến thể (variant) \+ tuỳ chọn tròng (lens) \-\> thêm giỏ \-\> đặt hàng \-\> thanh toán \-\> theo dõi đơn \-\> cập nhật hồ sơ/địa chỉ.  
* Admin/Staff: đăng nhập trang quản trị \-\> quản lý sản phẩm/đơn hàng/tồn kho/khách hàng \-\> cập nhật trạng thái đơn \-\> duyệt đơn thuốc/tròng (nếu có) \-\> xem báo cáo.

# **1\) Quy ước vai trò và quyền (RBAC)**

### **BR-01 \- Tài khoản và vai trò**

* Mọi người dùng lưu trong bảng Users.  
* Users.role\_id bắt buộc tham chiếu Roles; quyền thao tác quản trị kiểm soát qua Role\_Permissions \<-\> Permissions.

### **BR-02 \- Phân loại người dùng**

* Nếu tồn tại bản ghi Customers (khóa user\_id) \-\> user là khách hàng.  
* Nếu tồn tại bản ghi Staff\_Profiles \-\> user là nhân viên.  
* Mặc định để đơn giản (phù hợp intern): 1 user chỉ thuộc 1 nhóm chính.

### **BR-03 \- Trạng thái tài khoản**

* Users.status quyết định truy cập: ACTIVE dùng bình thường; BANNED/INACTIVE bị chặn đăng nhập và chặn đặt hàng.

# **2\) Đăng ký/Đăng nhập và xác thực email (OTP)**

### **BR-04 \- Đăng ký**

* Email là duy nhất (khuyến nghị unique).  
* Khi đăng ký: tạo Users \+ tạo OTP trong Email\_Verifications (otp\_code, expires\_at, verified=false).

### **BR-05 \- Xác thực email**

* OTP hợp lệ khi: đúng code \+ chưa hết hạn \+ chưa verified.  
* Verify thành công: Email\_Verifications.verified=true và Users.email\_verified=true.

### **BR-06 \- Chặn hành vi rủi ro**

* Không cho checkout/đặt hàng nếu Users.email\_verified \!= true (giảm đơn rác).

# **3\) Danh mục sản phẩm và biến thể**

### **BR-07 \- Phân loại theo category\_type**

* Products.category\_type cố định 2 loại cho MVP: FRAME (gọng) và LENS (tròng).

### **BR-08 \- Hiển thị theo trạng thái active**

* Chỉ hiển thị cho khách nếu Products.is\_active=true; chỉ cho chọn nếu Product\_Variants.is\_active=true.

### **BR-09 \- Biến thể sản phẩm**

* Một Products có nhiều Product\_Variants (màu/size/giá phụ).  
* Product\_Variants.sku duy nhất; is\_master dùng làm biến thể mặc định.  
* size\_price là giá cộng thêm so với base (nếu dùng).

### **BR-10 \- Thông tin đặc thù theo loại**

* Nếu category\_type=FRAME \-\> phải có bản ghi Product\_Frames.  
* Nếu category\_type=LENS \-\> phải có bản ghi Product\_Lenses.

### **BR-11 \- Ảnh sản phẩm**

* Ảnh lưu ở Product\_Images, có thể gắn theo product\_id và/hoặc variant\_id.  
* UI ưu tiên ảnh theo variant đang chọn; nếu không có thì dùng ảnh theo product.

### **BR-12 \- Công nghệ tròng (lens technologies)**

* Master data: Master\_Lens\_Technologies.  
* Lens hỗ trợ công nghệ qua bảng nối Product\_Lens\_Tech\_Map(product\_id, tech\_id).

# **4\) Giỏ hàng (Cart)**

### **BR-13 \- Mỗi user có 1 cart hoạt động**

* Mỗi Users có tối đa 1 Carts active; khi add-to-cart mà chưa có thì tạo mới.

### **BR-14 \- Cấu trúc cart item: gọng \+ tuỳ chọn tròng**

* Cart\_Items.variant\_id \= biến thể gọng (FRAME variant).  
* Cart\_Items.lens\_variant\_id \= biến thể tròng (LENS variant), có thể NULL nếu mua gọng không tròng.  
* Cart\_Items.quantity \> 0\.

### **BR-15 \- Gộp dòng giỏ**

* Nếu thêm cùng (cart\_id, variant\_id, lens\_variant\_id) thì tăng quantity thay vì tạo dòng mới.

# **5\) Đặt hàng và trạng thái**

### **BR-16 \- Tạo đơn hàng**

* Checkout tạo Orders (user\_id, address\_id, code unique, status=PENDING/CREATED).  
* Mỗi dòng từ giỏ tạo Order\_Items (variant\_id, lens\_variant\_id, quantity, unit\_price).

### **BR-17 \- Giá cuối cùng**

* Orders.final\_amount \= tổng Order\_Items.unit\_price \* quantity ± giảm giá (coupon) ± phí (nếu có).  
* unit\_price là giá chốt tại thời điểm đặt (không phụ thuộc thay đổi giá sau này).

### **BR-18 \- Địa chỉ mặc định**

* Addresses.is\_default=true dùng làm gợi ý khi checkout; user có thể có nhiều địa chỉ.

### **BR-19 \- Log đổi trạng thái đơn**

* Mọi lần đổi Orders.status phải ghi Order\_Status\_Logs (order\_id, user\_id, new\_status, note, time).

### **BR-20 \- Luồng trạng thái đơn tối thiểu (MVP)**

* PENDING \-\> CONFIRMED \-\> SHIPPING \-\> COMPLETED.  
* Nhánh huỷ: PENDING/CONFIRMED \-\> CANCELLED.  
* Nhánh hoàn: COMPLETED \-\> REFUND\_REQUESTED \-\> REFUNDED (nếu áp dụng).

# **6\) Thanh toán và hoàn tiền**

### **BR-21 \- Payment theo đơn**

* Payments.order\_id liên kết Orders; một đơn có thể có nhiều attempt nhưng chỉ 1 payment thành công.  
* Payments.status tối thiểu: PENDING, PAID, FAILED.

### **BR-22 \- Điều kiện CONFIRMED theo phương thức**

* Thanh toán online: chỉ khi Payments.status=PAID mới cho Orders.status sang CONFIRMED.  
* COD: tạo Payments.method=COD, status=PENDING và vẫn cho CONFIRMED.

### **BR-23 \- Hoàn tiền**

* Refunds chỉ tạo khi đơn đã PAID (hoặc theo chính sách).  
* Refunds.amount không vượt quá tổng đã thanh toán; Refunds.status: REQUESTED/APPROVED/REJECTED/DONE.

# **7\) Đơn thuốc / thông số mắt (Prescription)**

### **BR-24 \- Prescription theo từng order item có tròng**

* Prescriptions.order\_item\_id gắn vào từng dòng hàng.  
* Nếu Order\_Items.lens\_variant\_id \!= NULL thì bắt buộc có prescription (ảnh hoặc thông số).

### **BR-25 \- Thẩm định đơn thuốc**

* Prescriptions.validation\_status: PENDING, VALID, INVALID.  
* Nếu INVALID thì staff ghi sales\_note và yêu cầu bổ sung.

# **8\) Tồn kho và xuất/nhập**

### **BR-26 \- Tồn kho theo kho và theo variant**

* Inventory\_Stock(warehouse\_id, variant\_id) lưu quantity\_on\_hand và location\_code.

### **BR-27 \- Không bán vượt tồn (MVP)**

* Option A (khuyến nghị cho intern): chặn checkout nếu quantity\_on\_hand \< quantity đặt.  
* Option B (mở rộng): cho preorder nếu Product\_Variants.expected\_availability có ngày dự kiến.

### **BR-28 \- Stock movement bắt buộc**

* Mọi thay đổi tồn kho phải ghi Stock\_Movements(variant\_id, reason, quantity) để truy vết.

# **9\) Nội dung và cấu hình hệ thống**

### **BR-29 \- Banner trang chủ**

* Content\_Banners.position quyết định khu vực hiển thị (HOME\_TOP, HOME\_MID, ...).

### **BR-30 \- Cấu hình key-value**

* System\_Config(config\_key, config\_value, config\_group) cho tham số như phí ship, hotline, text footer, ...

### **BR-31 \- Template thông báo**

* Notification\_Templates(template\_code, body\_html) dùng cho email/notification (OTP, xác nhận đơn, ...).

# **10\) Audit / ghi log thao tác**

### **BR-32 \- Audit cho thao tác admin quan trọng**

* Các thao tác sửa sản phẩm/tồn kho, duyệt prescription, đổi trạng thái đơn, hoàn tiền phải ghi Audit\_Logs(user\_id, action, old\_value, new\_value).

# **11\) Rule tổ chức dự án cho team 5 intern**

### **BR-33 \- Chia module theo 5 người**

* 1\) Auth \+ Profile \+ Address  
* 2\) Product catalog (products/variants/images) \+ Home banners  
* 3\) Cart \+ Checkout \+ Order creation  
* 4\) Payment \+ Order status flow \+ Refund  
* 5\) Admin dashboard \+ Inventory \+ Prescription validation \+ Audit

### **BR-34 \- MVP bắt buộc để kịp tiến độ**

* Login/Signup \+ verify email OTP  
* List sản phẩm \+ detail  
* Cart \+ checkout tạo order  
* Admin xem danh sách đơn \+ đổi trạng thái  
* Tồn kho chặn vượt tồn (Option A)

### **BR-35 \- Chuẩn dữ liệu tối giản khi code**

* Giá dùng decimal; format phía UI.  
* Đổi trạng thái đơn đi qua 1 service duy nhất để luôn ghi Order\_Status\_Logs.