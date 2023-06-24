package Service;

import Model.StatusModel;
import Model.UsersModel;
import Model.WorkOnModel;
import Repository.StatusRepository;
import Repository.UsersRepository;
import Repository.WorkOnRepository;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProfileService {
    private UsersRepository usersRepository = new UsersRepository();
    private WorkOnRepository workOnRepository = new WorkOnRepository();
    private StatusRepository statusRepository = new StatusRepository();
    public ArrayList<UsersModel> getUserList(String email, String password){
        return usersRepository.selectByEmailAndPassword(email,password);
    }
    public ArrayList<UsersModel> getUserListByEmail(String email){
       return usersRepository.selectByEmail(email);
    }
    public UsersModel checkLoginIsSuccess(String email,String password){
      try {
          for (UsersModel user : usersRepository.selectByEmail(email)){
              System.out.println("Check roleId at checkLoginIsSuccess :"+user.getRolesModel().getId());
              System.out.println("check password user :"+user.getPassword());

              if(BCrypt.checkpw(password,user.getPassword())){
                  System.out.println("Check password:"+user.getPassword());
                  return user;
              }
          }
      }catch (Exception e){
         System.out.println("error at checkLoginIsSuccess : "+e.getMessage());
      }
        return null;
    }
    public boolean createCookieLogin(HttpServletRequest req,HttpServletResponse resp, UsersModel usersModel){
        if(usersModel!=null){
            Gson gson = new Gson();
            // EndcodingURLE userJson
            usersModel.setPassword("");
            String userJsonEncoded = URLEncoder.encode(gson.toJson(usersModel));
            // add object user to cookie under shape Json
            Cookie userJsonCookie = new Cookie("user",userJsonEncoded);
            userJsonCookie.setMaxAge(-1);
            userJsonCookie.setPath(req.getContextPath());
            resp.addCookie(userJsonCookie);
            return true;

        }
        return false;
    }
    public boolean deleteCookieLogin(HttpServletRequest req, HttpServletResponse resp){
        System.out.println("-----------deleteCookieLogin-----------");
        Cookie [] cookies = req.getCookies();
        // browse cookieArray
        for (Cookie item : cookies){
            if(item.getName().equals("user")){
                System.out.println("--Delete cookie :"+item.getName());
                item.setMaxAge(0);
                item.setPath(req.getContextPath());
                resp.addCookie(item);
                System.out.println("Cookie user is deleted");
                return true;
            }
        }
        return false;
    }
    public UsersModel getUser( HttpServletRequest req) throws UnsupportedEncodingException {
        Cookie []cookies = req.getCookies();
        UsersModel user = null;
        for (Cookie item : cookies){
            if(item.getName().equalsIgnoreCase("user")){
                Gson gson = new Gson();
                    user = gson.fromJson(URLDecoder.decode(item.getValue()),UsersModel.class);
                    break;
            }
        }
        return user;
    }
    public ArrayList<WorkOnModel> getWorkOnList(String userId){
       ArrayList<WorkOnModel> workOnList = workOnRepository.selectByUserId(userId);
       // process date
        for (WorkOnModel work : workOnList){
            work.setDateBegin(Dates.StringDate.stringTimeStampToStringDate(work.getDateBegin()));
            work.setDateEnd(Dates.StringDate.stringTimeStampToStringDate(work.getDateEnd()));

        }
       return workOnList;
    }
    public double statusPercent(ArrayList<WorkOnModel> workOnList, String statusId){
        int count = 0;
        for (WorkOnModel workOn : workOnList){
            if(workOn.getStatus().getId().equalsIgnoreCase(statusId)){
                count++;
            }
        }
        if(workOnList.size()>0){
            return (double) count*100/workOnList.size();
        }
        return count;
    }
    public String formatTwoDecimals(double number){
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedNumber = df.format(number);
       return formattedNumber;
    }
    public ArrayList<StatusModel> getSatusList (){
        return statusRepository.selectAll();
    }
    public boolean updateStatusIdById(WorkOnModel workOn, String statusIdUpdate){
        return workOnRepository.updateStatusIdById(workOn,statusIdUpdate);
    }
    public WorkOnModel getWorkOn(WorkOnModel workOn){
        for (WorkOnModel work : workOnRepository.selectById(workOn)){
            work.setDateBegin(Dates.StringDate.stringTimeStampToStringDate(work.getDateBegin()));
            work.setDateEnd(Dates.StringDate.stringTimeStampToStringDate(work.getDateEnd()));
            return work;

        }
        return null ;

    }



}
