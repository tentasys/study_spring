package com.bms;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.bms.book.Book;
import com.bms.book.service.BookRegisterService;
import com.bms.book.service.BookSearchService;
import com.bms.member.Member;
import com.bms.member.service.MemberRegisterService;
import com.bms.member.service.MemberSearchService;

public class MainClass {

	public static void main(String[] args) {
		String[] bNums = {"739", "985", "184", "830", "816"};
		String[] bTitles = {"html", "css", "jQuery", "java", "spring"};
		
		String[] mIds = {"rabbit", "hippo", "racoon", "elephan", "lion"};
		String[] mPws = {"96539", "94875", "15284", "48765", "28661"};
		String[] mNames = {"agatha", "babara", "chris", "doris", "elva"};
		
		//스프링 컨테이너 생성 -> 스프링 컨테이너가 생성되면서 빈 객체가 생성된다 
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:appCtx.xml");
		
		//더미 도서 목록 등록
		BookRegisterService bookRegisterService = ctx.getBean("bookRegisterService", BookRegisterService.class);
		for(int i=0; i<bNums.length; i++) {
			Book book = new Book(bNums[i], bTitles[i], true, null);
			bookRegisterService.register(book);
		}
		
		//더미 도서 목록 출력 
		BookSearchService bookSearchService = ctx.getBean("bookSearchService", BookSearchService.class);
		System.out.println("\nbNum\tbTitle\tbCanRenbLenderId");
		System.out.println("----------------------------------------");
		for(int i=0; i<bNums.length; i++) {
			Book book = bookSearchService.searchBook(bNums[i]);
			System.out.print(book.getbNum()+"\t");
			System.out.print(book.getbTitle()+"\t");
			System.out.print(book.isbCanRental()+"\t");
			System.out.println(book.getbMember() == null ? null : book.getbMember().getmId());
		}
		
		//더미 회원 목록 등록 
		MemberRegisterService memberRegisterService = ctx.getBean("memberRegisterService", MemberRegisterService.class);
		for(int i=0; i<mIds.length; i++) {
			Member member = new Member(mIds[i], mPws[i], mNames[i]);
			memberRegisterService.register(member);
		}
		
		//더미 회원 목록 출력 
		MemberSearchService memberSearchService = ctx.getBean("memberSearchService", MemberSearchService.class);
		System.out.println("\nmId\tmPw\tmName");
		System.out.println("----------------------------------------");
		for(int i=0; i<mIds.length; i++) {
			Member member = memberSearchService.searchMember(mIds[i]);
			System.out.print(member.getmId() + "\t");
			System.out.print(member.getmPw() + "\t");
			System.out.println(member.getmName());
		}
		
		//도서 대여 목록 등록
		bookRegisterService.register(new Book("739", "html", false, memberSearchService.searchMember("elephan")));
		bookRegisterService.register(new Book("184", "jQuery", false, memberSearchService.searchMember("hippo")));
		bookRegisterService.register(new Book("816", "spring", false, memberSearchService.searchMember("rabbit")));
		bookRegisterService.register(new Book("985", "css", false, memberSearchService.searchMember("rabbit")));
		
		//도서 대여 목록 출력
		System.out.println("\nbNum\tbTitle\tbCanRenbLenderId");
		System.out.println("----------------------------------------");
		for(int i=0; i<bNums.length; i++) {
			Book book = bookSearchService.searchBook(bNums[i]);
			System.out.print(book.getbNum()+"\t");
			System.out.print(book.getbTitle()+"\t");
			System.out.print(book.isbCanRental()+"\t");
			System.out.println(book.getbMember() == null ? null : book.getbMember().getmId());
		}
		
		ctx.close();
	}

}
