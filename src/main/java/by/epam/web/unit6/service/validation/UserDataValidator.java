package by.epam.web.unit6.service.validation;

public class UserDataValidator {
    private static final UserDataValidator instance = new UserDataValidator();

    private UserDataValidator() {
    }
    public static UserDataValidator getInstance() {
        return instance;
    }

    //проверить на валидность логин и пароль
    //логин должен быть уникальным? не пустой строкой и макс длина меньше

    public boolean checkLoginPassword(String login, String password) {
        String regPas = "[0-9a-zA-Z!@#$%^&*]{6,}";
        if (login != null && password != null) {
            if (login != "" && password != "") {
                if (password.matches(regPas) && login.matches("[0-9a-zA-Z]{4,10}")) {
                    return true;
                }else return false;
            } else return false;
        } else return false;

    }

    public boolean checkName(String name){
        String regExp = "[a-z][A-Z][а-я][А-Я]^[^\\s]*$ &nbsp";
        if(name!=null){
            if (name!=""){
                if (name.matches(regExp)){
                    return true;
                }else return false;
            }else  return false;
        }else return false;

    }

    public boolean checkPhone(String phone){
        String regExp = "/^\\+?(\\d{1,3})?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$/";

        if(phone!=null && phone.matches(regExp)){
            return true;
        }else return false;
    }

    public boolean checkEmail (String email){
        String regExp = "/^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}$/i";

        if(email!=null && email.matches(regExp)){
            return true;
        }else return false;
    }



}
