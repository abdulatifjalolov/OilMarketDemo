package model;

public class Car extends Base{
    private String model;
    private String brand;
    private int probeg;
    private int for100km;
    private int oilInTank;
    private int capacityOfTank;
    private int QR;
    private int userId;
    private boolean isTurnOn=false;
    private boolean isDrive=false;

    public Car() {}

    public int getCapacityOfTank() {
        return capacityOfTank;
    }

    public void setCapacityOfTank(int capacityOfTank) {
        this.capacityOfTank = capacityOfTank;
    }

    public boolean isDrive() {
        return isDrive;
    }

    public void setDrive(boolean drive) {
        isDrive = drive;
    }

    public boolean isTurnOn() {
        return isTurnOn;
    }

    public void setTurnOn(boolean turnOn) {
        isTurnOn = turnOn;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getQR() {
        return QR;
    }

    public void setQR(int QR) {
        this.QR = QR;
    }

    public int getOilInTank() {
        return oilInTank;
    }

    public void setOilInTank(int oilInTank) {
        this.oilInTank = oilInTank;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getProbeg() {
        return probeg;
    }

    public void setProbeg(int probeg) {
        this.probeg = probeg;
    }

    public int getFor100km() {
        return for100km;
    }

    public void setFor100km(int for100km) {
        this.for100km = for100km;
    }


    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", probeg=" + probeg +
                ", for100km=" + for100km +
                ", oilInTank=" + oilInTank +
                ", capacityOfTank=" + capacityOfTank +
                ", QR=" + QR +
                ", userId=" + userId +
                ", isTurnOn=" + isTurnOn +
                ", isDrive=" + isDrive +
                ", id=" + id +'\'' +
                '}';
    }
}
