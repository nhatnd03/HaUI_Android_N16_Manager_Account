package ninhduynhat.com.haui_android_n16_manager_account.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ninhduynhat.com.haui_android_n16_manager_account.Database.DatabaseHelper;
import ninhduynhat.com.haui_android_n16_manager_account.Model.PlanObject;
import ninhduynhat.com.haui_android_n16_manager_account.R;


public class UpdatePlanActivity extends Activity {
    PlanObject plan;
    EditText editTextTotalBudget, editTextSavedBudget, editTextDeadline, editTextDescribe;
    TextView textViewName;
    Spinner spinner;
    ProgressBar progressBar;
    ImageView imageViewDatePicker;
    Button btnUpdate, btnBack;

    List<String> type = Arrays.asList("Small", "Middle", "Big", "Short-term", "Long-term");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_target);
        setWidget();
        plan = (PlanObject) getIntent().getParcelableExtra("PlanObject");
        setupDataForFragment();
        editTextSavedBudgetEvent();
        setupSpinner();
        editTextDeadlineEvent();
        btnUpdateEvent();
        btnBackEvent();
        imageViewDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDate();
            }
        });
    }

    private void btnBackEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void btnUpdateEvent() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (editTextTotalBudget.getText().toString().equals("")) {
                        Toast.makeText(UpdatePlanActivity.this, "Total Budget can not empty", Toast.LENGTH_SHORT).show();
                    } else if (editTextSavedBudget.getText().toString().equals("")) {
                        Toast.makeText(UpdatePlanActivity.this, "Saved Budget can not empty", Toast.LENGTH_SHORT).show();
                    } else if (editTextDeadline.getText().toString().equals("")) {
                        Toast.makeText(UpdatePlanActivity.this, "Deadline can not empty", Toast.LENGTH_SHORT).show();
                    }

                    PlanObject newtarget = new PlanObject(plan.getPlanId(), textViewName.getText().toString(), Double.parseDouble(editTextTotalBudget.getText().toString()), Double.parseDouble(editTextSavedBudget.getText().toString()), editTextDeadline.getText().toString(), spinner.getSelectedItem().toString(),editTextDescribe.getText().toString());
                    DatabaseHelper.getInstance(UpdatePlanActivity.this).updatePlan(plan);
                    Toast.makeText(UpdatePlanActivity.this, "updated Target successfully", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(UpdatePlanActivity.this, "update target Error" , Toast.LENGTH_SHORT).show();
                }
                finish();

            }
        });
    }

    private void editTextDeadlineEvent() {
        editTextDeadline.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    pickDate();
                    view.clearFocus();
                }
            }
        });
    }

    private void setupSpinner() {
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(UpdatePlanActivity.this, android.R.layout.simple_spinner_item, type);
        spinner.setAdapter(spinnerAdapter);
    }

    private void editTextSavedBudgetEvent() {
        editTextSavedBudget.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (editTextSavedBudget.getText().toString().equals("")) {
                    editTextSavedBudget.setText("0");
                }

                int progressprecent = (int) (Double.parseDouble(editTextSavedBudget.getText().toString()) / Double.parseDouble(editTextTotalBudget.getText().toString()) * 100);
                progressBar.setProgress(progressprecent);
            }
        });
    }

    private void setupDataForFragment() {

        textViewName.setText(plan.getPlanName());
        editTextSavedBudget.setText(String.valueOf(plan.getAmoutReached()));
        editTextTotalBudget.setText(String.valueOf(plan.getAmoutNeeded()));
        editTextDeadline.setText(plan.getTimeLine());
    }

    private void setWidget() {
        textViewName =findViewById(R.id.planname);
        editTextTotalBudget = findViewById(R.id.editTextTotalBudget);
        editTextSavedBudget = findViewById(R.id.editTextSavedBudget);
        editTextDeadline = findViewById(R.id.editTextDeadline);
        imageViewDatePicker = findViewById(R.id.imageView);
        spinner = findViewById(R.id.spinner);
        progressBar = findViewById(R.id.progressBar);
        btnBack = findViewById(R.id.btnBack);
        btnUpdate = findViewById(R.id.btnUpdate);
    }

    public void pickDate() {
        SimpleDateFormat df = new SimpleDateFormat("dd-M-yyyy");
        Calendar calendar = Calendar.getInstance();
        int year, month, day;
        try {
            Date date = df.parse(editTextDeadline.getText().toString());
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                UpdatePlanActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        editTextDeadline.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                },
                year, month, day);
        datePickerDialog.show();
    }
}
