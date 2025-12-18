package model;

import java.io.Serializable;

public class TaxRate implements Serializable {
    // 序列化版本UID(Serialization version UID)
    private static final long serialVersionUID = 1L;
    // 该税率级别的下限(The lower bound of this tax rate level)
    private double lowerBound;
    // 该税率级别的上限(The upper bound of this tax rate level)
    private double upperBound;
    // 适用税率(Applicable tax rate)
    private double rate;
    // 速算扣除数(Quick calculation deduction)
    private double quickDeduction;


    public TaxRate(double lowerBound, double upperBound, double rate, double quickDeduction) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.rate = rate;
        this.quickDeduction = quickDeduction;
    }

    // Getters
    // 获取该税率级别的下限(Get the lower bound of this tax rate level)
    public double getLowerBound() {
        return lowerBound;
    }

    // 获取该税率级别的上限(Get the upper bound of this tax rate level)
    public double getUpperBound() {
        return upperBound;
    }

    // 获取适用税率(Get the applicable tax rate)
    public double getRate() {
        return rate;
    }

    // 获取速算扣除数(Get the quick calculation deduction)
    public double getQuickDeduction() {
        return quickDeduction;
    }
}
