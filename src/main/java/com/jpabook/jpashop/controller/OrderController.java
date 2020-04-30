package com.jpabook.jpashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jpabook.jpashop.domain.Item;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderSearch;
import com.jpabook.jpashop.service.ItemService;
import com.jpabook.jpashop.service.MemberService;
import com.jpabook.jpashop.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/order")
	public String createForm(Model model) {
		List<Member> members = memberService.findMembers();
		List<Item> items = itemService.findItems();
		
		model.addAttribute("members", members);
		model.addAttribute("items", items);
		
		return "order/orderForm";
	}
	@PostMapping("/order")
	public String order(@RequestParam("memberId") Long memberId,
				@RequestParam("itemId") Long itemId, @RequestParam("count")int count) {
		orderService.order(memberId, itemId, count);
		System.out.println("Do Order");
		return "redirect:/orders" ;
	}
	
	@GetMapping("/orders")
	public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch,
			Model model) {
		List<Order> orders = orderService.findAllList(orderSearch);
		model.addAttribute("orders", orders);
		return "order/orderList";
	}
	@PostMapping("/orders/{orderId}/cancel")
	public String cancelOrder(@PathVariable("orderId")Long orderId) {
		orderService.cancelOrder(orderId);
		return "redirect:/orders";
	}
}
