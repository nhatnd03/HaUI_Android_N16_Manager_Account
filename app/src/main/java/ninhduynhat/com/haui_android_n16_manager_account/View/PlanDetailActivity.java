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
    Button btnDelete, btnUpdate;
    PlanObject plan;

    TextView textViewTargetName, textViewTargetDeadline, textViewProgressPercent, tvTotalBudget, tvSavedBudget, tvType;
    ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_detail);
        setWidget();
        // get data
        plan = (PlanObject) getIntent().getParcelableExtra("PlanObject");
        setDataForActivity();
        btnUpdateOnClick();
        btnDeleteOnClick();
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
    }
    private void btnDeleteOnClick() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper.getInstance(PlanDetailActivity.this).deletePlan(plan);
                Toast.makeText(PlanDetailActivity.this, "Xóa mục tiêu thành công", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    private void btnUpdateOnClick() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlanDetailActivity.this, UpdatePlanActivity.class);
                intent.putExtra("Tên mục tiêu", plan);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setDataForActivity() {
        textViewTargetDeadline.setText("Thời gian: " + plan.getTimeLine());
        textViewTargetName.setText(plan.getPlanName());
        int percent = (int) (plan.getAmoutReached() / plan.getAmoutNeeded() * 100);
        textViewProgressPercent.setText(percent + "%");
        tvTotalBudget.setText(String.valueOf("Số tiền cần có :  " + plan.getAmoutNeeded()));
        tvSavedBudget.setText(String.valueOf("Số tiền đã có :  " + plan.getAmoutReached()));
        tvType.setText("Loại mục tiêu :" + plan.getPlanType());
        progressBar.setProgress(percent);
    }
}
