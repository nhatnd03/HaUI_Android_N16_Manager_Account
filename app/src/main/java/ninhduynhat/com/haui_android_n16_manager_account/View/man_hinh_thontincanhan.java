package ninhduynhat.com.haui_android_n16_manager_account.View;

import static ninhduynhat.com.haui_android_n16_manager_account.Login_Account.TEN_TT_DANG_NHAP;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ninhduynhat.com.haui_android_n16_manager_account.Login_Account;
import ninhduynhat.com.haui_android_n16_manager_account.MainActivity;
import ninhduynhat.com.haui_android_n16_manager_account.R;

public class man_hinh_thontincanhan extends AppCompatActivity {
    ImageView doimatkhau,thoattaikhoan,thoatmanhinhchinh;
    SwitchCompat switchvantay,switchluumatkhau;
    SharedPreferences sharedPreferences;
    TableRow hienthivantay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_man_hinh_thontincanhan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findId();

        sharedPreferences =getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE);
        boolean isTurnOnFingerPrint=sharedPreferences.getBoolean("isTurnOnFingerPrint",false);
        boolean check_device= sharedPreferences.getBoolean("Check_Device_onFinger",false);
        boolean isLogin =sharedPreferences.getBoolean("isLogin",false);
        switchluumatkhau.setChecked(isLogin);
        if(check_device){
            hienthivantay.setVisibility(View.VISIBLE);
            switchvantay.setChecked(isTurnOnFingerPrint);
        }

        doimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog_Matkhau(Gravity.CENTER);
            }
        });
        thoattaikhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog_Thoat(Gravity.CENTER);
            }
        });
        thoatmanhinhchinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(man_hinh_thontincanhan.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void findId(){
        doimatkhau=findViewById(R.id.doimatkhau);
        thoattaikhoan=findViewById(R.id.thoattaikhoan);
        switchvantay=findViewById(R.id.switchvantay);
        switchluumatkhau=findViewById(R.id.switchluumatkhau);
        hienthivantay=findViewById(R.id.hienthivantay);
        thoatmanhinhchinh=findViewById(R.id.thoatmanhinhchinh);
    }


    private void openFeedbackDialog_Matkhau(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_doimatkhau);
        Window window = dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAtrubus= window.getAttributes();
        windowAtrubus.gravity=gravity;
        window.setAttributes(windowAtrubus);

        EditText matkhauhientai,matkhaumoi,nhaclaimatkhaumoi;
        matkhauhientai=dialog.findViewById(R.id.matkhauhientai);
        matkhaumoi=dialog.findViewById(R.id.matkhaumoi);
        nhaclaimatkhaumoi=dialog.findViewById(R.id.nhaclaimatkhaumoi);
        Button thoatDialog,nhanDoimatkhau;
        thoatDialog=dialog.findViewById(R.id.thoatDialog);
        nhanDoimatkhau=dialog.findViewById(R.id.nhanDoimatkhau);
        thoatDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        nhanDoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(man_hinh_thontincanhan.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
    private void openFeedbackDialog_Thoat(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_thoattaikhoan);
        Window window = dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAtrubus= window.getAttributes();
        windowAtrubus.gravity=gravity;
        window.setAttributes(windowAtrubus);

        Button thoatDialog2,nhanThoat;
        thoatDialog2=dialog.findViewById(R.id.thoatDialog2);
        nhanThoat=dialog.findViewById(R.id.nhanThoat);
        thoatDialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        nhanThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(man_hinh_thontincanhan.this, Login_Account.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE);
        boolean checked= sharedPreferences.getBoolean("isTurnOnFingerPrint",false);
        boolean check_device= sharedPreferences.getBoolean("Check_Device_onFinger",false);
        if(check_device){
            hienthivantay.setVisibility(View.VISIBLE);
            switchvantay.setChecked(checked);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        boolean ischeckedSwitch=switchvantay.isChecked();
        boolean luumatkhau=switchluumatkhau.isChecked();
        SharedPreferences.Editor editor = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE).edit();
        editor.putBoolean("isTurnOnFingerPrint",ischeckedSwitch);
        editor.putBoolean("isLogin",luumatkhau);
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        boolean ischeckedSwitch=switchvantay.isChecked();
        boolean luumatkhau=switchluumatkhau.isChecked();
        SharedPreferences.Editor editor = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE).edit();
        editor.putBoolean("isTurnOnFingerPrint",ischeckedSwitch);
        editor.putBoolean("isLogin",luumatkhau);
        editor.commit();
    }
}