package Model;

import com.google.gson.Gson;

import javax.servlet.http.Cookie;
import java.net.URLEncoder;

public class UsersModel {
    private String id;
    private String email;
    private String password;
    private String fullName;
    private String avatar;
    private RolesModel rolesModel;
    private String country;
    private String phone;
    private String gender;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    // getter and setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public RolesModel getRolesModel() {
        return rolesModel;
    }

    public void setRolesModel(RolesModel rolesModel) {
        this.rolesModel = rolesModel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public UsersModel(String id, String email, String password, String fullName, String avatar, RolesModel rolesModel, String country, String phone, String gender) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.rolesModel = rolesModel;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
    }

    public UsersModel(String id, String email, String password, String fullName, String avatar, RolesModel rolesModel, String country) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.rolesModel = rolesModel;
        this.country = country;
    }

    public UsersModel(String email, String password, String fullName, String avatar, RolesModel rolesModel, String country) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.rolesModel = rolesModel;
        this.country = country;
    }

    public UsersModel(String id, String email, String password, String fullName, String avatar, RolesModel rolesModel, String country, String phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.rolesModel = rolesModel;
        this.country = country;
        this.phone = phone;
    }

    public UsersModel(String email, String password, String fullName, String avatar, RolesModel rolesModel, String country, String phone) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.rolesModel = rolesModel;
        this.country = country;
        this.phone = phone;
    }

    public UsersModel(String email, String password, String fullName, String avatar, RolesModel rolesModel, String country, String phone, String gender) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.avatar = avatar;
        this.rolesModel = rolesModel;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
    }

    public UsersModel(String id) {
        this.id = id;
    }

    public UsersModel() {
    }
}


