package com.happy.servlets;

import com.happy.dao.PostDao;
import com.happy.entity.Post;
import com.happy.entity.User;
import com.happy.helper.DateTime;
import com.happy.helper.ProfileHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;

@WebServlet("/AddPostServlet")
@MultipartConfig
public class AddPostServlet extends HttpServlet {

    private String msg;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        int categoryId = Integer.parseInt(req.getParameter("category"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String  code = req.getParameter("code");
        Part part = req.getPart("image");
        String image = part.getSubmittedFileName();
        String dateTime = DateTime.getCurrentDateTime();

        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();


        if(categoryId == 0){

            msg = "Category not specified";
            session.setAttribute("msg", msg);
            resp.sendRedirect("profile.jsp");

        } else if (title == null){

            msg = "Write a title for the post";
            session.setAttribute("msg", msg);
            resp.sendRedirect("profile.jsp");

        } else if (content == null) {

            msg = "Write a content for the post";
            session.setAttribute("msg", msg);
            resp.sendRedirect("profile.jsp");

        } else{

            Post postData = new Post(title, content, code, image, dateTime, categoryId, userId);

            PostDao postDao = new PostDao();
            boolean result = postDao.savePost(postData);

            if (result){

                String p = "PostPic";
                ProfileHelper profileHelper = new ProfileHelper();
                String path = getServletConfig().getServletContext().getRealPath(p)+ File.separator + image;

                if(!profileHelper.saveFile(part.getInputStream(), path)){
                    String msg = "Post image failed";
                    session.setAttribute("msg", msg);
                    resp.sendRedirect("profile.jsp");
                }

                msg = "Your post has saved successfully";
                session.setAttribute("msg", msg);
                resp.sendRedirect("profile.jsp");

            }else {
                msg = "Your post failed to be saved";
                session.setAttribute("msg", msg);
                resp.sendRedirect("profile.jsp");
            }


        }


    }
}
