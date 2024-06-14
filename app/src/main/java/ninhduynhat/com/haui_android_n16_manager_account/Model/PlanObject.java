package ninhduynhat.com.haui_android_n16_manager_account.Model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class PlanObject implements Parcelable {

    private int PlanId;
    private int UserID;
    private String PlanName;
    private double AmoutNeeded;
    private double AmoutReached;
    private String TimeLine;
    private String PlanType;

    private static PlanObject instance;
    public PlanObject() {
    }

    protected PlanObject(Parcel in){
        PlanId = in.readInt();
        UserID = in.readInt();
        PlanName = in.readString();
        AmoutNeeded = in.readDouble();
        AmoutReached = in.readDouble();
        TimeLine = in.readString();
        PlanType =  in.readString();
    }

    public static final Creator<PlanObject> CREATOR = new Creator<PlanObject>() {
        public PlanObject createFromParcel(Parcel in) {
            return new PlanObject(in); }
        public PlanObject[] newArray(int size) {
            return new PlanObject[size];
        }
    };

    public static synchronized PlanObject getInstance() {
        if (instance == null) {
            instance = new PlanObject();
        }
        return instance;
    }

    public PlanObject(int planId, String planName, double amoutNeeded, double amoutReached, String timeLine, String planType) {
        PlanId = planId;
        //UserID = userID;
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

    public String getTimeLine() {
        return TimeLine;
    }

    public void setTimeLine(String timeLine) {
        TimeLine = timeLine;
    }

    public String getPlanType() {
        return PlanType;
    }

    public void setPlanType(String planType) {
        PlanType = planType;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(PlanId);
        //parcel.writeInt(UserID);
        parcel.writeString(PlanName);
        parcel.writeDouble(AmoutNeeded);
        parcel.writeDouble(AmoutReached);
        parcel.writeString(TimeLine);
        parcel.writeString(PlanType);
    }
}
