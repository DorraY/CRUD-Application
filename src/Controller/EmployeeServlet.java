package Controller;

import DAO.EmployeeDAO;
import Model.Employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
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

    public ArrayList<String> handleRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("text/plain");
        ArrayList<String> resultat = new ArrayList<>();
        Enumeration<String> parameterNames = req.getParameterNames();

        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            String[] paramValues = req.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                resultat.add(paramValue);
            }
        }
        return resultat;

}


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String> LesParamteres ;

        Employee employee = new Employee();
        LesParamteres = handleRequest(request,response);
        for (int i=0;i<LesParamteres.size();i++) {
            System.out.println(i+ " " +LesParamteres.get(i));
        }
        employee.setNom(LesParamteres.get(2));

        employee.setDépartement(Integer.parseInt(LesParamteres.get(4)));
        employee.setPrénom(LesParamteres.get(3));

        handleRequest(request,response);
        if (LesParamteres.get(1)==null) {
            try { // save
                if (employeeDAO.insertEmployee(employee)) {
                    request.setAttribute("message","C'est bon. Employé ajouté.");
                    response.sendRedirect("list");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("message","C'est bon. Employé ajouté");
        } else { //update
            employee.setMatricule(Integer.parseInt(LesParamteres.get(1)));
            try {
                if (employeeDAO.updateEmployee(employee)) {
                    request.setAttribute("message","Employé modifié.");
                    response.sendRedirect("list");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
