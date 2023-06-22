package Controller;

import Config.MySqlConfig;
import Model.RolesModel;
import Service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name ="roleTableController",urlPatterns = {"/role","/role/add","/role/mod","/role/delete"})
public class RolesController extends HttpServlet {
    private RoleService roleService = new RoleService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("message","");
        switch (req.getServletPath()){
            case "/role" :
                roleTable(req, resp);
                break;
            case "/role/add" :
                addRole(req, resp);
                break;
            case "/role/delete" :
                deleteRoleAjax(req, resp);
                break;
            case "/role/mod" :
                modifyRole(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void roleTable(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        ArrayList <RolesModel> roleList = roleService.getRoleList();
        req.setAttribute("roleList",roleList);
        req.getRequestDispatcher("/role-table.jsp").forward(req,resp);
    }
    public void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "";
        String message = "";

        boolean insertSuccess = false;
        if(req.getMethod().equalsIgnoreCase("get")){
           url = "/role-add.jsp";
        } else {
            req.setCharacterEncoding("UTF-8");
            // get data and add data into object
            RolesModel role = new RolesModel(req.getParameter("roleName"),req.getParameter("description"));


             insertSuccess = roleService.insert(role);
            if(insertSuccess){
                message ="Success";
                url = "/role";
            } else {
                message = "Failed";
                url = "/role/add";
                req.setAttribute("description",role.getDescription());
                req.setAttribute("roleName",role.getName());
            }

        }

        req.setAttribute("message",message);
        req.getSession().setAttribute("message",message);

        if(insertSuccess){
            resp.sendRedirect(req.getContextPath()+url);
        }else {
            req.getRequestDispatcher(url).forward(req,resp);

        }
    }
    public void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getMethod().equalsIgnoreCase("post")){
            req.setCharacterEncoding("UTF-8");
            String roleId = req.getParameter("id");
            System.out.println("check roleId"+roleId);
            String message = "";
            boolean deleteIsSuccess = roleService.delete(new RolesModel(roleId));
            if(deleteIsSuccess){
                message ="Success";
                System.out.println("Check  deleteIsSuccess:"+message);
            } else {
                message = "Failed";
            }
            req.setAttribute("message",message);
        }

        req.getRequestDispatcher("/role").forward(req,resp);

    }
    public void deleteRoleAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = "";
        if(req.getMethod().equalsIgnoreCase("post")){
            req.setCharacterEncoding("UTF-8");
            String roleId = req.getParameter("id");
            System.out.println("check roleId"+roleId);

            boolean deleteIsSuccess = roleService.delete(new RolesModel(roleId));
            if(deleteIsSuccess){
                message ="true";
                System.out.println("Check  deleteIsSuccess:"+message);
            } else {
                message = "false";
            }
            req.setAttribute("message",message);
        }
        PrintWriter writer = resp.getWriter();
        writer.println(message);


    }
    public void modifyRole(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String methodCustom = "";
        methodCustom = (req.getParameter("methodCustom")==null)?methodCustom:req.getParameter("methodCustom");
        if(methodCustom.equalsIgnoreCase("get")){
            req.setAttribute("id",req.getParameter("id"));
            req.getRequestDispatcher("/role-mod.jsp").forward(req,resp);
        } else {
            // get and set data into object Roles
            RolesModel role = new RolesModel(req.getParameter("id"),req.getParameter("roleName"),req.getParameter("description"));
            String message = "";
            String url = "";
            if(role.getId()!=null&&!role.getId().isEmpty()){
                boolean modIsSuccess = roleService.update(role);
                System.out.println("Check modIsSuccess :"+modIsSuccess);
                if(modIsSuccess){
                    message ="Success";
                    url = "/role";
                } else {
                    message = "Failed";
                    url = "/role";
                    req.setAttribute("id",role.getId());
                    req.setAttribute("description",role.getDescription());
                    req.setAttribute("roleName",role.getName());
                }
            } else {
                System.out.println("id is null");
                url = "/role";
            }
            req.setAttribute("message",message);
            System.out.println("check url: "+url);
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }
}
