package domain;

public class Sale {
    private int scount;//卖了几件
    private String stime;//下单时间
    private String s_pname;//商品名称
    private String s_ptype;//商品类别

    public int getScount() {
        return scount;
    }

    public String getS_pname() {
        return s_pname;
    }

    public String getS_ptype() {
        return s_ptype;
    }

    public String getStime() {
        return stime;
    }

    public void setS_pname(String s_pname) {
        this.s_pname = s_pname;
    }

    public void setS_ptype(String s_ptype) {
        this.s_ptype = s_ptype;
    }

    public void setScount(int scount) {
        this.scount = scount;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }
}
