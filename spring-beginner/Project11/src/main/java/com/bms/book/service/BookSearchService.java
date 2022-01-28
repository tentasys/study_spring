package com.bms.book.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import com.bms.book.Book;
import com.bms.book.dao.BookDao;

public class BookSearchService implements InitializingBean, DisposableBean{

	@Autowired
	private BookDao bookDao;
	
	public BookSearchService() {}
	
	public Book searchBook(String bNum) {
		Book book = bookDao.select(bNum);
		return book;
	}
	
	//InitializingBean
	public void afterPropertiesSet() throws Exception {
		System.out.println("object create");
	}

	//DisposableBean
	public void destroy() throws Exception {
		System.out.println("object dispose");	//스프링 객체 소멸 이후에 소멸됨
		
	}
}
