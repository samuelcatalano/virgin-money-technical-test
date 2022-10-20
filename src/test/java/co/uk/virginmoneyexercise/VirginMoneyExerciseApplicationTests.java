package co.uk.virginmoneyexercise;

import co.uk.virginmoneyexercise.controller.TransactionController;
import co.uk.virginmoneyexercise.entity.Transaction;
import co.uk.virginmoneyexercise.enums.TransactionType;
import co.uk.virginmoneyexercise.repository.TransactionRepository;
import co.uk.virginmoneyexercise.service.impl.TransactionServiceImpl;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest
class VirginMoneyExerciseApplicationTests {

    private TransactionController controller;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        var service = new TransactionServiceImpl(repository);
        controller  = new TransactionController(service);
        mockMvc     = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void test_find_transactions_by_category() throws Exception {
        mockMvc.perform(get("/transactions/Groceries")).andDo(print()).andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void test_transactions_average_by_category() throws Exception {
        mockMvc.perform(get("/transactions/Groceries/average-monthly-spend")).andDo(print())
               .andExpect(status().isOk()).andExpect(jsonPath("$", is(16.39)));
    }

    @Test
    void test_validate_highest_spend_year_transactions_in_memory_database_by_category() throws Exception {
        mockMvc.perform(get("/transactions/Groceries/2020/max-amount")).andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$", is(10.40)));
    }

    @Test
    void test_validate_lowest_spend_year_transactions_in_memory_database_by_category() throws Exception {
        mockMvc.perform(get("/transactions/MyMonthlyDD/2020/max-amount")).andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$", is(600.0)));
    }
}
