package view;

import controller.AuthController;
import controller.TaxCalculatorController;

public class MainApp {

    public static void main(String[] args) {
        // 创建认证控制器实例(Create authentication controller instance)
        AuthController authController = new AuthController();
        // 认证状态标志(Authentication status flag)
        boolean isAuthenticated = false;

        // 循环直到用户认证成功(Loop until user authentication succeeds)
        while (!isAuthenticated) {
            isAuthenticated = authController.authenticateUser();
        }

        // 创建税务计算器控制器实例(Create tax calculator controller instance)
        TaxCalculatorController taxController = new TaxCalculatorController();
        // 启动税务计算器应用(Start tax calculator application)
        taxController.start();
    }
}
