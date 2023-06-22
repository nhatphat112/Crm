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
import java.util.Base64;

@WebFilter(urlPatterns = {"/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // check login
        boolean loginIsSuccess = false;
        Cookie []cookies = req.getCookies();
        String username = "";
        for (Cookie cookie : cookies){
            System.out.println("check cookie name :"+cookie.getName());
            if(cookie.getName().equals("user")){

                loginIsSuccess = true;
                UsersModel usersModel = new Gson().fromJson(URLDecoder.decode(cookie.getValue()), UsersModel.class);
                username = usersModel.getFullName();
                servletRequest.setAttribute("username",username);
                servletRequest.setAttribute("roleUsernameId",usersModel.getRolesModel().getId());

                break;
            }
        }
        System.out.println("Login Success :"+loginIsSuccess);
        if(loginIsSuccess){
            if(req.getServletPath().startsWith("/profile/login")){
                req.getRequestDispatcher("/dashboard").forward(servletRequest,servletResponse);
            }
            else {

                filterChain.doFilter(servletRequest,servletResponse);
            }
        } else {
            req.getRequestDispatcher("/profile/login").forward(servletRequest,servletResponse);
        }


    }
}
