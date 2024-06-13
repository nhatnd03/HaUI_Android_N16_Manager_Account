package ninhduynhat.com.haui_android_n16_manager_account.View;

<<<<<<< HEAD
import static android.content.Context.MODE_PRIVATE;
import static ninhduynhat.com.haui_android_n16_manager_account.Login_Account.LUU_TRANG_THAI_NGUOI_DUNG;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
=======
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
<<<<<<< HEAD

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.Collator;
import java.text.DateFormat;
import java.text.NumberFormat;
=======
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.ParseException;
>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
<<<<<<< HEAD
import java.util.List;
import java.util.Locale;

import ninhduynhat.com.haui_android_n16_manager_account.Database.DatabaseHelper;
import ninhduynhat.com.haui_android_n16_manager_account.Model.DashboardViewModel;
import ninhduynhat.com.haui_android_n16_manager_account.Model.ExpensesObject;
import ninhduynhat.com.haui_android_n16_manager_account.Model.UserObject;
//import ninhduynhat.com.haui_android_n16_manager_account.R;
=======

import ninhduynhat.com.haui_android_n16_manager_account.R;
>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
import ninhduynhat.com.haui_android_n16_manager_account.databinding.FragmentThongKeBinding;


public class Thong_Ke_Fragment extends Fragment {
    private FragmentThongKeBinding binding;
<<<<<<< HEAD
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;
    private TextView displayDate,displayMonth,displayYear;
    ListView listMonthlyExpense;
    //    private DBHandler dbHandler;
    private Button btnDaily, btnmonthly, btnyearly;

    private View screen1, screen2, screen3, screen4;
    int[] colorClassArray = new int[]{Color.LTGRAY, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GREEN, Color.MAGENTA, Color.RED};
=======
//    private DBHandler dbHandler;
    private Button btnDaily, btnweekly, btnmonthly, btnyearly;

    private View screen1, screen2, screen3, screen4;
>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
<<<<<<< HEAD
=======

>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
        binding = FragmentThongKeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


<<<<<<< HEAD
        displayDate=binding.displayDate;
        displayMonth=binding.displayMonth;
        displayYear=binding.displayYear;
=======

>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
        screen1 = binding.dailyStatistic;
        screen2 = binding.weeklyStatistic;
        screen3 = binding.monthlyStatistic;
        screen4 = binding.yearlyStatistic;
<<<<<<< HEAD
=======



        PieChart dailyPieChart, weeklyPieChart, monthlyPiechart, yearlyPiechart;
        LineChart yearlyLineChart, monthlyLineChart;

        ListView dailyList, weeklyList, monthlyList, yearlyList;

>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
        btnDaily = binding.btndaily;
        btnDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                showScreen1();

            }
        });
<<<<<<< HEAD
//        btnweekly = binding.btnweekly;
=======
        btnweekly = binding.btnweekly;
        btnweekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
                showScreen2();
            }
        });

>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
        btnmonthly = binding.btnmonthly;
        btnmonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMonthPickerDialog();
                showScreen3();
            }
        });
        btnyearly = binding.btnyearly;
        btnyearly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearPickerDialog();
                showScreen4();
            }
        });
<<<<<<< HEAD
        return root;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PieChart dailyPieChart, weeklyPieChart, monthlyPieChart, yearlyPiechart;
        LineChart yearlyLineChart, monthlyLineChart;

        ListView dailyList, weeklyList, monthlyList, yearlyList;
        //lấy ngày hiện tại
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        Date date = new Date(nam-1900,thang,ngay);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = df.format(date);
        displayDate.setText(dateString);

        UserObject userObject= new UserObject();
        userObject=getDataUserName();
        List<ExpensesObject>  list=databaseHelper.getExpensesObjectOfDate(userObject.getUserID(),dateString);

        Collator collator = Collator.getInstance(Locale.FRENCH);
        collator.setStrength(Collator.PRIMARY);
        float n1=0,n2=0,n3=0,n4=0,n5=0,n6=0,n7=0;
        for(int i=0;i<list.size();i++){
            if(collator.compare(list.get(i).getExpensesType(),"Thực phẩm & Đồ uống")==0){
                n1+= (float) list.get(i).getAmountSpent();
            } else if (collator.compare(list.get(i).getExpensesType(),"Sức khỏe")==0) {
                n2+= (float) list.get(i).getAmountSpent();
            } else if (collator.compare(list.get(i).getExpensesType(),"Chi phí nhà ở")==0) {
                n3+= (float) list.get(i).getAmountSpent();
            }else if (collator.compare(list.get(i).getExpensesType(),"Đầu tư")==0) {
                n4+= (float) list.get(i).getAmountSpent();
            }else if (collator.compare(list.get(i).getExpensesType(),"Chi phí đi lại")==0) {
                n5+= (float) list.get(i).getAmountSpent();
            }else if (collator.compare(list.get(i).getExpensesType(),"Du lịch")==0) {
                n6+= (float) list.get(i).getAmountSpent();
            }else if (collator.compare(list.get(i).getExpensesType(),"Chi phí khác")==0) {
                Log.e("check dữ liệu chi phí khác",n7+"");
                n7+= (float) list.get(i).getAmountSpent();
            }

        }
        ArrayList<PieEntry> dataVals = new ArrayList<>();
        dataVals.add(new PieEntry(n1,"Thực phẩm & Đồ uống"));
        dataVals.add(new PieEntry(n2,"Sức khỏe"));
        dataVals.add(new PieEntry(n3,"Chi phí nhà ở"));
        dataVals.add(new PieEntry(n4,"Đầu tư"));
        dataVals.add(new PieEntry(n5,"Chi phí đi lại"));
        dataVals.add(new PieEntry(n6,"Du lịch"));
        dataVals.add(new PieEntry(n7,"Chi phí khác"));



        //Nhập dữ liệu cho biểu đồ thống kê ngày //
        dailyPieChart = binding.dailyPieChart;
        PieDataSet piedataset = new PieDataSet(dataVals,"");
        piedataset.setColors(colorClassArray);
=======

        //Nhập dữ liệu cho biểu đồ thống kê ngày //
        dailyPieChart = binding.dailyPieChart;
        int[] colorClassArray = new int[]{Color.LTGRAY, Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GREEN, Color.MAGENTA, Color.RED};
        PieDataSet piedataset = new PieDataSet(dailydata1(),"");
        piedataset.setColors(ColorTemplate.MATERIAL_COLORS);
>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
        PieData pieData = new PieData(piedataset);
        dailyPieChart.setData(pieData);
        dailyPieChart.setCenterTextSize(25);
        dailyPieChart.setUsePercentValues(true);
        dailyPieChart.setCenterText("daily expense");
        dailyPieChart.invalidate();
<<<<<<< HEAD
        NumberFormat numberFormat = NumberFormat.getInstance();
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Thực phẩm & Đồ uống - "+numberFormat.format(n1)+" VND");
        list1.add("Sức khỏe - "+numberFormat.format(n2)+" VND");
        list1.add("Chi phí nhà ở - "+numberFormat.format(n3)+" VND");
        list1.add("Đầu tư - "+numberFormat.format(n4)+" VND");
        list1.add("Chi phí đi lại - "+numberFormat.format(n5)+" VND");
        list1.add("Du lịch - "+numberFormat.format(n6)+" VND");
        list1.add("Chi phí khác - "+numberFormat.format(n7)+" VND");
=======

        ArrayList<String> list1 = new ArrayList<>();

        list1.add("bánh tráng - 20k");
        list1.add("bún đậu - 30k");
        list1.add("bún bò - 30k");
>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab

        ArrayList<String> List = new ArrayList<>();
        List.addAll(list1);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List);
        dailyList = binding.listDailyExpense;
        dailyList.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

<<<<<<< HEAD
    }


=======
        //////////////////////////////////////////

        //Nhập dữ liệu cho biểu đồ thống kê tuần //

        weeklyPieChart = binding.weeklyPieChart;
        PieDataSet piedataset2 = new PieDataSet(weeklydata1(),"");
        piedataset2.setColors(ColorTemplate.PASTEL_COLORS);
        PieData pieData2 = new PieData(piedataset2);
        weeklyPieChart.setData(pieData2);
        weeklyPieChart.setCenterTextSize(25);
        weeklyPieChart.setUsePercentValues(true);
        weeklyPieChart.setCenterText("weekly expense");
        weeklyPieChart.invalidate();

        ArrayList<String> list2 = new ArrayList<>();

        list2.add("Giải trí - 140k");
        list2.add("Học tập - 220k");
        list2.add("Ăn uống - 200k");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list2);
        weeklyList = binding.listWeeklyExpense;
        weeklyList.setAdapter(adapter2);
        adapter2.notifyDataSetChanged();


        //////////////////////////////////////////

        //Nhập dữ liệu cho biểu đồ thống kê tháng //
        monthlyPiechart = binding.monthlyPieChart;
        PieDataSet piedataset3 = new PieDataSet(monthlydata1(),"");
        piedataset3.setColors(colorClassArray);
        PieData pieData3 = new PieData(piedataset3);
        monthlyPiechart.setData(pieData3);
        monthlyPiechart.setCenterTextSize(25);
        monthlyPiechart.setUsePercentValues(true);
        monthlyPiechart.setCenterText("monthly expense");
        monthlyPiechart.invalidate();


        monthlyLineChart = binding.monthlyLineChart;
        LineDataSet lineDataSet32 = new LineDataSet(monthlyLine1(),"monthly expense");
        lineDataSet32.setColor(Color.RED);

        ArrayList<ILineDataSet> dataSetsmonth = new ArrayList<>();
        dataSetsmonth.add(lineDataSet32);
        LineData lineData3 = new LineData(dataSetsmonth);

        final ArrayList<String> xLabelMonth = new ArrayList<>();
        for (int i = 1; i<= 30; i++)
        {
            xLabelMonth.add(String.valueOf(i));
        }

        XAxis xAxis3 = monthlyLineChart.getXAxis();
        xAxis3.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis3.setAxisMinimum(0f);
        xAxis3.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xLabelMonth.get((int) value % xLabelMonth.size());
            }
        });
        YAxis leftAxis3 = monthlyLineChart.getAxisLeft();
        leftAxis3.setAxisMinimum(0f);
        YAxis rightAxis3 = monthlyLineChart.getAxisRight();
        rightAxis3.setAxisMinimum(0f);

        monthlyLineChart.setDrawGridBackground(false);
        monthlyLineChart.setData(lineData3);
        monthlyLineChart.invalidate();

        ArrayList<String> list3 = new ArrayList<>();

        list3.add("Điện nước - 300k");
        list3.add("Tiền nhà  - 1000k");
        list3.add("Tiền mua sắm  - 400k");
        list3.add("Ăn uống - 1000k");

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list3);
        monthlyList = binding.listMonthlyExpense;
        monthlyList.setAdapter(adapter3);
        adapter3.notifyDataSetChanged();


        //////////////////////////////////////////


        //Nhập dữ liệu cho biểu đồ thống kê năm //
        yearlyPiechart = binding.yearlyPieChart;
        PieDataSet piedataset4 = new PieDataSet(yearlydata1(),"");
        piedataset4.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData4 = new PieData(piedataset4);
        yearlyPiechart.setData(pieData4);
        yearlyPiechart.setCenterTextSize(25);
        yearlyPiechart.setUsePercentValues(true);
        yearlyPiechart.setCenterText("yearly expense");
        yearlyPiechart.invalidate();




        yearlyLineChart = binding.yearlyLineChart;
        LineDataSet lineDataSet4 = new LineDataSet(yearlyLine1(),"yearly expense");
        lineDataSet4.setColor(Color.BLUE);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet4);
        LineData lineData = new LineData(dataSets);

        final ArrayList<String> xLabel = new ArrayList<>();
        xLabel.add("Jan");
        xLabel.add("Feb");
        xLabel.add("Mar");
        xLabel.add("Apr");
        xLabel.add("May");
        xLabel.add("Jun");
        xLabel.add("Jul");
        xLabel.add("Aug");
        xLabel.add("Sep");
        xLabel.add("Oct");
        xLabel.add("Nov");
        xLabel.add("Dec");

        XAxis xAxis = yearlyLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xLabel.get((int) value % xLabel.size());
            }
        });
        YAxis leftAxis42 = yearlyLineChart.getAxisLeft();
        leftAxis42.setAxisMinimum(0f);
        YAxis rightAxis42 = yearlyLineChart.getAxisRight();
        rightAxis42.setAxisMinimum(0f);

        yearlyLineChart.setDrawGridBackground(false);
        yearlyLineChart.setData(lineData);
        yearlyLineChart.invalidate();

        ArrayList<String> list4 = new ArrayList<>();

        list4.add("Điện nước - 3000k");
        list4.add("Tiền nhà  - 10000k");
        list4.add("Tiền mua sắm  - 500k");
        list4.add("Ăn uống - 10000k");

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list4);
        yearlyList = binding.listYearlyExpense;
        yearlyList.setAdapter(adapter4);
        adapter4.notifyDataSetChanged();



        //////////////////////////////////////////

        return root;
    }

    private ArrayList<PieEntry> dailydata1(){
        ArrayList<PieEntry> dataVals = new ArrayList<>();
        dataVals.add(new PieEntry(30,"Bún bò"));
        dataVals.add(new PieEntry(30,"Bún đậu"));
        dataVals.add(new PieEntry(20,"Bánh tráng"));

        return dataVals;
    }
    private ArrayList<PieEntry> weeklydata1(){
        ArrayList<PieEntry> dataVals = new ArrayList<>();
        dataVals.add(new PieEntry(140,"giải trí"));
        dataVals.add(new PieEntry(200,"ăn uống"));
        dataVals.add(new PieEntry(220,"học tập"));

        return dataVals;
    }

    private ArrayList<PieEntry> monthlydata1(){
        ArrayList<PieEntry> dataVals = new ArrayList<>();
        dataVals.add(new PieEntry(300,"Điện nước"));
        dataVals.add(new PieEntry(1000,"Tiền nhà"));
        dataVals.add(new PieEntry(400,"tiền mua sắm"));
        dataVals.add(new PieEntry(1000,"tiền ăn uống"));
        return dataVals;
    }

    private ArrayList<BarEntry> monthlyBar1(){
        ArrayList<BarEntry> datavals = new ArrayList<>();
        datavals.add(new BarEntry(1,2000));

        return  datavals;
    }
    private ArrayList<BarEntry> monthlybar2(){
        ArrayList<BarEntry> datavals = new ArrayList<>();
        datavals.add(new BarEntry(2,2500));

        return  datavals;
    }

    private ArrayList<PieEntry> yearlydata1(){
        ArrayList<PieEntry> dataVals = new ArrayList<>();
        dataVals.add(new PieEntry(3000,"Điện nước"));
        dataVals.add(new PieEntry(10000,"Tiền nhà"));
        dataVals.add(new PieEntry(5000,"tiền mua sắm"));
        dataVals.add(new PieEntry(10000,"tiền ăn uống"));
        return dataVals;
    }

    private ArrayList<BarEntry> yearlyBar1(){
        ArrayList<BarEntry> datavals = new ArrayList<>();
        datavals.add(new BarEntry(1,6000));

        return  datavals;
    }
    private ArrayList<BarEntry> yearlybar2(){
        ArrayList<BarEntry> datavals = new ArrayList<>();
        datavals.add(new BarEntry(2,8000));

        return  datavals;
    }
    private ArrayList<Entry> yearlyLine1(){
        ArrayList<Entry> datavals = new ArrayList<>();
        datavals.add(new Entry(0,6000));
        datavals.add(new Entry(1,7658));
        datavals.add(new Entry(2,8650));
        datavals.add(new Entry(3,6540));
        datavals.add(new Entry(4,7590));
        datavals.add(new Entry(5,9486));
        datavals.add(new Entry(6,8126));
        datavals.add(new Entry(7,7458));
        datavals.add(new Entry(8,6958));
        datavals.add(new Entry(9,5798));
        datavals.add(new Entry(10,9456));
        datavals.add(new Entry(11,6254));

        return datavals;
    }
    private ArrayList<Entry> monthlyLine1(){
        ArrayList<Entry> datavals = new ArrayList<>();

        datavals.add(new Entry(1,7658));
        datavals.add(new Entry(2,8650));
        datavals.add(new Entry(3,6540));
        datavals.add(new Entry(4,7590));
        datavals.add(new Entry(5,9486));
        datavals.add(new Entry(6,8126));
        datavals.add(new Entry(7,7458));
        datavals.add(new Entry(8,6958));
        datavals.add(new Entry(9,5798));
        datavals.add(new Entry(10,2615));
        datavals.add(new Entry(11,4567));
        datavals.add(new Entry(12,3455));
        datavals.add(new Entry(13,5478));
        datavals.add(new Entry(14,8654));
        datavals.add(new Entry(15,5687));
        datavals.add(new Entry(16,5434));
        datavals.add(new Entry(17,4577));
        datavals.add(new Entry(18,9787));
        datavals.add(new Entry(19,5465));
        datavals.add(new Entry(20,5448));
        datavals.add(new Entry(21,3214));
        datavals.add(new Entry(22,1124));
        datavals.add(new Entry(23,4578));
        datavals.add(new Entry(24,5456));
        datavals.add(new Entry(25,4568));
        datavals.add(new Entry(26,8795));
        datavals.add(new Entry(27,5786));
        datavals.add(new Entry(28,3456));
        datavals.add(new Entry(29,5687));
        datavals.add(new Entry(30,3456));
        datavals.add(new Entry(31,5338));

        return datavals;
    }
>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
    private void showScreen1() {
        screen1.setVisibility(View.VISIBLE);
        screen2.setVisibility(View.GONE);
        screen3.setVisibility(View.GONE);
        screen4.setVisibility(View.GONE);
    }

<<<<<<< HEAD
=======
    private void showScreen2() {

        screen1.setVisibility(View.GONE);
        screen2.setVisibility(View.VISIBLE);
        screen3.setVisibility(View.GONE);
        screen4.setVisibility(View.GONE);
    }
>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
    private void showScreen3() {

        screen1.setVisibility(View.GONE);
        screen2.setVisibility(View.GONE);
        screen3.setVisibility(View.VISIBLE);
        screen4.setVisibility(View.GONE);
    }
    private void showScreen4() {

        screen1.setVisibility(View.GONE);
        screen2.setVisibility(View.GONE);
        screen3.setVisibility(View.GONE);
        screen4.setVisibility(View.VISIBLE);
    }
<<<<<<< HEAD

    //hàm hiển thị thống kê theo ngày
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                try {
                    Date date = new Date(year-1900,month,dayOfMonth);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = df.format(date);
                    dailydata1(dateString);
                }
                catch (Exception e){

                }
            }
        }, nam, thang, ngay);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setTitle("Chọn thời gian");
        dialog.show();
    }

    private void dailydata1(String ngayhientai){
        databaseHelper= new DatabaseHelper(getActivity());

        displayDate.setText(ngayhientai);
        PieChart dailyPieChart;
        ListView dailyList;

        UserObject userObject= new UserObject();
        userObject=getDataUserName();
        List<ExpensesObject>  list=databaseHelper.getExpensesObjectOfDate(userObject.getUserID(),ngayhientai);
        Collator collator = Collator.getInstance(Locale.FRENCH);
        collator.setStrength(Collator.PRIMARY);
        float n1=0,n2=0,n3=0,n4=0,n5=0,n6=0,n7=0;
        for(int i=0;i<list.size();i++){
            if(collator.compare(list.get(i).getExpensesType(),"Thực phẩm & Đồ uống")==0){
                n1+= (float) list.get(i).getAmountSpent();
            } else if (collator.compare(list.get(i).getExpensesType(),"Sức khỏe")==0) {
                n2+= (float) list.get(i).getAmountSpent();
            } else if (collator.compare(list.get(i).getExpensesType(),"Chi phí nhà ở")==0) {
                n3+= (float) list.get(i).getAmountSpent();
            }else if (collator.compare(list.get(i).getExpensesType(),"Đầu tư")==0) {
                n4+= (float) list.get(i).getAmountSpent();
            }else if (collator.compare(list.get(i).getExpensesType(),"Chi phí đi lại")==0) {
                n5+= (float) list.get(i).getAmountSpent();
            }else if (collator.compare(list.get(i).getExpensesType(),"Du lịch")==0) {
                n6+= (float) list.get(i).getAmountSpent();
            }else if (collator.compare(list.get(i).getExpensesType(),"Chi phí khác")==0) {
                Log.e("check dữ liệu chi phí khác",n7+"");
                n7+= (float) list.get(i).getAmountSpent();
            }

        }
        ArrayList<PieEntry> dataVals = new ArrayList<>();
        dataVals.add(new PieEntry(n1,"Thực phẩm & Đồ uống"));
        dataVals.add(new PieEntry(n2,"Sức khỏe"));
        dataVals.add(new PieEntry(n3,"Chi phí nhà ở"));
        dataVals.add(new PieEntry(n4,"Đầu tư"));
        dataVals.add(new PieEntry(n5,"Chi phí đi lại"));
        dataVals.add(new PieEntry(n6,"Du lịch"));
        dataVals.add(new PieEntry(n7,"Chi phí khác"));



        //Nhập dữ liệu cho biểu đồ thống kê ngày //
        dailyPieChart = binding.dailyPieChart;
        PieDataSet piedataset = new PieDataSet(dataVals,"");
        piedataset.setColors(colorClassArray);
        PieData pieData = new PieData(piedataset);
        dailyPieChart.setData(pieData);
        dailyPieChart.setCenterTextSize(25);
        dailyPieChart.setUsePercentValues(true);
        dailyPieChart.setCenterText("daily expense");
        dailyPieChart.invalidate();

        NumberFormat numberFormat = NumberFormat.getInstance();
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Thực phẩm & Đồ uống - "+numberFormat.format(n1) +" VND");
        list1.add("Sức khỏe - "+numberFormat.format(n2)+" VND");
        list1.add("Chi phí nhà ở - "+numberFormat.format(n3)+" VND");
        list1.add("Đầu tư - "+numberFormat.format(n4)+" VND");
        list1.add("Chi phí đi lại - "+numberFormat.format(n5)+" VND");
        list1.add("Du lịch - "+numberFormat.format(n6)+" VND");
        list1.add("Chi phí khác - "+numberFormat.format(n7)+" VND");

        ArrayList<String> List = new ArrayList<>();
        List.addAll(list1);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List);
        dailyList = binding.listDailyExpense;
        dailyList.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();
    }



    private UserObject getDataUserName(){
        sharedPreferences =getActivity().getSharedPreferences(LUU_TRANG_THAI_NGUOI_DUNG,MODE_PRIVATE);
        databaseHelper= new DatabaseHelper(getActivity());
        UserObject userObject= new UserObject();
        String user_name=sharedPreferences.getString("UserName","");
        userObject=databaseHelper.getUserByUsername_Home(user_name);
        return userObject;
    }
    

//hiển thị thống kê theo tháng
    private void showMonthPickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                try {
                    Date date = new Date(year-1900,month,dayOfMonth);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = df.format(date);
                    Date dates = df.parse(dateString);
                    SimpleDateFormat monthFormat = new SimpleDateFormat("MM/yyyy");
                    String months = monthFormat.format(date);
                    monthlydata1(months);
                }
                catch (Exception e){

                }
            }
        }, nam, thang, ngay);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setTitle("Chọn thời gian");
        dialog.show();
    }

    private void monthlydata1(String thanghientai){

        displayMonth.setText("Thống kê theo tháng: "+thanghientai);
        PieChart  monthlyPiechart;
        ListView  monthlyList;
        databaseHelper= new DatabaseHelper(getActivity());

        UserObject userObject= new UserObject();
        userObject=getDataUserName();
        //danh sách các loại chi tiêu trong một tháng
        List<ExpensesObject>  list_1= databaseHelper.getExpensesObjectOfMonth(userObject.getUserID(),thanghientai);

        Collator collator = Collator.getInstance(Locale.FRENCH);
        collator.setStrength(Collator.PRIMARY);
        float n1=0,n2=0,n3=0,n4=0,n5=0,n6=0,n7=0;
        for(int i=0;i<list_1.size();i++){
            if(collator.compare(list_1.get(i).getExpensesType(),"Thực phẩm & Đồ uống")==0){
                n1+= (float) list_1.get(i).getAmountSpent();
            } else if (collator.compare(list_1.get(i).getExpensesType(),"Sức khỏe")==0) {
                n2+= (float) list_1.get(i).getAmountSpent();
            } else if (collator.compare(list_1.get(i).getExpensesType(),"Chi phí nhà ở")==0) {
                n3+= (float) list_1.get(i).getAmountSpent();
            }else if (collator.compare(list_1.get(i).getExpensesType(),"Đầu tư")==0) {
                n4+= (float) list_1.get(i).getAmountSpent();
            }else if (collator.compare(list_1.get(i).getExpensesType(),"Chi phí đi lại")==0) {
                n5+= (float) list_1.get(i).getAmountSpent();
            }else if (collator.compare(list_1.get(i).getExpensesType(),"Du lịch")==0) {
                n6+= (float) list_1.get(i).getAmountSpent();
            }else if (collator.compare(list_1.get(i).getExpensesType(),"Chi phí khác")==0) {
                Log.e("check dữ liệu chi phí khác",n7+"");
                n7+= (float) list_1.get(i).getAmountSpent();
            }

        }

        ArrayList<PieEntry> dataVals = new ArrayList<>();
        dataVals.add(new PieEntry(n1,"Thực phẩm & Đồ uống"));
        dataVals.add(new PieEntry(n2,"Sức khỏe"));
        dataVals.add(new PieEntry(n3,"Chi phí nhà ở"));
        dataVals.add(new PieEntry(n4,"Đầu tư"));
        dataVals.add(new PieEntry(n5,"Chi phí đi lại"));
        dataVals.add(new PieEntry(n6,"Du lịch"));
        dataVals.add(new PieEntry(n7,"Chi phí khác"));



        //Nhập dữ liệu cho biểu đồ thống kê tháng //


        monthlyPiechart = binding.monthlyPieChart;
        PieDataSet piedataset3 = new PieDataSet(dataVals,"");
        piedataset3.setColors(colorClassArray);
        PieData pieData3 = new PieData(piedataset3);
        monthlyPiechart.setData(pieData3);
        monthlyPiechart.setCenterTextSize(25);
        monthlyPiechart.setUsePercentValues(true);
        monthlyPiechart.setCenterText("monthly expense");
        monthlyPiechart.invalidate();



        NumberFormat numberFormat = NumberFormat.getInstance();
        ArrayList<String> list3 = new ArrayList<>();
        list3.add("Thực phẩm & Đồ uống - "+numberFormat.format(n1)+" VND");
        list3.add("Sức khỏe - "+numberFormat.format(n2)+" VND");
        list3.add("Chi phí nhà ở - "+numberFormat.format(n3)+" VND");
        list3.add("Đầu tư - "+numberFormat.format(n4)+" VND");
        list3.add("Chi phí đi lại - "+numberFormat.format(n5)+" VND");
        list3.add("Du lịch - "+numberFormat.format(n6)+" VND");
        list3.add("Chi phí khác - "+numberFormat.format(n7)+" VND");
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list3);
        monthlyList = binding.listMonthlyExpense;
        monthlyList.setAdapter(adapter3);
        adapter3.notifyDataSetChanged();

    }




//hiển thị thống kê theo năm
    private void showYearPickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                try {
                    Date date = new Date(year-1900,month,dayOfMonth);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = df.format(date);
                    Date dates = df.parse(dateString);
                    SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy");
                    String months = monthFormat.format(date);
                    yearlydata1(months);

                }
                catch (Exception e){

                }
            }
        }, nam, thang, ngay);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setTitle("Chọn thời gian");
        dialog.show();
    }

    private void yearlydata1(String year){
        displayYear.setText("Thống kê theo năm: "+year);
        PieChart  yearlyPiechart;
        ListView yearlyList;
        databaseHelper= new DatabaseHelper(getActivity());

        UserObject userObject= new UserObject();
        userObject=getDataUserName();
        //danh sách các loại chi tiêu trong một tháng
        List<ExpensesObject>  list_1= databaseHelper.getExpensesObjectOfYear(userObject.getUserID(),year);

        Collator collator = Collator.getInstance(Locale.FRENCH);
        collator.setStrength(Collator.PRIMARY);
        float n1=0,n2=0,n3=0,n4=0,n5=0,n6=0,n7=0;
        for(int i=0;i<list_1.size();i++){
            if(collator.compare(list_1.get(i).getExpensesType(),"Thực phẩm & Đồ uống")==0){
                n1+= (float) list_1.get(i).getAmountSpent();
            } else if (collator.compare(list_1.get(i).getExpensesType(),"Sức khỏe")==0) {
                n2+= (float) list_1.get(i).getAmountSpent();
            } else if (collator.compare(list_1.get(i).getExpensesType(),"Chi phí nhà ở")==0) {
                n3+= (float) list_1.get(i).getAmountSpent();
            }else if (collator.compare(list_1.get(i).getExpensesType(),"Đầu tư")==0) {
                n4+= (float) list_1.get(i).getAmountSpent();
            }else if (collator.compare(list_1.get(i).getExpensesType(),"Chi phí đi lại")==0) {
                n5+= (float) list_1.get(i).getAmountSpent();
            }else if (collator.compare(list_1.get(i).getExpensesType(),"Du lịch")==0) {
                n6+= (float) list_1.get(i).getAmountSpent();
            }else if (collator.compare(list_1.get(i).getExpensesType(),"Chi phí khác")==0) {
                Log.e("check dữ liệu chi phí khác",n7+"");
                n7+= (float) list_1.get(i).getAmountSpent();
            }

        }

        ArrayList<PieEntry> dataVals = new ArrayList<>();
        dataVals.add(new PieEntry(n1,"Thực phẩm & Đồ uống"));
        dataVals.add(new PieEntry(n2,"Sức khỏe"));
        dataVals.add(new PieEntry(n3,"Chi phí nhà ở"));
        dataVals.add(new PieEntry(n4,"Đầu tư"));
        dataVals.add(new PieEntry(n5,"Chi phí đi lại"));
        dataVals.add(new PieEntry(n6,"Du lịch"));
        dataVals.add(new PieEntry(n7,"Chi phí khác"));



        ////////////////////////////
        //Nhập dữ liệu cho biểu đồ thống kê năm //
        yearlyPiechart = binding.yearlyPieChart;
        PieDataSet piedataset4 = new PieDataSet(dataVals,"");
        piedataset4.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData4 = new PieData(piedataset4);
        yearlyPiechart.setData(pieData4);
        yearlyPiechart.setCenterTextSize(25);
        yearlyPiechart.setUsePercentValues(true);
        yearlyPiechart.setCenterText("yearly expense");
        yearlyPiechart.invalidate();


        NumberFormat numberFormat = NumberFormat.getInstance();
        ArrayList<String> list4 = new ArrayList<>();
        list4.add("Thực phẩm & Đồ uống - "+numberFormat.format(n1)+" VND");
        list4.add("Sức khỏe - "+numberFormat.format(n2)+" VND");
        list4.add("Chi phí nhà ở - "+numberFormat.format(n3)+" VND");
        list4.add("Đầu tư - "+numberFormat.format(n4)+" VND");
        list4.add("Chi phí đi lại - "+numberFormat.format(n5)+" VND");
        list4.add("Du lịch - "+numberFormat.format(n6)+" VND");
        list4.add("Chi phí khác - "+numberFormat.format(n7)+" VND");

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list4);
        yearlyList = binding.listYearlyExpense;
        yearlyList.setAdapter(adapter4);
        adapter4.notifyDataSetChanged();

=======
    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Xử lý sự kiện chọn ngày tháng năm ở đây
                        TextView displayDate = binding.displayDate;
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        displayDate.setText("Selected Date: " + selectedDate);
                        Toast.makeText(getActivity(), "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
                    }
                },
                currentYear,
                currentMonth,
                currentDay);

        datePickerDialog.show();
    }
    private void showMonthPickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Xử lý sự kiện chọn ngày tháng năm ở đây
                        TextView displayDate = binding.displayMonth;
                        String selectedDate = (monthOfYear + 1) + "/" + year;
                        displayDate.setText("Selected Month: " + selectedDate);
                        Toast.makeText(getActivity(), "Selected Month: " + selectedDate, Toast.LENGTH_SHORT).show();
                    }
                },
                currentYear,
                currentMonth,
                currentDay);

        datePickerDialog.show();
    }
    private void showYearPickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Xử lý sự kiện chọn ngày tháng năm ở đây
                        TextView displayDate = binding.displayMonth;
                        String selectedDate = "" + year;
                        displayDate.setText("Selected Year: " + selectedDate);
                        Toast.makeText(getActivity(), "Selected Year: " + selectedDate, Toast.LENGTH_SHORT).show();
                    }
                },
                currentYear,
                currentMonth,
                currentDay);

        datePickerDialog.show();
>>>>>>> 3a9ede9f57b692f2210c5863c731435fd19a2fab
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}