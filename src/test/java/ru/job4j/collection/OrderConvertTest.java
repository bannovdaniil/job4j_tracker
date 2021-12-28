package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrderConvertTest {
    @Test
    public void whenSingleOrder() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3sfe", "Dress"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("3sfe"), is(new Order("3sfe", "Dress")));
    }

    @Test
    public void whenDoubleOrder() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3sfe", "Dress"));
        orders.add(new Order("2222", "Jewelry"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("3sfe"), is(new Order("3sfe", "Dress")));
        assertThat(map.get("2222"), is(new Order("2222", "Jewelry")));
    }

    @Test
    public void whenEqualsName() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("3sfe", "Boots"));
        orders.add(new Order("2222", "Boots"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("3sfe"), is(new Order("3sfe", "Boots")));
        assertThat(map.get("2222"), is(new Order("2222", "Boots")));
    }

    @Test
    public void whenEqualsNumbers() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("2222", "Dress"));
        orders.add(new Order("2222", "Jewelry"));
        HashMap<String, Order> map = OrderConvert.process(orders);
        assertThat(map.get("2222"), is(new Order("2222", "Jewelry")));
        assertThat(map.size(), is(1));
    }

}