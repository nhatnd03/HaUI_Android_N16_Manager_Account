package ninhduynhat.com.haui_android_n16_manager_account.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ninhduynhat.com.haui_android_n16_manager_account.R;
import ninhduynhat.com.haui_android_n16_manager_account.Database.DatabaseHelper;
import ninhduynhat.com.haui_android_n16_manager_account.Model.PlanObject;
import ninhduynhat.com.haui_android_n16_manager_account.View.UpdatePlanActivity;

public class PlanDetailActivity extends AppCompatActivity {
    private Button btnDelete, btnUpdate;
    private PlanObject plan;
    private TextView textViewTargetName, textViewTargetDeadline, textViewProgressPercent, tvTotalBudget, tvSavedBudget, textViewDescribe, tvType;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_detail);
        setWidget();

        // Get data from Intent
        Intent intent = getIntent();
        if (intent != null) {
            plan = intent.getParcelableExtra("PlanObject");
        }

        if (plan != null) {
            setDataForActivity();
            setupButtonListeners();
        } else {
            Log.e("PlanDetailActivity", "PlanObject is null.");
            Toast.makeText(this, "Không thể tải thông tin mục tiêu", Toast.LENGTH_SHORT).show();
            finish(); // End activity if there is no valid data
        }
    }

    private void setWidget() {
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        textViewTargetName = findViewById(R.id.planName);
        textViewTargetDeadline = findViewById(R.id.textViewDeadline);
        textViewProgressPercent = findViewById(R.id.progress_percent);
        progressBar = findViewById(R.id.progress_bar);
        tvTotalBudget = findViewById(R.id.textViewTotalBudget);
        tvSavedBudget = findViewById(R.id.textViewSavedBudget);
        tvType = findViewById(R.id.textViewType);
        textViewDescribe = findViewById(R.id.textViewDescribe);
    }

    private void setupButtonListeners() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (plan != null) {
                    DatabaseHelper.getInstance(PlanDetailActivity.this).deletePlan(plan);
                    Toast.makeText(PlanDetailActivity.this, "Xóa mục tiêu thành công", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Log.e("PlanDetailActivity", "PlanObject is null. Cannot delete plan.");
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (plan != null) {
                    Intent intent = new Intent(PlanDetailActivity.this, UpdatePlanActivity.class);
                    intent.putExtra("PlanObject", plan);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("PlanDetailActivity", "PlanObject is null. Cannot update plan.");
                }
            }
        });
    }

    private void setDataForActivity() {
        if (plan != null) {
            textViewTargetDeadline.setText("Thời gian: " + plan.getTimeLine());
            textViewTargetName.setText(plan.getPlanName());
            int percent = (int) ((plan.getAmoutReached() / plan.getAmoutNeeded()) * 100);
            textViewProgressPercent.setText(percent + "%");
            tvTotalBudget.setText("Số tiền cần có: " + plan.getAmoutNeeded());
            tvSavedBudget.setText("Số tiền đã có: " + plan.getAmoutReached());
            tvType.setText("Loại mục tiêu: " + plan.getPlanType());
            progressBar.setProgress(percent);
            textViewDescribe.setText(plan.getDescription());
        } else {
            Log.e("PlanDetailActivity", "PlanObject is null. Cannot set data for activity.");
        }
    }
}
