package service;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService implements BaseService{
    static List<User> users=new ArrayList<>();

    public User login(String phoneNumber,String password){
        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
    public void showList(){
        for (User user : users) {
            if (user!=null){
                System.out.println(user);
            }
        }
    }

    public boolean add(User user) {
        for (User user1 : users) {
            if (user1!=null) {
                if (user1.getPhoneNumber().equals(user.getPhoneNumber())) {
                    return false;
                }
            }
        }
            if (user!=null) {
                users.add(user);
            }
        return true;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Object getById(int id) {
        return null;
    }
}
