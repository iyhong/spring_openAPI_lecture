package com.multi.contactsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.multi.contactsapp.domain.Contact;
import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.domain.Result;
import com.multi.contactsapp.service.ContactService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/contacts")
@Api(value="ContactList Service Controller, 연락처 RESTful 서비스")
public class ContactRestController {
	@Autowired
	private ContactService contactService;

//	@GetMapping()
//	public ContactList getContactList() {
//		return contactService.getContactList();
//
//	}
	
	@ApiOperation(value="연락처 목록 조회", notes="연락처 목록 조회용 리소스입니다...(생략)...")
	@ApiImplicitParams({
	    @ApiImplicitParam(name="pageno", value="페이지 번호", dataType="string", paramType="query", defaultValue="0"), 
	    @ApiImplicitParam(name="pagesize", value="페이지 사이즈", dataType="string", paramType="query", defaultValue="3") 
	})
	@GetMapping()
	public ContactList getContactList(
			@RequestParam(value = "pageno", required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = "pagesize", required = false, defaultValue = "2") int pageSize) {
		ContactList contactList = null;
		if (pageNo < 1) {
			contactList = contactService.getContactList();
		} else {
			if (pageSize < 2)
				pageSize = 5;
			contactList = contactService.getContactList(pageNo, pageSize);
		}
		return contactList;
	}

	@GetMapping("{no}")
	public Contact getContactOne(@PathVariable("no") int no) {
		Contact c = new Contact();
		c.setNo(no);
		return contactService.getContactOne(c);
	}

	@PostMapping()
	public Result insertContact(@RequestBody Contact c) {
		return contactService.insertContact(c);
	}

	@PutMapping("{no}")
	@ApiOperation(value="연락처 수정", notes="기존 연락처를 수정합니다. ......")
	public Result updateContact(
			@ApiParam(value="연락처의 일련번호") @PathVariable("no") int no,
			@ApiParam(value="수정할 연락처 정보") @RequestBody Contact c) {
		c.setNo(no);
		return contactService.updateContact(c);
	}

//  @PutMapping("{no}")
//  public Result updateContact(
//      @PathVariable("no") int no,
//      @RequestBody Contact c) {
//    c.setNo(no);
//    return contactService.updateContact(c);
//  }

	@DeleteMapping("{no}")
	public Result updateContact(@PathVariable("no") int no) {
		Contact c = new Contact();
		c.setNo(no);
		return contactService.deleteContact(c);
	}
}