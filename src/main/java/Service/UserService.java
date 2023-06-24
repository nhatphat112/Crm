package Service;

import Dates.StringDate;
import Model.JobOfUserDetailsModel;
import Model.UserDetailsModel;
import Model.UsersModel;
import Model.RolesModel;

import Repository.ProjectsRepository;
import Repository.RolesRepository;
import Repository.UserDetailsRepository;
import Repository.UsersRepository;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class UserService {
    private UsersRepository usersRepository = new UsersRepository();
    private RolesRepository rolesRepository = new RolesRepository();
    private ProjectsRepository projectsRepository = new ProjectsRepository();
    public ArrayList<UsersModel> getUserList(){
        return usersRepository.sellectAll();
    }
    private String uploadDirectory;
    public void init(HttpServletRequest request) {
        // Thiết lập đường dẫn tới thư mục lưu trữ file
         uploadDirectory = request.getServletContext().getRealPath("/") + "uploads/";
    }



    public boolean isValidEmail(String email) {
        // Định dạng chuỗi regex cho email
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        // Tạo đối tượng Pattern từ chuỗi regex
        Pattern pattern = Pattern.compile(regex);

        // So khớp chuỗi email với Pattern
        Matcher matcher = pattern.matcher(email);

        // Trả về true nếu chuỗi email khớp với Pattern, ngược lại trả về false
        return matcher.matches();
    }
    private String generateUniqueFileName() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String originalFileName = "filename";
        String timeStamp = dateFormat.format(new Date());
        int lastDotIndex = originalFileName.lastIndexOf(".");
        String extension = "";
        if (lastDotIndex >= 0) {
            extension = originalFileName.substring(lastDotIndex);
        }
        String uniqueName = timeStamp + extension;
        return uniqueName;
    }
    public boolean insert(UsersModel user){

//        // Kiểm tra và tạo thư mục lưu trữ nếu nó chưa tồn tại
//        init(request);
//        File uploadDir = new File(uploadDirectory);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdirs();
//        }
//        try {
//
//            InputStream fileContent = filePart.getInputStream();
//            // get format file
//            String fileName = filePart.getSubmittedFileName();
//            int dotIndex = fileName.lastIndexOf(".");
//            String fileExtension = fileName.substring(dotIndex);
//            System.out.println(fileExtension); // ".jpg"
//            user.setAvatar(generateUniqueFileName()+".jpg");
//
//            // Ghi dữ liệu vào file
//            FileOutputStream outputStream = new FileOutputStream(uploadDirectory + user.getAvatar());
//            byte[] buffer = new byte[4096];
//            int bytesRead = -1;
//            while ((bytesRead = fileContent.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//            outputStream.close();
//            fileContent.close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return usersRepository.insert(user);
    }
    public boolean modifyUser(UsersModel user,HttpServletRequest req){
        user.setEmail(user.getEmail().toLowerCase());
        if(user.getRolesModel().getId().equalsIgnoreCase("3")){
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));

            System.out.println("Check projectList.size :"+projectsRepository.selectByUserId(user.getId()).size());
            if(projectsRepository.selectByUserId(user.getId()).size()!=0){
                req.setAttribute("message","User is the leader of the project, cannot delete or downgrade");
                return false;
            }
        }
        return  usersRepository.update(user);
    }
    public boolean deleteUser(UsersModel user,HttpServletRequest req){
            if(projectsRepository.selectByUserId(user.getId()).size()!=0){
                req.setAttribute("message","User is the leader of the project, cannot delete or downgrade");
                return false;
            }
        return usersRepository.deleteById(user);
    }
    public boolean toDoGet(String to){
        if(to!=null){
            if(to.equals("toDoGet")){
                return true;
            }
        }
        return false;
    }
    public ArrayList<RolesModel> getRoleList(){
        return rolesRepository.selectAll();
    }
    private UserDetailsRepository userDetailsRepository = new UserDetailsRepository();
    public UserDetailsModel getUserDetails(UsersModel user){
        UserDetailsModel userDetails = new UserDetailsModel();
        // get user
        ArrayList<UsersModel> userList = userDetailsRepository.selectUserById(user);
        System.out.println("Check userList :"+userList.size());
        for (UsersModel uItem : userList){
            user = uItem;
        }
        ArrayList<JobOfUserDetailsModel> jobOfUserDetailsList = userDetailsRepository.selectJobListByUserId(user);
        System.out.println("Check Size jobOfUserDetailsList :"+jobOfUserDetailsList.size());
        userDetails.setId(user.getId());
        userDetails.setEmail(user.getEmail());
        userDetails.setAvatar(user.getAvatar());
        userDetails.setFullName(user.getFullName());
        for (JobOfUserDetailsModel job : jobOfUserDetailsList){
            if(job.getStatus().getId().equals("1")) userDetails.getJobListNotStarted().add(job);
            else if(job.getStatus().getId().equals("2")) userDetails.getJobListProcessing().add(job);
            else userDetails.getJobListCompleted().add(job);
        }
        return userDetails;
    }
    public UserDetailsModel StringTimeStampToStringDate(UserDetailsModel userDetails){
        for (JobOfUserDetailsModel job : userDetails.getJobListNotStarted()){
            if(job.getDateBegin()!=null){
                if(!job.getDateBegin().isEmpty()){
                    job.setDateBegin(Dates.StringDate.stringTimeStampToStringDate(job.getDateBegin()));
                }
            }
            if(job.getDateEnd()!=null){
                if(!job.getDateEnd().isEmpty()){
                    job.setDateEnd(StringDate.stringTimeStampToStringDate(job.getDateEnd()));
                }
            }

        }
        for (JobOfUserDetailsModel job : userDetails.getJobListProcessing()){
            if(job.getDateBegin()!=null){
                if(!job.getDateBegin().isEmpty()){
                    job.setDateBegin(Dates.StringDate.stringTimeStampToStringDate(job.getDateBegin()));
                }
            }
            if(job.getDateEnd()!=null){
                if(!job.getDateEnd().isEmpty()){
                    job.setDateEnd(StringDate.stringTimeStampToStringDate(job.getDateEnd()));
                }
            }

        }
        for (JobOfUserDetailsModel job : userDetails.getJobListCompleted()){
            if(job.getDateBegin()!=null){
                if(!job.getDateBegin().isEmpty()){
                    job.setDateBegin(StringDate.stringTimeStampToStringDate(job.getDateBegin()));
                }
            }
            if(job.getDateEnd()!=null){
                if(!job.getDateEnd().isEmpty()){
                    job.setDateEnd(StringDate.stringTimeStampToStringDate(job.getDateEnd()));
                }
            }

        }
        return userDetails;
    }
    public int sizeList(ArrayList<JobOfUserDetailsModel> jobList){
        if(jobList!=null){
            return jobList.size();
        }
        return 0;
    }
    public int totalSizeList(int number1, int number2, int number3){
        return number1+number2+number3;
    }
    public double calPercent(int number, int total){
        if(total!=0){
            return (double) number*100/total;
        }
        else return 0;
    }
    public double formatTwoDecimals(double number ){
        return  (double) Math.round(number * 100) / 100;
    }
    public boolean checkIdValid(String id){
        if(id!=null){
            if(!id.isEmpty()) return true;
        }
        return false;
    }
    public boolean checkAddEmailExisted(UsersModel usersModel){
       return usersRepository.selectByEmail(usersModel.getEmail()).size()>0;

    }
    public boolean checkModEmailExisted(UsersModel usersModel, HttpServletRequest req){
        ArrayList<UsersModel> userList = usersRepository.selectByEmail(usersModel.getEmail());
        if(userList.size()==1) {
            if(!userList.get(0).getId().equalsIgnoreCase(usersModel.getId())){
                req.setAttribute("message","email already used by someone else ");
                return false;
            }

        }
       return true;

    }
    public ArrayList<UsersModel> getUserListByRoleId(String id){
        return usersRepository.selectByRoleId(id);
    }



}
