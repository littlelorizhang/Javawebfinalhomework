package domain;

public class Operate {
    String oid;
    String o_uid;
    String otype;//操作类型
    String oip;
    String otime;

    public String getOtime() {
        return otime;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public void setO_uid(String o_uid) {
        this.o_uid = o_uid;
    }

    public String getOid() {
        return oid;
    }

    public String getO_uid() {
        return o_uid;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public String getOip() {
        return oip;
    }

    public String getOtype() {
        return otype;
    }

    public void setOip(String oip) {
        this.oip = oip;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }
}
