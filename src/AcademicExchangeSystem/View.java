package AcademicExchangeSystem;

import DAO.Entity.AcademicExchangeActivitiy;

import java.util.List;

public class View {
    static void printActivity(String str){
        System.out.println(str);
    }
    static void printActivity(List lst){
        System.out.println(lst);
    }
    static void printActivity(AcademicExchangeActivitiy aea){
        System.out.println(aea);
    }
}
