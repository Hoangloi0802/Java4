package poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.dao.UserDAO;
import poly.dao.UserDAOImpl;
import poly.entity.Users;
import poly.controller.EmailUntil;

import java.io.IOException;

@WebServlet("/SendPassword")
public class QuenMKController extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");

        if (username == null || username.isEmpty() || email == null || email.isEmpty()) {
            request.setAttribute("QuenerrorMessage", "Vui lòng nhập đầy đủ thông tin.");
            request.getRequestDispatcher("/Views/Trangchu.jsp").forward(request, response);
            return;
        }

        Users user = userDAO.findByIdOrEmail(username);

        if (user == null || !user.getEmail().equals(email)) {
            request.setAttribute("QuenerrorMessage", "Thông tin không chính xác.");
            request.getRequestDispatcher("/Views/Trangchu.jsp").forward(request, response);
            return;
        }

        try {
            String subject = "Khôi phục mật khẩu";
            String body = "Chào " + user.getFullname() + ",\nMật khẩu của bạn là: " + user.getPassword();
            boolean emailSent = EmailUntil.sendEmail(email, subject, body);

            if (emailSent) {
                request.setAttribute("QuensuccessMessage", "Mật khẩu đã được gửi đến email của bạn.");
            } else {
                request.setAttribute("QuenerrorMessage", "Không thể gửi email. Vui lòng thử lại.");
            }
            request.getRequestDispatcher("/Views/Trangchu.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("QuenerrorMessage", "Có lỗi xảy ra. Vui lòng thử lại.");
            request.getRequestDispatcher("/Views/Trangchu.jsp").forward(request, response);
        }
    }
}
