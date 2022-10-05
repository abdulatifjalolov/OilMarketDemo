package service;

import model.FillOilHistory;

import java.util.ArrayList;
import java.util.List;

public class FIllOilHistoryService implements BaseService {
    static List<FillOilHistory>fillOilHistories=new ArrayList<>();

    public boolean add(FillOilHistory fillOilHistory){
        if (fillOilHistory!=null){
            fillOilHistories.add(fillOilHistory);
            return true;
        }
        return false;
    }
    public void showUsersFillOilHistory(int userId){
        for (FillOilHistory fillOilHistory : fillOilHistories) {
            if (fillOilHistory!=null) {
                if (fillOilHistory.getUserId()==userId){
                    System.out.println(fillOilHistory);
                }
            }
        }
    }
    public void showAllFillOilHistories(){
        for (FillOilHistory fillOilHistory : fillOilHistories) {
            if (fillOilHistory!=null){
                System.out.println(fillOilHistory);
            }
        }
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
