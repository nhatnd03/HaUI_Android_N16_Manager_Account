package ninhduynhat.com.haui_android_n16_manager_account.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ninhduynhat.com.haui_android_n16_manager_account.Adapters.TransactionHistoryAdapter;
import ninhduynhat.com.haui_android_n16_manager_account.Database.DatabaseHelper;
import ninhduynhat.com.haui_android_n16_manager_account.Model.PayingTuitionObject;
import ninhduynhat.com.haui_android_n16_manager_account.Model.UserObject;
import ninhduynhat.com.haui_android_n16_manager_account.R;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link Cong_No_Fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class Cong_No_Fragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public Cong_No_Fragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment Cong_No_Fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static Cong_No_Fragment newInstance(String param1, String param2) {
//        Cong_No_Fragment fragment = new Cong_No_Fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_cong__no_, container, false);
//    }
    private RecyclerView recyclerView;
    private TransactionHistoryAdapter transactionAdapter;
    private List<PayingTuitionObject> transactionList;
    private DatabaseHelper databaseHelper;
    TextView soDuTextView;
    TextView soTienConNoTextView;
    Button btnThanhToan;
    Button btnNaptien;

    private void getWidget(View view){
        btnThanhToan = view.findViewById(R.id.btnThanhToan);
        btnNaptien = view.findViewById(R.id.btnNap);
        soDuTextView = view.findViewById(R.id.txtSoDuHocTap);
        soTienConNoTextView = view.findViewById(R.id.txtCongNo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cong__no_, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        transactionList = new ArrayList<>();
        // Thêm dữ liệu mẫu
        transactionList.add(new PayingTuitionObject(1, 1, "Sit excepturi recusandae quibusdam!", 1000000, true));
        transactionList.add(new PayingTuitionObject(2,2, "Sit excepturi recusandae quibusdam!", 100000, false));
        transactionAdapter = new TransactionHistoryAdapter(transactionList);
        recyclerView.setAdapter(transactionAdapter);

        // Handle button click to switch to Chi_Tiet_Fragment
        getWidget(view); // Assuming the button ID is btnThanhToan

        databaseHelper = new DatabaseHelper(getContext());

        loadUserData(1);

        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThanhToan_Fragment chiTietFragment = new ThanhToan_Fragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, chiTietFragment); // Assuming your fragment container has this ID
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btnNaptien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recharge_Fragment rechargeFragment = new Recharge_Fragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, rechargeFragment); // Assuming your fragment container has this ID
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }

    private void loadUserData(int userId) {
        UserObject user = databaseHelper.getUser(userId);
        if (user != null) {
            soDuTextView.setText(String.valueOf(user.getMoneyForStudying()));
            soTienConNoTextView.setText(String.valueOf(user.getDebtMoney()));
        }
    }
}