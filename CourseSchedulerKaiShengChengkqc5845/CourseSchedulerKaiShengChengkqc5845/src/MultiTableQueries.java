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
public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement getAllClassDescriptions;
    private static PreparedStatement getScheduledStudentsByClass;
    private static PreparedStatement getWaitlistedStudentsByClass;
    private static ResultSet resultSet;
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> classD = new ArrayList<ClassDescription>();
        
        try
        {
            getAllClassDescriptions = connection.
                prepareStatement("select app.class.courseCode, description, seats "
                + "from app.class, app.course "
                + "where semester = ? and app.class.courseCode = app.course.courseCode "
                + "order by app.class.courseCode");
            getAllClassDescriptions.setString(1, semester);
            resultSet = getAllClassDescriptions.executeQuery();
            
            while(resultSet.next())
            {
                classD.add(new ClassDescription(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return classD;
    }
    
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        
        try
        {
            getScheduledStudentsByClass = connection.
                    prepareStatement("select app.student.studentID, firstname, lastname " 
                    + "from app.student, app.schedule "
                    + "where semester = ? and courseCode = ? and status = ? and app.student.studentid = app.schedule.studentid "
                    + "order by app.schedule.courseCode");

            getScheduledStudentsByClass.setString(1, semester);
            getScheduledStudentsByClass.setString(2, courseCode);
            getScheduledStudentsByClass.setString(3, "s");
            resultSet = getScheduledStudentsByClass.executeQuery();
            
            while(resultSet.next())
            {
                students.add(new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
    }
    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        
        try
        {
            getWaitlistedStudentsByClass = connection.prepareStatement(
                "select app.student.studentID, firstname, lastname " +  
                "from app.student, app.schedule " +
                "where semester = ? and courseCode = ? and status = ? and app.student.studentid = app.schedule.studentid " +
                "order by app.schedule.courseCode");
            getWaitlistedStudentsByClass.setString(1, semester);
            getWaitlistedStudentsByClass.setString(2, courseCode);
            getWaitlistedStudentsByClass.setString(3, "w");
            resultSet = getWaitlistedStudentsByClass.executeQuery();
            
            while(resultSet.next())
            {
                students.add(new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
    }
}
