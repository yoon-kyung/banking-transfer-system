package com.banking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankingApplicationIntegrationTest {

    @Test
    @DisplayName("모든 모듈이 조립되어 스프링 컨텍스트가 뜬다")
    void contextLoads() {}
}
