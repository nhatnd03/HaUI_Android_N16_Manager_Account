package ninhduynhat.com.haui_android_n16_manager_account.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ninhduynhat.com.haui_android_n16_manager_account.Model.PayingTuitionObject;
import ninhduynhat.com.haui_android_n16_manager_account.Model.SubjectObject;
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
