package ninhduynhat.com.haui_android_n16_manager_account.Model;

public class KhoanChi {
    private String khoanchi;
    private String UserId;
    private String Kieu_CHi;
    private int so_Luong;
    private String ngay_thang;
    private String mo_Ta;

    public KhoanChi(String khoanchi, String userId, String kieu_CHi, int so_Luong, String ngay_thang, String mo_Ta) {
        this.khoanchi = khoanchi;
        UserId = userId;
        Kieu_CHi = kieu_CHi;
        this.so_Luong = so_Luong;
        this.ngay_thang = ngay_thang;
        this.mo_Ta = mo_Ta;
    }

    public String getKhoanchi() {
        return khoanchi;
    }

    public void setKhoanchi(String khoanchi) {
        this.khoanchi = khoanchi;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getKieu_CHi() {
        return Kieu_CHi;
    }

    public void setKieu_CHi(String kieu_CHi) {
        Kieu_CHi = kieu_CHi;
    }

    public int getSo_Luong() {
        return so_Luong;
    }

    public void setSo_Luong(int so_Luong) {
        this.so_Luong = so_Luong;
    }

    public String getNgay_thang() {
        return ngay_thang;
    }

    public void setNgay_thang(String ngay_thang) {
        this.ngay_thang = ngay_thang;
    }

    public String getMo_Ta() {
        return mo_Ta;
    }

    public void setMo_Ta(String mo_Ta) {
        this.mo_Ta = mo_Ta;
    }
}
