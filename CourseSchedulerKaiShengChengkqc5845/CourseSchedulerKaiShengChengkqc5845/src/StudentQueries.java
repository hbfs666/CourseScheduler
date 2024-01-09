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
public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement dropStudent;
    private static PreparedStatement getAllStudents;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.
                prepareStatement("insert into app.student (studentid,firstname,lastname) values (?,?,?)");
            addStudent.setString(1,student.getStudentID());
            addStudent.setString(2,student.getFirstName());
            addStudent.setString(3,student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection= DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try
        {
            getAllStudents = connection
                    .prepareStatement("select studentid, firstname, lastname from app.student");
            resultSet = getAllStudents.executeQuery();
            
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
    
    public static StudentEntry getStudent(String studentID)
    {
        connection= DBConnection.getConnection();
        try
        {
            getAllStudents = connection.prepareStatement("select studentid, firstname, lastname from app.student where studentid = ?");
            getAllStudents.setString(1, studentID);
            resultSet = getAllStudents.executeQuery();
            
            if (resultSet.next()) { 
                return new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
            } else {
                return null; 
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            return null;
        }
    }
    
    public static void dropStudent(String studentID)
    {
        connection= DBConnection.getConnection();
        try
        {
            dropStudent = connection.
                prepareStatement("delete from app.student where studentID = ? ");
            dropStudent.setString(1,studentID);
            dropStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        } 
    }
    
    
    
}
