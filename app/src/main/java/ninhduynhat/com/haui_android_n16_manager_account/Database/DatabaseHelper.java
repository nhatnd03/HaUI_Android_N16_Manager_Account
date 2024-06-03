package ninhduynhat.com.haui_android_n16_manager_account.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ninhduynhat.com.haui_android_n16_manager_account.Model.UserObject;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QUANLYTAIKHOAN.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE USER (" +
                "UserID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Username TEXT, " +
                "Password TEXT, " +
                "PhoneNumber TEXT, " +
                "LivingExpenses REAL, " +
                "MoneyForStudying REAL, " +
                "DebtMoney REAL" +
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
                "PlanName TEXT, " +
                "AmountNeeded REAL, " +
                "AmountReached REAL, " +
                "Timeline TEXT, " +
                "PlanType TEXT" +
                ")";

        String createMonHocTable = "CREATE TABLE SUBJECT (" +
                "SubjectId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "SubjectName TEXT, " +
                "StudyCredits INTEGER, " +
                "Semester INTEGER" +
                ")";

        String createThanhToanTable = "CREATE TABLE PayingTuition (" +
                "PayingTuitionId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "SubjectID INTEGER, " +
                "SubjectName TEXT, " +
                "TheAmount REAL, " +
                "IsPaided INTEGER, " +
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

    // Phương thức chèn dữ liệu vào bảng PLANNING
    public long insertPlanning(String planName, double amountNeeded, double amountReached, String timeline, String planType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PlanName", planName);
        values.put("AmountNeeded", amountNeeded);
        values.put("AmountReached", amountReached);
        values.put("Timeline", timeline);
        values.put("PlanType", planType);
        return db.insert("PLANNING", null, values);
    }

    // Phương thức chèn dữ liệu vào bảng SUBJECT
    public long insertSubject(String subjectName, int studyCredits, int semester) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SubjectName", subjectName);
        values.put("StudyCredits", studyCredits);
        values.put("Semester", semester);
        return db.insert("SUBJECT", null, values);
    }

    // Phương thức chèn dữ liệu vào bảng PayingTuition
    public long insertPayingTuition(int subjectId, String subjectName, double theAmount, int isPaided) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("SubjectID", subjectId);
        values.put("SubjectName", subjectName);
        values.put("TheAmount", theAmount);
        values.put("IsPaided", isPaided);
        return db.insert("PayingTuition", null, values);
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

    public UserObject getUser(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("USER",
                new String[]{"UserID", "Username", "Password", "PhoneNumber", "LivingExpenses", "MoneyForStudying", "DebtMoney"},
                "UserID" + "=?",
                new String[]{String.valueOf(userId)},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            UserObject user = new UserObject(
                    cursor.getInt(cursor.getColumnIndexOrThrow("UserID")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Username")),
                    cursor.getString(cursor.getColumnIndexOrThrow("Password")),
                    cursor.getString(cursor.getColumnIndexOrThrow("PhoneNumber")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("LivingExpenses")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("MoneyForStudying")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("DebtMoney"))
            );
            cursor.close();
            return user;
        } else {
            return null;
        }
    }
}
