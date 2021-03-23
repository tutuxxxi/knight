package cdu.linmu.knight;

import cdu.linmu.knight.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.sql.SQLException;

@SpringBootTest
class KnightApplicationTests {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void set(){
        redisTemplate.opsForValue().set("myKey","myValue");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }

}
