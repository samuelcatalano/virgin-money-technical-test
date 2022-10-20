package co.uk.virginmoneyexercise.controller;

import co.uk.virginmoneyexercise.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/{category}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findTransactionsByCategory(@PathVariable final String category) throws Exception {
        return ResponseEntity.ok(transactionService.findTransactionsByCategory(category));
    }

    @GetMapping(value = "/{category}/average-monthly-spend", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findAverageMonthlySpendByCategory(@PathVariable final String category) throws Exception {
        return ResponseEntity.ok(transactionService.findAverageMonthlySpendByCategory(category));
    }

    @GetMapping(value = "/{category}/total-outgoing", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findTotalOutgoingByCategory(@PathVariable final String category) throws Exception {
        return ResponseEntity.ok(transactionService.findTotalOutgoingByCategory(category));
    }

    @GetMapping(value = "/{category}/{year}/max-amount", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findHighestSpendForYearByCategory(@PathVariable final String category,
                                                                    @PathVariable final Integer year) throws Exception {
        return ResponseEntity.ok(transactionService.findHighestSpendForYearByCategory(category, year));
    }

    @GetMapping(value = "/{category}/{year}/min-amount", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findLowestSpendForYearByCategory(@PathVariable final String category,
                                                                   @PathVariable final Integer year) throws Exception {
        return ResponseEntity.ok(transactionService.findLowestSpendForYearByCategory(category, year));
    }
}