package service;

import model.OilMark;

import java.util.ArrayList;
import java.util.List;

public class OilMarkService implements BaseService{
    static List<OilMark>oilMarks=new ArrayList<>();

    public boolean add(OilMark oilMark){
        for (OilMark mark : oilMarks) {
            if (mark!=null) {
                if (mark.getOilType() == oilMark.getOilType()) {
                    return false;
                }
            }
        }
        if (oilMark!=null){
            oilMarks.add(oilMark);
            return true;
        }
        return false;
    }
    public OilMark getByType(int type){
        for (OilMark oilMark : oilMarks) {
            if (oilMark!=null){
                if (oilMark.getOilType()==type) {
                    return oilMark;
                }
            }
        }
        return null;
    }
    public void showOilMarksList(){
        for (OilMark oilMark : oilMarks) {
            if (oilMark!=null){
                System.out.println(oilMark);
            }
        }
    }
    public void showOilMarkByType(int oilMarkType){
        for (OilMark oilMark : oilMarks) {
            if (oilMark!=null){
                if (oilMark.getOilType()==oilMarkType){
                    System.out.println(oilMark);
                    return;
                }
            }
        }
    }
    @Override
    public boolean delete(int id) {
        for (OilMark oilMark : oilMarks) {
            if (oilMark.getId()==id){
                oilMarks.remove(oilMark);
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getById(int id) {
        for (OilMark oilMark : oilMarks) {
            if (oilMark!=null){
                if (oilMark.getId()==id) {
                    return oilMark;
                }
            }
        }
        return null;
    }
}
