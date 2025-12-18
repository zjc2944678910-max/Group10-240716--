package view;

import service.AuthService;

import java.util.Scanner;

/**
 * 登录视图(Login View)
 * <p>
 * 提供用户登录和注册的交互界面
 * (Provides user interface for login and registration)
 */
public class LoginView {
    // 认证服务实例(Authentication service instance)
    private AuthService authService;
    // 输入扫描器(Input scanner)
    private Scanner scanner;

    /**
     * 构造函数，初始化登录视图
     * (Constructor to initialize login view)
     *
     * @param authService 认证服务实例(Authentication service instance)
     */
    public LoginView(AuthService authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * 显示登录主菜单
     * (Display login main menu)
     * <p>
     * 显示选项菜单并处理用户选择
     * (Displays option menu and handles user selection)
     *
     * @return 登录成功返回true，否则返回false
     * (Returns true if login succeeds, otherwise false)
     */
    public boolean show() {
        System.out.println("=== 个人所得税计算器(Income Tax Calculator) ===");
        System.out.println("1. 登录(Login)");
        System.out.println("2. 注册(Register)");
        System.out.println("3. 退出(Exit)");
        System.out.print("请选择选项(Choose an option): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // 消耗换行符(Consume newline)

        switch (choice) {
            case 1:
                return login();
            case 2:
                register();
                return false;
            case 3:
                System.exit(0);
            default:
                System.out.println("选项无效，请重新选择(Invalid option. Please try again).");
                return false;
        }
    }

    /**
     * 处理用户登录
     * (Handle user login)
     * <p>
     * 获取用户名和密码并调用认证服务进行验证
     * (Gets username and password and calls authentication service for verification)
     *
     * @return 认证成功返回true，失败返回false
     * (Returns true if authentication succeeds, false otherwise)
     */
    private boolean login() {
        System.out.print("用户名(Username): ");
        String username = scanner.nextLine();
        System.out.print("密码(Password): ");
        String password = scanner.nextLine();

        if (authService.authenticate(username, password)) {
            System.out.println("登录成功(Login successful)!");
            return true;
        } else {
            System.out.println("用户名或密码错误(Invalid username or password).");
            return false;
        }
    }

    /**
     * 处理用户注册
     * (Handle user registration)
     * <p>
     * 获取新用户名和密码并调用认证服务进行注册
     * (Gets new username and password and calls authentication service for registration)
     */
    private void register() {
        System.out.print("新用户名(New Username): ");
        String username = scanner.nextLine();
        System.out.print("新密码(New Password): ");
        String password = scanner.nextLine();

        if (authService.register(username, password)) {
            System.out.println("注册成功！现在可以登录(Registration successful! You can now login).");
        } else {
            System.out.println("用户名已存在，请尝试其他用户名(Username already exists. Please try another username).");
        }
    }
}
