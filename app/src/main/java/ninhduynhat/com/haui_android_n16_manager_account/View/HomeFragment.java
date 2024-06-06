package ninhduynhat.com.haui_android_n16_manager_account.View;

import static android.content.Context.MODE_PRIVATE;
import static ninhduynhat.com.haui_android_n16_manager_account.Login_Account.LUU_TRANG_THAI_NGUOI_DUNG;


import android.app.DatePickerDialog;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ninhduynhat.com.haui_android_n16_manager_account.Adapters.ChiPhiSpiner_Adapter;
import ninhduynhat.com.haui_android_n16_manager_account.Adapters.Chi_Phi_Adapter;
import ninhduynhat.com.haui_android_n16_manager_account.Model.KhoanChi;
import ninhduynhat.com.haui_android_n16_manager_account.Model.LoaiChiPhi;
import ninhduynhat.com.haui_android_n16_manager_account.R;


public class HomeFragment extends Fragment {

    private RecyclerView rcl_Chi_Phi;
    private Chi_Phi_Adapter chiPhiAdapter;
    private CircleImageView home_imgAvartar;
    private FloatingActionButton floating_add;
    private SharedPreferences sharedPreferences;
    private LoaiChiPhi loaiChiPhi[];

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =inflater.inflate(R.layout.fragment_home, container, false);
        rcl_Chi_Phi=rootView.findViewById(R.id.rcl_Chi_Phi);

        rcl_Chi_Phi.setLayoutManager(new LinearLayoutManager(getActivity()));
        chiPhiAdapter = new Chi_Phi_Adapter();
        chiPhiAdapter.setData(getActivity(),dataInitalize());

        rcl_Chi_Phi.setAdapter(chiPhiAdapter);



        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        home_imgAvartar=view.findViewById(R.id.home_imgAvartar);
        floating_add=view.findViewById(R.id.floating_add);


        home_imgAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.fragment_container,
//                        new Thong_Tin_Ca_Nhan_Fragment()).addToBackStack(null).commit();
                Intent intent = new Intent(getActivity(), man_hinh_thontincanhan.class);
                startActivity(intent);
            }
        });
        floating_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog_themchiphi(Gravity.CENTER);
            }
        });

        rcl_Chi_Phi.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy>0){
                    floating_add.hide();
                }else {
                    floating_add.show();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }


    private List<KhoanChi> dataInitalize() {


        List<KhoanChi> khoanChiArrayLists = new ArrayList<>();
        khoanChiArrayLists.add(new KhoanChi("Gà rán",1000,"Mua buổi chiều"));
        khoanChiArrayLists.add(new KhoanChi("Cơm rang",1000,"Mua buổi chiều"));
        khoanChiArrayLists.add(new KhoanChi("Phở bò",1000,"Mua buổi chiều"));
        khoanChiArrayLists.add(new KhoanChi("Phở xào",1000,"Mua buổi chiều"));
        khoanChiArrayLists.add(new KhoanChi("Phở xào",1000,"Mua buổi chiều"));
        khoanChiArrayLists.add(new KhoanChi("Phở xào",1000,"Mua buổi chiều"));
        khoanChiArrayLists.add(new KhoanChi("Phở xào",1000,"Mua buổi chiều"));
        khoanChiArrayLists.add(new KhoanChi("Phở xào",1000,"Mua buổi chiều"));
        khoanChiArrayLists.add(new KhoanChi("Phở xào",1000,"Mua buổi chiều"));
        khoanChiArrayLists.add(new KhoanChi("Phở xào",1000,"Mua buổi chiều"));
        return khoanChiArrayLists;
    }

    private void xuLySuaXoaChiPhi(){

    }

    @Override
    public void onResume() {
        super.onResume();

        sharedPreferences= getActivity().getSharedPreferences(LUU_TRANG_THAI_NGUOI_DUNG,MODE_PRIVATE);
        String LUU_DU_LIEU_ANH=sharedPreferences.getString("LUU_DU_LIEU_ANH","");
        Bitmap bitmap=StringToBitMap(LUU_DU_LIEU_ANH);
        if(!LUU_DU_LIEU_ANH.equals("")){
            home_imgAvartar.setImageBitmap(bitmap);
        }

    }

    @androidx.annotation.Nullable
    private Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }


    private void openFeedbackDialog_themchiphi(int gravity){
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_themchiphi);
        Window window = dialog.getWindow();
        if(window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAtrubus= window.getAttributes();
        windowAtrubus.gravity=gravity;
        window.setAttributes(windowAtrubus);


        EditText mo_ta_chi_phi;
        TextView date_picker,thoi_gian_mua;
        Spinner loai_chi_phi;
        Button thoatDialogthemchiphi,nhanThemChiPhi;
        date_picker=dialog.findViewById(R.id.date_picker);
        loai_chi_phi= dialog.findViewById(R.id.loai_chi_phi);

        thoi_gian_mua=dialog.findViewById(R.id.thoi_gian_mua);
        mo_ta_chi_phi=dialog.findViewById(R.id.mo_ta_chi_phi);
        thoatDialogthemchiphi=dialog.findViewById(R.id.thoatDialogthemchiphi);
        nhanThemChiPhi=dialog.findViewById(R.id.nhanThemChiPhi);


        loaiChiPhi=LoaiChiPhi.values();
        ChiPhiSpiner_Adapter chiPhiSpinerAdapter=new ChiPhiSpiner_Adapter(getActivity(),loaiChiPhi);
        loai_chi_phi.setAdapter(chiPhiSpinerAdapter);
        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(Calendar.DATE);
                int thang = calendar.get(Calendar.MONTH);
                int nam = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        try {
                            Date date = new Date(year-1900,month,dayOfMonth);
                            DateFormat df = new SimpleDateFormat("dd/MM/yyyy ");
                            String dateString = df.format(date);
                            thoi_gian_mua.setText(dateString);
                        }
                        catch (Exception e){
                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, nam, thang, ngay);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setTitle("Chọn thời gian");
                dialog.show();
            }
        });

        nhanThemChiPhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        thoatDialogthemchiphi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}