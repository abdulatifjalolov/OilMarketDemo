package service;

import model.PaymentType;

import java.util.ArrayList;
import java.util.List;

public class PaymentTypeService implements BaseService{
    static List<PaymentType>paymentTypes=new ArrayList<>();

    public boolean add(PaymentType paymentType){
        for (PaymentType type : paymentTypes) {
            if (paymentType==null || paymentType.getName().equals(type.getName())){
                return false;
            }
        }
        if (paymentType!=null){
            paymentTypes.add(paymentType);
        }
        return true;
    }
    public String getPaymentTypeNameById(int id){
        for (PaymentType paymentType : paymentTypes) {
            if (paymentType.getId()==id) {
                return paymentType.getName();
            }
        }
        return null;
    }
    public void showPaymentTypeList(){
        for (PaymentType paymentType : paymentTypes) {
            if (paymentType!=null){
                System.out.println(paymentType);
            }
        }
    }

    @Override
    public boolean delete(int id) {
        for (PaymentType paymentType : paymentTypes) {
            if (paymentType.getId()==id){
                paymentTypes.remove(paymentType);
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getById(int id) {
        for (PaymentType paymentType : paymentTypes) {
            if (paymentType.getId()==id){
                return paymentType;
            }
        }
        return null;
    }
}
