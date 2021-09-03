package entities.accounts;

import business.abstracts.Insurance;

public class Enterprise extends Account{


    public Enterprise(User user) {
         this.setUser(user);
    }

    @Override
    public void insuranceAdd(Insurance insurance) {
        this.getInsurances().add(insurance);
    }

}
