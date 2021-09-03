package entities.accounts;

import business.abstracts.Insurance;

public class Individual extends Account {
    @Override
    public void insuranceAdd(Insurance insurance) {
        this.getInsurances().add(insurance);
    }

    public Individual(User user) {

        this.setUser(user);
    }

}
