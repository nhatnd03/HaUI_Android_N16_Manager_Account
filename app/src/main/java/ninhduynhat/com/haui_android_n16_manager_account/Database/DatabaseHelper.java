package ninhduynhat.com.haui_android_n16_manager_account.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ninhduynhat.com.haui_android_n16_manager_account.Model.ExpensesObject;
import ninhduynhat.com.haui_android_n16_manager_account.Model.PayingTuitionObject;
import ninhduynhat.com.haui_android_n16_manager_account.Model.PlanObject;
import ninhduynhat.com.haui_android_n16_manager_account.Model.SubjectObject;
import ninhduynhat.com.haui_android_n16_manager_account.Model.UserObject;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QUANLYTAIKHOAN.db";
    private static final int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static DatabaseHelper instance;
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE USER (" +
                "UserID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Username TEXT, " +
                "Password TEXT, " +
                "Fullname TEXT, " +
                "PhoneNumber TEXT, " +
                "LivingExpenses REAL, " +
                "MoneyForStudying REAL, " +
                "DebtMoney REAL," +
                "Image TEXT" +
                ")";

        String createKhoanChiTable = "CREATE TABLE EXPENSES (" +
                "ExpensesID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "UserID INTEGER, " +
                "ExpensesType TEXT, " +
                "AmountSpent REAL, " +
                "DateSpent TEXT, " +
                "Description TEXT, " +
                "FOREIGN KEY(UserID) REFERENCES USER(UserID)" +
                ")";

        String createKeHoachTable = "CREATE TABLE PLANNING (" +
                "PlanId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "UserID INTEGER, " +
                "PlanName TEXT, " +
                "AmountNeeded REAL, " +
                "AmountReached REAL, " +
                "Timeline TEXT, " +
                "PlanType TEXT, " +
                "FOREIGN KEY(UserID) REFERENCES USER(UserID)" +
                ")";

        String createMonHocTable = "CREATE TABLE SUBJECT (" +
                "SubjectId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "UserID INTEGER, " +
                "SubjectName TEXT, " +
                "StudyCredits INTEGER, " +
                "Semester INTEGER, " +
                "FOREIGN KEY(UserID) REFERENCES USER(UserID)" +
                ")";

        String createThanhToanTable = "CREATE TABLE PayingTuition (" +
                "PayingTuitionId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "UserID INTEGER, " +
                "SubjectID INTEGER, " +
                "SubjectName TEXT, " +
                "TheAmount REAL, " +
                "IsPaided INTEGER, " +
                "FOREIGN KEY(UserID) REFERENCES USER(UserID)," +
                "FOREIGN KEY(SubjectId) REFERENCES SUBJECT(SubjectId)" +
                ")";

        db.execSQL(createUserTable);
        db.execSQL(createKhoanChiTable);
        db.execSQL(createKeHoachTable);
        db.execSQL(createMonHocTable);
        db.execSQL(createThanhToanTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.execSQL("DROP TABLE IF EXISTS EXPENSES");
        db.execSQL("DROP TABLE IF EXISTS PLANNING");
        db.execSQL("DROP TABLE IF EXISTS SUBJECT");
        db.execSQL("DROP TABLE IF EXISTS PayingTuition");
        onCreate(db);
    }
    // Phương thức chèn dữ liệu vào bảng USER
    public long insertUser(String username, String password, String phoneNumber, double livingExpenses, double moneyForStudying, double debtMoney) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", username);
        values.put("Password", password);
        values.put("PhoneNumber", phoneNumber);
        values.put("LivingExpenses", livingExpenses);
        values.put("MoneyForStudying", moneyForStudying);
        values.put("DebtMoney", debtMoney);
        return db.insert("USER", null, values);
    }

    public UserObject getUserById(int userid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("USER", null, "UserID = ?", new String[]{String.valueOf(userid)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int userID = cursor.getInt(cursor.getColumnIndexOrThrow("UserID"));
            String username = cursor.getString(cursor.getColumnIndexOrThrow("Username"));
            String password = cursor.getString(cursor.getColumnIndexOrThrow("Password"));
            String fullname = cursor.getString(cursor.getColumnIndexOrThrow("Fullname"));
            String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("PhoneNumber"));
            double livingExpenses = cursor.getDouble(cursor.getColumnIndexOrThrow("LivingExpenses"));
            double moneyForStudying = cursor.getDouble(cursor.getColumnIndexOrThrow("MoneyForStudying"));
            double debtMoney = cursor.getDouble(cursor.getColumnIndexOrThrow("DebtMoney"));
            String image = cursor.getString(cursor.getColumnIndexOrThrow("Image"));

            cursor.close();
            return new UserObject(userID, username, password, fullname, phoneNumber, livingExpenses, moneyForStudying, debtMoney, image);
        }

        return null;
    }




    public UserObject getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER WHERE Username = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            return new UserObject(
                    cursor.getInt(cursor.getColumnIndexOrThrow("UserID")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Username")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Password")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Fullname")),
                    cursor.getString(cursor.getColumnIndexOrThrow("PhoneNumber")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("LivingExpenses")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("MoneyForStudying")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("DebtMoney")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Image"))
            );
        }
        cursor.close();
        return null;
    }


    //lấy dữ liệu cho màn hình home
    public UserObject getUserByUsername_Home(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USER WHERE Username = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            return new UserObject(
                    Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("UserID"))) ,
                    cursor.getString(cursor.getColumnIndexOrThrow("Fullname")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("LivingExpenses")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Image"))
            );
        }
        cursor.close();
        return null;
    }

    //lưu dữ liệu ảnh cho user
    public void updateImageUser(int userId, String stringImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Image", stringImage);
        db.update("USER", values, "UserID = ?", new String[]{String.valueOf(userId)});
        db.close();
    }

    //cập nhật dữ liệu cho bảng khoản chi
    public void updateKhoanChi(int expensesId, String type,double gia,String ngay,String mota) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ExpensesType", type);
        values.put("AmountSpent", gia);
        values.put("DateSpent", ngay);
        values.put("Description", mota);
        db.update("EXPENSES", values, "ExpensesID = ?", new String[]{String.valueOf(expensesId)});
        db.close();
    }

    //ExpensesType AmountSpent DateSpent Description
    //lấy dữ liệu theo id của bảng expenses
    public ExpensesObject getExpensesByIdExpense(int idexpense) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT ExpensesType,AmountSpent,DateSpent,Description" +
                " FROM EXPENSES WHERE ExpensesID = ?", new String[]{String.valueOf(idexpense)});
        if (cursor.moveToFirst()) {
            return new ExpensesObject(
                    cursor.getString(cursor.getColumnIndexOrThrow("ExpensesType")),
                    Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("AmountSpent"))),
                    cursor.getString(cursor.getColumnIndexOrThrow("DateSpent")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Description"))
            );
        }
        cursor.close();
        return null;
    }



    //cập nhật lại số tiền chi
    public void update_LivingExpenses(int userId, double tienmua) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("LivingExpenses", tienmua);
        db.update("USER", values, "UserID = ?", new String[]{String.valueOf(userId)});
        db.close();
    }




    //thêm một khoản chi
    public void insertUser_KhoanChi(int UserID, String ExpensesType, double AmountSpent,String DateSpent,String Description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserID", UserID);
        values.put("ExpensesType", ExpensesType);
        values.put("AmountSpent", AmountSpent);
        values.put("DateSpent",DateSpent);
        values.put("Description",Description);
        long newRowId = db.insert("EXPENSES", null, values);
        if (newRowId != -1) {
           Log.e("thêm khoản chi","thành công");
        } else {
            Log.e("thêm khoản chi","không được");
            // Thêm dữ liệu không thành công
        }
        db.close();
    }


    //hàm lấy dữ liệu chi phí theo ngày cho màn hình home
    public List<ExpensesObject> getExpensesObjectOfDate(int userId,String dateToday) {
        List<ExpensesObject> expenses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Thực hiện câu truy vấn SELECT với khóa ngoại
        String query = "SELECT EXPENSES.ExpensesID, EXPENSES.ExpensesType,EXPENSES.AmountSpent,EXPENSES.DateSpent,EXPENSES.Description" +
                " FROM EXPENSES " +  "WHERE UserID = ? AND DateSpent =?";
        String[] selectionArgs = {String.valueOf(userId),dateToday};
        Cursor cursor = db.rawQuery(query,selectionArgs);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int ExpensesID=Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("ExpensesID")));
                String ExpensesType = cursor.getString(cursor.getColumnIndexOrThrow("ExpensesType"));
                double AmountSpent= Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("AmountSpent")));
                String DateSpent = cursor.getString(cursor.getColumnIndexOrThrow("DateSpent"));
                String Description=cursor.getString(cursor.getColumnIndexOrThrow("Description"));
                ExpensesObject expense = new ExpensesObject( ExpensesID,ExpensesType,AmountSpent, DateSpent,Description);
                expenses.add(expense);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return expenses;
    }
    //lấy dữ liệu chi phí theo tháng
    public List<ExpensesObject> getExpensesObjectOfMonth(int userId,String monthDaily) {
        List<ExpensesObject> expenses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Thực hiện câu truy vấn SELECT với khóa ngoại
        String query = "SELECT  EXPENSES.ExpensesType,EXPENSES.AmountSpent" +
                " FROM EXPENSES " +  "WHERE UserID = ? AND SUBSTR(DateSpent, 4, 7) =? "; //strftime('%m', your_date_column) = '06',AND SUBSTR(DateSpent, 7, 4) ='2024'
        String[] selectionArgs = {String.valueOf(userId),monthDaily};
        Cursor cursor = db.rawQuery(query,selectionArgs);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String ExpensesType = cursor.getString(cursor.getColumnIndexOrThrow("ExpensesType"));
                double AmountSpent= Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("AmountSpent")));
                ExpensesObject expense = new ExpensesObject( ExpensesType,AmountSpent);
                expenses.add(expense);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return expenses;
    }
//lấy dữ liệu chi phí theo năm
public List<ExpensesObject> getExpensesObjectOfYear(int userId,String year) {
    List<ExpensesObject> expenses = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();
    // Thực hiện câu truy vấn SELECT với khóa ngoại
    String query = "SELECT  EXPENSES.ExpensesType,EXPENSES.AmountSpent" +
            " FROM EXPENSES " +  "WHERE UserID = ?  AND SUBSTR(DateSpent, 7, 4) =?"; //strftime('%m', your_date_column) = '06'
    String[] selectionArgs = {String.valueOf(userId),year};
    Cursor cursor = db.rawQuery(query,selectionArgs);
    if (cursor != null && cursor.moveToFirst()) {
        do {
            String ExpensesType = cursor.getString(cursor.getColumnIndexOrThrow("ExpensesType"));
            double AmountSpent= Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("AmountSpent")));
            ExpensesObject expense = new ExpensesObject( ExpensesType,AmountSpent);
            expenses.add(expense);
        } while (cursor.moveToNext());
        cursor.close();
    }
    Log.e("check số lượng bên DB",expenses.size()+"");
    return expenses;
}






    // Phương thức kiểm tra xem chỉ tài khoản có bị trùng lặp hay không
    public boolean isUsernameDuplicated(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM USER WHERE Username = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean isDuplicated = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isDuplicated;
    }
    public long insertUser_sign(String username, String password,String fullname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", username);
        values.put("Password", password);
        values.put("Fullname", fullname);
        return db.insert("USER", null, values);
    }



    //hàm lấy dữ liệu toàn bộ chi phí
    public List<ExpensesObject> getExpensesObject(int userId) {
        List<ExpensesObject> expenses = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        // Thực hiện câu truy vấn SELECT với khóa ngoại
        String query = "SELECT EXPENSES.ExpensesID, EXPENSES.ExpensesType,EXPENSES.AmountSpent,EXPENSES.DateSpent,EXPENSES.Description" +
                " FROM EXPENSES " +  "WHERE UserID = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        Cursor cursor = db.rawQuery(query,selectionArgs);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int ExpensesID = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("ExpensesID")));
                String ExpensesType = cursor.getString(cursor.getColumnIndexOrThrow("ExpensesType"));
                double AmountSpent= Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow("AmountSpent")));
                String DateSpent = cursor.getString(cursor.getColumnIndexOrThrow("DateSpent"));
                String Description=cursor.getString(cursor.getColumnIndexOrThrow("Description"));

                ExpensesObject expense = new ExpensesObject( ExpensesID,ExpensesType,AmountSpent, DateSpent,Description);
                expenses.add(expense);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return expenses;
    }
    //
    // Phương thức kiểm tra xem chỉ tài khoản có bị trùng lặp hay không
    public boolean isUsernameDuplicate(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM USER WHERE Username = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username});
        boolean isDuplicated = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isDuplicated;
    }
    public long insertUser_sign_1(String username, String password,String fullname) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", username);
        values.put("Password", password);
        values.put("Fullname", fullname);
        return db.insert("USER", null, values);
    }


    //hàm check đăng nhập
    public boolean checkUserName_Password(String username,String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + "USER" + " WHERE "
                + "Username" + " = ? AND " + "Password" + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{username, pass});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }


    // Phương thức chèn dữ liệu vào bảng EXPENSES
    public long insertExpenses(int userId, String expensesType, double amountSpent, String dateSpent, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserID", userId);
        values.put("ExpensesType", expensesType);
        values.put("AmountSpent", amountSpent);
        values.put("DateSpent", dateSpent);
        values.put("Description", description);
        return db.insert("EXPENSES", null, values);
    }

    // them
    public void addPlan(PlanObject plan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("PlanName", plan.getPlanName());
        cv.put("UserID", UserObject.getInstance().getUserID());
        cv.put("AmountNeeded", plan.getAmoutNeeded());
        cv.put("AmountReached", plan.getAmoutReached());
        cv.put("Timeline", plan.getTimeLine());
        cv.put("PlanType", plan.getPlanType());
        db.insert("PLANNING", null, cv);
        db.close();

    }

    // sua
    public void updatePlan(PlanObject plan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("PlanName", plan.getPlanName());
        cv.put("AmountNeeded", plan.getAmoutNeeded());
        cv.put("AmountReached", plan.getAmoutReached());
        cv.put("Timeline", plan.getTimeLine());
        cv.put("PlanType", plan.getPlanType());
        String whereClause = "PlanId" + " = ?";
        String[] whereArgs = {String.valueOf(plan.getPlanId())};
        db.update("PLANNING", cv, whereClause, whereArgs);

    }

    // xoa
    public void deletePlan(PlanObject plan){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "PlanId" + " = ?";
        String[] whereArgs = {String.valueOf(plan.getPlanId())};
        db.delete("PLANNING", whereClause, whereArgs);

    }

    //
    public ArrayList<PlanObject> fetchPlanByType(String type){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM" + "PLANNING" + "WHERE" + "UserID"+ " = " + "'" + UserObject.getInstance().getUserID()+"'" + " AND " + "PlanType" + " = " + "'"+ type + "'", null );

        ArrayList<PlanObject> plans
                = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                plans.add(new PlanObject(
                        cursor.getInt(cursor.getColumnIndexOrThrow("PlanId")),
                        //cursor.getInt(cursor.getColumnIndexOrThrow("UserId")),
                        cursor.getString(cursor.getColumnIndexOrThrow("PlanName")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("AmountNeeded")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("AmountReached")),
                        cursor.getString(cursor.getColumnIndexOrThrow("Timeline")),
                        cursor.getString(cursor.getColumnIndexOrThrow("PlanType"))
                ));
            } while (cursor.moveToNext());
        }
        Collections.reverse(plans);
        cursor.close();
        return plans;
    }
    // select toan bo du lieu
    public ArrayList<PlanObject> fetchAllPlan(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM" + "PLANNING" + "WHERE" + "UserID"+ " = " + "'" + UserObject.getInstance().getUserID()+ "'", null );

        ArrayList<PlanObject> plans
                = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                plans.add(new PlanObject(
                        cursor.getInt(cursor.getColumnIndexOrThrow("PlanId")),
                        //cursor.getInt(cursor.getColumnIndexOrThrow("UserId")),
                        cursor.getString(cursor.getColumnIndexOrThrow("PlanName")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("AmountNeeded")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("AmountReached")),
                        cursor.getString(cursor.getColumnIndexOrThrow("Timeline")),
                        cursor.getString(cursor.getColumnIndexOrThrow("PlanType"))
                ));
            } while (cursor.moveToNext());
        }
        Collections.reverse(plans);
        cursor.close();
        return plans;
    }

    // Phương thức chèn dữ liệu vào bảng SUBJECT
    public long insertSubject(int userID, String subjectName, int studyCredits, int semester) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserID", userID);
        values.put("SubjectName", subjectName);
        values.put("StudyCredits", studyCredits);
        values.put("Semester", semester);
        return db.insert("SUBJECT", null, values);
    }

    public void updateUser(UserObject user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", user.getUserName());
        values.put("Password", user.getPassword());
        values.put("PhoneNumber", user.getPhoneNumber());
        values.put("LivingExpenses", user.getLivingExpenses());
        values.put("MoneyForStudying", user.getMoneyForStudying());
        values.put("DebtMoney", user.getDebtMoney());

        db.update("USER", values, "UserID = ?", new String[]{String.valueOf(user.getUserID())});
    }


    public List<SubjectObject> getSubjectsBySemester(int semester) {
        List<SubjectObject> subjects = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("SUBJECT", null, "Semester = ?", new String[]{String.valueOf(semester)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int subjectId = cursor.getInt(cursor.getColumnIndexOrThrow("SubjectId"));
                String subjectName = cursor.getString(cursor.getColumnIndexOrThrow("SubjectName"));
                int studyCredits = cursor.getInt(cursor.getColumnIndexOrThrow("StudyCredits"));
                double amount = studyCredits * 415000;

                SubjectObject subject = new SubjectObject(subjectId, 1, subjectName, (byte) studyCredits, semester);
                subjects.add(subject);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return subjects;
    }

    public double getCreditSubjectByID(int subjectID) {
        double amount = 0;
        SubjectObject subject = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("SUBJECT", null, "SubjectID = ?", new String[]{String.valueOf(subjectID)}, null, null, null);

        if (cursor != null) {
                amount = cursor.getInt(cursor.getColumnIndexOrThrow("StudyCredits")) * 415000;
            cursor.close();
        }

        return amount;
    }

    // Phương thức chèn dữ liệu vào bảng PayingTuition
    public long insertPayingTuition(int userID, int subjectId, String subjectName, double theAmount, int isPaided) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UserID", userID);
        values.put("SubjectID", subjectId);
        values.put("SubjectName", subjectName);
        values.put("TheAmount", theAmount);
        values.put("IsPaided", isPaided);
        return db.insert("PayingTuition", null, values);
    }

    public List<PayingTuitionObject> getUnpaidTuitionList(int userId) {
        List<PayingTuitionObject> payingTuitionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PayingTuition WHERE IsPaided = 0 AND UserID = ?", new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            do {
                PayingTuitionObject payingTuition = new PayingTuitionObject(
                        cursor.getInt(cursor.getColumnIndexOrThrow("PayingTuitionId")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("UserID")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("SubjectID")),
                        cursor.getString(cursor.getColumnIndexOrThrow("SubjectName")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("TheAmount")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("IsPaided")) == 1
                );
                payingTuitionList.add(payingTuition);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return payingTuitionList;
    }
    public List<PayingTuitionObject> getPaidTuitionList(int userId) {
        List<PayingTuitionObject> payingTuitionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM PayingTuition WHERE IsPaided = 1 AND UserID = ?", new String[]{String.valueOf(userId)});
        if (cursor.moveToFirst()) {
            do {
                PayingTuitionObject payingTuition = new PayingTuitionObject(
                        cursor.getInt(cursor.getColumnIndexOrThrow("PayingTuitionId")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("SubjectID")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("UserID")),
                        cursor.getString(cursor.getColumnIndexOrThrow("SubjectName")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("TheAmount")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("IsPaided")) == 1
                );
                payingTuitionList.add(payingTuition);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return payingTuitionList;
    }


    public void updatePayingTuition(PayingTuitionObject payingTuition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("IsPaided", payingTuition.isPaided() ? 1 : 0);
        db.update("PayingTuition", values, "PayingTuitionId = ?", new String[]{String.valueOf(payingTuition.getPayingTuitionId())});
    }

    // Phương thức xóa tất cả dữ liệu từ một bảng
    public void deleteAllFromTable(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, null, null);
    }

    // Phương thức xóa bảng
    public void dropTable(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }

//    public UserObject getUser(int userId) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query("USER",
//                new String[]{"UserID", "Username", "Password", "Fullname", "PhoneNumber", "LivingExpenses", "MoneyForStudying", "DebtMoney"},
//                "UserID" + "=?",
//                new String[]{String.valueOf(userId)},
//                null, null, null, null);
//
//        if (cursor != null) {
//            cursor.moveToFirst();
//            UserObject user = new UserObject(
//                    cursor.getInt(cursor.getColumnIndexOrThrow("UserID")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("Username")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("Password")),
////                    cursor.getString(cursor.getColumnIndexOrThrow("Fullname")),
//                    cursor.getString(cursor.getColumnIndexOrThrow("PhoneNumber")),
//                    cursor.getDouble(cursor.getColumnIndexOrThrow("LivingExpenses")),
//                    cursor.getDouble(cursor.getColumnIndexOrThrow("MoneyForStudying")),
//                    cursor.getDouble(cursor.getColumnIndexOrThrow("DebtMoney"))
//            );
//            cursor.close();
//            return user;
//        } else {
//            return null;
//        }
//    }
}
