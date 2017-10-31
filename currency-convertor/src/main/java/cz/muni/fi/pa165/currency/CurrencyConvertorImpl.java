package cz.muni.fi.pa165.currency;

import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class CurrencyConvertorImpl implements CurrencyConvertor
{
    private final ExchangeRateTable exchangeRateTable;

    @Autowired
    public CurrencyConvertorImpl(ExchangeRateTable exchangeRateTable)
    {
        this.exchangeRateTable = exchangeRateTable;
    }

    @Override
    public BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceAmount)
    {
        if (sourceCurrency == null) {
            throw new IllegalArgumentException("sourceCurrency is null");
        }
        if (targetCurrency == null) {
            throw new IllegalArgumentException("targetCurrency is null");
        }
        if (sourceAmount == null) {
            throw new IllegalArgumentException("sourceAmount is null");
        }
        try {
            BigDecimal exchangeRate = exchangeRateTable.getExchangeRate(sourceCurrency, targetCurrency);
            if (exchangeRate == null) {
                throw new UnknownExchangeRateException("ExchangeRate is unknown");
            }
            return exchangeRate.multiply(sourceAmount).setScale(2, RoundingMode.HALF_EVEN);
        } catch (ExternalServiceFailureException ex) {
            throw new UnknownExchangeRateException("Error when fetching exchange rate", ex);
        }
    }
}