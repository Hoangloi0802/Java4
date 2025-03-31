package poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.dao.UserDAO;
import poly.dao.UserDAOImpl;
import poly.entity.Users;

import java.io.IOException;

@WebServlet("/Login")
public class LoginController extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String idOrEmail = request.getParameter("idOrEmail");
        String password = request.getParameter("password");

        Users user = userDAO.findByIdOrEmail(idOrEmail);

        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userName", user.getFullname());
            session.setAttribute("userRole", user.getAdmin()); 
            
            // Redirect to Home page based on user role
            if (user.getAdmin()) {
                response.sendRedirect(request.getContextPath() + "/Home");
            } else {
                response.sendRedirect(request.getContextPath() + "/Home");
            }
        } else {
            // Handle incorrect credentials or non-existent account
            if (user == null) {
                String errorMessage = "Tài khoản không tồn tại.";
                request.setAttribute("loginErrorMessage", errorMessage);
            } else {
                String errorMessage = "Sai tài khoản hoặc mật khẩu";
                request.setAttribute("loginErrorMessage", errorMessage);
            }
            request.getRequestDispatcher("/Home").forward(request, response);
        }
    }
}

