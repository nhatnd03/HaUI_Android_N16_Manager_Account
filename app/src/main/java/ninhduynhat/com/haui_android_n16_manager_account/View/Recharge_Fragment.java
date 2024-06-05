package ninhduynhat.com.haui_android_n16_manager_account.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ninhduynhat.com.haui_android_n16_manager_account.Database.DatabaseHelper;
import ninhduynhat.com.haui_android_n16_manager_account.Model.UserObject;
import ninhduynhat.com.haui_android_n16_manager_account.R;


public class Recharge_Fragment extends Fragment {

    EditText editText;
    Button buttonNap, btnQuayLai;
    TextView txtCongno;

    private void getWidget(View view){
        editText = view.findViewById(R.id.editNapTien);
        buttonNap = view.findViewById(R.id.btnNapTien);
        btnQuayLai = view.findViewById(R.id.btnQuayLaiMHNap);
        txtCongno = view.findViewById(R.id.txtGoiYTien);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recharge, container, false);

        getWidget(view);
        editText.requestFocus();

        DatabaseHelper databaseHelper = new DatabaseHelper(getContext());

        UserObject userObject = databaseHelper.getUserById(1);

        txtCongno.setText(userObject.getDebtMoney() + "");

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        buttonNap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double tienNap = Double.parseDouble(editText.getText().toString());
                userObject.setMoneyForStudying(tienNap + userObject.getMoneyForStudying());
                databaseHelper.updateUser(userObject);
                editText.setText("");
            }
        });

        return view;
    }
}