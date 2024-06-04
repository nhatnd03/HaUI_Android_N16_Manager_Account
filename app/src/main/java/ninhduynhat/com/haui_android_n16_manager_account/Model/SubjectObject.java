package ninhduynhat.com.haui_android_n16_manager_account.Model;

import android.content.ContentValues;

public class SubjectObject {

    private static final double TIENMOTTINCHI = 415000;

    private int SubjectId;
    private int UserID;
    private String SubjectName;
    private byte StudyCredits;
    private int Semester;

    public SubjectObject() {
    }

    public SubjectObject(int subjectId, int userID, String subjectName, byte studyCredits, int semester) {
        SubjectId = subjectId;
        UserID = userID;
        SubjectName = subjectName;
        StudyCredits = studyCredits;
        Semester = semester;
    }

    public int getSubjectId() {
        return SubjectId;
    }

    public void setSubjectId(int subjectId) {
        SubjectId = subjectId;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public byte getStudyCredits() {
        return StudyCredits;
    }

    public void setStudyCredits(byte studyCredits) {
        StudyCredits = studyCredits;
    }

    public int getSemester() {
        return Semester;
    }

    public void setSemester(int semester) {
        Semester = semester;
    }

    public double getAmount(){
        return getStudyCredits() * TIENMOTTINCHI;
    }

}
