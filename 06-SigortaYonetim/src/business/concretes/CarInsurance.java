package business.concretes;

import business.abstracts.Insurance;

import java.util.Date;

public class CarInsurance extends Insurance {
    private final double carInsurancePrice = 1;
    public CarInsurance(String insuranceName, double insurancePrice, Date startDate, Date endDate) {
        super(insuranceName, insurancePrice, startDate, endDate);
    }

    @Override
    public double calculate() {

        return carInsurancePrice * this.getInsurancePrice();
    }
}
