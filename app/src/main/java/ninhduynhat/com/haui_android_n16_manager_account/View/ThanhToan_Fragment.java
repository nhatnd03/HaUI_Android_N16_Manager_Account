package ninhduynhat.com.haui_android_n16_manager_account.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.List;
import java.util.Set;

import ninhduynhat.com.haui_android_n16_manager_account.Adapters.PayingTuitionAdapter;
import ninhduynhat.com.haui_android_n16_manager_account.Database.DatabaseHelper;
import ninhduynhat.com.haui_android_n16_manager_account.Model.PayingTuitionObject;
import ninhduynhat.com.haui_android_n16_manager_account.Model.UserObject;
import ninhduynhat.com.haui_android_n16_manager_account.R;

public class ThanhToan_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private PayingTuitionAdapter adapter;
    private List<PayingTuitionObject> payingTuitionList;
    private DatabaseHelper databaseHelper;
    private UserObject currentUser;
    private Button btnThanhToanFinal, btnQuayLai, btnChonTatCa;

    RadioButton rbChonTatCa;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_thanh_toan, container, false);

        View view = inflater.inflate(R.layout.fragment_thanh_toan, container, false);

        databaseHelper = new DatabaseHelper(getContext());
        // Assume currentUser is retrieved from SharedPreferences or passed as argument
        currentUser = databaseHelper.getUserByUsername("quangkedo");

        recyclerView = view.findViewById(R.id.chi_tiet_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        payingTuitionList = databaseHelper.getUnpaidTuitionList(currentUser.getUserID());
        adapter = new PayingTuitionAdapter(getContext(), payingTuitionList);
        recyclerView.setAdapter(adapter);

//        btnChonTatCa = view.findViewById(R.id.radioButton);
        rbChonTatCa = view.findViewById(R.id.radioButton);
        rbChonTatCa.setOnClickListener(v -> {
            if (adapter.getSelectedPositions().size() == payingTuitionList.size()) {
                adapter.getSelectedPositions().clear();
                rbChonTatCa.setChecked(false);
            } else {
                for (int i = 0; i < payingTuitionList.size(); i++) {
                    adapter.getSelectedPositions().add(i);
                }
            }
            adapter.notifyDataSetChanged();

        });

        btnThanhToanFinal = view.findViewById(R.id.btnThanhToanFinal);
        btnThanhToanFinal.setOnClickListener(v -> processPayment());


        btnQuayLai = view.findViewById(R.id.btnQuayLai);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }

    private void processPayment() {
        Set<Integer> selectedPositions = adapter.getSelectedPositions();
        double totalAmount = 0;

        for (int position : selectedPositions) {
            totalAmount += payingTuitionList.get(position).getAmount();
        }

        if (currentUser.getMoneyForStudying() >= totalAmount) {
            for (int position : selectedPositions) {
                PayingTuitionObject payingTuition = payingTuitionList.get(position);
                payingTuition.setPaided(true);
                databaseHelper.updatePayingTuition(payingTuition);

                //Cập nhật
                payingTuitionList = databaseHelper.getUnpaidTuitionList(currentUser.getUserID());
                adapter = new PayingTuitionAdapter(getContext(), payingTuitionList);
                recyclerView.setAdapter(adapter);
            }
            currentUser.setMoneyForStudying(currentUser.getMoneyForStudying() - totalAmount);
            currentUser.setDebtMoney(currentUser.getDebtMoney() - totalAmount);
            databaseHelper.updateUser(currentUser);

            Toast.makeText(getContext(), "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Không đủ tiền để thanh toán!", Toast.LENGTH_SHORT).show();
        }
    }
}