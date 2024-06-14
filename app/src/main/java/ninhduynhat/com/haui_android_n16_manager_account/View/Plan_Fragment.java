package ninhduynhat.com.haui_android_n16_manager_account.View;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import ninhduynhat.com.haui_android_n16_manager_account.Adapters.PlanAdapter;
import ninhduynhat.com.haui_android_n16_manager_account.Model.PlanObject;
import ninhduynhat.com.haui_android_n16_manager_account.R;
import ninhduynhat.com.haui_android_n16_manager_account.Model.ListTypePlan;
import ninhduynhat.com.haui_android_n16_manager_account.Database.DatabaseHelper;
import ninhduynhat.com.haui_android_n16_manager_account.Adapters.TypePlanAdapter;
import ninhduynhat.com.haui_android_n16_manager_account.databinding.FragmentKeHoachBinding;

public class Plan_Fragment extends Fragment implements PlanAdapter.OnItemClickListener, TypePlanAdapter.OnItemClickListener {

    public static ArrayList<PlanObject> items = new ArrayList<>();
    private TypePlanAdapter typeAdapter;
    private PlanAdapter listPlanAdapter;
    private RecyclerView recyclerViewType, recyclerViewListPlan;
    private FragmentKeHoachBinding binding;
    private ImageView ic_plus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout cho fragment này bằng cách sử dụng view binding
        binding = FragmentKeHoachBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Khởi tạo ImageView từ binding
        ic_plus = binding.icPlus;
        if (ic_plus == null) {
            throw new NullPointerException("ic_plus là null. Đảm bảo rằng file layout của bạn có chứa một ImageView với id 'icPlus'.");
        }
        icPlusOnclick();
        initRecyclerviewType();
        initRecyclerviewPlan();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Set binding về null để tránh memory leaks
        binding = null;
    }

    private void icPlusOnclick() {
        ic_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddNewPlanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerviewPlan() {
        items = DatabaseHelper.getInstance(getContext()).fetchAllPlan();
        recyclerViewListPlan = binding.listviewTarget;
        recyclerViewListPlan.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        listPlanAdapter = new PlanAdapter(getContext(), items, this);
        recyclerViewListPlan.setAdapter(listPlanAdapter);
    }

    private void initRecyclerviewType() {
        ArrayList<ListTypePlan> typeItems = new ArrayList<>();
        typeItems.add(new ListTypePlan("piggy_bank", "Small"));
        typeItems.add(new ListTypePlan("piggy_bank", "Middle"));
        typeItems.add(new ListTypePlan("piggy_bank", "Big"));
        typeItems.add(new ListTypePlan("piggy_bank", "Short-term"));
        typeItems.add(new ListTypePlan("piggy_bank", "Long-term"));
        recyclerViewType = binding.listviewType;
        recyclerViewType.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        typeAdapter = new TypePlanAdapter(getContext(), typeItems, this);
        recyclerViewType.setAdapter(typeAdapter);
    }

    @Override
    public void onItemClick(PlanObject item) {
        Intent intent = new Intent(getContext(), PlanDetailActivity.class);
        intent.putExtra("Target", item);
        startActivity(intent);
    }

    @Override
    public void onItemClick(ListTypePlan item) {
        String type = item.getTitle();
        TextView txtName = binding.planName;
        TextView txtListName = binding.textViewListTarget;
        txtName.setText("Loại mục tiêu " + type);
        txtListName.setText("Danh sách mục tiêu " + type);
        items = DatabaseHelper.getInstance(getContext()).fetchPlanByType(type);
        listPlanAdapter.setTypePlan(items);
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecyclerviewType();
        initRecyclerviewPlan();
    }
}
