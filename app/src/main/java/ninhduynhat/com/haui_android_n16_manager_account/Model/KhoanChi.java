package ninhduynhat.com.haui_android_n16_manager_account.Model;

public class KhoanChi {
    private String khoanchi;
    private int so_Luong;
    private String mo_Ta;


    public KhoanChi(String khoanchi, int so_Luong, String mo_Ta) {
        this.khoanchi = khoanchi;
        this.so_Luong = so_Luong;
        this.mo_Ta = mo_Ta;
    }

    public String getKhoanchi() {
        return khoanchi;
    }

    public void setKhoanchi(String khoanchi) {
        this.khoanchi = khoanchi;
    }

    public int getSo_Luong() {
        return so_Luong;
    }

    public void setSo_Luong(int so_Luong) {
        this.so_Luong = so_Luong;
    }

    public String getMo_Ta() {
        return mo_Ta;
    }

    public void setMo_Ta(String mo_Ta) {
        this.mo_Ta = mo_Ta;
    }
}
