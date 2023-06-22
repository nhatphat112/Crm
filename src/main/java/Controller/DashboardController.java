package Controller;

import Model.ListJobOfUserModel;
import Model.WorkOnModel;
import Service.DashboardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "dashboardController", urlPatterns = "/dashboard")
public class DashboardController extends HttpServlet {
    private DashboardService dashboardService = new DashboardService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("messageContent")==null){
            req.getSession().setAttribute("message","");
            req.setAttribute("message","");


        }
        else if(req.getSession().getAttribute("messageContent")!=null){
            req.getSession().setAttribute("messageContent",null);
        }
        req.setCharacterEncoding("UTF-8");
        ArrayList<WorkOnModel> workOnList = dashboardService.getTaskList();
        // cal % CTCV
        int jobNotStarted = dashboardService.countjobByStatusId(workOnList,"1");
        int jobProcessing = dashboardService.countjobByStatusId(workOnList,"2");
        int jobCompleted = dashboardService.countjobByStatusId(workOnList,"3");
        int jobTotal = dashboardService.jobTotal(jobNotStarted,jobProcessing,jobCompleted);
        System.out.println("Check job total:"+jobTotal);

        double jobNotStartedPercent = dashboardService.calPercent(jobNotStarted,jobTotal);
        double jobJobProcessingPercent = dashboardService.calPercent(jobProcessing,jobTotal);
        double jobJobCompletedPercent = dashboardService.calPercent(jobCompleted,jobTotal);;

        String formattedjobNotStartedPercent = dashboardService.formaTwoDecimals(jobNotStartedPercent);
        String formattedjobJobProcessingPercent = dashboardService.formaTwoDecimals(jobJobProcessingPercent);
        String formattedjobJobCompletedPercent = dashboardService.formaTwoDecimals(jobJobCompletedPercent);
        // return data
        req.setAttribute("username",req.getAttribute("username"));
        req.setAttribute("jobNotStarted",jobNotStarted);
        req.setAttribute("jobProcessing",jobProcessing);
        req.setAttribute("jobCompleted",jobCompleted);

        req.setAttribute("formattedjobNotStartedPercent",formattedjobNotStartedPercent);
        req.setAttribute("formattedjobJobProcessingPercent",formattedjobJobProcessingPercent);
        req.setAttribute("formattedjobJobCompletedPercent",formattedjobJobCompletedPercent);
//        req.setAttribute("listJobOfUsersArrayList",listJobOfUsersArrayList);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
