package ninhduynhat.com.haui_android_n16_manager_account.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ninhduynhat.com.haui_android_n16_manager_account.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThanhToan_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThanhToan_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThanhToan_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThanhToan_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThanhToan_Fragment newInstance(String param1, String param2) {
        ThanhToan_Fragment fragment = new ThanhToan_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_thanh_toan, container, false);

        View view = inflater.inflate(R.layout.fragment_thanh_toan, container, false);
//        recyclerView = view.findViewById(R.id.chi_tiet_recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        // Replace with your data or logic to populate chi tiet list
//        chiTietList = new ArrayList<>();
//        // ... (Add data to chiTietList)
//        chiTietAdapter = new ChiTietAdapter(chiTietList);
//        recyclerView.setAdapter(chiTietAdapter);

        Button btnQuayLai = view.findViewById(R.id.btnQuayLai);
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }
}