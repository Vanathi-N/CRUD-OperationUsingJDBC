package com.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class EmployeeDao {

    static Connection con;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedet", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        EmployeeDao dao = new EmployeeDao();
        
        String photoPath = "C:\\Users\\vanathi\\Downloads\\Captured20231018180356197.jpg";
        String photoPath1 = "C:\\Users\\vanathi\\OneDrive\\Pictures\\736462111.png";
        String photoPath2 = "C:\\Users\\vanathi\\OneDrive\\Pictures\\7364622222.png";
        String photoPath3 = "C:\\Users\\vanathi\\OneDrive\\Pictures\\Fate Samurai Remnant 5.jpg";
        String photoPath4 = "C:\\Users\\vanathi\\OneDrive\\Pictures\\Fate Samurai Remnant 55555555.jpg";

        dao.saveEmployee(1, "Rani", "KTM", "IT", "FullDeveloper", "101 East Street KTM", Date.valueOf("2024-02-01"), Date.valueOf("2004-05-01"), 1569700.0, photoPath);

        dao.saveEmployee(2, "Nattu", "VKPM", "AGRI", "Engineer", "57 Gandhi Street VKPM", Date.valueOf("2024-05-01"), Date.valueOf("2004-01-19"), 156600.0, photoPath1);
        dao.saveEmployee(3, "Durai", "KLM", "ECE", "Engineer", "75 Vaan Street KLM", Date.valueOf("2024-01-10"), Date.valueOf("2004-05-28"), 156000.0, photoPath2);
        dao.saveEmployee(4, "Mani", "ODC", "Sales", "SalesExecutive", "35/101 West Street ODC", Date.valueOf("2024-05-30"), Date.valueOf("2004-03-01"), 156700.0, photoPath3);
        dao.saveEmployee(5, "Ram", "Chennai", "Sales", "SalesAdmin", "RP Street ODC", Date.valueOf("2024-05-30"), Date.valueOf("2003-03-01"), 1567700.0, photoPath4);
      
        dao.getEmployee(3);
        dao.getAllEmployees();
        dao.updateSalary(1, 750500.0);
        dao.deleteEmployee(2);
    }

    // Method to save an employee
    public void saveEmployee(int id, String ename, String city, String dept, String dest, String address, Date doj, Date dob, double salary, String photoPath) {
        String sql = "INSERT INTO employee (id, ename, city, dept, dest, address, doj, dob, salary, photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pst = con.prepareStatement(sql);
             FileInputStream photoStream = new FileInputStream(photoPath)) {

            pst.setInt(1, id);
            pst.setString(2, ename);
            pst.setString(3, city);
            pst.setString(4, dept);
            pst.setString(5, dest);
            pst.setString(6, address);
            pst.setDate(7, doj);
            pst.setDate(8, dob);
            pst.setDouble(9, salary);
            pst.setBlob(10, photoStream);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.err.println("Photo file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading photo file: " + e.getMessage());
        }
    }
    // Method to get an employee by id
    public void getEmployee(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("ename"));
                System.out.println("City: " + rs.getString("city"));
                System.out.println("Dept: " + rs.getString("dept"));
                System.out.println("Designation: " + rs.getString("dest"));
                System.out.println("DOJ: " + rs.getDate("doj"));
                System.out.println("DOB: " + rs.getDate("dob"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("Salary: " + rs.getDouble("salary"));
                Blob photo = rs.getBlob("photo");
                if (photo != null) {
                    try (InputStream photoStream = photo.getBinaryStream()) {
						byte[] arr=photoStream.readAllBytes();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all employees
    public void getAllEmployees() {
        String sql = "SELECT * FROM employee";

        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("ename"));
                System.out.println("City: " + rs.getString("city"));
                System.out.println("Dept: " + rs.getString("dept"));
                System.out.println("Designation: " + rs.getString("dest"));
                System.out.println("DOJ: " + rs.getDate("doj"));
                System.out.println("DOB: " + rs.getDate("dob"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("Salary: " + rs.getDouble("salary"));
                // Handle photo as needed
                Blob photo = rs.getBlob("photo");
                if (photo != null) {
                    InputStream photoStream = photo.getBinaryStream();
                    // Here you can save the photo to a file or process it as needed
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update salary
    public void updateSalary(int id, double sal) {
        String sql = "UPDATE employee SET salary = ? WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setDouble(1, sal);
            pst.setInt(2, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete an employee
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employee WHERE id = ?";

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
