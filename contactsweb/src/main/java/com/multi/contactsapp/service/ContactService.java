package com.multi.contactsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.contactsapp.dao.ContactDAO;
import com.multi.contactsapp.domain.Contact;
import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.domain.Result;

import jdk.jshell.spi.ExecutionControl.ExecutionControlException;

@Service
public class ContactService {
	@Autowired
	private ContactDAO contactDAO;

	public ContactList getContactList() {
		List<Contact> contacts = contactDAO.getContactList();
		return new ContactList(0,0,contacts.size(), contacts);
	}

	public ContactList getContactList(int pageNo, int pageSize) {
		List<Contact> contacts = contactDAO.getContactList(pageNo, pageSize);
		return new ContactList(pageNo, pageSize, contacts.size(), contacts);
	}

	public Contact getContactOne(Contact c) {
		return contactDAO.getContactOne(c);
	}

	public Result insertContact(Contact c) {
		Result result = new Result("ok", "","");
		try {
			long no = contactDAO.insertContact(c);
			result.setMessage("연락처 추가 성공, 추가된 연락처의 일련번호 :" + no);
			result.setKey(""+no);
		}catch(Exception e) {
			result.setStatus("fail");
			result.setMessage(e.getMessage());
			result.setKey("");
		}
		return result;
	}

	public int updateContact(Contact c) {
		return contactDAO.updateContact(c);
	}

	public int deleteContact(Contact c) {
		return contactDAO.deleteContact(c);
	}
}
