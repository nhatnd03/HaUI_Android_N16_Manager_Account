package ninhduynhat.com.haui_android_n16_manager_account;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Home extends AppCompatActivity {


    MeowBottomNavigation meowBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findId();
        buttonMeo();

    }
    private void findId(){

        meowBottomNavigation = findViewById(R.id.meoButtonNavigation);
//        switchvantay=findViewById(R.id.switchvantay);
//        thongbao=findViewById(R.id.thongbao);
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
                    intent = new Intent(Home.this, Home.class);
                } else if (id==2) {
                    intent = new Intent(Home.this, Statistical.class);
                } else if (id==3) {
                    intent = new Intent(Home.this, Plan.class);
                }else if (id==4) {
                    intent = new Intent(Home.this, CourseRegistration.class);
                }else if (id==5) {
                    intent = new Intent(Home.this, Debt.class);
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
                    intent = new Intent(Home.this, Home.class);
                } else if (id==2) {
                    intent = new Intent(Home.this, Statistical.class);
                } else if (id==3) {
                    intent = new Intent(Home.this, Plan.class);
                }else if (id==4) {
                    intent = new Intent(Home.this, CourseRegistration.class);
                }else if (id==5) {
                    intent = new Intent(Home.this, Debt.class);
                }
                if(intent != null){
                    startActivity(intent);
                }
                return null;
            }
        });
    }
}