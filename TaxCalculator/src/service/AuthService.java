package service;

import model.User;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证服务(Authentication Service)
 * <p>
 * 负责用户认证和注册功能的业务逻辑实现
 * (Responsible for implementing business logic for user authentication and registration)
 */
public class AuthService {
    // 用户数据文件路径(User data file path)
    private static final String USERS_FILE = "data/users.dat";
    // 用户列表(User list)
    private List<User> users;

    /**
     * 构造函数，初始化认证服务
     * (Constructor to initialize authentication service)
     * <p>
     * 尝试从文件加载用户数据，如果加载失败则创建默认管理员账户
     * (Attempts to load user data from file, creates default admin account if loading fails)
     */
    public AuthService() {
        loadUsers();
        if (users == null) {
            users = new ArrayList<>();
            // 添加默认管理员账户(Add default admin user)
            users.add(new User("admin", "admin123"));
            saveUsers();
        }
    }

    /**
     * 从文件加载用户数据
     * (Load user data from file)
     * <p>
     * 使用FileUtils工具类读取序列化对象
     * (Uses FileUtils utility class to read serialized object)
     */
    @SuppressWarnings("unchecked")
    private void loadUsers() {
        users = (List<User>) FileUtils.readObjectFromFile(USERS_FILE);
    }

    /**
     * 保存用户数据到文件
     * (Save user data to file)
     * <p>
     * 使用FileUtils工具类写入序列化对象
     * (Uses FileUtils utility class to write serialized object)
     */
    private void saveUsers() {
        FileUtils.writeObjectToFile(USERS_FILE, users);
    }

    /**
     * 用户认证
     * (User authentication)
     * <p>
     * 验证用户名和密码是否匹配
     * (Verifies if username and password match)
     *
     * @param username 用户名(Username)
     * @param password 密码(Password)
     * @return 认证成功返回true，失败返回false
     * (Returns true if authentication succeeds, false otherwise)
     */
    public boolean authenticate(String username, String password) {
        return users.stream()
                .anyMatch(user -> user.getUsername().equals(username) &&
                        user.getPassword().equals(password));
    }

    /**
     * 用户注册
     * (User registration)
     * <p>
     * 创建新用户账户，检查用户名是否已存在
     * (Creates new user account, checks if username already exists)
     *
     * @param username 用户名(Username)
     * @param password 密码(Password)
     * @return 注册成功返回true，用户名已存在返回false
     * (Returns true if registration succeeds, false if username already exists)
     */
    public boolean register(String username, String password) {
        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            return false;
        }
        users.add(new User(username, password));
        saveUsers();
        return true;
    }
}
