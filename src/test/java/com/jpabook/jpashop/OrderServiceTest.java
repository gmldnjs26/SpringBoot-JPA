package com.jpabook.jpashop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.jpashop.domain.Delivery;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderItem;
import com.jpabook.jpashop.domain.OrderStatus;
import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.repository.OrderRepository;
import com.jpabook.jpashop.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	@Test
	public void orderTest() {
		//Given
		Book book = new Book();
		book.setStockQuantity(5);
		OrderItem orderItem = 
				OrderItem.createOrderItem(book, 10000, 5);

		Member member = new Member();
		Delivery delivery = new Delivery();

		//When
		Order order = Order.createOrder(member, delivery, orderItem);
		orderRepository.save(order);

		//Then
		assertEquals(order,orderRepository.findOne(order.getId()));
	}
	@Test
	public void cancelTest() {
		//Given
		Book book = new Book();
		book.setStockQuantity(5);
		OrderItem orderItem = 
				OrderItem.createOrderItem(book, 10000, 5);

		Member member = new Member();
		Delivery delivery = new Delivery();

		//When
		Order order = Order.createOrder(member, delivery, orderItem);
		orderRepository.save(order);
		orderService.cancelOrder(order.getId());
		//Then
		assertEquals(order.getStatus(),OrderStatus.CANCEL); // Good
	}
	@Test
	public void updateTest() {
		// Given
		
		// When
		
		// Then
		
	}
}
