package com.multi.contactsapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.service.ContactService;

@Controller
@RequestMapping("/contacts2")
public class Test2Controller {

	@Autowired
	private ContactService contactService;

	@GetMapping()
	public void getContacts(Model model,
			@RequestParam(value = "pageno", required = false, defaultValue = "0") int pageNo,
			@RequestParam(value = "pagesize", required = false, defaultValue = "3") int pageSize) {
		ContactList contactList = null;
		if (pageNo == 0) {
			contactList = contactService.getContactList();
		} else {
			contactList = contactService.getContactList(pageNo, pageSize);
		}

		model.addAttribute("data", contactList);
	}
}