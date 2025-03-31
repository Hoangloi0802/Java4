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
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;


@WebServlet({
	"/user/index",
	"/user/create",
	"/user/update",
	"/user/edit/*",
	"/user/reset",
	"/user/delete/*",
	"/user/delete",
	"/user/findbyname",
	"/user/paginate"
})
public class UserController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO dao = new UserDAOImpl();
		Users form = new Users();
		String message = "Nhập thông tin người dùng";
		List<Users> list = dao.findAll();

		try {
			BeanUtils.populate(form, req.getParameterMap());
		} catch (IllegalAccessException  | InvocationTargetException e) {
			e.printStackTrace();
		}

		String path = req.getServletPath();

		if (path.contains("edit")) {
			String id = req.getPathInfo().substring(1);
			form = dao.findById(id);
			if (form != null) {
				message = "Edit: " + id;
			} else {
				message = "ID không tồn tại: " + id;
			}
		} else if (path.contains("create")) {
			dao.create(form);
			message = "Created successfully: " + form.getId();
			form = new Users();
			list = dao.findAll();
		} else if (path.contains("update")) {
			if (form.getId() == null || form.getId().trim().isEmpty()) {
				throw new IllegalArgumentException("ID của người dùng không được để trống.");
			}
			dao.update(form);
			message = "Updated successfully: " + form.getId();
			list = dao.findAll();
			form = new Users();
		} else if (path.contains("delete")) {
			String id = form.getId();
			dao.deleteById(id);
			message = "Deleted successfully: " + id;
			form = new Users();
			list = dao.findAll();
		} else if (path.contains("reset")) {
			form = new Users();
		}

		req.setAttribute("message", message);
		req.setAttribute("user", form);
		req.setAttribute("users", list);


		req.getRequestDispatcher("/Views/QuantriUser.jsp").forward(req, resp);
	}

}
