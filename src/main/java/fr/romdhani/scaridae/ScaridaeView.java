package fr.romdhani.scaridae;

import fr.romdhani.scaridae.core.database.DBEntityManager;
import fr.romdhani.scaridae.core.orm.*;

import javax.persistence.TypedQuery;
import java.util.HashSet;
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
              /*  ProductUse productUse = new ProductUse("intern", "intern");
                ProductCategory productCategory = new ProductCategory("cat1", "");
                ProductGroup productGroup = new ProductGroup("grp1", "ge");
                ProductType proudctType = new ProductType("type1", "");
                ProductItem productItem = new ProductItem("item", "item");
                Product product = new Product("key1", 123.3f);
                product.setProductCategory(productCategory);
                product.setProductGroup(productGroup);
                product.setProudctType(proudctType);
                product.setProductItem(productItem);
                product.setProductUse(productUse);

                UserAccount userAccount = new UserAccount("hello1", "sdcqs");
                Set<Product> productSet = new HashSet<>();
                productSet.add(product);
                userAccount.setProductSet(productSet);

                entityManager.persist(productUse);
                entityManager.persist(productCategory);
                entityManager.persist(productGroup);
                entityManager.persist(proudctType);
                entityManager.persist(productItem);
                entityManager.persist(product);
                entityManager.persist(userAccount);
                */
                TypedQuery<UserAccount> q2 = entityManager.createQuery("SELECT c FROM UserAccount c", UserAccount.class);
                for (UserAccount user : q2.getResultList()) {
                   System.out.println(user.getProductSet()+"/");
                    user.getProductSet().forEach(System.out::println);
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
