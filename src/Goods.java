public class Goods {
    private String goodsname;
    private String goodsID;
    private String goodsquantity;
    private String goodsprice;

    public Goods() {
    }

    public Goods(String goodsname, String goodsID, String goodsquantity, String goodsprice) {
        this.goodsname = goodsname;
        this.goodsID = goodsID;
        this.goodsquantity = goodsquantity;
        this.goodsprice = goodsprice;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodsID() {
        return goodsID;
    }

    public void setGoodsID(String goodsID) {
        this.goodsID = goodsID;
    }

    public String getGoodsquantity() {
        return goodsquantity;
    }

    public void setGoodsquantity(String goodsquantity) {
        this.goodsquantity = goodsquantity;
    }

    public String getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(String goodsprice) {
        this.goodsprice = goodsprice;
    }
}

