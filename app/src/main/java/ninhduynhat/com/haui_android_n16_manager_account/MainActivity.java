package ninhduynhat.com.haui_android_n16_manager_account;

import static ninhduynhat.com.haui_android_n16_manager_account.Login_Account.TEN_TT_DANG_NHAP;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation meowBottomNavigation;
    SharedPreferences sharedPreferences;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switchvantay;
    TextView thongbao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findId();
        buttonMeo();

        sharedPreferences = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE);
        boolean isTurnOnFingerPrint=sharedPreferences.getBoolean("isTurnOnFingerPrint",false);
        boolean check_device= sharedPreferences.getBoolean("Check_Device_onFinger",false);
        if(check_device){
            switchvantay.setVisibility(View.VISIBLE);
            switchvantay.setChecked(isTurnOnFingerPrint);
        }else {
            thongbao.setText("Thiết bị không hỗ trợ đăng nhập bằng vân tay");
        }





    }
    private void findId(){

        meowBottomNavigation = findViewById(R.id.meoButtonNavigation);
        switchvantay=findViewById(R.id.switchvantay);
        thongbao=findViewById(R.id.thongbao);
    }
    private void buttonMeo(){
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.navbar_ic_home));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.icon_pertion));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.icon_setting));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.icon_password));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.icon_username));


        meowBottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                Intent intent = null;
                int id= model.getId();
                if(id==1){
                    intent = new Intent(MainActivity.this, Home.class);
                } else if (id==2) {
                    intent = new Intent(MainActivity.this, Statistical.class);
                } else if (id==3) {
                    intent = new Intent(MainActivity.this, Plan.class);
                }else if (id==4) {
                    intent = new Intent(MainActivity.this, CourseRegistration.class);
                }else if (id==5) {
                    intent = new Intent(MainActivity.this, Debt.class);
                }
                if(intent != null){
                    startActivity(intent);
                }
                return null;
            }
        });

        meowBottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                Intent intent = null;
                int id= model.getId();
                if(id==1){
                    intent = new Intent(MainActivity.this, Home.class);
                } else if (id==2) {
                    intent = new Intent(MainActivity.this, Statistical.class);
                } else if (id==3) {
                    intent = new Intent(MainActivity.this, Plan.class);
                }else if (id==4) {
                    intent = new Intent(MainActivity.this, CourseRegistration.class);
                }else if (id==5) {
                    intent = new Intent(MainActivity.this, Debt.class);
                }
                if(intent != null){
                    startActivity(intent);
                }
                return null;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sharedPreferences = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE);
        boolean checked= sharedPreferences.getBoolean("isTurnOnFingerPrint",false);
        boolean check_device= sharedPreferences.getBoolean("Check_Device_onFinger",false);
        if(check_device){
            switchvantay.setVisibility(View.VISIBLE);
            switchvantay.setChecked(checked);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        boolean ischeckedSwitch=switchvantay.isChecked();
        SharedPreferences.Editor editor = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE).edit();
        editor.putBoolean("isTurnOnFingerPrint",ischeckedSwitch);
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        boolean ischeckedSwitch=switchvantay.isChecked();
        SharedPreferences.Editor editor = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE).edit();
        editor.putBoolean("isTurnOnFingerPrint",ischeckedSwitch);
        editor.commit();
    }
}