package Gamers;

import java.util.List;

public class CaseAward {
    private boolean isSuccess;
    private int id;
    private String name;


    public boolean getIsIsSuccess() {
        return isSuccess;
    }

    public boolean setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
        return isSuccess;
    }

    public CaseAward(){}

    public CaseAward(int id, String name, boolean isSuccess) {
        this.id = id;
        this.name = name;
        this.isSuccess = isSuccess;
    }

    /*public static CaseAward[] caseAwards(){
        CaseAward[] caseAwards = new CaseAward[3];
        caseAwards[0] = new CaseAward(1,"Yemek",getIsSuccess());
        caseAwards[1] = new CaseAward(2,"Odun",getIsSuccess());
        caseAwards[2] = new CaseAward(3,"Su",getIsSuccess());

        return caseAwards;
    }*/

    /*public static CaseAward getAwardById(int id){
        for (CaseAward ca : CaseAward.caseAwards()) {
            if (ca.getId() == id) {
                return ca;
            }
        }
        return null;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
