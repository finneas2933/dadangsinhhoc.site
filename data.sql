-- Xóa thủ tục nếu tồn tại
DROP PROCEDURE IF EXISTS DeleteLoai;
DROP PROCEDURE IF EXISTS DeleteUser;
DROP PROCEDURE IF EXISTS HienThi;
DROP PROCEDURE IF EXISTS InsertBo;
DROP PROCEDURE IF EXISTS InsertHo;
DROP PROCEDURE IF EXISTS InsertLoai;
DROP PROCEDURE IF EXISTS InsertLop;
DROP PROCEDURE IF EXISTS InsertNganh;
DROP PROCEDURE IF EXISTS InsertUser;
DROP PROCEDURE IF EXISTS Search;
DROP PROCEDURE IF EXISTS SuaNganh;
DROP PROCEDURE IF EXISTS ThongKe;
DROP PROCEDURE IF EXISTS ThongKeDongVat;
DROP PROCEDURE IF EXISTS ThongKeThucVat;
DROP PROCEDURE IF EXISTS UpdateBo;
DROP PROCEDURE IF EXISTS UpdateHo;
DROP PROCEDURE IF EXISTS UpdateLoai;
DROP PROCEDURE IF EXISTS UpdateLop;
DROP PROCEDURE IF EXISTS UpdateNganh;
DROP PROCEDURE IF EXISTS UpdateThongTin;
DROP PROCEDURE IF EXISTS UpdateUser;

-- Thủ tục DeleteLoai
CREATE PROCEDURE DeleteLoai(IN id INT)
BEGIN
    DELETE FROM HinhAnhLoai WHERE id_dtv_loai = id;
    DELETE FROM Loai WHERE id = id;
END;

-- Thủ tục DeleteUser
CREATE PROCEDURE DeleteUser(IN UserID INT)
BEGIN
    DELETE FROM `user` WHERE id = UserID;
END;

-- Thủ tục HienThi
CREATE PROCEDURE HienThi(IN id INT)
BEGIN
    SELECT
        l.name AS TenTiengViet,
        l.name_latinh AS TenLatinh,
        l.ten_khac AS TenKhac,
        h.name AS Ho,
        b.name AS Bo,
        lp.name AS Lop,
        n.name AS Nganh,
        l.muc_do_bao_ton_iucn,
        l.muc_do_bao_ton_sdvn,
        l.muc_do_bao_ton_ndcp,
        l.muc_do_bao_ton_nd64cp,
        l.dac_diem,
        l.gia_tri_su_dung,
        l.phan_bo
    FROM 
        Nganh n
    JOIN 
        Lop lp ON n.id = lp.id_dtv_nganh
    JOIN 
        Bo b ON lp.id = b.id_dtv_lop
    JOIN 
        Ho h ON b.id = h.id_dtv_bo
    JOIN 
        Loai l ON h.id = l.id_dtv_ho
    WHERE l.id = id;
END;

-- Thủ tục InsertBo
CREATE PROCEDURE InsertBo(
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN loai BIT,
    IN id_dtv_lop INT,
    IN status BIT,
    IN created_at DATETIME,
    IN created_by INT
)
BEGIN
    INSERT INTO Bo (name, name_latinh, loai, id_dtv_lop, status, created_at, created_by)
    VALUES (name, name_latinh, loai, id_dtv_lop, status, created_at, created_by);
END;

-- Thủ tục InsertHo
CREATE PROCEDURE InsertHo(
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN loai BIT,
    IN id_dtv_bo INT,
    IN status BIT,
    IN created_at DATETIME,
    IN created_by INT
)
BEGIN
    INSERT INTO Ho (name, name_latinh, loai, id_dtv_bo, status, created_at, created_by)
    VALUES (name, name_latinh, loai, id_dtv_bo, status, created_at, created_by);
END;

-- Thủ tục InsertLoai
CREATE PROCEDURE InsertLoai(
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN loai BIT,
    IN id_dtv_ho INT,
    IN ten_khac NVARCHAR(255),
    IN dac_diem NVARCHAR(255),
    IN gia_tri_su_dung NVARCHAR(255),
    IN phan_bo NVARCHAR(255),
    IN nguon_tai_lieu NVARCHAR(255),
    IN muc_do_bao_ton_iucn NVARCHAR(50),
    IN muc_do_bao_ton_sdvn NVARCHAR(50),
    IN muc_do_bao_ton_ndcp NVARCHAR(50),
    IN muc_do_bao_ton_nd64cp NVARCHAR(50),
    IN status BIT,
    IN created_at DATETIME,
    IN created_by INT
)
BEGIN
    INSERT INTO Loai (
        name, name_latinh, loai, id_dtv_ho, ten_khac, dac_diem, gia_tri_su_dung, phan_bo,
        nguon_tai_lieu, muc_do_bao_ton_iucn, muc_do_bao_ton_sdvn,
        muc_do_bao_ton_ndcp, muc_do_bao_ton_nd64cp, status, created_at, created_by
    )
    VALUES (
        name, name_latinh, loai, id_dtv_ho, ten_khac, dac_diem, gia_tri_su_dung,
        phan_bo, nguon_tai_lieu, muc_do_bao_ton_iucn,
        muc_do_bao_ton_sdvn, muc_do_bao_ton_ndcp, muc_do_bao_ton_nd64cp,
        status, created_at, created_by
    );
END;

-- Thủ tục InsertLop
CREATE PROCEDURE InsertLop(
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN loai INT,
    IN id_dtv_nganh INT,
    IN status BIT,
    IN created_at DATETIME,
    IN created_by INT
)
BEGIN
    INSERT INTO Lop (name, name_latinh, loai, id_dtv_nganh, status, created_at, created_by)
    VALUES (name, name_latinh, loai, id_dtv_nganh, status, created_at, created_by);
END;

-- Thủ tục InsertNganh
CREATE PROCEDURE InsertNganh(
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN loai BIT,
    IN status BIT,
    IN created_at DATETIME,
    IN created_by INT
)
BEGIN
    INSERT INTO Nganh (name, name_latinh, loai, status, created_at, created_by)
    VALUES (name, name_latinh, loai, status, created_at, created_by);
END;

-- Thủ tục InsertUser
CREATE PROCEDURE InsertUser(
    IN Address NVARCHAR(255),
    IN Name NVARCHAR(255),
    IN Password NVARCHAR(128),
    IN Email NVARCHAR(255),
    IN Phone NVARCHAR(255),
    IN Gender NVARCHAR(10),
    IN Dob DATE,
    IN CreatedAt DATETIME,
    IN IsAdmin TINYINT,
    IN Status BIT
)
BEGIN
    INSERT INTO `user` (address, name, password, email, phone, gender, dob, created_at, is_admin, status)
    VALUES (Address, Name, Password, Email, Phone, Gender, Dob, CreatedAt, IsAdmin, Status);
END;

-- Thủ tục Search
CREATE PROCEDURE Search(
    IN loai BIT,
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN id_dtv_nganh INT,
    IN id_dtv_lop INT,
    IN id_dtv_bo INT,
    IN id_dtv_ho INT,
    IN muc_do_bao_ton_iucn NVARCHAR(50),
    IN muc_do_bao_ton_sdvn NVARCHAR(50),
    IN muc_do_bao_ton_ndcp NVARCHAR(50),
    IN muc_do_bao_ton_nd64cp NVARCHAR(50)
)
BEGIN
    SELECT
        l.id AS ID,
        l.name AS TenTiengViet,
        l.name_latinh AS TenLatinh,
        l.ten_khac AS TenKhac,
        h.name AS Ho,
        b.name AS Bo,
        lp.name AS Lop,
        n.name AS Nganh
    FROM 
        Nganh n
    JOIN 
        Lop lp ON n.id = lp.id_dtv_nganh
    JOIN 
        Bo b ON lp.id = b.id_dtv_lop
    JOIN 
        Ho h ON b.id = h.id_dtv_bo
    JOIN 
        Loai l ON h.id = l.id_dtv_ho
    WHERE 
        (l.loai = loai OR loai IS NULL)
    AND (l.name LIKE CONCAT('%', name, '%') OR name IS NULL)
    AND (l.name_latinh LIKE CONCAT('%', name_latinh, '%') OR name_latinh IS NULL)
    AND (n.id = id_dtv_nganh OR id_dtv_nganh IS NULL)
    AND (lp.id = id_dtv_lop OR id_dtv_lop IS NULL)
    AND (b.id = id_dtv_bo OR id_dtv_bo IS NULL)
    AND (h.id = id_dtv_ho OR id_dtv_ho IS NULL)
    AND (l.muc_do_bao_ton_iucn = muc_do_bao_ton_iucn OR muc_do_bao_ton_iucn IS NULL OR muc_do_bao_ton_iucn = '')
    AND (l.muc_do_bao_ton_sdvn = muc_do_bao_ton_sdvn OR muc_do_bao_ton_sdvn IS NULL OR muc_do_bao_ton_sdvn = '')
    AND (l.muc_do_bao_ton_ndcp = muc_do_bao_ton_ndcp OR muc_do_bao_ton_ndcp IS NULL OR muc_do_bao_ton_ndcp = '')
    AND (l.muc_do_bao_ton_nd64cp = muc_do_bao_ton_nd64cp OR muc_do_bao_ton_nd64cp IS NULL OR muc_do_bao_ton_nd64cp = '');
END;

-- Thủ tục SuaNganh
CREATE PROCEDURE SuaNganh(
    IN ID INT,
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN loai BIT,
    IN status BIT,
    IN updated_at DATETIME,
    IN updated_by INT
)
BEGIN
    UPDATE Nganh
    SET 
        name = name,
        name_latinh = name_latinh,
        loai = loai,
        status = status,
        updated_at = updated_at,
        updated_by = updated_by
    WHERE id = ID;
END;

-- Thủ tục ThongKe
CREATE PROCEDURE ThongKe()
BEGIN
    SELECT 
        n.name AS Nganh,
        COUNT(l.id) AS SoLuongLoai
    FROM 
        Nganh n
    LEFT JOIN 
        Lop lp ON n.id = lp.id_dtv_nganh
    LEFT JOIN 
        Bo b ON lp.id = b.id_dtv_lop
    LEFT JOIN 
        Ho h ON b.id = h.id_dtv_bo
    LEFT JOIN 
        Loai l ON h.id = l.id_dtv_ho
    GROUP BY 
        n.name;
END;

-- Thủ tục ThongKeDongVat
CREATE PROCEDURE ThongKeDongVat()
BEGIN
    SELECT 
        n.name AS Nganh,
        COUNT(l.id) AS SoLuongLoai
    FROM 
        Nganh n
    LEFT JOIN 
        Lop lp ON n.id = lp.id_dtv_nganh
    LEFT JOIN 
        Bo b ON lp.id = b.id_dtv_lop
    LEFT JOIN 
        Ho h ON b.id = h.id_dtv_bo
    LEFT JOIN 
        Loai l ON h.id = l.id_dtv_ho
    WHERE 
        l.loai = 0
    GROUP BY 
        n.name;
END;

-- Thủ tục ThongKeThucVat
CREATE PROCEDURE ThongKeThucVat()
BEGIN
    SELECT 
        n.name AS Nganh,
        COUNT(l.id) AS SoLuongLoai
    FROM 
        Nganh n
    LEFT JOIN 
        Lop lp ON n.id = lp.id_dtv_nganh
    LEFT JOIN 
        Bo b ON lp.id = b.id_dtv_lop
    LEFT JOIN 
        Ho h ON b.id = h.id_dtv_bo
    LEFT JOIN 
        Loai l ON h.id = l.id_dtv_ho
    WHERE 
        l.loai = 1
    GROUP BY 
        n.name;
END;

-- Thủ tục UpdateBo
CREATE PROCEDURE UpdateBo(
    IN ID INT,
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN loai BIT,
    IN id_dtv_lop INT,
    IN status BIT,
    IN updated_at DATETIME,
    IN updated_by INT
)
BEGIN
    UPDATE Bo
    SET 
        name = name,
        name_latinh = name_latinh,
        loai = loai,
        id_dtv_lop = id_dtv_lop,
        status = status,
        updated_at = updated_at,
        updated_by = updated_by
    WHERE id = ID;
END;

-- Thủ tục UpdateHo
CREATE PROCEDURE UpdateHo(
    IN ID INT,
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN loai BIT,
    IN id_dtv_bo INT,
    IN status BIT,
    IN updated_at DATETIME,
    IN updated_by INT
)
BEGIN
    UPDATE Ho
    SET 
        name = name,
        name_latinh = name_latinh,
        loai = loai,
        id_dtv_bo = id_dtv_bo,
        status = status,
        updated_at = updated_at,
        updated_by = updated_by
    WHERE id = ID;
END;

-- Thủ tục UpdateLoai
CREATE PROCEDURE UpdateLoai(
    IN ID INT,
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN loai BIT,
    IN id_dtv_ho INT,
    IN ten_khac NVARCHAR(255),
    IN dac_diem NVARCHAR(255),
    IN gia_tri_su_dung NVARCHAR(255),
    IN phan_bo NVARCHAR(255),
    IN nguon_tai_lieu NVARCHAR(255),
    IN muc_do_bao_ton_iucn NVARCHAR(50),
    IN muc_do_bao_ton_sdvn NVARCHAR(50),
    IN muc_do_bao_ton_ndcp NVARCHAR(50),
    IN muc_do_bao_ton_nd64cp NVARCHAR(50),
    IN status BIT,
    IN updated_at DATETIME,
    IN updated_by INT
)
BEGIN
    UPDATE Loai
    SET 
        name = name,
        name_latinh = name_latinh,
        loai = loai,
        id_dtv_ho = id_dtv_ho,
        ten_khac = ten_khac,
        dac_diem = dac_diem,
        gia_tri_su_dung = gia_tri_su_dung,
        phan_bo = phan_bo,
        nguon_tai_lieu = nguon_tai_lieu,
        muc_do_bao_ton_iucn = muc_do_bao_ton_iucn,
        muc_do_bao_ton_sdvn = muc_do_bao_ton_sdvn,
        muc_do_bao_ton_ndcp = muc_do_bao_ton_ndcp,
        muc_do_bao_ton_nd64cp = muc_do_bao_ton_nd64cp,
        status = status,
        updated_at = updated_at,
        updated_by = updated_by
    WHERE id = ID;
END;

-- Thủ tục UpdateLop
CREATE PROCEDURE UpdateLop(
    IN ID INT,
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN loai BIT,
    IN id_dtv_nganh INT,
    IN status BIT,
    IN updated_at DATETIME,
    IN updated_by INT
)
BEGIN
    UPDATE Lop
    SET 
        name = name,
        name_latinh = name_latinh,
        loai = loai,
        id_dtv_nganh = id_dtv_nganh,
        status = status,
        updated_at = updated_at,
        updated_by = updated_by
    WHERE id = ID;
END;

-- Thủ tục UpdateNganh
CREATE PROCEDURE UpdateNganh(
    IN ID INT,
    IN name NVARCHAR(255),
    IN name_latinh NVARCHAR(255),
    IN loai BIT,
    IN status BIT,
    IN updated_at DATETIME,
    IN updated_by INT
)
BEGIN
    UPDATE Nganh
    SET 
        name = name,
        name_latinh = name_latinh,
        loai = loai,
        status = status,
        updated_at = updated_at,
        updated_by = updated_by
    WHERE id = ID;
END;

-- Thủ tục UpdateThongTin
CREATE PROCEDURE UpdateThongTin(
    IN ID INT,
    IN address NVARCHAR(255),
    IN name NVARCHAR(255),
    IN email NVARCHAR(255),
    IN phone NVARCHAR(255),
    IN gender NVARCHAR(10),
    IN dob DATE,
    IN updated_at DATETIME
)
BEGIN
    UPDATE `user`
    SET 
        address = address,
        name = name,
        email = email,
        phone = phone,
        gender = gender,
        dob = dob,
        updated_at = updated_at
    WHERE id = ID;
END;

-- Thủ tục UpdateUser
CREATE PROCEDURE UpdateUser(
    IN ID INT,
    IN address NVARCHAR(255),
    IN name NVARCHAR(255),
    IN password NVARCHAR(128),
    IN email NVARCHAR(255),
    IN phone NVARCHAR(255),
    IN gender NVARCHAR(10),
    IN dob DATE,
    IN updated_at DATETIME,
    IN is_admin TINYINT,
    IN status BIT
)
BEGIN
    UPDATE `user`
    SET 
        address = address,
        name = name,
        password = password,
        email = email,
        phone = phone,
        gender = gender,
        dob = dob,
        updated_at = updated_at,
        is_admin = is_admin,
        status = status
    WHERE id = ID;
END;
