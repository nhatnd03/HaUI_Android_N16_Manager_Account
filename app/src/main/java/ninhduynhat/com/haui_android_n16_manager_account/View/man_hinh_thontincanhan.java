package ninhduynhat.com.haui_android_n16_manager_account.View;

import static ninhduynhat.com.haui_android_n16_manager_account.Login_Account.LUU_TRANG_THAI_NGUOI_DUNG;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import gun0912.tedbottompicker.TedBottomPicker;
import ninhduynhat.com.haui_android_n16_manager_account.Login_Account;
import ninhduynhat.com.haui_android_n16_manager_account.MainActivity;
import ninhduynhat.com.haui_android_n16_manager_account.R;

public class man_hinh_thontincanhan extends AppCompatActivity {
    CircleImageView home_imgAvartar;
    ImageView doimatkhau,thoattaikhoan,thoatmanhinhchinh;
    SwitchCompat switchvantay,switchluumatkhau;
    SharedPreferences sharedPreferences;
    TableRow hienthivantay;
    TextView txtNchangeImage;

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

        sharedPreferences =getSharedPreferences(LUU_TRANG_THAI_NGUOI_DUNG,MODE_PRIVATE);
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
                openFeedbackDialog_DangXuat(Gravity.CENTER);
            }
        });
        thoatmanhinhchinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(man_hinh_thontincanhan.this, MainActivity.class);
                startActivity(intent);
            }
        });

        txtNchangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requesPermission();
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
        txtNchangeImage=findViewById(R.id.txtNchangeImage);
        home_imgAvartar=findViewById(R.id.home_imgAvartar);
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
    private void openFeedbackDialog_DangXuat(int gravity){
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
        sharedPreferences = getSharedPreferences(LUU_TRANG_THAI_NGUOI_DUNG,MODE_PRIVATE);
        boolean checked= sharedPreferences.getBoolean("isTurnOnFingerPrint",false);
        boolean check_device= sharedPreferences.getBoolean("Check_Device_onFinger",false);
        String LUU_DU_LIEU_ANH=sharedPreferences.getString("LUU_DU_LIEU_ANH","");
        Bitmap bitmap=StringToBitMap(LUU_DU_LIEU_ANH);
        if(!LUU_DU_LIEU_ANH.equals("")){
            home_imgAvartar.setImageBitmap(bitmap);
        }

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
        SharedPreferences.Editor editor = getSharedPreferences(LUU_TRANG_THAI_NGUOI_DUNG,MODE_PRIVATE).edit();
        editor.putBoolean("isTurnOnFingerPrint",ischeckedSwitch);
        editor.putBoolean("isLogin",luumatkhau);
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        boolean ischeckedSwitch=switchvantay.isChecked();
        boolean luumatkhau=switchluumatkhau.isChecked();
        SharedPreferences.Editor editor = getSharedPreferences(LUU_TRANG_THAI_NGUOI_DUNG,MODE_PRIVATE).edit();
        editor.putBoolean("isTurnOnFingerPrint",ischeckedSwitch);
        editor.putBoolean("isLogin",luumatkhau);
        editor.commit();
    }

    private void requesPermission(){

        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                openImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(man_hinh_thontincanhan.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                Log.e("check quyền truy cập ảnh",""+deniedPermissions.toString());
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_MEDIA_IMAGES)
                .check();
    }

    private void openImagePicker(){
        TedBottomPicker.OnImageSelectedListener listener= new TedBottomPicker.OnImageSelectedListener() {
            @Override
            public void onImageSelected(Uri uri) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    home_imgAvartar.setImageBitmap(bitmap);
                    SharedPreferences.Editor editor =getSharedPreferences(LUU_TRANG_THAI_NGUOI_DUNG,MODE_PRIVATE).edit();
                    editor.putString("LUU_DU_LIEU_ANH",BitMapToString(bitmap));
                    editor.commit();

                } catch (IOException e) {
                    Log.e("lỗi chọn ảnh", e+"");
                }
            }
        };
        TedBottomPicker tedBottomPicker = new TedBottomPicker.Builder(man_hinh_thontincanhan.this)
                .setOnImageSelectedListener(listener)
                .setCompleteButtonText("Xong")
                .create();
        tedBottomPicker.show(getSupportFragmentManager());
    }



    private String BitMapToString(@NonNull Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    @Nullable
    private Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }



}