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

@WebServlet("/UpdateProfile")
public class ProfileController extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Users loggedUser = (Users) session.getAttribute("user");

        if (loggedUser == null) {
            response.sendRedirect("Login"); 
            return;
        }

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            request.setAttribute("user", loggedUser);
            request.getRequestDispatcher("/Home").forward(request, response);
            return;
        }

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");

        if (fullname == null || fullname.isEmpty() ||
            email == null || email.isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng nhập đầy đủ các trường.");
            request.setAttribute("user", loggedUser); 
            request.getRequestDispatcher("/Home").forward(request, response);
            return;
        }

        try {
            loggedUser.setFullname(fullname);
            loggedUser.setEmail(email);

            userDAO.update(loggedUser); 
            session.setAttribute("user", loggedUser); 

            request.setAttribute("successMessage", "Cập nhật thông tin tài khoản thành công.");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật tài khoản.");
        }

        request.setAttribute("user", loggedUser);
        request.getRequestDispatcher("/Home").forward(request, response);
    }
}
