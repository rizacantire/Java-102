package business.concretes;

import business.abstracts.Insurance;

import java.util.Date;

public class HealthInsurance extends Insurance {
    private final double healthInsurancePrice = 1.5;

    public HealthInsurance(String insuranceName, double insurancePrice, Date startDate, Date endDate) {
        super(insuranceName, insurancePrice, startDate, endDate);
    }

    @Override
    public double calculate() {

        return healthInsurancePrice * this.getInsurancePrice();
    }
}
