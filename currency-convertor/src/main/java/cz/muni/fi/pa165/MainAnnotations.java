package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Miroslav Kubus
 */
public class MainAnnotations
{

    public static void main(String[] args)
    {
        AnnotationConfigApplicationContext applicationContext 
                                = new AnnotationConfigApplicationContext();
        applicationContext.scan("cz.muni.fi.pa165");
        applicationContext.refresh();


        CurrencyConvertor currencyConvertor = applicationContext.getBean(CurrencyConvertor.class);
        BigDecimal converted = currencyConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), BigDecimal.valueOf(1));

        System.out.println("1 EUR to CZK is " + converted.toString());
    }

}