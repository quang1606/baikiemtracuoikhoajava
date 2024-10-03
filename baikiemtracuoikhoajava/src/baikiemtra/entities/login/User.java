package baikiemtra.entities.login;

import baikiemtra.entities.applicationmanagement.Role;

public class User {
    private static  int idAuto;
    private int id;
    private String useName;
    private String passWord;
    private String email;
    private Role role;

    public User(String useName, String passWord, String email, Role role) {
        this.id = ++idAuto;
        this.useName = useName;
        this.passWord = passWord;
        this.email = email;
        this.role = role;
    }

    public static int getIdAuto() {
        return idAuto;
    }

    public static void setIdAuto(int idAuto) {
        User.idAuto = idAuto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
