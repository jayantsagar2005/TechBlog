package com.happy.servlets;

import com.happy.dao.UserDao;
import com.happy.entity.User;
import com.happy.helper.ProfileHelper;
import com.happy.validation.UserValidation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;

@WebServlet("/EditProfileServlet")
@MultipartConfig
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String username = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user1 = new User(username, email, password);

        UserValidation validation = new UserValidation();
        String valid = validation.userData(user1);

        // Image work
        Part part = req.getPart("image");
        String imageName = part.getSubmittedFileName();

        if(valid.equals("Valid")){
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            String oldImage = user.getProfile();
            if(!imageName.isEmpty()){
                user.setProfile(imageName);
            }
            UserDao userDao = new UserDao();
            boolean result = userDao.updateUser(user);

            if(result){

                try{
                    String p = "ProfilePic";
                    String oldPath = getServletConfig().getServletContext().getRealPath(p)+ File.separator + oldImage;
                    String path = getServletConfig().getServletContext().getRealPath(p)+ File.separator + user.getProfile();

                    ProfileHelper profileHelper = new ProfileHelper();

                    if(!oldImage.equals("default.jpg")){
                        profileHelper.deleteFile(oldPath);
                    }

                    if(!profileHelper.saveFile(part.getInputStream(), path)){
                        String msg = "Profile not updated";
                        session.setAttribute("msg", msg);
                        resp.sendRedirect("profile.jsp");
                    }

                    String msg = "User update successfully";
                    session.setAttribute("msg", msg);
                    resp.sendRedirect("profile.jsp");


                }catch(Exception e){
                    e.printStackTrace();
                    String msg = "User prifile not updated";
                    session.setAttribute("msg", msg);
                    resp.sendRedirect("profile.jsp");
                }
            }else{
                String msg = "Failed to update user";
                session.setAttribute("msg", msg);
                resp.sendRedirect("profile.jsp");
            }

        }else{
            session.setAttribute("msg", valid);
            resp.sendRedirect("profile.jsp");
        }



    }
}
