package ninhduynhat.com.haui_android_n16_manager_account.View;

import static android.content.Context.MODE_PRIVATE;
import static ninhduynhat.com.haui_android_n16_manager_account.Login_Account.LUU_TRANG_THAI_NGUOI_DUNG;

import android.content.SharedPreferences;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ninhduynhat.com.haui_android_n16_manager_account.Adapters.SubjectAdapter;
import ninhduynhat.com.haui_android_n16_manager_account.Database.DatabaseHelper;
import ninhduynhat.com.haui_android_n16_manager_account.Model.PayingTuitionObject;
import ninhduynhat.com.haui_android_n16_manager_account.Model.SubjectObject;
import ninhduynhat.com.haui_android_n16_manager_account.Model.UserObject;
import ninhduynhat.com.haui_android_n16_manager_account.R;

public class RegisterFragment extends Fragment {
    private RecyclerView recyclerView;
    private SubjectAdapter subjectAdapter;
    private List<SubjectObject> subjects;
    private Spinner spinnerSemester;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db;
    private int userId;
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

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LUU_TRANG_THAI_NGUOI_DUNG",MODE_PRIVATE);
        String user= sharedPreferences.getString("UserName","");

        userId = databaseHelper.getUserByUsername(user).getUserID();

        // Define semesters array in Java code
        String[] semestersArray = {"Kỳ 1", "Kỳ 2", "Kỳ 3", "Kỳ 4", "Kỳ 5", "Kỳ 6", "Kỳ 7", "Kỳ 8"};

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item , semestersArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemester.setAdapter(adapter);

        // Fetch subjects for the selected semester
        subjects = fetchSubjectsForSemester(1); // Default to semester 1
        subjectAdapter = new SubjectAdapter(subjects, getContext());
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
                int subjectId = cursor.getInt(cursor.getColumnIndexOrThrow("SubjectId"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("SubjectName"));
                byte credits = (byte) cursor.getInt(cursor.getColumnIndexOrThrow("StudyCredits"));
                subjects.add(new SubjectObject(subjectId,  name, credits, semester));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return subjects;
    }

    private void registerSelectedSubjects() {

        try {
            Set<Integer> selectedPositions = subjectAdapter.getSelectedPositions();
            double totalDebt = 0;

            if (selectedPositions.isEmpty()) {
                // Hiển thị thông báo lỗi bằng cách sử dụng Toast hoặc Dialog
                Toast.makeText(getContext(), "Vui lòng chọn ít nhất một môn học để đăng ký", Toast.LENGTH_SHORT).show();
                return;
            }

            for (int position : selectedPositions) {
                SubjectObject subject = subjects.get(position);
                PayingTuitionObject payingTuition = new PayingTuitionObject(userId, subject.getSubjectId(), subject.getSubjectName(), subject.getStudyCredits() * 415000, false);
                databaseHelper.insertPayingTuition(userId, payingTuition.getSubjectID(), payingTuition.getSubjectName(), payingTuition.getAmount(), payingTuition.isPaided()?1:0);
                totalDebt += subject.getAmount();
            }

            // Update the user's debt in the database
            SharedPreferences sharedPreferences = getContext().getSharedPreferences(LUU_TRANG_THAI_NGUOI_DUNG, MODE_PRIVATE);
//        UserObject user = databaseHelper.getUserByUsername(sharedPreferences.getString("Username",""));
            UserObject user = databaseHelper.getUserById(userId);
            if (user != null) {
                double newDebt = user.getDebtMoney() + totalDebt;
                user.setDebtMoney(newDebt);
                databaseHelper.updateUser(user);
                Toast.makeText(getContext(), "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                subjectAdapter.clearSelection();
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getContext(), "Đăng ký không thành công!", Toast.LENGTH_SHORT).show();
        }

//        Set<Integer> selectedPositions = subjectAdapter.getSelectedPositions();
//        double totalDebt = 0;
//        Set<Integer> registeredSubjectIds = new HashSet<>();
//
//        // Get the list of already registered subjects for the user
//        Cursor cursor = db.query("PayingTuition", null, "UserID = ?", new String[]{String.valueOf(userId)}, null, null, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                int subjectId = cursor.getInt(cursor.getColumnIndexOrThrow("SubjectID"));
//                registeredSubjectIds.add(subjectId);
//            } while (cursor.moveToNext());
//            cursor.close();
//        }
//
//        for (int position : selectedPositions) {
//            SubjectObject subject = subjects.get(position);
//            if (!registeredSubjectIds.contains(subject.getSubjectId())) {
//                PayingTuitionObject payingTuition = new PayingTuitionObject(userId, subject.getSubjectId(), subject.getSubjectName(), subject.getStudyCredits() * 415000, false);
//                databaseHelper.insertPayingTuition(userId, payingTuition.getSubjectID(), payingTuition.getSubjectName(), payingTuition.getAmount(), payingTuition.isPaided() ? 1 : 0);
//                totalDebt += payingTuition.getAmount();
//            }
//        }
//
//        // Update the user's debt in the database
//        UserObject user = databaseHelper.getUserById(userId);
//        if (user != null) {
//            double newDebt = user.getDebtMoney() + totalDebt;
//            user.setDebtMoney(newDebt);
//            databaseHelper.updateUser(user);
//        }
    }


}