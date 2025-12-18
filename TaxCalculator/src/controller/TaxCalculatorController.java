package controller;

import service.TaxCalculationService;
import view.TaxCalculatorView;

public class TaxCalculatorController {
    private TaxCalculationService taxService; // 税务计算服务
    private TaxCalculatorView taxView; // 税务计算器视图


    public TaxCalculatorController() {
        this.taxService = new TaxCalculationService(); // 初始化税务计算服务
        this.taxView = new TaxCalculatorView(taxService); // 初始化税务计算器视图，传入服务实例
    }


    public void start() {
        taxView.show(); // 调用视图的显示方法
    }
}
