package Model;

import com.google.gson.Gson;

public class RolesModel {
    private String id;
    private String name;
    private String description;
    // getter and setter


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RolesModel(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public RolesModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public RolesModel() {
    }

    public RolesModel(String id) {
        this.id = id;
    }
}
