package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Employee;
import UTIL.ConnectionDB;

public class EmployeeDAO {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    public List<Employee> listAllEmployees() throws SQLException {
        List<Employee> listEmployee = null;


        try {
            listEmployee = new ArrayList<>();
            String sql = "SELECT * FROM Employé ;";
            connection = ConnectionDB.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int matricule = resultSet.getInt("Matricule");
                String nom = resultSet.getString("Nom");
                String prénom = resultSet.getString("Prénom");
                int département = resultSet.getInt("Département");
                Employee employee = new Employee(matricule, nom, prénom, département);
                listEmployee.add(employee);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        resultSet.close();
        statement.close();
        return listEmployee;
    }

    public boolean insertEmployee(Employee employee) throws SQLException {
        boolean flag = false;

        try {
            String sql = "INSERT INTO Employé (Matricule, Nom, Prénom,Département) VALUES "
                    +"(NULL' "+employee.getNom()+"','"+employee.getPrénom()+"','"+employee.getDépartement()+"');";
            connection = ConnectionDB.openConnection();
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            flag =true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;

    }
    public boolean deleteEmployee(int matricule) throws SQLException {
        boolean flag=false;
        try {

            String sql="DELETE FROM Employé where Matricule="+matricule + ";";
            connection =ConnectionDB.openConnection();
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            flag =true;

        }catch (SQLException ex) {
            ex.printStackTrace();
        }


        return flag;
    }


    public boolean updateEmployee(Employee employee) throws SQLException {
        boolean flag = false;
        try {

            String sql="UPDATE Employé  SET name='"+employee.getNom()+"',Département='"+employee.getDépartement()+ "',Prénom='"+employee.getPrénom()+"' where Matricule="+employee.getMatricule()+";";
            connection =ConnectionDB.openConnection();
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            flag =true;

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return flag;




    }
    public Employee getEmployee(int matricule) throws SQLException {
        Employee employee = null;
        try {
            employee = new Employee();
            String sql = "SELECT * FROM Employé WHERE Matricule = ";
            sql+= matricule + ";";
            connection = ConnectionDB.openConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String nom = resultSet.getString("Nom");
                String prénom = resultSet.getString("Prénom");
                int département = resultSet.getInt("Département");
                employee.setPrénom(prénom);
                employee.setNom(nom);
                employee.setDépartement(département);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


        resultSet.close();
        statement.close();

        return employee;
    }

}
