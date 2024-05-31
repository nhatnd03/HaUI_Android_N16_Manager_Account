package ninhduynhat.com.haui_android_n16_manager_account;


 






import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import ninhduynhat.com.haui_android_n16_manager_account.View.Add_Chi_Phi_Fragment;
import ninhduynhat.com.haui_android_n16_manager_account.View.Cong_No_Fragment;
import ninhduynhat.com.haui_android_n16_manager_account.View.HomeFragment;
import ninhduynhat.com.haui_android_n16_manager_account.View.Ke_Hoach_Fragment;
import ninhduynhat.com.haui_android_n16_manager_account.View.Thong_Ke_Fragment;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation meowBottomNavigation;
    SharedPreferences sharedPreferences;

//    Switch switchvantay;


    TextView thongbao;
    protected long thoatungdung;
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







//        sharedPreferences = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE);
//        boolean isTurnOnFingerPrint=sharedPreferences.getBoolean("isTurnOnFingerPrint",false);
//        boolean check_device= sharedPreferences.getBoolean("Check_Device_onFinger",false);
//        if(check_device){
//            switchvantay.setVisibility(View.VISIBLE);
//            switchvantay.setChecked(isTurnOnFingerPrint);
//        }else {
//            thongbao.setText("Thiết bị không hỗ trợ đăng nhập bằng vân tay");
//        }





    }
    private void findId(){

        meowBottomNavigation = findViewById(R.id.meoButtonNavigation);
//        switchvantay=findViewById(R.id.switchvantay);
//        thongbao=findViewById(R.id.thongbao);
    }
    private void buttonMeo(){


        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.navbar_ic_home));

        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.icon_thongke));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.icon_add));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.icon_kehoach));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.icon_cong_no));

        // Set default selection
        meowBottomNavigation.show(1, true);




        meowBottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                int id= model.getId();
                int enterAnimation = R.anim.enter_from_right;
                int exitAnimation = R.anim.exit_to_left;
                if(id==1){

                    enterAnimation = R.anim.enter_from_right;
                    exitAnimation = R.anim.exit_to_left;
                    openFragment(new HomeFragment(), enterAnimation, exitAnimation);

                } else if (id==2) {
                    enterAnimation = R.anim.enter_from_right;
                    exitAnimation = R.anim.exit_to_left;
                    openFragment(new Thong_Ke_Fragment(), enterAnimation, exitAnimation);

                } else if (id==3) {
                    new Add_Chi_Phi_Fragment();
                }else if (id==4) {
                    enterAnimation = R.anim.enter_from_left;
                    exitAnimation = R.anim.exit_to_right;
                    openFragment(new Ke_Hoach_Fragment(), enterAnimation, exitAnimation);

                }else if (id==5) {
                    openFragment(new Cong_No_Fragment(), enterAnimation, exitAnimation);

                }
                return null;
            }
        });

        meowBottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                int id= model.getId();
                int enterAnimation = R.anim.enter_from_right;
                int exitAnimation = R.anim.exit_to_left;
                if(id==1){
                    enterAnimation = R.anim.enter_from_right;
                    exitAnimation = R.anim.exit_to_left;
                    openFragment(new HomeFragment(), enterAnimation, exitAnimation);
                } else if (id==2) {
                    enterAnimation = R.anim.enter_from_right;
                    exitAnimation = R.anim.exit_to_left;
                    openFragment(new Thong_Ke_Fragment(), enterAnimation, exitAnimation);
                } else if (id==3) {
                    new Add_Chi_Phi_Fragment();
                }else if (id==4) {
                    enterAnimation = R.anim.enter_from_left;
                    exitAnimation = R.anim.exit_to_right;
                    openFragment(new Ke_Hoach_Fragment(), enterAnimation, exitAnimation);
                }else if (id==5) {
                    openFragment(new Cong_No_Fragment(), enterAnimation, exitAnimation);

                }
                return null;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        sharedPreferences = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE);
//        boolean checked= sharedPreferences.getBoolean("isTurnOnFingerPrint",false);
//        boolean check_device= sharedPreferences.getBoolean("Check_Device_onFinger",false);
//        if(check_device){
//            switchvantay.setVisibility(View.VISIBLE);
//            switchvantay.setChecked(checked);
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        boolean ischeckedSwitch=switchvantay.isChecked();
//        SharedPreferences.Editor editor = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE).edit();
//        editor.putBoolean("isTurnOnFingerPrint",ischeckedSwitch);
//        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        boolean ischeckedSwitch=switchvantay.isChecked();
//        SharedPreferences.Editor editor = getSharedPreferences(TEN_TT_DANG_NHAP,MODE_PRIVATE).edit();
//        editor.putBoolean("isTurnOnFingerPrint",ischeckedSwitch);
//        editor.commit();
    }

    @Override
    public void onBackPressed() {

        if(thoatungdung+2000>System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Toast.makeText(this, "Nhấn lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT).show();
        }
        thoatungdung=System.currentTimeMillis();
    }

    private void openFragment(Fragment fragment, int enterAnimation, int exitAnimation) {
        if (enterAnimation == -1 || exitAnimation == -1) {
            enterAnimation = R.anim.exit_to_left;
            exitAnimation = R.anim.enter_from_right;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}