package domain;

public class User {
    private String uid;
    private  String uname;
    private  String upassword;
    private  String uemail;
    private  String urole;
    private  String uaddress;
    private String utype;

    public String getUaddress() {
        return uaddress;
    }

    public String getUemail() {
        return uemail;
    }

    public String getUid() {
        return uid;
    }

    public String getUname() {
        return uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public String getUrole() {
        return urole;
    }

    public void setUaddress(String uaddress) {
        this.uaddress = uaddress;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public void setUrole(String urole) {
        this.urole = urole;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }
}
