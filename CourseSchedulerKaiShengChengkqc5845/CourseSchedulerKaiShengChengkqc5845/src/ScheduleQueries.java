/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author wo4mo
 */
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getWaitListedStudentsByClass;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static ResultSet resultSet;
    private static int count;
    
    public static void addScheduleEntry(ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection
                .prepareStatement("insert into app.schedule "
                        + "(semester,coursecode,studentID,status,timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1,entry.getSemester());
            addScheduleEntry.setString(2,entry.getCourseCode());
            addScheduleEntry.setString(3,entry.getStudentID());
            addScheduleEntry.setString(4,entry.getStatus());
            addScheduleEntry.setTimestamp(5,entry.getTimestamp());
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> studentSchedule = new ArrayList<ScheduleEntry>();
        
        try
        {
            getScheduleByStudent = connection.
                prepareStatement("select semester, coursecode, studentid, status, timestamp "
                + "from app.schedule where semester = ? and studentID = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                studentSchedule
                    .add(new ScheduleEntry(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentSchedule; 
    }
    
    public static int getScheduledStudentCount(String semester,String courseCode){
        connection = DBConnection.getConnection();
        count = 0;
        try
        {
            getScheduledStudentCount = connection
                .prepareStatement("select count(studentID) "
                + "from app.schedule where semester = ? and courseCode = ?");
            getScheduledStudentCount.setString(1, semester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
            while(resultSet.next())
            {
                count = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return count;
    }
    
    public static ArrayList<ScheduleEntry> getWaitListedStudentsByClass(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> studentSchedule = new ArrayList<ScheduleEntry>();
        
        try
        {
            getWaitListedStudentsByClass = connection.
                prepareStatement("select semester, coursecode, studentid, status, timestamp "
                + "from app.schedule where semester = ? and coursecode = ? and status = ?");
            getWaitListedStudentsByClass.setString(1, semester);
            getWaitListedStudentsByClass.setString(2, courseCode);
            getWaitListedStudentsByClass.setString(3, "w");
            resultSet = getWaitListedStudentsByClass.executeQuery();
            
            while(resultSet.next())
            {
                studentSchedule
                    .add(new ScheduleEntry(resultSet.getString(1),resultSet.getString(2),
                    resultSet.getString(3),resultSet.getString(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return studentSchedule;   
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode)
    {
        connection= DBConnection.getConnection();
        try
        {
            dropStudentScheduleByCourse = connection.
                prepareStatement("delete from app.schedule where semester = ? and studentID = ? and courseCode = ?");
            dropStudentScheduleByCourse.setString(1,semester);
            dropStudentScheduleByCourse.setString(2,studentID);
            dropStudentScheduleByCourse.setString(3,courseCode);
            dropStudentScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode)
    {
        connection= DBConnection.getConnection();
        try
        {
            dropStudentScheduleByCourse = connection.
                prepareStatement("delete from app.schedule where semester = ? and courseCode = ?");
            dropStudentScheduleByCourse.setString(1,semester);
            dropStudentScheduleByCourse.setString(2,courseCode);
            dropStudentScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
    
    public static void updateScheduleEntry(ScheduleEntry entry)
    {
        connection= DBConnection.getConnection();
        try
        {
            dropStudentScheduleByCourse = connection.
                prepareStatement("update app.schedule set status = 's' where semester = ? and courseCode = ? and studentid = ?");
            dropStudentScheduleByCourse.setString(1,entry.getSemester());
            dropStudentScheduleByCourse.setString(2,entry.getCourseCode());
            dropStudentScheduleByCourse.setString(3,entry.getStudentID());
            dropStudentScheduleByCourse.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
}
