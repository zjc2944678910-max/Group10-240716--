package controller;

import service.AuthService;
import view.LoginView;

public class AuthController {
    private AuthService authService; // 认证服务，处理认证业务逻辑
    private LoginView loginView; // 登录视图，负责与用户交互

    public AuthController() {
        this.authService = new AuthService(); // 初始化认证服务
        this.loginView = new LoginView(authService); // 初始化登录视图，传入认证服务实例
    }

    public boolean authenticateUser() {
        return loginView.show(); // 调用视图的显示方法并返回认证结果
    }
}
