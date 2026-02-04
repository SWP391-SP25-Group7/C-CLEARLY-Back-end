CREATE TABLE [Users]
(
    [
    user_id]
    uuid
    PRIMARY
    KEY, [
    email]
    varchar
(
    255
),
    [password_hash] varchar
(
    255
),
    [full_name] varchar
(
    100
),
    [phone_number] varchar
(
    20
),
    [role_id] uuid,
    [status] varchar
(
    50
),
    [is_email_verified] boolean,
    [created_at] timestamp,
    [last_login] timestamp
    )
    GO

CREATE TABLE [Roles]
(
    [
    role_id]
    uuid
    PRIMARY
    KEY, [
    role_name]
    varchar
(
    50
),
    [description] varchar
(
    255
)
    )
    GO

CREATE TABLE [Permissions]
(
    [
    permission_id]
    uuid
    PRIMARY
    KEY, [
    slug]
    varchar
(
    100
),
    [description] varchar
(
    255
)
    )
    GO

CREATE TABLE [Role_Permissions]
(
    [
    role_id]
    uuid, [
    permission_id]
    uuid,
    PRIMARY
    KEY (
    [
    role_id],
[
    permission_id]
)
    )
    GO

CREATE TABLE [Customers]
(
    [
    user_id]
    uuid
    PRIMARY
    KEY, [
    loyalty_points]
    int, [
    membership_level]
    varchar
(
    50
),
    [date_of_birth] date
    )
    GO

CREATE TABLE [Staff_Profiles]
(
    [
    user_id]
    uuid
    PRIMARY
    KEY, [
    employee_code]
    varchar
(
    20
),
    [department] varchar
(
    50
),
    [salary_grade] int
    )
    GO

CREATE TABLE [Addresses]
(
    [
    address_id]
    uuid
    PRIMARY
    KEY, [
    user_id]
    uuid, [
    street]
    varchar
(
    255
),
    [city] varchar
(
    100
),
    [is_default] boolean
    )
    GO

CREATE TABLE [Email_Verifications]
(
    [
    verification_id]
    uuid
    PRIMARY
    KEY, [
    user_id]
    uuid, [
    otp_code]
    varchar
(
    10
),
    [expired_at] timestamp,
    [verified] boolean
    )
    GO

CREATE TABLE [Products]
(
    [
    product_id]
    uuid
    PRIMARY
    KEY, [
    name]
    varchar
(
    255
),
    [category_type] varchar
(
    50
),
    [base_price] decimal,
    [is_active] boolean
    )
    GO

CREATE TABLE [Product_Frames]
(
    [
    product_id]
    uuid
    PRIMARY
    KEY, [
    material]
    varchar
(
    100
),
    [shape] varchar
(
    50
),
    [total_width_mm] int,
    [lens_width_mm] int,
    [bridge_width_mm] int
    )
    GO

CREATE TABLE [Product_Lenses]
(
    [
    product_id]
    uuid
    PRIMARY
    KEY, [
    refractive_index]
    float, [
    lens_type]
    varchar
(
    50
)
    )
    GO

CREATE TABLE [Master_Lens_Technologies]
(
    [
    tech_id]
    uuid
    PRIMARY
    KEY, [
    name]
    varchar
(
    255
)
    )
    GO

CREATE TABLE [Product_Lens_Tech_Map]
(
    [
    product_id]
    uuid, [
    tech_id]
    uuid,
    PRIMARY
    KEY (
    [
    product_id],
[
    tech_id]
)
    )
    GO

CREATE TABLE [Product_Variants]
(
    [
    variant_id]
    uuid
    PRIMARY
    KEY, [
    product_id]
    uuid, [
    sku]
    varchar
(
    50
),
    [color_name] varchar
(
    50
),
    [sale_price] decimal,
    [is_preorder] boolean,
    [expected_availability] date
    )
    GO

CREATE TABLE [Product_Images]
(
    [
    image_id]
    uuid
    PRIMARY
    KEY, [
    product_id]
    uuid, [
    variant_id]
    uuid, [
    image_url]
    varchar
(
    255
)
    )
    GO

CREATE TABLE [Carts]
(
    [
    cart_id]
    uuid
    PRIMARY
    KEY,
[
    user_id]
    uuid
)
    GO

CREATE TABLE [Cart_Items]
(
    [
    cart_item_id]
    uuid
    PRIMARY
    KEY, [
    cart_id]
    uuid, [
    variant_id]
    uuid, [
    lens_variant_id]
    uuid,
[
    quantity]
    int
)
    GO

CREATE TABLE [Orders]
(
    [
    order_id]
    uuid
    PRIMARY
    KEY, [
    user_id]
    uuid, [
    code]
    varchar
(
    20
),
    [status] varchar
(
    50
),
    [final_amount] decimal,
    [coupon_code] varchar
(
    20
),
    [address_id] uuid
    )
    GO

CREATE TABLE [Order_Items]
(
    [
    order_item_id]
    uuid
    PRIMARY
    KEY, [
    order_id]
    uuid, [
    variant_id]
    uuid, [
    lens_variant_id]
    uuid,
[
    unit_price]
    decimal
)
    GO

CREATE TABLE [Prescriptions]
(
    [
    prescription_id]
    uuid
    PRIMARY
    KEY, [
    order_item_id]
    uuid, [
    image_url]
    varchar
(
    255
),
    [sph_od] float,
    [validation_status] varchar
(
    50
),
    [sales_note] text
    )
    GO

CREATE TABLE [Payments]
(
    [
    payment_id]
    uuid
    PRIMARY
    KEY, [
    order_id]
    uuid, [
    method]
    varchar
(
    50
),
    [status] varchar
(
    50
),
    [payos_order_code] varchar
(
    50
)
    )
    GO

CREATE TABLE [Refunds]
(
    [
    refund_id]
    uuid
    PRIMARY
    KEY, [
    order_id]
    uuid, [
    amount]
    decimal, [
    reason]
    text, [
    status]
    varchar
(
    50
),
    [created_at] timestamp
    )
    GO

CREATE TABLE [Order_Status_Logs]
(
    [
    log_id]
    uuid
    PRIMARY
    KEY, [
    order_id]
    uuid, [
    user_id]
    uuid, [
    new_status]
    varchar
(
    50
),
    [note] text
    )
    GO

CREATE TABLE [Warehouses]
(
    [
    warehouse_id]
    uuid
    PRIMARY
    KEY, [
    name]
    varchar
(
    255
)
    )
    GO

CREATE TABLE [Inventory_Stock]
(
    [
    warehouse_id]
    uuid, [
    variant_id]
    uuid, [
    quantity_on_hand]
    int, [
    location_code]
    varchar
(
    255
),
    PRIMARY KEY
(
    [
    warehouse_id],
[
    variant_id]
)
    )
    GO

CREATE TABLE [Stock_Movements]
(
    [
    movement_id]
    uuid
    PRIMARY
    KEY, [
    variant_id]
    uuid, [
    reason]
    varchar
(
    50
),
    [quantity] int
    )
    GO

CREATE TABLE [Promotions]
(
    [
    promotion_id]
    uuid
    PRIMARY
    KEY, [
    code]
    varchar
(
    255
),
    [discount_type] varchar
(
    50
),
    [value] decimal
    )
    GO

CREATE TABLE [System_Configs]
(
    [
    config_key]
    varchar
(
    100
) PRIMARY KEY,
    [config_value] text,
    [config_group] varchar
(
    50
)
    )
    GO

CREATE TABLE [Audit_Logs]
(
    [
    log_id]
    uuid
    PRIMARY
    KEY, [
    user_id]
    uuid, [
    action]
    varchar
(
    255
),
    [old_value] json,
    [new_value] json
    )
    GO

CREATE TABLE [Content_Banners]
(
    [
    banner_id]
    uuid
    PRIMARY
    KEY, [
    image_url]
    varchar
(
    255
),
    [position] varchar
(
    50
)
    )
    GO

CREATE TABLE [Notification_Templates]
(
    [
    template_code]
    varchar
(
    255
) PRIMARY KEY,
    [body_html] text
    )
    GO
    EXEC sp_addextendedproperty
    @name = N'Column_Description',
    @value = 'VD: admin@lily.com',
    @level0type = N'Schema', @level0name = 'dbo',
    @level1type = N'Table', @level1name = 'Users',
    @level2type = N'Column', @level2name = 'email';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'Mỗi user chỉ có 1 role',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Users',
@level2type = N'Column', @level2name = 'role_id';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'ACTIVE, BANNED',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Users',
@level2type = N'Column', @level2name = 'status';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'CUSTOMER, SALES, OPS, MANAGER, ADMIN',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Roles',
@level2type = N'Column', @level2name = 'role_name';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'order:confirm, product:view',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Permissions',
@level2type = N'Column', @level2name = 'slug';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'FRAME, LENS',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Products',
@level2type = N'Column', @level2name = 'category_type';
GO

EXEC sp_addextendedproperty
@name = N'Column_Description',
@value = 'REQUESTED, APPROVED, REJECTED',
@level0type = N'Schema', @level0name = 'dbo',
@level1type = N'Table',  @level1name = 'Refunds',
@level2type = N'Column', @level2name = 'status';
GO

ALTER TABLE [Users] ADD FOREIGN KEY ([role_id]) REFERENCES [Roles] ([role_id])
    GO

ALTER TABLE [Role_Permissions] ADD FOREIGN KEY ([role_id]) REFERENCES [Roles] ([role_id])
    GO

ALTER TABLE [Role_Permissions] ADD FOREIGN KEY ([permission_id]) REFERENCES [Permissions] ([permission_id])
    GO

ALTER TABLE [Customers] ADD FOREIGN KEY ([user_id]) REFERENCES [Users] ([user_id])
    GO

ALTER TABLE [Staff_Profiles] ADD FOREIGN KEY ([user_id]) REFERENCES [Users] ([user_id])
    GO

ALTER TABLE [Addresses] ADD FOREIGN KEY ([user_id]) REFERENCES [Users] ([user_id])
    GO

ALTER TABLE [Email_Verifications] ADD FOREIGN KEY ([user_id]) REFERENCES [Users] ([user_id])
    GO

ALTER TABLE [Product_Frames] ADD FOREIGN KEY ([product_id]) REFERENCES [Products] ([product_id])
    GO

ALTER TABLE [Product_Lenses] ADD FOREIGN KEY ([product_id]) REFERENCES [Products] ([product_id])
    GO

ALTER TABLE [Product_Lens_Tech_Map] ADD FOREIGN KEY ([product_id]) REFERENCES [Product_Lenses] ([product_id])
    GO

ALTER TABLE [Product_Lens_Tech_Map] ADD FOREIGN KEY ([tech_id]) REFERENCES [Master_Lens_Technologies] ([tech_id])
    GO

ALTER TABLE [Product_Variants] ADD FOREIGN KEY ([product_id]) REFERENCES [Products] ([product_id])
    GO

ALTER TABLE [Product_Images] ADD FOREIGN KEY ([product_id]) REFERENCES [Products] ([product_id])
    GO

ALTER TABLE [Product_Images] ADD FOREIGN KEY ([variant_id]) REFERENCES [Product_Variants] ([variant_id])
    GO

ALTER TABLE [Carts] ADD FOREIGN KEY ([user_id]) REFERENCES [Users] ([user_id])
    GO

ALTER TABLE [Cart_Items] ADD FOREIGN KEY ([cart_id]) REFERENCES [Carts] ([cart_id])
    GO

ALTER TABLE [Cart_Items] ADD FOREIGN KEY ([variant_id]) REFERENCES [Product_Variants] ([variant_id])
    GO

ALTER TABLE [Cart_Items] ADD FOREIGN KEY ([lens_variant_id]) REFERENCES [Product_Variants] ([variant_id])
    GO

ALTER TABLE [Orders] ADD FOREIGN KEY ([user_id]) REFERENCES [Users] ([user_id])
    GO

ALTER TABLE [Orders] ADD FOREIGN KEY ([address_id]) REFERENCES [Addresses] ([address_id])
    GO

ALTER TABLE [Order_Items] ADD FOREIGN KEY ([order_id]) REFERENCES [Orders] ([order_id])
    GO

ALTER TABLE [Order_Items] ADD FOREIGN KEY ([variant_id]) REFERENCES [Product_Variants] ([variant_id])
    GO

ALTER TABLE [Order_Items] ADD FOREIGN KEY ([lens_variant_id]) REFERENCES [Product_Variants] ([variant_id])
    GO

ALTER TABLE [Prescriptions] ADD FOREIGN KEY ([order_item_id]) REFERENCES [Order_Items] ([order_item_id])
    GO

ALTER TABLE [Payments] ADD FOREIGN KEY ([order_id]) REFERENCES [Orders] ([order_id])
    GO

ALTER TABLE [Refunds] ADD FOREIGN KEY ([order_id]) REFERENCES [Orders] ([order_id])
    GO

ALTER TABLE [Order_Status_Logs] ADD FOREIGN KEY ([order_id]) REFERENCES [Orders] ([order_id])
    GO

ALTER TABLE [Order_Status_Logs] ADD FOREIGN KEY ([user_id]) REFERENCES [Users] ([user_id])
    GO

ALTER TABLE [Inventory_Stock] ADD FOREIGN KEY ([warehouse_id]) REFERENCES [Warehouses] ([warehouse_id])
    GO

ALTER TABLE [Inventory_Stock] ADD FOREIGN KEY ([variant_id]) REFERENCES [Product_Variants] ([variant_id])
    GO

ALTER TABLE [Stock_Movements] ADD FOREIGN KEY ([variant_id]) REFERENCES [Product_Variants] ([variant_id])
    GO

ALTER TABLE [Orders] ADD FOREIGN KEY ([coupon_code]) REFERENCES [Promotions] ([code])
    GO

ALTER TABLE [Audit_Logs] ADD FOREIGN KEY ([user_id]) REFERENCES [Users] ([user_id])
    GO
