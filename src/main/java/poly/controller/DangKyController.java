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

@WebServlet("/Dangky")
public class DangKyController extends HttpServlet {
	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAOImpl();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String username = request.getParameter("username1");
		    String password = request.getParameter("password1");
		    String fullname = request.getParameter("fullname1");
		    String email = request.getParameter("email1");

		    // 🔹 Kiểm tra thông tin nhập vào có bị thiếu không (TC08)
		    if (username == null || username.isEmpty() || password == null || password.isEmpty() || 
		        fullname == null || fullname.isEmpty() || email == null || email.isEmpty()) {
		        request.setAttribute("registrationErrorMessage", "Vui lòng nhập đầy đủ thông tin");
		        request.getRequestDispatcher("/Home").forward(request, response);
		        return;
		    }

		    // 🔹 Kiểm tra nếu tài khoản hoặc email đã tồn tại (TC06)
		    if (userDAO.findByIdOrEmail(username) != null || userDAO.findByIdOrEmail(email) != null) {
		        request.setAttribute("registrationErrorMessage", "Email đã được sử dụng");
		        request.getRequestDispatcher("/Home").forward(request, response);
		        return;
		    }

		    // 🔹 Tạo người dùng mới
		    Users user = new Users();
		    user.setId(username);
		    user.setPassword(password);
		    user.setFullname(fullname);
		    user.setEmail(email);
		    user.setAdmin(false);
		try {
			userDAO.create(user);
			String subject = "Chào mừng bạn đến với hệ thống!";
			String body = "Chào " + fullname + ",\nCảm ơn bạn đã đăng ký.";
			boolean emailSent = EmailUntil.sendEmail(email, subject, body);

			if (emailSent) {
				request.setAttribute("registrationErrorMessage", "Đăng ký thành công.");
			} else {
				request.setAttribute("registrationErrorMessage", "Đăng ký thành công. Tuy nhiên, không thể gửi email xác nhận.");
			}
			request.getRequestDispatcher("/Home").forward(request, response);
		} catch (Exception e) {
			request.setAttribute("registrationErrorMessage", "Đăng ký thất bại. Vui lòng thử lại.");
			request.getRequestDispatcher("/Home").forward(request, response);
		}
	}

}
