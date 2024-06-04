package ninhduynhat.com.haui_android_n16_manager_account.View;

import static android.content.Context.MODE_PRIVATE;
import static ninhduynhat.com.haui_android_n16_manager_account.Login_Account.LUU_TRANG_THAI_NGUOI_DUNG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import ninhduynhat.com.haui_android_n16_manager_account.Adapters.Chi_Phi_Adapter;
import ninhduynhat.com.haui_android_n16_manager_account.Model.KhoanChi;
import ninhduynhat.com.haui_android_n16_manager_account.R;


public class HomeFragment extends Fragment {

    
    private RecyclerView rcl_Chi_Phi;
    private Chi_Phi_Adapter chiPhiAdapter;
    CircleImageView home_imgAvartar;
    FloatingActionButton floating_add;
    SharedPreferences sharedPreferences;
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
        chiPhiAdapter.setData(dataInitalize());
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
}