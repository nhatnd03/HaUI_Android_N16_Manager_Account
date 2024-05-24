package ninhduynhat.com.haui_android_n16_manager_account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sign_Account extends AppCompatActivity {
    TextView chuyenmanhinhdangnhap;
    EditText edt_UserName_Sign,edt_Password_Sign,edt_Password_Sign_Confirm;
    Button btn_Sign_In;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_account);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findId();
        chuyenmanhinhdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_Account.this,Login_Account.class);
                startActivity(intent);
            }
        });
        btn_Sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInPutSignIn();
            }
        });
    }
    private void findId(){

        chuyenmanhinhdangnhap = findViewById(R.id.chuyendangnhap);
        edt_UserName_Sign = findViewById(R.id.edt_UserName_Sign);
        edt_Password_Sign= findViewById(R.id.edt_Password_Sign);
        edt_Password_Sign_Confirm= findViewById(R.id.edt_Password_Sign_Confirm);
    }
    private void checkInPutSignIn(){
        if(edt_UserName_Sign.getText().toString().isEmpty()||
                edt_Password_Sign.getText().toString().isEmpty()||
                edt_Password_Sign_Confirm.getText().toString().isEmpty()
        ){
            Toast.makeText(this, "Phải điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(edt_Password_Sign.getText().toString().equals(edt_Password_Sign_Confirm.getText().toString()))){
            Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(Sign_Account.this,Login_Account.class);
        startActivity(intent);
    }
}