package business.concretes;

import business.abstracts.Insurance;

import java.util.Date;

public class ResidenceInsurance extends Insurance {
    private final double residenceInsurance = 0.75;
    public ResidenceInsurance(String insuranceName, double insurancePrice, Date startDate, Date endDate) {
        super(insuranceName, insurancePrice, startDate, endDate);
    }

    @Override
    public double calculate() {
        return residenceInsurance* this.getInsurancePrice();
    }
}
