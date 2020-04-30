package com.jpabook.jpashop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import com.jpabook.jpashop.service.MemberService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
	@Autowired
	MemberService memberService;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Test
	public void joinTest() throws Exception {
		// Given
		Member member = new Member();
		member.setName("kim");
		
		// When
		Long saveId = memberService.join(member);
		
		// Then
		assertEquals(member,memberRepository.findOne(saveId));
	}
	
	@Test(expected = IllegalStateException.class) // この例外が見込みと言う事
	public void validTest() throws Exception {
		Member member1 = new Member();
		Member member2 = new Member();
		
		member1.setName("gwak");
		member2.setName("gwak");
		
		memberService.join(member1);
		memberService.join(member2);
		
		fail("例外が発生しなければならない。");
	}
}
