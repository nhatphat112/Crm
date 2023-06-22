package Controller;

import Model.ProjectsModel;
import Model.StatusModel;
import Model.UsersModel;
import Model.WorkOnModel;
import Service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "profileController",urlPatterns = {"/profile","/profile/login","/profile/logout","/profile/mod"})
public class ProfileController extends HttpServlet {
    private ProfileService profileService = new ProfileService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("message","");

        switch (req.getServletPath()){
            case "/profile" :
                profile(req, resp);
                break;
            case "/profile/login" :
                login(req, resp);
                break;
            case "/profile/logout" :
                logout(req, resp);
                break;
            case "/profile/mod" :
                profileMod(req,resp);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void profile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            UsersModel user = profileService.getUser(req);
            String url = "/profile/login";
            if(user!=null){
                user.setPassword("");
                ArrayList<WorkOnModel> workOnList = profileService.getWorkOnList(user.getId());

                double workNotStartPercent = profileService.statusPercent(workOnList,"1");
                double workProcessingPercent = profileService.statusPercent(workOnList,"2");
                double workCompeletedPercent = profileService.statusPercent(workOnList,"3");
                req.setAttribute("workNotStartPercent",profileService.formatTwoDecimals(workNotStartPercent));
                req.setAttribute("workProcessingPercent",profileService.formatTwoDecimals(workProcessingPercent));
                req.setAttribute("workCompeletedPercent",profileService.formatTwoDecimals(workCompeletedPercent));

                req.setAttribute("user",user);
                req.setAttribute("workOnList",workOnList);
                url = "/profile.jsp";
            }
        req.getRequestDispatcher(url).forward(req,resp);


    }
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean createCookieIsSuccess = false;
        UsersModel loginIsSuccess = null;
        System.out.println("Check method"+req.getMethod());
        if(req.getMethod().equalsIgnoreCase("get")){
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        } else {
            System.out.println("-----------LoginController - DoPost_________");
            String message = "";
            String url = "index.jsp";
            // get Parameter
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            System.out.println("Check email :" + email);
            System.out.println("Check password :" + password);
            // get list user by email and password from LoginService

             loginIsSuccess = profileService.checkLoginIsSuccess(email,password);
             System.out.println("check loginIsSuccess :"+loginIsSuccess);
            if(loginIsSuccess!=null){
                createCookieIsSuccess = profileService.createCookieLogin(req,resp, loginIsSuccess);
            }
            System.out.println("check login :" + loginIsSuccess);
            System.out.println("check create cookie :" + createCookieIsSuccess);
            if (loginIsSuccess!=null) {
                url = "/dashboard";
                message = "Login Success!";
            } else {
                req.setAttribute("email", email);
                req.setAttribute("password", password);
                url = "/login.jsp";
                message = "Account or password incorrect!";
            }
            // return data

            req.setAttribute("message", message);
           if(loginIsSuccess!=null){
               resp.sendRedirect(req.getContextPath()+url);
           }else {
               req.getRequestDispatcher(url).forward(req,resp);
           }
        }

    }
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getScheme()+"://" + req.getServerName()+":"+req.getLocalPort()+req.getContextPath();
        boolean logoutIsSuccess = profileService.deleteCookieLogin(req,resp);
        System.out.println("check logoutIsSuccess : "+logoutIsSuccess);
        resp.sendRedirect(path+"/dashboard");
    }
    public void profileMod(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/profile";
        String methodCustom ="";
        boolean returnData = false;
        WorkOnModel workOn = new WorkOnModel();
        workOn.getUsers().setId(req.getParameter("userId"));
        workOn.getJobs().setId(req.getParameter("jobId"));
        workOn.getStatus().setId(req.getParameter("statusId"));
        workOn.getProjects().setId(req.getParameter("projectId"));
        String statusIdUpdate = req.getParameter("statusIdUpdate");
        methodCustom = (req.getParameter("methodCustom")!=null)?req.getParameter("methodCustom"):methodCustom;
        System.out.println("Check methodCustom:"+methodCustom);
        System.out.println("Check method:"+req.getMethod());
        System.out.println("check userId+ :"+("userName"));

        if(methodCustom.equalsIgnoreCase("get")){
            returnData = true;
            url = "/profile-edit.jsp";
        } else if(methodCustom.equalsIgnoreCase("post")){
            if(profileService.updateStatusIdById(workOn,statusIdUpdate)){
                url = "/profile";
                req.setAttribute("message","Success!");
            }else {
                returnData = true;
                url = "/profile-edit.jsp";
            }
        }else {
            url = "/profile";
        }
        if(returnData){
            req.setAttribute("statusList",profileService.getSatusList());
            req.setAttribute("workOn",profileService.getWorkOn(workOn));
            req.setAttribute("statusIdUpdate",req.getParameter("statusIdUpdate"));
            req.getRequestDispatcher(url).forward(req,resp);
        }else {
            req.getRequestDispatcher(url).forward(req,resp);
        }

    }
}
