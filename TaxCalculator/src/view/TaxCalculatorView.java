package view;

import model.TaxData;
import service.TaxCalculationService;

import java.util.Scanner;

/**
 * 税务计算器视图(Tax Calculator View)
 * 提供个人所得税计算的用户交互界面
 * (Provides user interface for personal income tax calculation)
 */
public class TaxCalculatorView {
    // 税务计算服务实例(Tax calculation service instance)
    private TaxCalculationService taxService;
    // 输入扫描器(Input scanner)
    private Scanner scanner;

    /**
     * 构造函数，初始化税务计算器视图(Constructor to initialize tax calculator view)
     *
     * @param taxService 税务计算服务实例(Tax calculation service instance)
     */
    public TaxCalculatorView(TaxCalculationService taxService) {
        this.taxService = taxService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * 显示主菜单(Display main menu)
     * 显示选项菜单并处理用户选择(Displays option menu and handles user selection)
     */
    public void show() {
        while (true) {
            System.out.println("\n=== 个人所得税计算器(Tax Calculator) ===");
            System.out.println("1. 计算税额(Calculate Tax)");
            System.out.println("2. 查看计算详情(View Calculation Details)");
            System.out.println("3. 退出(Exit)");
            System.out.print("请选择操作(Choose an option): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 消耗换行符(Consume newline)

            switch (choice) {
                case 1:
                    calculateTax();
                    break;
                case 2:
                    showCalculationDetails();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("无效选项，请重试(Invalid option. Please try again).");
            }
        }
    }

    /**
     * 计算个人所得税(Calculate personal income tax)
     * 获取用户输入的税务数据并计算应缴税额(Gets tax data input from user and calculates tax payable)
     */
    private void calculateTax() {
        TaxData taxData = getTaxDataInput();
        double tax = taxService.calculateTax(taxData);
        System.out.printf("\n您预计应缴纳的个人所得税为(Your estimated income tax payable is): %.2f\n", tax);
    }

    /**
     * 显示税务计算详情(Show tax calculation details)
     * 获取用户输入的税务数据并显示详细计算过程(Gets tax data input from user and displays detailed calculation process)
     */
    private void showCalculationDetails() {
        TaxData taxData = getTaxDataInput();
        String details = taxService.getCalculationDetails(taxData);
        System.out.println("\n" + details);
    }

    /**
     * 获取税务数据输入(Get tax data input)
     * 从用户获取各项收入和扣除项数据(Gets various income and deduction data from user)
     *
     * @return 包含用户输入数据的TaxData对象(TaxData object containing user input data)
     */
    private TaxData getTaxDataInput() {
        System.out.print("请输入工资收入(Enter salary income): ");
        double salary = scanner.nextDouble();
        System.out.print("请输入奖金收入(Enter bonus income): ");
        double bonus = scanner.nextDouble();
        System.out.print("请输入社保扣除(Enter social security deductions): ");
        double socialSecurity = scanner.nextDouble();
        System.out.print("请输入公积金扣除(Enter provident fund deductions): ");
        double providentFund = scanner.nextDouble();
        System.out.print("请输入其他扣除(Enter other deductions): ");
        double otherDeductions = scanner.nextDouble();

        return new TaxData(salary, bonus, socialSecurity, providentFund, otherDeductions);
    }
}
