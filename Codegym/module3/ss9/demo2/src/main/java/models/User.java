package models;

public class User {
    private int maNguoiDung;
    private String ten;
    private String email;
    private String matKhau;
    private String soDienThoai;
    private String diaChi;
    private int maVaiTro;

    public User() {}

    // Constructor đầy đủ có cả mã người dùng
    public User(int maNguoiDung, String ten, String email, String matKhau, String soDienThoai, String diaChi, int maVaiTro) {
        this.maNguoiDung = maNguoiDung;
        this.ten = ten;
        this.email = email;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.maVaiTro = maVaiTro;
    }

    // Constructor KHÔNG có mã người dùng (phục vụ cho đăng ký)
    public User(String ten, String email, String matKhau, String soDienThoai, String diaChi, int maVaiTro) {
        this.ten = ten;
        this.email = email;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.maVaiTro = maVaiTro;
    }

    public int getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(int maVaiTro) {
        this.maVaiTro = maVaiTro;
    }
}
