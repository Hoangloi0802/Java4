package poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.json.JSONObject;
import poly.dao.FavoriteDAO;
import poly.dao.FavoriteDAOImpl;
import poly.entity.Favorite;
import poly.entity.Users;
import poly.entity.Video;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet({"/likeVideo","/likedVideos"})
public class LikeServlet extends HttpServlet {
    private FavoriteDAO favoriteDAO = new FavoriteDAOImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
            HttpSession session = req.getSession();
            Users currentUser = (Users) session.getAttribute("user");

            if (currentUser == null) {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().write("{\"success\": false, \"message\": \"Vui lòng đăng nhập.\"}");
                return;
            }

            List<Video> likedVideos = favoriteDAO.findLikedVideosByUser(currentUser.getId());
            List<String> likedVideoIds = likedVideos.stream().map(Video::getId).toList();

            JSONObject responseJson = new JSONObject();
            responseJson.put("success", true);
            responseJson.put("likedVideoIds", likedVideoIds);
            resp.getWriter().write(responseJson.toString());
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"success\": false, \"message\": \"An error occurred.\"}");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        try {
     
            HttpSession session = req.getSession();
            Users currentUser = (Users) session.getAttribute("user");

            if (currentUser == null) {
            
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                JSONObject responseJson = new JSONObject();
                responseJson.put("success", false);
                responseJson.put("message", "Vui lòng đăng nhập !!!");
                responseJson.put("redirectUrl", req.getContextPath() + "/Login"); 
                resp.getWriter().write(responseJson.toString());
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

            Video video = favoriteDAO.findVideoById(videoId);
            if (video == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"success\": false, \"message\": \"Video not found.\"}");
                return;
            }

            Favorite favorite = new Favorite();
            favorite.setUser(currentUser);
            favorite.setVideo(video);
            favorite.setLikeDate(new Date());
            favoriteDAO.create(favorite);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("{\"success\": true}");
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"success\": false, \"message\": \"An error occurred.\"}");
        }
    }
}
