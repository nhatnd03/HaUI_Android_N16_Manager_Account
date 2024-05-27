package ninhduynhat.com.haui_android_n16_manager_account;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.Executor;

public class Login_Account extends AppCompatActivity {

    //vân tay
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;
    ImageView image_finger_login;



    private static final int REQUEST_CODE = 11111;
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

        //login to fingerPrint
        SharedPreferences sharedPreferences = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE);
        boolean islogin=sharedPreferences.getBoolean("isLogin",false);
        boolean isTurnOnFingerPrint=sharedPreferences.getBoolean("isTurnOnFingerPrint",false);


        if(islogin){
            String user= sharedPreferences.getString("Username","");
            edt_TenDangNhap.setText(user);
        }
        if(islogin && isTurnOnFingerPrint&&Check_Device_Biometric()){
            login_by_finger();
            image_finger_login.setVisibility(View.VISIBLE);
        }

        //chuyển ma hình đăng ký
        txtchuyendangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_Account.this,Sign_Account.class);
                startActivity(intent);
            }
        });


        //nút đăng nhập
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
//        ckbLuuTTDangNhap=findViewById(R.id.ckbLuuTTDangNhap);
        image_finger_login= findViewById(R.id.image_finger_login);
    }


    public void saveLoginState(){
        if(edt_TenDangNhap.getText().toString().isEmpty()||edt_MatKhau.getText().toString().isEmpty()){
            Toast.makeText(this, "Chưa điền tên đăng nhập hoặc mật kẩu", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Login_Account.this,MainActivity.class);
        startActivity(intent);
    }


    private boolean Check_Device_Biometric(){
        boolean check_device=false;

        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                //Ứng dụng có chức năng sinh trắc học
                check_device=true;
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                //Ứng dung không có chức năng sinh trắc học
                check_device=false;
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Toast.makeText(this, "Cảm biến không hoạt động hoặc bận", Toast.LENGTH_SHORT).show();
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                // Prompts the user to create credentials that your app accepts.
                final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                startActivityForResult(enrollIntent, REQUEST_CODE);
                break;
        }
        return check_device;
    }

    private void login_by_finger(){


        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(Login_Account.this,
                executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode,
                                              @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);

//                Toast.makeText(getApplicationContext(),
//                                "Đăng nhập lỗi " + errString, Toast.LENGTH_SHORT)
//                        .show();
            }

            @Override
            public void onAuthenticationSucceeded(
                    @NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                startActivity(new Intent(Login_Account.this,MainActivity.class));
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Vân tay không tồn tại",
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Cảm biến sinh trắc học")
                .setSubtitle("Đăng nhập bằng vân tay")
                .setNegativeButtonText("Thoát")
                .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.

        image_finger_login.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor=getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE).edit();
        editor.putString("UserName",
                edt_TenDangNhap.getText().toString());
        editor.putString("PassWord",
                edt_MatKhau.getText().toString());
//            editor.putBoolean("Save",
//                    ckbLuuTTDangNhap.isChecked());
        editor.putBoolean("isLogin",true);
        editor.putBoolean("Check_Device_onFinger",Check_Device_Biometric());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE);
        boolean isTurnOnFingerPrint=sharedPreferences.getBoolean("isTurnOnFingerPrint",false);
        if(isTurnOnFingerPrint&&Check_Device_Biometric()){
            login_by_finger();
            image_finger_login.setVisibility(View.VISIBLE);
        }
        else {
            image_finger_login.setVisibility(View.GONE);
        }
    }


}