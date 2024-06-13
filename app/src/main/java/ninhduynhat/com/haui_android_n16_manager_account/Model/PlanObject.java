package ninhduynhat.com.haui_android_n16_manager_account.Model;

import java.sql.Date;

public class PlanObject {

    private int PlanId;
    private int UserID;
    private String PlanName;
    private double AmoutNeeded;
    private double AmoutReached;
    private Date TimeLine;
    private String PlanType;

    public PlanObject() {
    }

    public PlanObject(int planId, int userID, String planName, double amoutNeeded, double amoutReached, Date timeLine, String planType) {
        PlanId = planId;
        UserID = userID;
        PlanName = planName;
        AmoutNeeded = amoutNeeded;
        AmoutReached = amoutReached;
        TimeLine = timeLine;
        PlanType = planType;
    }

    public int getPlanId() {
        return PlanId;
    }

    public void setPlanId(int planId) {
        PlanId = planId;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String planName) {
        PlanName = planName;
    }

    public double getAmoutNeeded() {
        return AmoutNeeded;
    }

    public void setAmoutNeeded(double amoutNeeded) {
        AmoutNeeded = amoutNeeded;
    }

    public double getAmoutReached() {
        return AmoutReached;
    }

    public void setAmoutReached(double amoutReached) {
        AmoutReached = amoutReached;
    }

    public Date getTimeLine() {
        return TimeLine;
    }

    public void setTimeLine(Date timeLine) {
        TimeLine = timeLine;
    }

    public String getPlanType() {
        return PlanType;
    }

    public void setPlanType(String planType) {
        PlanType = planType;
    }
}
