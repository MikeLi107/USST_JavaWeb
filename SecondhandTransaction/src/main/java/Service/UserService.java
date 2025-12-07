package Service;


import DAO.UserDao;
import Model.User;

public class UserService {
    private UserDao userDao = new UserDao();

    public void register(User user) {
        // 1. 业务逻辑处理：密码加密
        String pwd = user.getPassword();
        String PASSWORD = SecureTool.md5(pwd);
        user.setPassword(PASSWORD);

        // 2. 调用 DAO 存入 MySQL
        userDao.addUser(user);
    }
}