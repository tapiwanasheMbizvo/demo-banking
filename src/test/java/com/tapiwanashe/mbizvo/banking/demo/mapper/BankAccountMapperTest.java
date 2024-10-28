package com.tapiwanashe.mbizvo.banking.demo.mapper;

import com.tapiwanashe.mbizvo.banking.demo.dto.BankAccountDto;
import com.tapiwanashe.mbizvo.banking.demo.BankTestConfiguration;
import com.tapiwanashe.mbizvo.banking.demo.models.BankAccount;
import com.tapiwanashe.mbizvo.banking.demo.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@ContextConfiguration(classes = BankTestConfiguration.class)
@SpringBootTest
class BankAccountMapperTest {
    /**
     * we are telling spring boot to boot up a spring boot IOC container but limiting the beans to the ones defined in
     * @BankTestConfiguration.class
     */

    /**
     * If we try to autowire another bean not defined it will fail
     */

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private BankAccountMapper bankAccountMapper;

    private BankAccountDto bankAccountDto;
    private BankAccount bankAccount;

    @BeforeEach
    public void setUp() {

        bankAccount = new BankAccount(

                13L,
                "AC00000015",
                BigDecimal.valueOf(200)
        );
        bankAccountDto = new BankAccountDto(
                12L,
                "AC00000015",
                BigDecimal.valueOf(100)
        );
    }

    @Test
    void toEntity() {

        BankAccount entity = bankAccountMapper.toEntity(bankAccountDto);

        assertEquals(entity.getBalance(), bankAccountDto.getBalance());

    }

    @Test
    void toDto() {
    }


    @Test
    void failingTestBecauseWeDidNotIncludeTheBeanToBeScanned(){

        String user = userAccountService.createUser();

        assertEquals(user, "USER");
    }
}