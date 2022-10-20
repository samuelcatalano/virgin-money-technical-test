package co.uk.virginmoneyexercise.service;

import co.uk.virginmoneyexercise.repository.TransactionRepository;
import co.uk.virginmoneyexercise.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class TransactionServiceImplTest {

    private TransactionServiceImpl service;

    @Autowired
    private TransactionRepository repository;

    private static final String CATEGORY_MY_MONTHLY_DD = "MyMonthlyDD";
    private static final String CATEGORY_GROCERIES = "Groceries";
    private static final Integer YEAR = 2020;
    private static final Integer SCALE = 2;

    @BeforeEach
    void setup() {
        service = new TransactionServiceImpl(repository);
    }

    @Test
    void validate_transactions_count_in_memory_database_by_category() throws Exception {
        var transactions = service.findTransactionsByCategory(CATEGORY_MY_MONTHLY_DD);
        assertThat(transactions.size(), is(2));
    }

    @Test
    void validate_total_outgoing_transactions_in_memory_database_by_category() throws Exception {
        var amount = service.findTotalOutgoingByCategory(CATEGORY_GROCERIES);
        assertThat(amount, is(BigDecimal.valueOf(16.39)));
    }

    @Test
    void validate_average_monthly_transactions_in_memory_database_by_category() throws Exception {
        var amount = service.findAverageMonthlySpendByCategory(CATEGORY_MY_MONTHLY_DD);
        assertThat(amount, is(BigDecimal.valueOf(640.00).setScale(SCALE, RoundingMode.UNNECESSARY)));
    }

    @Test
    void validate_highest_spend_year_transactions_in_memory_database_by_category() throws Exception {
        var amount = service.findHighestSpendForYearByCategory(CATEGORY_MY_MONTHLY_DD, YEAR);
        assertThat(amount, is(BigDecimal.valueOf(600.00).setScale(SCALE, RoundingMode.UNNECESSARY)));
    }

    @Test
    void validate_lowest_spend_year_transactions_in_memory_database_by_category() throws Exception {
        var amount = service.findHighestSpendForYearByCategory(CATEGORY_GROCERIES, YEAR);
        assertThat(amount, is(BigDecimal.valueOf(10.40).setScale(SCALE, RoundingMode.UNNECESSARY)));
    }
}
