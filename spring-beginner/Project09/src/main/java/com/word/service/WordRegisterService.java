package com.word.service;

import com.word.dao.WordDao;
import com.word.WordSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class WordRegisterService {
//	@Resource
	@Autowired
	@Qualifier("usedDao")
	private WordDao wordDao;	//Resource -> wordDao랑 동일한 이름을 찾아서 간다. 
	
	//컨테이너 안에 있는 객체 중에 WordDao라는 타입 객체를 찾아서 매핑 
	//@Autowired
	public WordRegisterService() {
	}
	
	public WordRegisterService(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	
	public void register(WordSet wordSet) {
		String wordKey = wordSet.getWordKey();
		if(verify(wordKey))
			wordDao.insert(wordSet);
		else
			System.out.println("The word has already registered.");
	}
	
	public boolean verify(String wordKey) {
		WordSet wordSet = wordDao.select(wordKey);
		return wordSet == null ? true : false;
	}
	
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
}
