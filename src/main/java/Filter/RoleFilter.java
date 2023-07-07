package Filter;

import Model.UsersModel;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

@WebFilter(urlPatterns = {"/*"})
public class RoleFilter implements Filter {
    //         ROLE_ADMIN,ROLE_MANAGER ,  ROLE_USER
    private ArrayList<String> roleUserList;
    private ArrayList<String> roleManagerList;

    private void initRoleUserList() {
        this.roleUserList = new ArrayList<>();
        this.roleUserList.add("/profile/login");
        this.roleUserList.add("/profile/logout");
        this.roleUserList.add("/profile/mod");
        this.roleUserList.add("/profile");
        this.roleUserList.add("/dashboard");
        this.roleUserList.add("/css/customStyle.css");
        this.roleUserList.add("/js/announces.js");



    }

    private void initRoleManagerList() {
        initRoleUserList();
     this.roleManagerList = new ArrayList<>();
      for (String item : this.roleUserList){
          this.roleManagerList.add(item);
      }
        // management projects
        this.roleManagerList.add("/groupwork");
        this.roleManagerList.add("/groupwork/mod");
        this.roleManagerList.add("/groupwork/details");
        this.roleManagerList.add("/groupwork/delete");
        this.roleManagerList.add("/task");

        this.roleManagerList.add("/task/add");
        this.roleManagerList.add("/task/mod");
        this.roleManagerList.add("/user");
        this.roleManagerList.add("/user/delete");


        // management employees
        //
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        System.out.println("Check Servlet Path ------- :" + req.getServletPath());
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        System.out.println("active filter Role");
        // get User
        String url = "";
        Cookie[] cookies = req.getCookies();
        UsersModel user = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                System.out.println("check user is exist : true");
                String userJsonDecoded = URLDecoder.decode(cookie.getValue());
                Gson gson = new Gson();
                user = gson.fromJson(userJsonDecoded, UsersModel.class);
                break;
            }
        }
        boolean IsAllowAccess = false;
        String role = "";
        if (user != null) {
            role = user.getRolesModel().getName();

            String filePath = servletRequest.getServletContext().getRealPath("/") + "uploads/"+user.getAvatar();
            Path path = Paths.get(filePath);
            String contentType = Files.probeContentType(path);
            System.out.println("----- check image------");
            System.out.println("check filePath :"+filePath);
            servletRequest.setAttribute("roleUserId", user.getRolesModel().getId());
            servletRequest.setAttribute("userId", user.getId());
            servletRequest.setAttribute("contentType",contentType);
            servletRequest.setAttribute("avatarUser",user.getAvatar());



        } else {
           url = "/profile/login";
        }

        if (role.equals("ROLE_USER")) {
            if (this.roleUserList == null) {
                initRoleUserList();
            }

            String path = req.getServletPath();
            System.out.println("Check list RoleUSerList :");
            for (String item : roleUserList) {
                System.out.println(item);
            }
            for (String item : roleUserList) {

                if (path.equals(item)) {
                    System.out.println("item :"+item);
                    System.out.println("path:"+path);
                    System.out.println("check path----------user");
                    IsAllowAccess = true;
                    break;
                }
            }
            System.out.println("check Role :" + role);

        }  else if (role.equals("ROLE_MANAGER")) {


            if (this.roleManagerList == null) {
                initRoleManagerList();
            }

            String path = req.getServletPath();
            for (String item : roleManagerList) {
                if (path.equals(item)){
                    IsAllowAccess = true;
                    break;
                }
            }


        }else if (role.equals("ROLE_ADMIN")) {
            IsAllowAccess = true;

        }
        if(!IsAllowAccess) {
            url = "/404";
            if(user==null){
                url="/profile/login";
            }
            req.getRequestDispatcher(url).forward(req,resp);
        }
        else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
