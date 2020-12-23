package fr.romdhani.scaridae;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.*;

import java.util.Currency;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;


/**
 * Creates and displays the main view.
 *
 * @author aromdhani
 */
public class ScaridaeView {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        try {
            System.out.println("*** Start scaridae ***");
            DBEntityManager.getInstance().doInTransaction((entityManager) -> {

                BillingType billingType = new BillingType("Card", "original card");
                Currency currency = Currency.getInstance("GBP");
                BillingCurrency billingCurrency = new BillingCurrency(currency.getCurrencyCode());
                Vat vat = new Vat("french tax", 0.2f);
                Billing billing = new Billing("ref", 11122.2f);

                billing.setBillingtype(billingType);
                billing.setBillingCurrency(billingCurrency);
                billing.setVat(vat);

                entityManager.persist(billingType);
                entityManager.persist(billingCurrency);
                entityManager.persist(vat);
                entityManager.persist(billing);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
