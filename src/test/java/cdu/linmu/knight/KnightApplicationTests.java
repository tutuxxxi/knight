package cdu.linmu.knight;

import cdu.linmu.knight.dao.TestDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@SpringBootTest
class KnightApplicationTests {


    @Autowired
    TestDao testDao;

    @Test
    void contextLoads() throws SQLException {
        testDao.test();
    }

}
