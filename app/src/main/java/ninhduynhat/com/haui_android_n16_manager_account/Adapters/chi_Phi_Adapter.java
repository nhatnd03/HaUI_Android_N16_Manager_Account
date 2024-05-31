package ninhduynhat.com.haui_android_n16_manager_account.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ninhduynhat.com.haui_android_n16_manager_account.Model.KhoanChi;
import ninhduynhat.com.haui_android_n16_manager_account.R;

public class chi_Phi_Adapter extends RecyclerView.Adapter<chi_Phi_Adapter.chi_PhiViewHolder>{
    private Context mContext;
    private List<KhoanChi> mListKhoanChi;

    public chi_Phi_Adapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<KhoanChi> list){
        this.mListKhoanChi=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public chi_PhiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chi_phi,parent,false);
        return new chi_PhiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chi_PhiViewHolder holder, int position) {
        KhoanChi khoanChi =mListKhoanChi.get(position);
        if(khoanChi==null){
            return;
        }
        holder.ten_chi_phi.setText(khoanChi.getKhoanchi());
        holder.mo_ta_chi_phi.setText(khoanChi.getMo_Ta());
        holder.gia_chi_phi.setText(khoanChi.getSo_Luong());
    }

    @Override
    public int getItemCount() {
        if(mListKhoanChi !=null){
            return mListKhoanChi.size();
        }
        return 0;
    }

    public class chi_PhiViewHolder extends RecyclerView.ViewHolder{
        private TextView ten_chi_phi,mo_ta_chi_phi,gia_chi_phi;

        public chi_PhiViewHolder(@NonNull View itemView) {
            super(itemView);
            ten_chi_phi=itemView.findViewById(R.id.ten_chi_phi);
            mo_ta_chi_phi=itemView.findViewById(R.id.mo_ta_chi_phi);
            gia_chi_phi=itemView.findViewById(R.id.gia_chi_phi);
        }
    }
}
