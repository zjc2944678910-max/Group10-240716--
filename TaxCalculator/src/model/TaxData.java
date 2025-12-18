package model;

import java.io.Serializable;

public class TaxData implements Serializable {
    // 序列化版本UID(Serialization version UID)
    private static final long serialVersionUID = 1L;
    // 工资收入(Salary income)
    private double salaryIncome;
    // 奖金收入(Bonus income)
    private double bonusIncome;
    // 社保缴纳金额(Social security contributions)
    private double socialSecurity;
    // 公积金缴纳金额(Housing provident fund contributions)
    private double providentFund;
    // 其他扣除项金额(Other deductions)
    private double otherDeductions;

    public TaxData(double salaryIncome, double bonusIncome,
                   double socialSecurity, double providentFund,
                   double otherDeductions) {
        this.salaryIncome = salaryIncome;
        this.bonusIncome = bonusIncome;
        this.socialSecurity = socialSecurity;
        this.providentFund = providentFund;
        this.otherDeductions = otherDeductions;
    }

    // Getters and setters
    // 获取工资收入(Get salary income)
    public double getSalaryIncome() {
        return salaryIncome;
    }

    // 获取奖金收入(Get bonus income)
    public double getBonusIncome() {
        return bonusIncome;
    }

    // 获取社保缴纳金额(Get social security contributions)
    public double getSocialSecurity() {
        return socialSecurity;
    }

    // 获取公积金缴纳金额(Get housing provident fund contributions)
    public double getProvidentFund() {
        return providentFund;
    }

    // 获取其他扣除项金额(Get other deductions)
    public double getOtherDeductions() {
        return otherDeductions;
    }

    // 设置工资收入(Set salary income)
    public void setSalaryIncome(double salaryIncome) {
        this.salaryIncome = salaryIncome;
    }

    // 设置奖金收入(Set bonus income)
    public void setBonusIncome(double bonusIncome) {
        this.bonusIncome = bonusIncome;
    }

    // 设置社保缴纳金额(Set social security contributions)
    public void setSocialSecurity(double socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    // 设置公积金缴纳金额(Set housing provident fund contributions)
    public void setProvidentFund(double providentFund) {
        this.providentFund = providentFund;
    }

    // 设置其他扣除项金额(Set other deductions)
    public void setOtherDeductions(double otherDeductions) {
        this.otherDeductions = otherDeductions;
    }
}
