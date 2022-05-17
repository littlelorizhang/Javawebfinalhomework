package domain;

public class Cart {
    private String cid;
    private String c_uid;
    private String c_pid;
    private String c_pname;
    private int cnum;
    private int cprice;

    public int getCnum() {
        return cnum;
    }

    public int getCprice() {
        return cprice;
    }

    public String getC_pid() {
        return c_pid;
    }

    public String getC_pname() {
        return c_pname;
    }

    public String getC_uid() {
        return c_uid;
    }

    public String getCid() {
        return cid;
    }

    public void setC_pid(String c_pid) {
        this.c_pid = c_pid;
    }

    public void setC_pname(String c_pname) {
        this.c_pname = c_pname;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public void setC_uid(String c_uid) {
        this.c_uid = c_uid;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
    }

    public void setCprice(int cprice) {
        this.cprice = cprice;
    }
}
