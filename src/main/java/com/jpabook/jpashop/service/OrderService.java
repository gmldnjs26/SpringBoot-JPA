package com.jpabook.jpashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.jpashop.domain.Delivery;
import com.jpabook.jpashop.domain.DeliveryStatus;
import com.jpabook.jpashop.domain.Item;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderItem;
import com.jpabook.jpashop.domain.OrderSearch;
import com.jpabook.jpashop.repository.MemberRepository;
import com.jpabook.jpashop.repository.OrderRepository;

@Service
@Transactional(readOnly = true)
public class OrderService {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ItemService itemService;
	
	@Transactional
	public Long order(Long memberId, Long itemId, int count) {
		Member member = memberRepository.findOne(memberId);
		Item item = itemService.findOne(itemId);
		
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		delivery.setStatus(DeliveryStatus.READY);
		
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		
		Order order = Order.createOrder(member, delivery, orderItem);
		
		orderRepository.save(order);
		return order.getId();
	}
	@Transactional
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findOne(orderId);
		order.cancel();
	}
	
	public List<Order> findAllList(OrderSearch orderSearch) {
		return orderRepository.findAllByCriteria(orderSearch);
	}
	
}
