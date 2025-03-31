package poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import poly.dao.VideoDAO;
import poly.dao.VideoDAOImpl;
import poly.entity.Users; // Assuming you have a Users entity
import poly.entity.Video;

import java.io.IOException;
import java.util.List;

@WebServlet("/Home")
public class Trangchu extends HttpServlet {

    private VideoDAO videoDAO = new VideoDAOImpl(); // DAO for accessing videos

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        List<Video> videos = videoDAO.findAll(); 
        
        req.setAttribute("videos", videos);

       
        req.getRequestDispatcher("/Views/Trangchu.jsp").forward(req, resp);
    }
}


