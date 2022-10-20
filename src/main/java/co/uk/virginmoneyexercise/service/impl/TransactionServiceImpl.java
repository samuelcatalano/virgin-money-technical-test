package co.uk.virginmoneyexercise.service.impl;

import co.uk.virginmoneyexercise.entity.Transaction;
import co.uk.virginmoneyexercise.repository.TransactionRepository;
import co.uk.virginmoneyexercise.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService<Transaction> {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(final TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findTransactionsByCategory(final String category) {
        return transactionRepository.findAll().stream()
               .filter(transaction -> Objects.nonNull(transaction.getCategory()))
               .filter(transaction -> transaction.getCategory().equals(category))
               .sorted(Comparator.comparing(Transaction::getTransactionDate).reversed())
               .collect(Collectors.toList());
    }

    @Override
    public BigDecimal findAverageMonthlySpendByCategory(final String category) {
        var transactions = findTransactionsByCategory(category);
        return transactions.stream()
                           .map(Transaction::getAmount)
                           .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal findTotalOutgoingByCategory(final String category) {
        var transactionAmounts = findTransactionsByCategory(category)
                                                .stream().map(Transaction::getAmount)
                                                .collect(Collectors.toList());

        return transactionAmounts.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    @Override
    public BigDecimal findHighestSpendForYearByCategory(final String category, final Integer year) {
        var transactions = findTransactionsByCategory(category);
        var transaction = transactions.stream()
                                                  .filter(t -> t.getTransactionDate().getYear() == year)
                                                  .max(Comparator.comparing(Transaction::getAmount))
                                                  .orElseThrow(NoSuchElementException::new);

        return transaction.getAmount();
    }

    @Override
    public BigDecimal findLowestSpendForYearByCategory(final String category, final Integer year) {
        var transactions = findTransactionsByCategory(category);
        var transaction= transactions.stream()
                                                 .filter(t -> t.getTransactionDate().getYear() == year)
                                                 .min(Comparator.comparing(Transaction::getAmount))
                                                 .orElseThrow(NoSuchElementException::new);

        return transaction.getAmount();
    }
}