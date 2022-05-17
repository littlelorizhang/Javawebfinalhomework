package domain;

public class Product {
    private String pid;
    private String pname;
    //因为是两元店就不设置价格
    private int pnum;//库存
    private String ptype;//类别
    private  int psale;//销量
    private int price;

    public int getPnum() {
        return pnum;
    }

    public int getPsale() {
        return psale;
    }

    public String getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public void setPsale(int psale) {
        this.psale = psale;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
