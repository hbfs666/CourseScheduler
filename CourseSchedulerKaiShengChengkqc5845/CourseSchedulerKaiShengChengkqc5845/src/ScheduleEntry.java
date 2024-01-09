/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Timestamp;
/**
 *
 * @author wo4mo
 */
public class ScheduleEntry {
    private String semester;
    private String studentID;
    private String courseCode;
    private String status;
    private Timestamp timeStamp;

    public ScheduleEntry(String semester, String courseCode, String studentID, String status, Timestamp timestamp) {
        this.semester = semester;
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.status = status;
        this.timeStamp = timestamp;
    }

    public String getSemester() {
        return semester;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getTimestamp() {
        return timeStamp;
    }
    
    
}
