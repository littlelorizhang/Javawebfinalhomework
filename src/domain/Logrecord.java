package domain;

public class Logrecord {
    private String lid;
    private String l_uid;
    private String l_urole;
    private String logintime;
    private String lip;
    private String logouttime;

    public String getL_uid() {
        return l_uid;
    }

    public String getL_urole() {
        return l_urole;
    }

    public String getLid() {
        return lid;
    }

    public String getLip() {
        return lip;
    }



    public void setL_uid(String l_uid) {
        this.l_uid = l_uid;
    }

    public void setL_urole(String l_urole) {
        this.l_urole = l_urole;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public void setLip(String lip) {
        this.lip = lip;
    }

    public void setLogouttime(String logouttime) {
        this.logouttime = logouttime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public String getLogouttime() {
        return logouttime;
    }

    public String getLogintime() {
        return logintime;
    }
}
