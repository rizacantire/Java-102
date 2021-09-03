package business.concretes;

import business.abstracts.Insurance;

import java.util.Date;

public class TravelInsurance extends Insurance {
    private final double travelInsurance = 0.5;
    public TravelInsurance(String insuranceName, double insurancePrice, Date startDate, Date endDate) {
        super(insuranceName, insurancePrice, startDate, endDate);
    }

    @Override
    public double calculate() {

        return travelInsurance * this.getInsurancePrice();
    }
}
