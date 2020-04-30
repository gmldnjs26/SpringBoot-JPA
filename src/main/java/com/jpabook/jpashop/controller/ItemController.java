package com.jpabook.jpashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jpabook.jpashop.domain.Item;
import com.jpabook.jpashop.domain.item.Book;
import com.jpabook.jpashop.service.ItemService;
import com.jpabook.jpashop.web.BookForm;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/items/new")
	public String createForm(Model model) {
		model.addAttribute("form", new BookForm());
		return "items/createItemForm";
	}
	@PostMapping("/items/new")
	public String create(BookForm form) {
		Book book = new Book();
		
		book.setName(form.getName());
		book.setPrice(form.getPrice());
		book.setStockQuantity(form.getStockQuantity());
		book.setAuthor(form.getAuthor());
		book.setIsbn(form.getIsbn());
		
		
		itemService.saveItem(book);
		return "redirect:/items";
	}
	@GetMapping("/items")
	public String list(Model model) {
		List<Item>items = itemService.findItems();
		model.addAttribute("items", items);
		return "items/itemList";
	}
	
	// 商品の修正コード
	// 修正申請をしたらその後インデックス番号に対するものを
	@GetMapping("/items/{itemId}/edit")
	public String updateItemForm(@PathVariable("itemId")Long itemId,Model model) {
		Book item = (Book) itemService.findOne(itemId);
		
		BookForm form = new BookForm();
		form.setId(item.getId());
		form.setName(item.getName());
		form.setPrice(item.getPrice());
		form.setStockQuantity(item.getStockQuantity());
		form.setAuthor(item.getAuthor());
		form.setIsbn(item.getIsbn());
	
		model.addAttribute("form", form);
		return "items/updateItemForm";
	}
	// あえてUpdateは使用しなくて
	@PostMapping("/items/{itemId}/edit")
	public String updateItem(@ModelAttribute("form") BookForm form) {
		Book book = new Book();
		book.setId(form.getId());
		book.setName(form.getName());
		book.setPrice(form.getPrice());
		book.setStockQuantity(form.getStockQuantity());
		book.setAuthor(form.getAuthor());
		book.setIsbn(form.getIsbn());
		
		// 엔티티를 변경할 때는 항상 변경 감지를 사용하세요
		itemService.saveItem(book);
		return "redirect:/items";
	}
}
