package service;

import model.TaxData;
import model.TaxRate;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 税务计算服务(Tax Calculation Service)
 * <p>
 * 负责个人所得税的计算逻辑和税率数据的管理
 * (Responsible for personal income tax calculation logic and tax rate data management)
 */
public class TaxCalculationService {
    // 税率数据文件路径(Tax rate data file path)
    private static final String TAX_RATES_FILE = "data/tax_rates.dat";
    // 税率列表(Tax rate list)
    private List<TaxRate> taxRates;

    /**
     * 构造函数，初始化税率数据
     * (Constructor to initialize tax rate data)
     * <p>
     * 尝试从文件加载税率，如果加载失败则使用默认税率
     * (Attempts to load tax rates from file, uses default rates if loading fails)
     */
    public TaxCalculationService() {
        loadTaxRates();
        if (taxRates == null) {
            // 默认税率表（中国个人所得税税率示例）
            // (Default tax rate table (example for China individual income tax))
            taxRates = new ArrayList<>();
            taxRates.add(new TaxRate(0, 36000, 0.03, 0));
            taxRates.add(new TaxRate(36000, 144000, 0.10, 2520));
            taxRates.add(new TaxRate(144000, 300000, 0.20, 16920));
            taxRates.add(new TaxRate(300000, 420000, 0.25, 31920));
            taxRates.add(new TaxRate(420000, 660000, 0.30, 52920));
            taxRates.add(new TaxRate(660000, 960000, 0.35, 85920));
            taxRates.add(new TaxRate(960000, Double.MAX_VALUE, 0.45, 181920));
            saveTaxRates();
        }
    }

    /**
     * 从文件加载税率数据
     * (Load tax rate data from file)
     * <p>
     * 使用FileUtils工具类读取序列化对象
     * (Uses FileUtils utility class to read serialized object)
     */
    @SuppressWarnings("unchecked")
    private void loadTaxRates() {
        taxRates = (List<TaxRate>) FileUtils.readObjectFromFile(TAX_RATES_FILE);
    }

    /**
     * 保存税率数据到文件
     * (Save tax rate data to file)
     * <p>
     * 使用FileUtils工具类写入序列化对象
     * (Uses FileUtils utility class to write serialized object)
     */
    private void saveTaxRates() {
        FileUtils.writeObjectToFile(TAX_RATES_FILE, taxRates);
    }

    /**
     * 计算个人所得税
     * (Calculate personal income tax)
     * <p>
     * 根据输入的税务数据计算应缴税额
     * (Calculates tax payable based on input tax data)
     *
     * @param taxData 包含收入和扣除项的税务数据对象
     *                (Tax data object containing income and deductions)
     * @return 计算得出的应缴税额
     * (Calculated tax payable)
     */
    public double calculateTax(TaxData taxData) {
        // 计算总收入(Calculate total income)
        double totalIncome = taxData.getSalaryIncome() + taxData.getBonusIncome();
        // 计算总扣除项(Calculate total deductions)
        double totalDeductions = taxData.getSocialSecurity() +
                taxData.getProvidentFund() +
                taxData.getOtherDeductions();
        // 计算应纳税所得额（总收入 - 总扣除项 - 标准扣除额5000元）
        // (Calculate taxable income (total income - total deductions - standard deduction of 5000 yuan))
        double taxableIncome = totalIncome - totalDeductions - 5000;

        // 应纳税所得额小于等于0时，无需缴税
        // (No tax payable if taxable income is less than or equal to 0)
        if (taxableIncome <= 0) {
            return 0;
        }

        // 根据应纳税所得额查找适用税率并计算税额
        // (Find applicable tax rate and calculate tax based on taxable income)
        for (TaxRate rate : taxRates) {
            if (taxableIncome > rate.getLowerBound() && taxableIncome <= rate.getUpperBound()) {
                return taxableIncome * rate.getRate() - rate.getQuickDeduction();
            }
        }

        return 0;
    }

    /**
     * 获取税务计算详情
     * (Get tax calculation details)
     * <p>
     * 生成详细的计算过程和结果说明
     * (Generates detailed calculation process and result explanation)
     *
     * @param taxData 包含收入和扣除项的税务数据对象
     *                (Tax data object containing income and deductions)
     * @return 格式化的计算详情字符串
     * (Formatted calculation details string)
     */
    public String getCalculationDetails(TaxData taxData) {
        // 计算总收入(Calculate total income)
        double totalIncome = taxData.getSalaryIncome() + taxData.getBonusIncome();
        // 计算总扣除项(Calculate total deductions)
        double totalDeductions = taxData.getSocialSecurity() +
                taxData.getProvidentFund() +
                taxData.getOtherDeductions();
        // 计算应纳税所得额(Calculate taxable income)
        double taxableIncome = totalIncome - totalDeductions - 5000;

        // 应纳税所得额小于等于0时的处理
        // (Handling when taxable income is less than or equal to 0)
        if (taxableIncome <= 0) {
            return "应纳税所得额: 0 (无需缴税)(Taxable Income: 0 (No tax payable))";
        }

        // 构建详细计算过程(Build detailed calculation process)
        StringBuilder details = new StringBuilder();
        details.append("计算详情(Calculation Details):\n");
        details.append(String.format("总收入(Total Income): %.2f\n", totalIncome));
        details.append(String.format("总扣除项(Total Deductions): %.2f\n", totalDeductions));
        details.append(String.format("标准扣除额(Standard Deduction): 5000.00\n"));
        details.append(String.format("应纳税所得额(Taxable Income): %.2f\n", taxableIncome));

        // 添加适用税率和速算扣除数信息
        // (Add applicable tax rate and quick deduction information)
        for (TaxRate rate : taxRates) {
            if (taxableIncome > rate.getLowerBound() && taxableIncome <= rate.getUpperBound()) {
                details.append(String.format("适用税率(Applicable Tax Rate): %.0f%%\n", rate.getRate() * 100));
                details.append(String.format("速算扣除数(Quick Deduction): %.2f\n", rate.getQuickDeduction()));
                double tax = taxableIncome * rate.getRate() - rate.getQuickDeduction();
                details.append(String.format("应缴税额(Tax Payable): %.2f\n", tax));
                break;
            }
        }

        return details.toString();
    }
}