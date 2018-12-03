package au.com.riosoftware;

import au.com.riosoftware.model.Customer;
import au.com.riosoftware.model.Item;
import au.com.riosoftware.model.Order;
import au.com.riosoftware.model.Product;
import au.com.riosoftware.model.repository.CustomerRepository;
import au.com.riosoftware.model.repository.OrderRepository;
import au.com.riosoftware.model.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JPABasicMappings.class)
@WebAppConfiguration
public class JPABasicMappingsTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Sql({"/clear-tables.sql","/populate-tables.sql"})
    @Transactional
    public void shouldReturnManyPhonesForCustomer() {
        Customer marcio = customerRepository.findByName("Marcio");
        assertThat(marcio.getPhoneNumbers().size(), is(2));
    }

    @Test
    @Sql({"/clear-tables.sql", "/populate-tables.sql"})
    @Transactional
    public void shouldCreateOrder() {

        Customer myself = customerRepository.findByName("Marcio");

        Order order = Order.builder()
                .customer(myself)
                .createdAt(new Date())
                .build();

        Product mazdaCar = productRepository.findByName("Mazda 3");

        Product myPopCorn = productRepository.findByName("Pop Corn");

        Item mazda = Item.builder()
                .product(mazdaCar)
                .order(order)
                .quantity(3)
                .build();

        Item popcorn = Item.builder()
                .product(myPopCorn)
                .order(order)
                .quantity(7)
                .build();

        List<Item> items = new ArrayList<Item>() {{
            add(mazda);
            add(popcorn);
        }};

        order.setItems(items);

        orderRepository.save(order);

        List<Order> orders = (List<Order>) orderRepository.findAll();
        Order foundOrder = orders.get(1);

        assertThat(orders.size(), is(2));
        assertThat(foundOrder.getItems().size(), is(2));
        assertThat(foundOrder.getItems().get(0).getProduct(), is(mazdaCar));
        assertThat(foundOrder.getItems().get(1).getProduct(), is(myPopCorn));

    }


    @Test
    @Sql({"/clear-tables.sql", "/populate-tables.sql"})
    @Transactional
    public void shouldDeleteOrder() {

        Customer jonas = customerRepository.findByName("Jonas");
        Order jonasOrder = orderRepository.findByCustomer(jonas);
        orderRepository.delete(jonasOrder);

        List<Order> orders = (List<Order>)orderRepository.findAll();
        assertThat(orders.size(), is(0));

    }

}
