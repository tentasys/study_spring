package com.word.service;

import com.word.dao.WordDao;
import com.word.WordSet;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class WordSearchService {

//	@Resource
	@Autowired
	@Qualifier("usedDao")
	
	//autowired대신 아래와 같이 사용해도 된다.
//	@Inject
//	@Named(value="wordDao1")
	private WordDao wordDao;
	
	//프로퍼티, 메소드에 autowired를 줘야 할 때는 반드시 default 생성자를 만들어둬야 한다. -> autowired가 생성자에 없어서 찾치 못하는 불상사 방지 
	public WordSearchService() {
	}
	
	//@Autowired
	public WordSearchService(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	
	public WordSet searchWord(String wordKey) {
		if(verify(wordKey))
			return wordDao.select(wordKey);
		else
			System.out.println("WordKey information is unavaliable.");
		
		return null;
	}
	
	public boolean verify(String wordKey) {
		WordSet wordSet = wordDao.select(wordKey);
		return wordSet == null ? false : true;
	}
	
//	@Autowired
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
}
