package ninhduynhat.com.haui_android_n16_manager_account.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import ninhduynhat.com.haui_android_n16_manager_account.Model.SubjectObject;
import ninhduynhat.com.haui_android_n16_manager_account.R;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {

    private List<SubjectObject> subjects;
    private Set<Integer> selectedSubjects;
    private Context context;

    public SubjectAdapter(List<SubjectObject> subjects, Context context) {
        this.subjects = subjects;
        this.selectedSubjects = new HashSet<>();
        this.context = context;
    }

    public void clearSelection() {
        selectedSubjects.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView description;
        public TextView credit;
        public TextView amount;

        public ViewHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cardSubject);
            description = view.findViewById(R.id.nameSubject);
            credit = view.findViewById(R.id.tvcredit);
            amount = view.findViewById(R.id.subject_amount);
        }
    }

    @Override
    public SubjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subject, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SubjectObject subject = subjects.get(position);
        // Giả sử bạn có TextView trong ViewHolder để hiển thị thông tin
//        holder.description.setText(subject.getSubjectName());
//        holder.credit.setText(String.format("Số tín chỉ: %d", subject.getStudyCredits()));
//        holder.amount.setText(String.format("Số tiền: %.2f", subject.getAmount()));

        holder.description.setText(subject.getSubjectName());
        holder.credit.setText(String.format("Số tín chỉ: %d", subject.getStudyCredits()));

        // Sử dụng NumberFormat để định dạng số tiền
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        String formattedAmount = numberFormat.format(subject.getAmount());
        holder.amount.setText(String.format("Số tiền: %s", formattedAmount));

        // Sử dụng context để truy cập tài nguyên
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Các thao tác khác khi ấn vào item
//                if (selectedSubjects.contains(position)) {
//                    selectedSubjects.remove(position);
//                    v.setBackgroundColor(context.getResources().getColor(android.R.color.white));
//                } else {
//                    selectedSubjects.add(position);
//                    v.setBackgroundColor(context.getResources().getColor(R.color.xanhla));
//                }
                if (selectedSubjects.contains(position)) {
                    selectedSubjects.remove(position);
                    holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
                } else {
                    selectedSubjects.add(position);
                    holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.xanhla));
                }
            }
        });

        holder.itemView.setBackgroundColor(selectedSubjects.contains(position) ?
                context.getResources().getColor(R.color.xanhla) :
                context.getResources().getColor(android.R.color.white));

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (selectedSubjects.contains(subject)) {
//                    selectedSubjects.remove(subject);
//                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));
//                } else {
//                    selectedSubjects.add(subject);
//                    holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.black));
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public Set<Integer> getSelectedPositions() {
        return selectedSubjects;
    }

}