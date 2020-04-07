package Controller;

import DAO.EmployeeDAO;
import Model.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends  HttpServlet{
    private static final long serialVersionUID = 1L;
    RequestDispatcher dispatcher = null;
    EmployeeDAO employeeDAO = null;

    public EmployeeServlet() {
        employeeDAO = new EmployeeDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action==null) {
            action="list";
        }
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertEmployee(request, response);
                    break;
                case "update":
                    updateEmployee(request, response);
                    break;
                case "delete":
                    deleteEmployee(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "list":
                default:
                    listEmployee(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int matricule = Integer.parseInt(request.getParameter("matricule"));
        String prenom = request.getParameter("Prénom");
        String nom = request.getParameter("Nom");
        int departement = Integer.parseInt(request.getParameter("Département"));

        Employee employee = new Employee();

        employee.setDépartement(departement);
        employee.setMatricule(matricule);
        employee.setNom(nom);
        employee.setPrénom(prenom);

        if (matricule==0) {
            try { // save
                if (employeeDAO.insertEmployee(employee)) {
                    request.setAttribute("message","C'est bon. Employé ajouté.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("message","C'est bon. Employé ajouté");
        } else { //update
            employee.setMatricule(matricule);
            try {
                if (employeeDAO.updateEmployee(employee)) {
                    request.setAttribute("message","Employé modifié.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            listEmployee(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            listEmployee(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Employee> listEmployee = employeeDAO.listAllEmployees();
        request.setAttribute("listEmployee", listEmployee);
        dispatcher = request.getRequestDispatcher("/View/EmployeeList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        dispatcher = request.getRequestDispatcher("/View/EmployeeForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int matricule = Integer.parseInt(request.getParameter("id"));
        Employee existingEmployee = employeeDAO.getEmployee(matricule);
        request.setAttribute("employee",existingEmployee);

        dispatcher = request.getRequestDispatcher("/View/EmployeeForm.jsp");
        dispatcher.forward(request, response);

    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        dispatcher = request.getRequestDispatcher("/View/EmployeeForm.jsp");

        String nom = request.getParameter("Nom");
        String prenom = request.getParameter("Prénom");
        int departement = Integer.parseInt(request.getParameter("Département"));

        Employee newEmployee = new Employee(nom, prenom, departement);
        employeeDAO.insertEmployee(newEmployee);
        response.sendRedirect("list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int matricule = Integer.parseInt(request.getParameter("id"));
        Employee existingEmployee = employeeDAO.getEmployee(matricule);
        request.setAttribute("employee",existingEmployee);
        dispatcher = request.getRequestDispatcher("/View/EmployeeForm.jsp");
        dispatcher.forward(request,response);

        response.sendRedirect("list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int matricule = Integer.parseInt(request.getParameter("id"));

        if (employeeDAO.deleteEmployee(matricule)) {
            request.setAttribute("message","Employé supprimé");
        }
        listEmployee(request,response);

    }



}
