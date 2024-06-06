package ninhduynhat.com.haui_android_n16_manager_account.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ninhduynhat.com.haui_android_n16_manager_account.Model.PayingTuitionObject;
import ninhduynhat.com.haui_android_n16_manager_account.R;

public class PayingTuitionAdapter extends RecyclerView.Adapter<PayingTuitionAdapter.ViewHolder> {

    private Context context;
    private List<PayingTuitionObject> payingTuitionList;
    private Set<Integer> selectedPositions = new HashSet<>();

    public PayingTuitionAdapter(Context context, List<PayingTuitionObject> payingTuitionList) {
        this.context = context;
        this.payingTuitionList = payingTuitionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_paying_tuition, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PayingTuitionObject payingTuition = payingTuitionList.get(position);
        holder.subjectName.setText(payingTuition.getSubjectName());
        holder.amount.setText(String.format("%,.0f đ", payingTuition.TIENMOTTINCHI));
        holder.totalAmount.setText(String.format("%,.0f đ", payingTuition.getAmount()));

        holder.itemView.setBackgroundColor(selectedPositions.contains(position) ?
                context.getResources().getColor(R.color.xanhla) : context.getResources().getColor(R.color.white));

        holder.itemView.setOnClickListener(v -> {
            if (selectedPositions.contains(position)) {
                selectedPositions.remove(position);
            } else {
                selectedPositions.add(position);
            }
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return payingTuitionList.size();
    }

    public Set<Integer> getSelectedPositions() {
        return selectedPositions;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subjectName, amount, totalAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subject_name);
            amount = itemView.findViewById(R.id.amount);
            totalAmount = itemView.findViewById(R.id.total_amount);
        }
    }
}