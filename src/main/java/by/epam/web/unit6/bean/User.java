package by.epam.web.unit6.bean;

import by.epam.web.unit6.bean.util.PasswordCreater;

import java.io.Serializable;
import java.util.Objects;


public class User implements Serializable {

    private int id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private Role role;
    private int balance;
    private boolean active;

    private String login;//нельзя хранить логин/пароль дольше, чем проходит авторизация
    private String password; //шифровать пароль

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role.toUpperCase());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    //шифрование пароля ПРОДУМАТЬ ЛОГИКУ
    public void setPassword(String password) {
        this.password = PasswordCreater.createPassword(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +

                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", balance=" + balance +
                ", active=" + active +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                getBalance() == user.getBalance() &&
                isActive() == user.isActive() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(getPhone(), user.getPhone()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                getRole() == user.getRole() &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getPhone(), getEmail(), getRole(), getBalance(), isActive(), getLogin(), getPassword());
    }
}
