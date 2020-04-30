package com.jpabook.jpashop.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.domain.Order;
import com.jpabook.jpashop.domain.OrderSearch;

@Repository
public class OrderRepository {
	@Autowired
	private EntityManager em;
	
	public void save(Order order) {
		em.persist(order);
	}
	public Order findOne(Long id) {
		return em.find(Order.class, id);
	}
	
	// public List<Order>findAll(OrderSearch orderSearch) {}
	
	public List<Order> findAllByCriteria(OrderSearch orderSearch) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Order>cq = cb.createQuery(Order.class);
		Root<Order>o = cq.from(Order.class);
		Join<Order,Member> m = o.join("member",JoinType.INNER);
		
		List<Predicate>criteria = new ArrayList<>();
		
		//注文状態検索
		if(orderSearch.getOrderStatus() != null) {
			Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
			criteria.add(status);
		}
		
		//会員名前検索
		if(StringUtils.hasText(orderSearch.getMemberName())) {
			Predicate name = cb.like(m.<String>get("name"), 
					"%" + orderSearch.getMemberName() + "%");
			criteria.add(name);
			
		}
		cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
		TypedQuery<Order>query = em.createQuery(cq).setMaxResults(1000); // 最大１０００件
		return query.getResultList();
	}
}
