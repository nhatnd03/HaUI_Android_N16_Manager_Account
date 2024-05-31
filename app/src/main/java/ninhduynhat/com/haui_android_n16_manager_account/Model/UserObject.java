package ninhduynhat.com.haui_android_n16_manager_account.Model;

public class UserObject {

    private int UserID;
    private String UserName;
    private String Password;
    private String PhoneNumber;
    private double LivingExpenses;
    private double MoneyForStudying;
    private double DebtMoney;

    public UserObject(){

    }

    public UserObject(int userID, String userName, String password, String phoneNumber, double livingExpenses, double moneyForStudying, double debtMoney) {
        UserID = userID;
        UserName = userName;
        Password = password;
        PhoneNumber = phoneNumber;
        LivingExpenses = livingExpenses;
        MoneyForStudying = moneyForStudying;
        DebtMoney = debtMoney;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public double getLivingExpenses() {
        return LivingExpenses;
    }

    public void setLivingExpenses(double livingExpenses) {
        LivingExpenses = livingExpenses;
    }

    public double getMoneyForStudying() {
        return MoneyForStudying;
    }

    public void setMoneyForStudying(double moneyForStudying) {
        MoneyForStudying = moneyForStudying;
    }

    public double getDebtMoney() {
        return DebtMoney;
    }

    public void setDebtMoney(double debtMoney) {
        DebtMoney = debtMoney;
    }
}
