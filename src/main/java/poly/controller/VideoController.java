package poly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import poly.dao.VideoDAO;
import poly.dao.VideoDAOImpl;
import poly.entity.Video;
import org.apache.commons.beanutils.BeanUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet({
        "/video/index",
        "/video/create",
        "/video/update",
        "/video/edit/*",
        "/video/reset",
        "/video/delete/*",
        "/video/delete",
        "/video/findbyname",
        "/video/paginate"
})
@MultipartConfig
public class VideoController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VideoDAO dao = new VideoDAOImpl();
        Video form = new Video();
        String message = "Nhập thông tin video";
        List<Video> list = dao.findAll();

        try {
            BeanUtils.populate(form, request.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        String path = request.getServletPath();
        String id = request.getPathInfo() != null ? request.getPathInfo().substring(1) : null;

        if (path.contains("edit") && id != null) {
            form = dao.findById(id);
            if (form != null) {
                message = "Edit: " + id;
            } else {
                message = "ID không tồn tại: " + id;
            }
        } else if (path.contains("create")) {
        	String posterPath = savePosterFile(request, form);
        	if (posterPath != null) {
        	    form.setPoster(posterPath);
        	}

            dao.create(form);
            message = "Created successfully: " + form.getId();
            form = new Video();
            list = dao.findAll();
        } else if (path.contains("update")) {
            if (form.getId() == null || form.getId().trim().isEmpty()) {
                throw new IllegalArgumentException("ID của video không được để trống.");
            }
            String posterPath = savePosterFile(request, form);
            if (posterPath != null) {
                form.setPoster(posterPath);
            }

            dao.update(form);
            message = "Updated successfully: " + form.getId();
            list = dao.findAll();
            form = new Video();
        } else if (path.contains("delete") && id != null) {
            dao.deleteById(id);
            message = "Deleted successfully: " + id;
            form = new Video();
            list = dao.findAll();
        } else if (path.contains("reset")) {
            form = new Video();
        }

        request.setAttribute("message", message);
        request.setAttribute("video", form);
        request.setAttribute("videos", list);

        request.getRequestDispatcher("/Views/QuantriVD.jsp").forward(request, response);
    }

    private String savePosterFile(HttpServletRequest req, Video form) throws IOException, ServletException {
        Part filePart = req.getPart("poster");
        String videoUrl = form.getUrl();

        // Nếu URL là YouTube, tự động lấy poster
        if (videoUrl != null && (videoUrl.contains("youtube.com") || videoUrl.contains("youtu.be"))) {
            if (form.getId() != null) {
                return "https://img.youtube.com/vi/" + form.getId() + "/maxresdefault.jpg"; // URL của thumbnail YouTube
            }
        }
        return null;
    }


}