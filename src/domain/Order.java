package domain;

public class Order {
    private String oid;
    private String o_pid;
    private String o_uid;
    private String otime;

    public String getO_pid() {
        return o_pid;
    }
    public String getO_uid() {
        return o_uid;
    }
    public String getOid() {
        return oid;
    }
    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public void setO_pid(String o_pid) {
        this.o_pid = o_pid;
    }

    public void setO_uid(String o_uid) {
        this.o_uid = o_uid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }




}
