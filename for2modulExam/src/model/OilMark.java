package model;

public class OilMark extends Base{
    private int price;
    private int oilType;
    private int totalAmount;

    public OilMark() {}

    public int getOilType() {
        return oilType;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOilType(int oilType) {
        this.oilType = oilType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "OilMark{" +
                "price=" + price +
                ", oilType=" + oilType +
                ", totalAmount=" + totalAmount +
                ", id=" + id +
                '}';
    }
}
