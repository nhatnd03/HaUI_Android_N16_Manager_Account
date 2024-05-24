package ninhduynhat.com.haui_android_n16_manager_account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login_Account extends AppCompatActivity {
    TextView txtchuyendangky;
    EditText edt_TenDangNhap,edt_MatKhau;
    Button btn_DangNhapManHinh;
    CheckBox ckbLuuTTDangNhap;
    public static final String TEN_TT_DANG_NHAP="TEN_TT_DANG_NHAP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findId();
        txtchuyendangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Account.this,Sign_Account.class);
                startActivity(intent);
            }
        });
        btn_DangNhapManHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Login_Account.this,MainActivity.class);
//                intent.putExtra("user",edt_TenDangNhap.getText().toString());
//                intent.putExtra("pass",edt_MatKhau.getText().toString());
//                startActivity(intent);
                saveLoginState();
            }
        });
    }
    private void   findId(){

        txtchuyendangky = findViewById(R.id.chuyendangky);
        edt_TenDangNhap= findViewById(R.id.edt_TenDangNhap);
        edt_MatKhau= findViewById(R.id.edt_MatKhau);
        btn_DangNhapManHinh=findViewById(R.id.btnDangNhapManHinh);
        ckbLuuTTDangNhap=findViewById(R.id.ckbLuuTTDangNhap);
    }


    public void saveLoginState(){
        if(edt_TenDangNhap.getText().toString().isEmpty()||edt_MatKhau.getText().toString().isEmpty()){
            Toast.makeText(this, "Chưa điền tên đăng nhập hoặc mật kẩu", Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences preferences=getSharedPreferences(
                TEN_TT_DANG_NHAP,MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("UserName",
                edt_TenDangNhap.getText().toString());
        editor.putString("PassWord",
                edt_MatKhau.getText().toString());
        editor.putBoolean("Save",
                ckbLuuTTDangNhap.isChecked());

        editor.commit();
        Intent intent = new Intent(Login_Account.this,MainActivity.class);
        startActivity(intent);
    }

        @Override
    protected void onPause() {
        super.onPause();

        saveLoginState();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences=getSharedPreferences(
                TEN_TT_DANG_NHAP,MODE_PRIVATE);
        String userName= preferences.getString("UserName","");
        String PassWord= preferences.getString("PassWord","");
        boolean save=preferences.getBoolean("Save",false);

        if(save){
            edt_TenDangNhap.setText(userName);
            edt_MatKhau.setText(PassWord);
            ckbLuuTTDangNhap.setChecked(save);
        }

    }
}