package model;

public class FillOilHistory extends Base{
    private int userId;
    private String paymentType;

    public FillOilHistory() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }


    @Override
    public String toString() {
        return "FillOilHistory{" +
                "userId=" + userId +
                ", paymentType=" + paymentType +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
