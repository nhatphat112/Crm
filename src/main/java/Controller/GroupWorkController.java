package Controller;

import Model.ListJobOfUserModel;
import Model.ProjectsModel;
import Model.UsersModel;
import Service.GroupWorkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
@WebServlet(name = "groupWorkController",urlPatterns = {"/groupwork","/groupwork/add","/groupwork/delete","/groupwork/mod","/groupwork/details"})
public class GroupWorkController extends HttpServlet {
    private GroupWorkService groupWorkService = new GroupWorkService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getSession().getAttribute("messageContent") == null) {
            req.getSession().setAttribute("message", "");
            req.setAttribute("message", "");


        } else if (req.getSession().getAttribute("messageContent") != null) {
            req.getSession().setAttribute("messageContent", null);
        }

        switch (req.getServletPath()){
            case "/groupwork" :
                groupWork(req, resp);
                break;
            case "/groupwork/add" :
                addGroupWork(resp,req);
                break;
            case "/groupwork/delete" :
                deleteGroupWork(req, resp);
                break;
            case "/groupwork/mod":
                modifyGroupWork(req, resp);
                break;
            case "/groupwork/details":
                detailsGroupWork(req, resp);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
    public void groupWork(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

            req.setCharacterEncoding("UTF-8");
            ArrayList<ProjectsModel> projectList = groupWorkService.getProjectList((String) req.getAttribute("roleUserId"), (String) req.getAttribute("userId"));
            req.setAttribute("projectModelArrayList",projectList);
            req.getRequestDispatcher("/groupwork.jsp").forward(req,resp);
    }
    public void addGroupWork(HttpServletResponse resp ,HttpServletRequest req) throws ServletException, IOException {
        String url = "/groupwork-add.jsp";
        String message = "";
        boolean insertIsSuccess = false;
        boolean returnData = true;
        req.setCharacterEncoding("UTF-8");
        if(req.getMethod().equalsIgnoreCase("post")){

            // getParameter
            String userId = req.getParameter("userManagerId");
            String projectName = req.getParameter("projectName");
            String dateBegin = req.getParameter("dateBegin");
            String dateEnd = req.getParameter("dateEnd");
            System.out.println("check date beginParameter controller :"+dateBegin);
            System.out.println("check date endParameter controller :"+dateEnd);
            UsersModel usersModel = new UsersModel();
            usersModel.setId(userId);
            ProjectsModel project = new ProjectsModel();
            project.setDateBegin(dateBegin);
            project.setDateEnd(dateEnd);
            project.setUser(usersModel);
            project.setProjectName(projectName);
            System.out.println("checkProjectName :" + projectName);
            System.out.println("check date begin controller :"+project.getDateBegin());
            System.out.println("check date end controller :"+project.getDateEnd());

            // check valid?
            boolean checkInputFormValid = groupWorkService.checkInputFormValid(project, req);
            System.out.println("Check checkInputFormValid :" + checkInputFormValid);
            System.out.println("Check message :" + req.getAttribute("message"));
            if (checkInputFormValid) {
                boolean convertToFormTimeStamp = groupWorkService.convertToFormTimeStamp(project);
                System.out.println("Check convertToFormTimeStamp :" + convertToFormTimeStamp);
                if (convertToFormTimeStamp) {
                    boolean checkDateValid = groupWorkService.checkDateValid(project,req);
                    System.out.println("Check checkDateValid" + checkDateValid);
                    if (checkDateValid) {
                        insertIsSuccess = groupWorkService.insert(project);
                        System.out.println("check insert" + insertIsSuccess);
                        if (insertIsSuccess) {
                            url = "/groupwork";
                            message = "Success!";
                            returnData = false;
                        }


                    }

                }
            }
            if(returnData){
                req.setAttribute("projectName",projectName);
                req.setAttribute("dateBegin",dateBegin);
                req.setAttribute("dateEnd",dateEnd);
                req.setAttribute("userManagerList",groupWorkService.getUserListByRoleId("2"));

            }



        }else {
            url = "/groupwork-add.jsp";
            req.setAttribute("userManagerList",groupWorkService.getUserListByRoleId("2"));
        }
        System.out.println("Check url :"+url);
        req.getSession().setAttribute("messageContent","true");
        if (insertIsSuccess){
            req.getSession().setAttribute("message",message);
            resp.sendRedirect(req.getContextPath()+url);
        }else {
            req.getRequestDispatcher(url).forward(req, resp);
        }
    }
    public  void deleteGroupWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean deleteIsSuccess = false;
        String url = "/groupwork";
        String message ="";
        if (req.getMethod().equalsIgnoreCase("post")){
            String id = req.getParameter("id");

            ProjectsModel projectModel = new ProjectsModel();
            projectModel.setId(id);
             deleteIsSuccess = groupWorkService.deleteGroupWork(projectModel);
            if(deleteIsSuccess){
                message="true";
            } else {
                message="false";
            }
        }
        if(deleteIsSuccess){
            PrintWriter printWriter = resp.getWriter();
            printWriter.println(message);
        }else {
            resp.sendRedirect(req.getContextPath()+url);
        }
    }
    public void modifyGroupWork(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        boolean update = false;
        String methodCustom = "";
        String url = "/groupwork-mod.jsp";
        String message = "";
        boolean returnData = false;
        String userId = (String) req.getAttribute("userId");
        String roleUserId = (String) req.getAttribute("roleUserId");
        methodCustom = (req.getParameter("methodCustom")!=null)?req.getParameter("methodCustom"):methodCustom;
        System.out.println("check id"+req.getParameter("id"));
        if (methodCustom.equalsIgnoreCase("get")){
            returnData = true;

        } else {
            req.setCharacterEncoding("UTF-8");
            // getParameter
            String id = req.getParameter("id");
            System.out.println("Check id post :"+id);
            String projectName = req.getParameter("projectName");
            String dateBegin = req.getParameter("dateBegin");
            String dateEnd = req.getParameter("dateEnd");
            UsersModel usersModel = new UsersModel(userId);
            ProjectsModel project = new ProjectsModel(id, projectName, dateBegin, dateEnd,usersModel);
            project.setUser(new UsersModel(req.getParameter("userManagerId")));

            if(project.getId()!=null){
                // check valid?
                boolean updateIsSuccess = false;
                boolean checkInputFormValid = groupWorkService.checkInputFormValid(project, req);
                System.out.println("Check checkInputFormValid :" + checkInputFormValid);
                System.out.println("Check message :" + req.getAttribute("message"));
                if (checkInputFormValid) {
                    boolean convertToFormTimeStamp = groupWorkService.convertToFormTimeStamp(project);
                    System.out.println("Check convertToFormTimeStamp :" + convertToFormTimeStamp);
                    if (convertToFormTimeStamp) {
                        boolean checkDateValid = groupWorkService.checkDateValid(project,req);
                        System.out.println("Check checkDateValid" + checkDateValid);
                        if (checkDateValid) {
                             update = groupWorkService.update(project);
                            System.out.println("check update" + update);
                            if (update) {
                                url = "/groupwork";
                                message = "Success!";
                                updateIsSuccess = true;
                            } else {
                                message = "Update Is Failed!";
                            }
                            req.setAttribute("message", message);
                        }

                    }
                } if(!updateIsSuccess){
                    returnData = true;
                }


            } else {
                url = "/groupwork";
            }
        }
        System.out.println("Check returndata :"+returnData);
        if(returnData){
            req.setAttribute("id",req.getParameter("id"));
            if(roleUserId.equals("2")){
                req.setAttribute("userManagerList",groupWorkService.getUserById(userId));
            }else {
                req.setAttribute("userManagerList",groupWorkService.getUserListByRoleId("2"));
                System.out.println("Check size list userManagerList :"+groupWorkService.getUserListByRoleId("2"));
            }
            req.setAttribute("id",req.getParameter("id"));
            req.setAttribute("projectName",req.getParameter("projectName"));
            req.setAttribute("dateBegin",req.getParameter("dateBegin"));
            req.setAttribute("dateEnd",req.getParameter("dateEnd"));

        }
        req.getSession().setAttribute("messageContent","true");
        if(update){
            req.getSession().setAttribute("message",message);
            resp.sendRedirect(req.getContextPath()+url);
        }else {
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }
    public void  detailsGroupWork(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String projectId = "";
        String url = "";
        projectId = (req.getParameter("projectId")!=null)?req.getParameter("projectId"):projectId;
        if(!projectId.isEmpty()){
            ArrayList <ListJobOfUserModel> listJobOfUsersArrayList = groupWorkService.getListJobOfUsersArrayList(projectId);

            System.out.println("Check size listJobOfUsersArrayList :"+listJobOfUsersArrayList.size());
            // cal % CTCV
            int jobNotStarted = groupWorkService.countjobNotStarted(listJobOfUsersArrayList);
            int jobProcessing = groupWorkService.countJobProcessing(listJobOfUsersArrayList);
            int jobCompleted = groupWorkService.countJobCompleted(listJobOfUsersArrayList);
            int jobTotal = groupWorkService.jobTotal(jobNotStarted,jobProcessing,jobCompleted);
            System.out.println("Check job total:"+jobTotal);

            double jobNotStartedPercent = groupWorkService.calPercent(jobNotStarted,jobTotal);
            double jobJobProcessingPercent = groupWorkService.calPercent(jobProcessing,jobTotal);
            double jobJobCompletedPercent = groupWorkService.calPercent(jobCompleted,jobTotal);;

            String formattedjobNotStartedPercent = groupWorkService.formaTwoDecimals(jobNotStartedPercent);
            String formattedjobJobProcessingPercent = groupWorkService.formaTwoDecimals(jobJobProcessingPercent);
            String formattedjobJobCompletedPercent = groupWorkService.formaTwoDecimals(jobJobCompletedPercent);
            // return data
            req.setAttribute("formattedjobNotStartedPercent",formattedjobNotStartedPercent);
            req.setAttribute("formattedjobJobProcessingPercent",formattedjobJobProcessingPercent);
            req.setAttribute("formattedjobJobCompletedPercent",formattedjobJobCompletedPercent);
            req.setAttribute("listJobOfUsersArrayList",listJobOfUsersArrayList);
            url = "/groupwork-details.jsp";

        }else {
            url ="/groupwork";
        }
        req.getRequestDispatcher(url).forward(req,resp);
    }
}
