package com.multi.oauth20resourceserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.oauth20resourceserver.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{
}