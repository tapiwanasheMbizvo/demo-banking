package com.tapiwanashe.mbizvo.banking.demo;

import com.tapiwanashe.mbizvo.banking.demo.mapper.BankAccountMapper;
import com.tapiwanashe.mbizvo.banking.demo.service.UserAccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankTestConfiguration {

    @Bean
    public BankAccountMapper bankAccountMapper(){

        return  new BankAccountMapper();
    }
    @Bean
    public UserAccountService userAccountService(){

        return  new UserAccountService();
    }

}
