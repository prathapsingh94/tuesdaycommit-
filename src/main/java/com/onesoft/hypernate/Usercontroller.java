package com.onesoft.hypernate;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@RestController
public class Usercontroller {
	@Autowired
	EntityManager em;
	
	@PostMapping("/save")
	@Transactional
	public String save(@RequestBody User user) {
		Session session = em.unwrap(Session.class);
		session.save(user);
		return"user detail saved";
		}
	@GetMapping("/get/{id}")
	public User getuser(@PathVariable int id) {
		Session session = em.unwrap(Session.class);
		User user = session.get(User.class, id);
		return user;
		
	}
	
	@GetMapping("/getList")
	public List<User> getAll(){
		Session session =em.unwrap(Session.class);
		List<User>list=session.createQuery("from User",User.class).getResultList();
		return list;
	}

}
