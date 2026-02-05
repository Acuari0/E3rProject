package com.e3r.project;

import static org.assertj.core.api.Assertions.assertThatCode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void main() {
        assertThatCode(() -> {
            ProjectApplication.main(new String[] {"--server.port=0"});
        }).doesNotThrowAnyException();
    }
}
