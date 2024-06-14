package ninhduynhat.com.haui_android_n16_manager_account.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import ninhduynhat.com.haui_android_n16_manager_account.R;
import ninhduynhat.com.haui_android_n16_manager_account.Model.PlanObject;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.viewholder> {
    ArrayList<PlanObject> items;
    Context context;
    private OnItemClickListener listener;

    public PlanAdapter(Context context, ArrayList<PlanObject> items, OnItemClickListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }
    public void setTypePlan(ArrayList<PlanObject> Plans) {
        items = Plans;
        notifyDataSetChanged();
    }
    public interface OnItemClickListener {
        void onItemClick(PlanObject item);
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_list_target, parent, false);
        return new viewholder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.viewholder holder, int position) {
        holder.title.setText(items.get(position).getPlanName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(items.get(position));
            }
        });
        int a = (int) (items.get(position).getAmoutReached() / items.get(position).getAmoutReached() * 100);
        System.out.println(items.get(position).getAmoutReached());
        holder.processPercent.setText(a + "%");
        holder.progressBar.setProgress(a);
//        int drawableResourceId = holder.itemView.getResources()
//                .getIdentifier(items.get(position).getImgSrc(), "drawable", holder.itemView.getContext().getPackageName());
//
//        Glide.with(context)
//                .load(drawableResourceId)
//                .into(holder.ic);


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView ic;
        TextView title, processPercent;
        ProgressBar progressBar;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            ic = itemView.findViewById(R.id.icon_target);
            title = (TextView) itemView.findViewById(R.id.title);
            processPercent = itemView.findViewById(R.id.progress);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

    }
}
