package ninhduynhat.com.haui_android_n16_manager_account.View;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ninhduynhat.com.haui_android_n16_manager_account.Adapters.SubjectAdapter;
import ninhduynhat.com.haui_android_n16_manager_account.Database.DatabaseHelper;
import ninhduynhat.com.haui_android_n16_manager_account.Model.SubjectObject;
import ninhduynhat.com.haui_android_n16_manager_account.R;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link RegisterFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class RegisterFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public RegisterFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment RegisterFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static RegisterFragment newInstance(String param1, String param2) {
//        RegisterFragment fragment = new RegisterFragment();
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

    private RecyclerView recyclerView;
    private SubjectAdapter subjectAdapter;
    private List<SubjectObject> subjects;
    private Spinner spinnerSemester;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;

    Button btnRegister;

    private void getWidget(View view){
        btnRegister = view.findViewById(R.id.buttonRegister);
        spinnerSemester = view.findViewById(R.id.spinnerSemester);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewSubjects);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getWidget(view);

        databaseHelper = new DatabaseHelper(getContext());
        db = databaseHelper.getReadableDatabase();

        // Define semesters array in Java code
        String[] semestersArray = {"Kỳ 1", "Kỳ 2", "Kỳ 3", "Kỳ 4", "Kỳ 5", "Kỳ 6", "Kỳ 7", "Kỳ 8"};

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item , semestersArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(adapter);

        // Fetch subjects for the selected semester
        subjects = fetchSubjectsForSemester(1); // Default to semester 1
        subjectAdapter = new SubjectAdapter(subjects);
        recyclerView.setAdapter(subjectAdapter);

        // Handle spinner selection change
        spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedSemester = position + 1; // Assuming semesters start from 1
                subjects.clear();
                subjects.addAll(fetchSubjectsForSemester(selectedSemester));
                subjectAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Handle button click to register selected subjects
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerSelectedSubjects();
            }
        });

        return view;
    }

    private List<SubjectObject> fetchSubjectsForSemester(int semester) {
        List<SubjectObject> subjects = new ArrayList<>();
        Cursor cursor = db.query("SUBJECT", null, "Semester = ?", new String[]{String.valueOf(semester)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("SubjectId"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("SubjectName"));
                byte credits = (byte) cursor.getInt(cursor.getColumnIndexOrThrow("StudyCredits"));
                subjects.add(new SubjectObject(id, name, credits, semester));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return subjects;
    }

    private void registerSelectedSubjects() {
        List<SubjectObject> selectedSubjects = subjectAdapter.getSelectedSubjects();
        for (SubjectObject subject : selectedSubjects) {
            // Insert into PayingTuition table
            ContentValues values = new ContentValues();
            values.put("SubjectID", subject.getSubjectId());
            values.put("SubjectName", subject.getSubjectName());
            values.put("TheAmount", subject.getAmount());
            values.put("IsPaided", 0);
            db.insert("PayingTuition", null, values);

            // Update DebtMoney for the user (Assuming userId is 1 for example)
            db.execSQL("UPDATE USER SET DebtMoney = DebtMoney + ? WHERE UserID = ?", new Object[]{subject.getAmount(), 1});
        }
    }


}