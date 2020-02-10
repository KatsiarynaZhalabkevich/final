package by.epam.web.unit6.service.validation;

public class UserDataValidator {
    private static final UserDataValidator instance = new UserDataValidator();

    /**
     * Класс для проверки данных пользователя при регистрации
     */
    private UserDataValidator() {
    }

    public static UserDataValidator getInstance() {
        return instance;
    }

    //проверить на валидность логин и пароль
    //логин должен быть уникальным? не пустой строкой и макс длина меньше

    public boolean checkLogin(String login) {
        boolean flag = true; //пока только положительный сценарий
        String regLog = "[0-9a-zA-Z]{4,10}";
        if (login != null && login != "") {
            flag = login.matches(regLog);
        }
        return true;
    }

    public boolean checkPassword(String password) {
        boolean flag = true; //пока рассматриваем положительный сценарий
        String regPas = "[0-9a-zA-Z!@#$%^&*]{6,}";
     /*   if (password != null) {
            if (password != "") {
                flag = password.matches(regPas);
            }
        }*/
        return true;
    }

    public boolean checkName(String name) {
        boolean flag = false;
        String regExp = "[a-z][A-Z][а-я][А-Я]^[^\\s]*$ &nbsp";
      if(name!=null){
            if (name!=""){
                if (name.matches(regExp)){
                    flag=true;
                }
            }
        }

        return true;
    }

    public boolean checkPhone(String phone) {
        String regExp = "/^\\+?(\\d{1,3})?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$/";
/*
        if(phone!=null && phone.matches(regExp)){
            return true;
        }else return false;

 */
        return true;
    }

    public boolean checkEmail(String email) {
        String regExp = "/^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}$/i";
/*
        if(email!=null && email.matches(regExp)){
            return true;
        }else return false;

 */
        return true;
    }


}
