package Controller;

import Model.*;
import Service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "taskController", urlPatterns = {"/task", "/task/add", "/task/mod", "/task/delete"})
public class TaskController extends HttpServlet {
    private TaskService taskService = new TaskService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getSession().getAttribute("messageContent") == null) {
            req.getSession().setAttribute("message", "");
            req.setAttribute("message", "");


        } else if (req.getSession().getAttribute("messageContent") != null) {
            req.getSession().setAttribute("messageContent", null);
        }
        switch (req.getServletPath()) {
            case "/task":
                task(req, resp);
                break;
            case "/task/add":
                taskAdd(req, resp);
                break;
            case "/task/mod":
                taskModify(req, resp);
                break;
            case "/task/delete":
                taskDelete(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void task(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<ListJobModel> listJobArrayList = taskService.getjobList((String) req.getAttribute("roleUserId"), (String) req.getAttribute("userId"));
        req.setAttribute("listJobArrayList", listJobArrayList);

        req.getRequestDispatcher("/task.jsp").forward(req, resp);
    }

    public void taskAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/task/add";
        String consequence = "";
        boolean insertTaskIsSuccess = false;
        String roleUserId = (String) req.getAttribute("roleUserId");
        String userId = (String) req.getAttribute("userId");
        consequence = (req.getAttribute("consequence") != null) ? (String) req.getAttribute("consequence") : consequence;
        if (req.getMethod().equalsIgnoreCase("get") || consequence.equalsIgnoreCase("failed")) {
            ArrayList<ProjectsModel> projectList = taskService.getProjectList(roleUserId,userId);
            ArrayList<UsersModel> userList = taskService.getUserList();
            ArrayList<StatusModel> statusList = taskService.getStatusList();
            req.setAttribute("statusList", statusList);
            req.setAttribute("projectList", projectList);
            req.setAttribute("userList", userList);
            url = "/task-add.jsp";
            if(projectList.size()==0){
                url = "/404";
            }
        } else {
            System.out.println("Check post");
            consequence = "failed";
            WorkOnModel workOn = new WorkOnModel();
            workOn.getProjects().setId(req.getParameter("projectId"));
            workOn.getUsers().setId(req.getParameter("userId"));
            workOn.getJobs().setJobName(req.getParameter("jobName"));
            workOn.getStatus().setId(req.getParameter("statusId"));
            workOn.setDateBegin(req.getParameter("dateBegin"));
            workOn.setDateEnd(req.getParameter("dateEnd"));
            System.out.println("Check inputData :");
            System.out.println("check projectId" + req.getParameter("projectId"));
            System.out.println("check userId" + req.getParameter("userId"));
            System.out.println("check statusId" + req.getParameter("statusId"));
            System.out.println("check jobName" + req.getParameter("jobName"));
            System.out.println("check dateBegin" + req.getParameter("dateBegin"));
            System.out.println("check dateEnd" + req.getParameter("dateEnd"));
            boolean inputDataIsValid = taskService.checkInputData(req, resp, workOn);
            System.out.println("Check inputDataIsValid" + inputDataIsValid);
            if (inputDataIsValid) {
                insertTaskIsSuccess = taskService.insertWorkOn(workOn);
                System.out.println("Check insertTaskIsSuccess :" + insertTaskIsSuccess);
                if (insertTaskIsSuccess) {
                    req.getSession().setAttribute("message", "Success!");
                    consequence = "success";
                    url = "/task";
                }
            }
        }
        if (consequence.equalsIgnoreCase("failed")) {
            req.setAttribute("jobName", req.getParameter("jobName"));
            req.setAttribute("dateBegin", req.getParameter("dateBegin"));
            req.setAttribute("dateEnd", req.getParameter("dateEnd"));
        }
        System.out.println("Check url :" + url);
        req.setAttribute("consequence", consequence);
        req.getSession().setAttribute("messageContent", "true");
        if (insertTaskIsSuccess) {
            resp.sendRedirect(req.getContextPath() + url);
        } else {
            req.getRequestDispatcher(url).forward(req, resp);

        }
    }

    public void taskModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url = "/task/mod";
        String methodCustom = "";
        boolean returnData = false;
        boolean updateIsSuccess = false;
        methodCustom = (req.getParameter("methodCustom") != null) ? req.getParameter("methodCustom") : methodCustom;
        System.out.println("check methodCustom :" + methodCustom);
        if (methodCustom.equalsIgnoreCase("get")) {
            returnData = true;
            System.out.println("Check jobId :" + req.getParameter("jobId"));
            url = "/task-mod.jsp";
        } else if (methodCustom.equalsIgnoreCase("post")) {
            // older workOn
            WorkOnModel workOnCurrent = new WorkOnModel();
            workOnCurrent.getUsers().setId(req.getParameter("userModifyId"));
            workOnCurrent.getJobs().setId(req.getParameter("jobModifyId"));
            workOnCurrent.getProjects().setId(req.getParameter("projectModifyId"));
            workOnCurrent.getStatus().setId(req.getParameter("statusModifyId"));
            // new workOn
            WorkOnModel workOnModify = new WorkOnModel();
            workOnModify.getUsers().setId(req.getParameter("userId"));
            workOnModify.getJobs().setJobName(req.getParameter("jobName"));
            workOnModify.getProjects().setId(req.getParameter("projectId"));
            workOnModify.getStatus().setId(req.getParameter("statusId"));
            workOnModify.setDateBegin(req.getParameter("dateBegin"));
            workOnModify.setDateEnd(req.getParameter("dateEnd"));
            if (taskService.checkInputData(req, resp, workOnModify)) {
                System.out.println("checkInputData :true");
                if (taskService.updateWorkOn(workOnCurrent, workOnModify)) {
                    updateIsSuccess = true;
                    url = "/task";
                    req.getSession().setAttribute("message", "Modify is success!");
                } else {
                    returnData = true;
                }
            }

        }

        if (returnData) {
            ArrayList<ProjectsModel> projectList = taskService.getProjectListById(req.getParameter("projectId"));
            ArrayList<UsersModel> userList = taskService.getUserList();
            ArrayList<StatusModel> statusList = taskService.getStatusList();
            req.setAttribute("statusList", statusList);
            req.setAttribute("projectList", projectList);
            req.setAttribute("userList", userList);
            req.setAttribute("userModifyId", req.getParameter("userId"));
            req.setAttribute("jobModifyId", req.getParameter("jobId"));
            req.setAttribute("projectModifyId", req.getParameter("projectId"));
            req.setAttribute("statusModifyId", req.getParameter("statusId"));
            req.setAttribute("jobName", req.getParameter("jobName"));
            req.setAttribute("dateBegin", req.getParameter("dateBegin"));
            req.setAttribute("dateEnd", req.getParameter("dateEnd"));
        }
        req.getSession().setAttribute("messageContent", "true");
        if (updateIsSuccess) {
            resp.sendRedirect(req.getContextPath() + url);
        } else {
            req.getRequestDispatcher(url).forward(req, resp);

        }

    }

    public void taskDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "";
        String methodCustom = "";
        String message = "";
        methodCustom = (req.getParameter("methodCustom") != null) ? req.getParameter("methodCustom") : methodCustom;
        if (methodCustom.equalsIgnoreCase("post")) {
            // delete
            System.out.println("access");

            WorkOnModel workOn = new WorkOnModel();
            workOn.getUsers().setId(req.getParameter("userId"));
            workOn.getJobs().setId(req.getParameter("jobId"));
            workOn.getStatus().setId(req.getParameter("statusId"));
            workOn.getProjects().setId(req.getParameter("projectId"));
            System.out.println("Check userId:" + req.getParameter("userId"));
            System.out.println("Check jobId:" + req.getParameter("jobId"));
            System.out.println("Check statusId:" + req.getParameter("statusId"));
            System.out.println("Check projectId:" + req.getParameter("projectId"));
            if (taskService.deleteWorkOnById(workOn)) {
                message = "true";

            }else {
                message = "false";
            }
        }
        req.getSession().setAttribute("messageContent", "true");
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(message);
    }
}
