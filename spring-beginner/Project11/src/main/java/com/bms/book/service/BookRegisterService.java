package com.bms.book.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import com.bms.book.Book;
import com.bms.book.dao.BookDao;

public class BookRegisterService implements InitializingBean, DisposableBean{

	@Autowired
	private BookDao bookDao;
	
	public BookRegisterService() {}
	
	public void register(Book book) {
		bookDao.insert(book);
	}
	
	//InitializingBean
	public void afterPropertiesSet() throws Exception {
		System.out.println("bean 객체 생성");
	}

	//DisposableBean
	public void destroy() throws Exception {
		System.out.println("bean 객체 소멸");	//스프링 객체 소멸 이후에 소멸됨
		
	}
}
