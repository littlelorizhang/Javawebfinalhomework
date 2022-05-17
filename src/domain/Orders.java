package domain;
//我是大冤种呜呜呜
public class Orders {
    String o_pid;
    String o_uid;
    String oid;
    String o_pname;
    String o_ptype;
    String otime;
    int onum;
    int oprice;

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public String getOtime() {
        return otime;
    }

    public void setOprice(int oprice) {
        this.oprice = oprice;
    }

    public void setOnum(int onum) {
        this.onum = onum;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setO_uid(String o_uid) {
        this.o_uid = o_uid;
    }

    public void setO_ptype(String o_ptype) {
        this.o_ptype = o_ptype;
    }

    public void setO_pid(String o_pid) {
        this.o_pid = o_pid;
    }

    public String getOid() {
        return oid;
    }

    public String getO_uid() {
        return o_uid;
    }

    public String getO_ptype() {
        return o_ptype;
    }

    public String getO_pid() {
        return o_pid;
    }

    public int getOprice() {
        return oprice;
    }

    public int getOnum() {
        return onum;
    }

    public String getO_pname() {
        return o_pname;
    }

    public void setO_pname(String o_pname) {
        this.o_pname = o_pname;
    }
}
