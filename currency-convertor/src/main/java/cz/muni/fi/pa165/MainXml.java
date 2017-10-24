package cz.muni.fi.pa165;

import cz.muni.fi.pa165.currency.CurrencyConvertor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author Miroslav Kubus
 */
public class MainXml
{

    public static void main(String[] args)
    {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");

        CurrencyConvertor currencyConvertor = applicationContext.getBean(CurrencyConvertor.class);
        BigDecimal converted = currencyConvertor.convert(Currency.getInstance("EUR"), Currency.getInstance("CZK"), BigDecimal.valueOf(1));

        System.out.println("1 EUR to CZK is " + converted.toString());
    }

}