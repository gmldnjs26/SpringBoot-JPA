package com.jpabook.jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.jpashop.domain.Item;

@Repository

public class ItemRepository {
	@Autowired
	private EntityManager em;
	
	public void save(Item item) {
		if(item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item);
		}
	}
	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
	
	public List<Item> findAll(){
		return em.createQuery("select i from Item i",Item.class).getResultList();
	}

}
