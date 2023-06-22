package Controller;

import Model.RolesModel;
import Model.UserDetailsModel;
import Model.UsersModel;
import Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
@MultipartConfig
@WebServlet(name = "userController", urlPatterns = {"/user", "/user/details", "/user/delete", "/user/add", "/user/mod"})
public class UserController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("messageContent")==null){
            req.getSession().setAttribute("message","");
            req.setAttribute("message","");


        }
       else if(req.getSession().getAttribute("messageContent")!=null){
            req.getSession().setAttribute("messageContent",null);
        }
        switch (req.getServletPath()) {
            case "/user":
                user(req, resp);
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/mod" :
                modifyUser(req, resp);
                break;
            case "/user/delete" :
                deleteUserAjax(req, resp);
                break;
            case "/user/details" :
                detailsUser(req, resp);
                break;
            default:

                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void user(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get list user
        ArrayList<UsersModel> userList = userService.getUserList();
        // return data
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("/user-table.jsp").forward(req, resp);
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean returnData = false;
        boolean insertDataIsSuccess = false;
        UsersModel user = new UsersModel();
        String url = "";
        String message = "";
        if (req.getMethod().equalsIgnoreCase("get")) {
            returnData = true;
            url= "/user-add.jsp";
        } else {
            Part filePart = req.getPart("avatar");
            user.setEmail(req.getParameter("email"));
            user.setFullName(req.getParameter("fullName"));
            user.setPassword(req.getParameter("password"));
            user.setPhone(req.getParameter("phone"));
            user.setCountry(req.getParameter("country"));
            user.setRolesModel(new RolesModel(req.getParameter("roleId")));

            if(!userService.checkAddEmailExisted(user)){
                if(userService.insert(user)){
                    insertDataIsSuccess = true;
                    url = "/user";
                    message = "Add user is success ";
                }else {
                    url = "/user-add.jsp";
                    returnData = true;
                    message = "Add user is failed";


                }
            } else {
                url = "/user-add.jsp";
                message = "email existed !";
                returnData = true;
            }


        }
        if(returnData){
            req.setAttribute("roleList",  userService.getRoleList());
            req.setAttribute("fullName", req.getParameter("fullName"));
            req.setAttribute("email",req.getParameter("email"));
            req.setAttribute("password", req.getParameter("password"));
            req.setAttribute("phone", req.getParameter("phone"));
            req.setAttribute("country",req.getParameter("country"));
            req.setAttribute("roleId", req.getParameter("roleId"));
        }
        if(insertDataIsSuccess){
            req.getSession().setAttribute("messageContent","true");
            req.getSession().setAttribute("message","success");

            resp.sendRedirect(req.getContextPath()+url);
        } else {
            req.setAttribute("message", message);
            req.getRequestDispatcher(url).forward(req, resp);
        }

    }
    public void modifyUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodCustom = "";
        String url = "";
        String message = "";
        boolean modifyUserUserIsSuccess = false;
        boolean returnData = true;
        if (req.getParameter("methodCustom")!=null){
            methodCustom = req.getParameter("methodCustom");
        }
        if(methodCustom.equalsIgnoreCase("get")){

            url ="/user-mod.jsp";

        }else {
            UsersModel user = new UsersModel();
            String to = req.getParameter("toDoGet");
            boolean toDoGet = userService.toDoGet(to);
            if(toDoGet){
                doGet(req,resp);
            }
            else {
                user.setId(req.getParameter("id"));
                user.setEmail(req.getParameter("email"));
                user.setFullName(req.getParameter("fullName"));
                user.setPassword(req.getParameter("password"));
                user.setPhone(req.getParameter("phone"));
                user.setCountry(req.getParameter("country"));
                user.setRolesModel(new RolesModel(req.getParameter("roleId")));
                if(userService.checkModEmailExisted(user,req)){

                    modifyUserUserIsSuccess = userService.modifyUser(user,req);
                    if(modifyUserUserIsSuccess){
                        System.out.println("modifyUserUserIsSuccess");


                    }

                }else {
                    url ="/user-mod.jsp";
                }

            }
        }
        if (modifyUserUserIsSuccess) {
            returnData = false;
            url = "/user";
            req.getSession().setAttribute("message","Modify user is success ");
        }else {
            url ="/user-mod.jsp";
        }
        if(returnData){
            req.setAttribute("id",req.getParameter("id"));
            req.setAttribute("fullName", req.getParameter("fullName"));
            req.setAttribute("email", req.getParameter("email"));
            req.setAttribute("password", req.getParameter("password"));
            req.setAttribute("phone", req.getParameter("phone"));
            req.setAttribute("country", req.getParameter("country"));
            req.setAttribute("roleId",req.getParameter("roleId"));
            ArrayList<RolesModel> roleList = userService.getRoleList();
            req.setAttribute("roleList",roleList);

        }
        System.out.println("Check url:"+url);
        if(modifyUserUserIsSuccess){
            req.getSession().setAttribute("messageContent","true");
            resp.sendRedirect(req.getContextPath()+url);
        }else {
            req.getRequestDispatcher(url).forward(req, resp);

        }
    }
    public void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersModel user = new UsersModel(req.getParameter("id"));
        boolean deleteIsSuccess = userService.deleteUser(user,req);
        String message = "";
        System.out.println("Check deleteIsSuccess"+deleteIsSuccess);
        if(deleteIsSuccess){
            message = "User is deleted!";
        } else {
            message = "Delete user is failed !";
        }
        req.setAttribute("message",message);
        req.getRequestDispatcher("/user").forward(req,resp);
    }
    public void deleteUserAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {;
        String message = "";
        boolean deleteIsSuccess = false;
        UsersModel user = new UsersModel(req.getParameter("userId"));
        if(req.getAttribute("roleUsernameId").equals("1")){
            deleteIsSuccess = userService.deleteUser(user,req);
            if(deleteIsSuccess){
                message = "true";
            } else {
                message =(String) req.getAttribute("message");
            }
        }else {
            message = "you don't have permisson !";
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.println(message);
    }
    public void detailsUser(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        String idUser = req.getParameter("id");
        boolean checkIdValid = userService.checkIdValid(idUser);
        if (!checkIdValid) {
            req.getRequestDispatcher("/user").forward(req, resp);
        } else {

            UserDetailsModel userDetails = userService.getUserDetails(new UsersModel(idUser));
            userDetails = userService.StringTimeStampToStringDate(userDetails);
            int jobListNotStarted = userService.sizeList(userDetails.getJobListNotStarted());
            int jobListProcessing = userService.sizeList(userDetails.getJobListProcessing());
            int jobListCompleted = userService.sizeList(userDetails.getJobListCompleted());
            int total = userService.totalSizeList(jobListNotStarted, jobListProcessing, jobListCompleted);
            // call Percent
            double jobListNotStartedPercent = userService.calPercent(jobListNotStarted, total);
            double jobListProcessingPercent = userService.calPercent(jobListProcessing, total);
            double jobListCompletedPercent = userService.calPercent(jobListCompleted, total);
            // format Percent two decimals
            jobListNotStartedPercent = userService.formatTwoDecimals(jobListNotStartedPercent);
            jobListProcessingPercent = userService.formatTwoDecimals(jobListProcessingPercent);
            jobListCompletedPercent = userService.formatTwoDecimals(jobListCompletedPercent);
            // return data
            req.setAttribute("jobListNotStartedPercent", jobListNotStartedPercent);
            req.setAttribute("jobListProcessingPercent", jobListProcessingPercent);
            req.setAttribute("jobListCompletedPercent", jobListCompletedPercent);
            req.setAttribute("userDetails", userDetails);
            req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
        }
    }

}
