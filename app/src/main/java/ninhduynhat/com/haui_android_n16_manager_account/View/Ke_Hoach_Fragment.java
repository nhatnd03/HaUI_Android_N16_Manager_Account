package ninhduynhat.com.haui_android_n16_manager_account.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
<<<<<<< HEAD
import ninhduynhat.com.haui_android_n16_manager_account.Adapters.TypeTargetAdapter;
import ninhduynhat.com.haui_android_n16_manager_account.Adapters.TargetAdapter;
=======

>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ninhduynhat.com.haui_android_n16_manager_account.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Ke_Hoach_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
<<<<<<< HEAD
public class Ke_Hoach_Fragment extends Fragment implements TargetAdapter.OnItemClickListener, TypeTargetAdapter.OnItemClickListener {
=======
public class Ke_Hoach_Fragment extends Fragment {
>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Ke_Hoach_Fragment() {
        // Required empty public constructor
    }

<<<<<<< HEAD
     /**
=======
    /**
>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Ke_Hoach_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Ke_Hoach_Fragment newInstance(String param1, String param2) {
        Ke_Hoach_Fragment fragment = new Ke_Hoach_Fragment();
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
        return inflater.inflate(R.layout.fragment_ke__hoach_, container, false);
    }
}