package sample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arup3 on 5/16/2017.
 */
public class RepoModel implements Serializable{

    String name="";
    String permission = "No Permission Needed";
    String dependency = "No External Dependency";
    boolean isDeprecated = false;
    boolean isXML = false;
    String codeSnipet = "";
    String minSdk = "No SDK Limitation";
    String searchKey = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public boolean isDeprecated() {
        return isDeprecated;
    }

    public void setDeprecated(boolean deprecated) {
        isDeprecated = deprecated;
    }

    public boolean isXML() {
        return isXML;
    }

    public void setXML(boolean XML) {
        isXML = XML;
    }

    public String getCodeSnipet() {
        return codeSnipet;
    }

    public void setCodeSnipet(String codeSnipet) {
        this.codeSnipet = codeSnipet;
    }

    public String getMinSdk() {
        return minSdk;
    }

    public void setMinSdk(String minSdk) {
        this.minSdk = minSdk;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

}
