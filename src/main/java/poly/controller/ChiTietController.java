package poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import poly.dao.VideoDAOImpl;
import poly.entity.Video;

import java.io.IOException;
import java.util.List;

@WebServlet("/videoDetails")
public class ChiTietController extends HttpServlet {
	  private static final long serialVersionUID = 1L;

	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        String videoId = request.getParameter("videoId");

	        VideoDAOImpl videoDAO = new VideoDAOImpl();
	        Video selectedVideo = videoDAO.findById(videoId);
	        List<Video> videos = videoDAO.findAll(); 
	        
	     
	        request.setAttribute("videos", videos);

	        
	        if (selectedVideo != null) {
	            request.setAttribute("selectedVideo", selectedVideo);
	            request.getRequestDispatcher("Views/Chitiet.jsp").forward(request, response);
	        } else {
	            response.sendRedirect("videoList");
	        }
	    }
	}
