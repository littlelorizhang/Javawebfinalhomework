package domain;

public class Record {
    String rid;
    String r_uid;
    String r_pid;
    String r_pname;
    String r_ptype;
    int rtime;

    public int getRtime() {
        return rtime;
    }

    public String getR_pid() {
        return r_pid;
    }

    public String getR_pname() {
        return r_pname;
    }

    public String getR_uid() {
        return r_uid;
    }

    public String getR_ptype() {
        return r_ptype;
    }

    public String getRid() {
        return rid;
    }

    public void setR_pid(String r_pid) {
        this.r_pid = r_pid;
    }

    public void setR_pname(String r_pname) {
        this.r_pname = r_pname;
    }

    public void setR_ptype(String r_ptype) {
        this.r_ptype = r_ptype;
    }

    public void setR_uid(String r_uid) {
        this.r_uid = r_uid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public void setRtime(int rtime) {
        this.rtime = rtime;
    }
}
