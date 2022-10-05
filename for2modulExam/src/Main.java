import model.*;
import service.*;

import java.util.Scanner;

public class Main {
    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);
    static UserService userService = new UserService();
    static CarService carService=new CarService();
    static OilMarkService oilMarkService=new OilMarkService();
    static PaymentTypeService paymentTypeService=new PaymentTypeService();
    static FIllOilHistoryService fIllOilHistoryService=new FIllOilHistoryService();

    public static void main(String[] args) {
        int var1 = 10;
        while (var1 != 0) {
            System.out.println("1.LOG IN 2.REGISTRATION 0.BACK");
            var1 = scannerInt.nextInt();
            switch (var1) {
                case 1 -> {
                    System.out.println("ENTER PHONE NUMBER:");
                    String phoneNUmber = scannerStr.nextLine();
                    System.out.println("ENTER PASSWORD:");
                    String password = scannerStr.nextLine();
                    User currentUser = userService.login(phoneNUmber, password);
                    if (currentUser != null) {
                        if (currentUser.getRole().equals("ADMIN")) {
                            forAdmin();
                        }
                        if (currentUser.getRole().equals("USER")) {
                                int var7=10;
                                while (var7!=0){
                                    System.out.println("1.CHOOSE CAR 2.TURN ON CAR 3.TURN OFF CAR 4.DRIVE CAR 5.FILL CAR(oil) 6.FILL BALANCE 7.FILL OIL HISTORY 0.BACK");
                                    var7=scannerInt.nextInt();
                                    switch (var7){
                                        case 1->{
                                            carService.showCarsList();
                                            System.out.println("ENTER CAR ID:");
                                            Car currentCar= (Car) carService.getById(scannerInt.nextInt());
                                            if (currentCar!=null) {
                                                currentCar.setUserId(currentUser.getId());
                                                System.out.println("CHOOSED SUCCESFULLY");
                                            }
                                        }
                                        case 2->{
                                            Car currentCar=carService.getCarByUserId(currentUser.getId());
                                            if (currentCar.getOilInTank()>0) {
                                                currentCar.setTurnOn(true);
                                                System.out.println("CAR IS TURNED ON");
                                            }
                                        }
                                        case 3->{
                                            Car currentCar=carService.getCarByUserId(currentUser.getId());
                                            if (!currentCar.isTurnOn()){
                                                System.out.println("CAR ALREADY TURNED OFF");
                                            }else {
                                                currentCar.setTurnOn(false);
                                                System.out.println("CAR IS TURNED OFF");
                                            }
                                        }
                                        case 4->{
                                            Car currentCar=carService.getCarByUserId(currentUser.getId());
                                            if (currentCar.isTurnOn()){
                                                currentCar.setDrive(true);
                                                System.out.println("DRIVING");
                                            }else {
                                                currentCar.setDrive(false);
                                                System.out.println("SMTH FALSE NOT DRIVING");
                                            }

                                        }
                                        case 5->{
                                            Car currentCar=carService.getCarByUserId(currentUser.getId());

                                            System.out.println("NOW AVAILABLE OIL IN TANK: "+currentCar.getOilInTank());
                                            System.out.println("NOW AVAILABLE BALANCE: "+ currentUser.getBalance());

                                            System.out.println("CHOOSE OIL TYPE(80(esli ti v uzb), 91(esli ti v uzb i u tebya MALIBU), 95(not recommended))");
                                            int currentOilMarkType=scannerInt.nextInt();
                                            //oilMarkService.showOilMarkByType(currentOilMarkType);
                                            OilMark currentOilMark= oilMarkService.getByType(currentOilMarkType);

                                            int var11=10;
                                            while (var11!=0){
                                                System.out.println("1.FULL TANK 2.ENTER SUM 3.ENTER LITRE 0.BACK");
                                                var11=scannerInt.nextInt();
                                                switch (var11){
                                                    case 1->{
                                                        FillOilHistory fillOilHistory=new FillOilHistory();
                                                        int filledLitre=currentCar.getCapacityOfTank()-currentCar.getOilInTank();
                                                        if (filledLitre*currentOilMark.getPrice()<currentUser.getBalance()){
                                                            if (filledLitre<currentOilMark.getTotalAmount()){
                                                                currentOilMark.setTotalAmount(currentOilMark.getTotalAmount()-filledLitre);
                                                                currentUser.setBalance(currentUser.getBalance()-filledLitre*currentOilMark.getPrice());
                                                                currentCar.setOilInTank(currentCar.getOilInTank()+filledLitre);
                                                                fillOilHistory.setUserId(currentUser.getId());
                                                                System.out.println("ENTER PAYMENT TYPE:");
                                                                String currentPaymentType=scannerStr.nextLine();
                                                                fillOilHistory.setPaymentType(currentPaymentType);
                                                                fillOilHistory.setName(currentUser.getName());
                                                                fIllOilHistoryService.add(fillOilHistory);
                                                                System.out.println("SUCCESSFULLY FILLED");
                                                            }else {
                                                                System.out.println("SORRY WE DON'T HAVE SUCH AMOUNT");
                                                            }
                                                        }else {
                                                            System.out.println("NOT ENOUGH BALANCE");
                                                            System.out.println("1.FILL ALL BALANCE 2.NOT FILL");
                                                            int var12=scannerInt.nextInt();
                                                            if (var12==1){
                                                                int currentFilledLitre= (int) (currentUser.getBalance()/currentOilMark.getPrice());
                                                                currentOilMark.setTotalAmount(currentOilMark.getTotalAmount()-currentFilledLitre);
                                                                currentUser.setBalance(currentUser.getBalance()-currentFilledLitre*currentOilMark.getPrice());
                                                                currentCar.setOilInTank(currentCar.getOilInTank()+currentFilledLitre);
                                                                fillOilHistory.setUserId(currentUser.getId());
                                                                System.out.println("ENTER PAYMENT TYPE:");
                                                                String currentPaymentType=scannerStr.nextLine();
                                                                fillOilHistory.setPaymentType(currentPaymentType);
                                                                fillOilHistory.setName(currentUser.getName());
                                                                fIllOilHistoryService.add(fillOilHistory);
                                                                System.out.println("FILLED SUCCESSFULLY");
                                                            } else if (var12==2) {
                                                                System.out.println("NOT FILLED");
                                                            }
                                                        }
                                                    }
                                                    case 2->{
                                                        FillOilHistory fillOilHistory=new FillOilHistory();
                                                        System.out.println("ENTER SUM:");
                                                        int currentSum=scannerInt.nextInt();
                                                        int currentLitre=currentSum/currentOilMark.getPrice();
                                                        if (currentLitre>currentCar.getCapacityOfTank()-currentCar.getOilInTank()) {
                                                            currentLitre=currentCar.getCapacityOfTank()-currentCar.getOilInTank();
                                                        }
                                                            if (currentOilMark.getTotalAmount() > currentLitre) {
                                                                currentUser.setBalance(currentUser.getBalance()-currentLitre*currentOilMark.getPrice());
                                                                currentOilMark.setTotalAmount(currentOilMark.getTotalAmount()-currentLitre);
                                                                currentCar.setOilInTank(currentCar.getOilInTank()+currentLitre);
                                                                fillOilHistory.setUserId(currentUser.getId());
                                                                System.out.println("ENTER PAYMENT TYPE:");
                                                                String currentPaymentType=scannerStr.nextLine();
                                                                fillOilHistory.setPaymentType(currentPaymentType);
                                                                fillOilHistory.setName(currentUser.getName());
                                                                fIllOilHistoryService.add(fillOilHistory);
                                                                System.out.println("SUCCESSFULLY FILLED");
                                                            } else {
                                                                System.out.println("SORRY NOT FILLED WE DON'T HAVE SUCH AMOUNT");
                                                            }
                                                    }
                                                    case 3->{
                                                        FillOilHistory fillOilHistory=new FillOilHistory();
                                                        System.out.println("ENTER LITRE:");
                                                        int currentLitre=scannerInt.nextInt();
                                                        if (currentLitre>currentCar.getCapacityOfTank()-currentCar.getOilInTank()) {
                                                            currentLitre=currentCar.getCapacityOfTank()-currentCar.getOilInTank();
                                                        }
                                                        if (currentOilMark.getTotalAmount() > currentLitre) {
                                                            currentUser.setBalance(currentUser.getBalance()-currentLitre*currentOilMark.getPrice());
                                                            currentOilMark.setTotalAmount(currentOilMark.getTotalAmount()-currentLitre);
                                                            currentCar.setOilInTank(currentCar.getOilInTank()+currentLitre);
                                                            fillOilHistory.setUserId(currentUser.getId());
                                                            System.out.println("ENTER PAYMENT TYPE:");
                                                            String currentPaymentType=scannerStr.nextLine();
                                                            fillOilHistory.setPaymentType(currentPaymentType);
                                                            fillOilHistory.setName(currentUser.getName());
                                                            fIllOilHistoryService.add(fillOilHistory);
                                                            System.out.println("SUCCESSFULLY FILLED");
                                                        } else {
                                                            System.out.println("SORRY NOT FILLED WE DON'T HAVE SUCH AMOUNT");
                                                        }
                                                    }
                                                    case 0->{}
                                                }
                                            }
                                        }
                                        case 6->{
                                            int var10=10;
                                            while (var10!=0) {
                                                System.out.println("1.FILL BALANCE 2.SHOW CURRENT BALANCE 0.BACK");
                                                var10=scannerInt.nextInt();
                                                switch (var10){
                                                    case 1->{
                                                        System.out.println("ENTER FILL BALANCE:");
                                                        currentUser.setBalance(currentUser.getBalance() + scannerInt.nextInt());
                                                        System.out.println("FILLED SUCCESSFULLY");
                                                    }
                                                    case 2->{
                                                        System.out.println("CURRENT BALANCE: " + currentUser.getBalance());
                                                    }
                                                    case 0->{}
                                                }


                                            }
                                        }
                                        case 7->{
                                            fIllOilHistoryService.showUsersFillOilHistory(currentUser.getId());
                                        }
                                        case 0->{}
                                    }
                                }
                        }
                    }else {
                        System.out.println("INCORRECT PASSWORD OR PHONE NUMBER");
                    }
                }
                case 2 -> {
                    User user = new User();

                    System.out.println("ENTER NAME: ");
                    user.setName(scannerStr.nextLine());

                    System.out.println("ENTER PHONE NUMBER: ");
                    user.setPhoneNumber(scannerStr.nextLine());

                    System.out.println("ENTER PASSWORD: ");
                    user.setPassword(scannerStr.nextLine());

                    System.out.println("ENTER ROLE: (1.ADMIN 2.USER)");
                    int var2 = scannerInt.nextInt();
                    if (var2 == 1) {
                        user.setRole("ADMIN");
                    } else if (var2 == 2) {
                        user.setRole("USER");
                    }
                    System.out.println(userService.add(user));
                }
                case 0 -> {
                }
            }
        }
    }

    //FOR ADMIN
    private static void forAdmin() {
        int var3 = 10;
        while (var3 != 0) {
            System.out.println("1.CAR 2.OIL MARK 3.PAYMENT TYPE 4.FILL OIL HISTORY(with PAYMENT TYPES) 0.BACK");
            var3 = scannerInt.nextInt();
            switch (var3) {
                case 1 -> {
                    forCarAdmin();
                }
                case 2 -> {
                    forOilMarkAdmin();
                }
                case 3->{
                    forPaymentTypeAdmin();
                }
                case 4->{
                    forFillOilHistory();
                }
                case 0 ->{}
            }
        }
    }

    private static void forFillOilHistory() {
        fIllOilHistoryService.showAllFillOilHistories();
    }

    private static void forPaymentTypeAdmin() {
        int var6=10;
        while (var6!=0){
            System.out.println("1.ADD PAYMENT TYPE 2.DELETE PAYMENT TYPE 3.PAYMENT TYPE LIST 0.BACK");
            var6=scannerInt.nextInt();
            switch (var6){
                case 1->{
                    PaymentType paymentType=new PaymentType();

                    System.out.println("ENTER PAYMENT TYPE NAME: ");
                    paymentType.setName(scannerStr.nextLine());

                    System.out.println(paymentTypeService.add(paymentType));
                }
                case 2->{
                    System.out.println("ENTER PAYMENT TYPE ID:");
                    System.out.println(paymentTypeService.delete(scannerInt.nextInt()));
                }
                case 3->{
                    paymentTypeService.showPaymentTypeList();
                }
                case 0->{}
            }
        }
    }
    private static void forCarAdmin() {
        int var4=10;
        while (var4!=0){
            System.out.println("1.ADD CAR 2.DELETE CAR 3.EDIT CAR 4.SHOW CARS LIST 0.BACK");
            var4=scannerInt.nextInt();
            switch (var4){
                case 1->{
                    Car car=new Car();

                    System.out.println("ENTER MODEL:");
                    car.setModel(scannerStr.nextLine());

                    System.out.println("ENTER BRAND:");
                    car.setBrand(scannerStr.nextLine());

                    System.out.println("ENTER PROBEG:");
                    car.setProbeg(scannerInt.nextInt());

                    System.out.println("ENTER OIL(for 100km):");
                    car.setFor100km(scannerInt.nextInt());

                    System.out.println("ENTER CAPACITY OF TANK:");
                    car.setCapacityOfTank(scannerInt.nextInt());

                    System.out.println("ENTER OIL(in tank):");
                    car.setOilInTank(scannerInt.nextInt());

                    car.setQR((int) (Math.random()*10000));

                    System.out.println(carService.add(car));
                }
                case 2->{
                    System.out.println("ENTER CAR ID:");
                    System.out.println(carService.delete(scannerInt.nextInt()));
                }
                case 3->{
                    System.out.println("ENTER CAR ID:");
                    Car currentCar= (Car) carService.getById(scannerInt.nextInt());

                    if (currentCar!=null) {
                        System.out.println("ENTER NEW MODEL:");
                        currentCar.setModel(scannerStr.nextLine());

                        System.out.println("ENTER NEW BRAND:");
                        currentCar.setBrand(scannerStr.nextLine());

                        System.out.println("ENTER NEW PROBEG:");
                        currentCar.setProbeg(scannerInt.nextInt());

                        System.out.println("ENTER NEW OIL(for 100km):");
                        currentCar.setFor100km(scannerInt.nextInt());

                        System.out.println("ENTER NEW OIL(in tank):");
                        currentCar.setOilInTank(scannerInt.nextInt());
                        System.out.println("SUCCESSFULLY EDITED");
                    }
                }
                case 4->{
                    carService.showCarsList();
                }
                case 0->{}
            }
        }
    }
    private static void forOilMarkAdmin(){
        int var5=10;
        while (var5!=0){
            System.out.println("1.ADD OIL MARK 2.DELETE OIL MARK 3.EDIT OIL MARK 4.SHOW OIL MARKS 0.BACK");
            var5=scannerInt.nextInt();
            switch (var5){
                case 1->{
                    OilMark oilMark=new OilMark();

                    System.out.println("ENTER OIL TYPE(80,91,95):");
                    oilMark.setOilType(scannerInt.nextInt());

                    System.out.println("ENTER TOTAL AMOUNT:");
                    oilMark.setTotalAmount(scannerInt.nextInt());

                    System.out.println("ENTER PRICE:");
                    oilMark.setPrice(scannerInt.nextInt());

                    System.out.println(oilMarkService.add(oilMark));
                }
                case 2->{
                    System.out.println("ENTER OIL MARK ID:");
                    System.out.println(oilMarkService.delete(scannerInt.nextInt()));
                }
                case 3->{
                    System.out.println("ENTER OIL MAR ID:");
                    OilMark currentOilMark= (OilMark) oilMarkService.getById(scannerInt.nextInt());
                    if (currentOilMark!=null) {
                        System.out.println("ENTER NEW TOTAL AMOUNT:");
                        currentOilMark.setTotalAmount(scannerInt.nextInt());

                        System.out.println("ENTER NEW PRICE:");
                        currentOilMark.setPrice(scannerInt.nextInt());
                        System.out.println("SUCCESSFULLY EDITED");
                    }
                }
                case 4->{
                    oilMarkService.showOilMarksList();
                }
                case 0->{}
            }
        }
    }
}