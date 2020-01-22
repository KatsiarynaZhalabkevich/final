package by.epam.web.unit6.bean.util;

import java.util.Objects;

public class PasswordCreater {

    public static String createPassword(String password){
        return Integer.toString(Objects.hash(password));
    }
}
