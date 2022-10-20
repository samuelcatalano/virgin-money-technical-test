package co.uk.virginmoneyexercise.service;

import co.uk.virginmoneyexercise.entity.base.BaseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface TransactionService<E extends BaseEntity> {

    List<E> findTransactionsByCategory(String category) throws Exception;

    BigDecimal findTotalOutgoingByCategory(String category) throws Exception;

    BigDecimal findHighestSpendForYearByCategory(String category, Integer year) throws Exception;

    BigDecimal findLowestSpendForYearByCategory(String category, Integer year) throws Exception;

    BigDecimal findAverageMonthlySpendByCategory(String category) throws Exception;
}