package cdu.linmu.knight;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cdu.linmu.knight.mapper")
public class KnightApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnightApplication.class, args);
    }

}
