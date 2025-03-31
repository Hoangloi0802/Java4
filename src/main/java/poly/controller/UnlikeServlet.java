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

import java.io.IOException;

import org.json.JSONObject;

@WebServlet("/unlikeVideo")
public class UnlikeServlet extends HttpServlet {
    private FavoriteDAO favoriteDAO = new FavoriteDAOImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            HttpSession session = req.getSession();
            Users currentUser = (Users) session.getAttribute("user");

            if (currentUser == null) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().write("{\"success\": false, \"message\": \"Please log in first.\"}");
                return;
            }

            String body = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
            JSONObject jsonObject = new JSONObject(body);
            String videoId = jsonObject.optString("videoId", null);

            if (videoId == null || videoId.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"success\": false, \"message\": \"Invalid video ID.\"}");
                return;
            }

            // Hủy "Like" video
            favoriteDAO.deleteFavorite(currentUser.getId(), videoId);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"success\": true}");

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"success\": false, \"message\": \"An error occurred.\"}");
        }
    }
}
