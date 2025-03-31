package poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.dao.FavoriteDAO;
import poly.dao.FavoriteDAOImpl;
import poly.entity.Users;
import poly.entity.Video;

import java.io.IOException;
import java.util.List;

@WebServlet("/myFavorite")
public class MyFavoriteServlet extends HttpServlet {
    private FavoriteDAO favoriteDAO = new FavoriteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        try {
            HttpSession session = req.getSession();
            Users currentUser = (Users) session.getAttribute("user");

            // Kiểm tra nếu người dùng chưa đăng nhập
            if (currentUser == null) {
                resp.sendRedirect("/login.jsp"); // Chuyển hướng tới trang đăng nhập nếu chưa đăng nhập
                return;
            }

            // Lấy danh sách video yêu thích của người dùng
            List<Video> favoriteVideos = favoriteDAO.findFavoritesByUser(currentUser.getId());

            // Truyền dữ liệu vào JSP
            req.setAttribute("videos", favoriteVideos);
            req.getRequestDispatcher("/Views/VDyeuthich.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching your favorite videos.");
        }
    }
}


