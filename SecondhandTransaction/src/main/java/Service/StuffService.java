package Service;

import DAO.StuffDao;
import Model.Stuff;
import java.util.List;

public class StuffService {
    private StuffDao StuffDao = new StuffDao();

    public List<Stuff> search(String keyword) {
        // 业务逻辑：判断关键字是否为空，决定调哪个 DAO 方法
        if (keyword == null || keyword.trim().isEmpty()) {
            return StuffDao.selectAll();
        } else {
            return StuffDao.selectByKeyword(keyword);
        }
    }

    public void publish(Stuff Stuff) {
        StuffDao.addStuff(Stuff);
    }

    public void remove(int id) {
        StuffDao.deleteStuff(id);
    }
}