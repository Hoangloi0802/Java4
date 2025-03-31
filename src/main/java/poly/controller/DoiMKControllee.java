package poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.dao.UserDAO;
import poly.dao.UserDAOImpl;
import poly.entity.Users;

import java.io.IOException;

@WebServlet("/ChangePassword")
public class DoiMKControllee extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

       
        if (username == null || username.isEmpty() ||
            currentPassword == null || currentPassword.isEmpty() ||
            newPassword == null || newPassword.isEmpty() ||
            confirmNewPassword == null || confirmNewPassword.isEmpty()) {
            request.setAttribute("Loi", "Vui lòng nhập đầy đủ các trường.");
            request.getRequestDispatcher("/Home").forward(request, response);
            return;
        }

        
        if (!newPassword.equals(confirmNewPassword)) {
            request.setAttribute("Loi", "Mật khẩu mới và xác nhận không khớp.");
            request.getRequestDispatcher("/Home").forward(request, response);
            return;
        }

        try {
            
            Users user = userDAO.findById(username);

            if (user == null) {
                request.setAttribute("Loi", "Tài khoản không tồn tại.");
            } else if (!user.getPassword().equals(currentPassword)) {
                request.setAttribute("Loi", "Mật khẩu hiện tại không chính xác.");
            } else {
 
                user.setPassword(newPassword);
                userDAO.update(user);
                request.setAttribute("Dung", "Đổi mật khẩu thành công.");
            }

        } catch (Exception e) {
            request.setAttribute("Loi", "Có lỗi xảy ra. Vui lòng thử lại.");
        }

        // Quay lại trang chính với thông báo
        request.getRequestDispatcher("/Home").forward(request, response);
    }
}
