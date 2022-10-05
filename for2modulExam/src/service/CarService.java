package service;

import model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService implements BaseService{
    static List<Car>cars=new ArrayList<>();


    public boolean add(Car car){
        for (Car car1 : cars) {
            if (car1!=null) {
                if (car.getQR() == car1.getQR()) {
                    return false;
                }
            }
        }
            if (car != null) {
                    cars.add(car);
                    return true;
            }
        return false;
    }
    public void showCarsList(){
        for (Car car : cars) {
            if (car!=null){
                System.out.println(car);
            }
        }
    }
    public Car getCarByUserId(int id){
        for (Car car : cars) {
            if (car!=null){
                if (car.getUserId()==id){
                    return car;
                }
            }
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        for (Car car : cars) {
            if (car.getId()==id){
                cars.remove(car);
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getById(int id) {
        for (Car car : cars) {
            if (car!=null){
                if (car.getId()==id){
                    return car;
                }
            }
        }
        return null;
    }
}
