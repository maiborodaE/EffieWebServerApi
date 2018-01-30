package Manufacturers;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
public class UserCredentials {
    public  String login = "userName";
    public  String pswrd = "password";

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public static String cookies = "SESSION=3d9482b4-3f6b-4a29-88d3-497b3675d887";

    public static String serverName = "nginx";

    public static Date date = new Date();
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    public static String randomName = dateFormat.format( date);



    public String getRandomName() {
        return randomName;
    }

    public void setRandomName(String randomName) {
        this.randomName = randomName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }
}

