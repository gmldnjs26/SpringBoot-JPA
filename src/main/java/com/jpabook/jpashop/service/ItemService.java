package com.jpabook.jpashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.jpashop.domain.Item;
import com.jpabook.jpashop.repository.ItemRepository;

@Service
@Transactional(readOnly = true)
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	
	//saveItem
	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
	//findItems
	public List<Item>findItems(){
		return itemRepository.findAll();
	}
	
	//findOne
	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}
	//update 
	@Transactional
	public void updateItem(Long id,String name, int price) {
		Item item = itemRepository.findOne(id);
		item.setName(name);
		item.setPrice(price);
	}
//	영속성 컨텍스트에서 엔티티를 다시 조회한 후에 데이터를 수정하는 방법
//	트랜잭션 안에서 엔티티를 다시 조회, 변경할 값 선택 트랜잭션 커밋 시점에 변경 감지(Dirty Checking)
//	이 동작해서 데이터베이스에 UPDATE SQL 실행
	
}
