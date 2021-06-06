package com.multi.contactsapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.multi.contactsapp.domain.Contact;
import com.multi.contactsapp.domain.ContactList;
import com.multi.contactsapp.domain.Result;
import com.multi.contactsapp.service.ContactService;

@SpringBootTest
@AutoConfigureMockMvc
public class ContactRestControllerTest1 {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	ContactService contactService;

	@Test
	public void getAllContactsReturnsOkWithListOfContacts() throws Exception {
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact(1, "홍길동", "010-2222-1111", "서울"));
		contacts.add(new Contact(2, "이몽룡", "010-2222-1112", "제주"));
		contacts.add(new Contact(3, "성춘향", "010-2222-1113", "경기"));
		contacts.add(new Contact(4, "박문수", "010-2222-1114", "강원"));
		ContactList contactList = new ContactList(1, 4, 100, contacts);

		Mockito.when(contactService.getContactList()).thenReturn(contactList);

		mockMvc.perform(get("/contacts").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.pageNo", Matchers.is(1))).andExpect(jsonPath("$.contacts", Matchers.hasSize(4)))
				.andExpect(jsonPath("$.contacts[0].no", Matchers.is(1)))
				.andExpect(jsonPath("$.contacts[3].address", Matchers.is("강원"))).andDo(print());

	}

	@Test
	public void insertContactReturnsOkWithValidStatus() throws Exception {
		Result result = new Result("ok", "연락처 추가 성공", "5");

		Mockito.when(contactService.insertContact(Mockito.any((Contact.class)))).thenReturn(result);

		mockMvc.perform(MockMvcRequestBuilders.post("/contacts").contentType(MediaType.APPLICATION_JSON)
				.content("{ \"name\" : \"다희\", \"tel\" : \"010-9999-9999\", \"address\" : \"서울\" }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.status").value("ok"))
				.andDo(print());
	}

	@Test
	public void invalidRequestPathReturns404() throws Exception {
		mockMvc.perform(get("/asdf").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void invalidRequestReturns400WithAppStatus() throws Exception {
		mockMvc.perform(get("/test/error1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status").value("100"));
	}
}
