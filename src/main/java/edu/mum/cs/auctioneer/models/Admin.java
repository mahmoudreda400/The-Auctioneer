package edu.mum.cs.auctioneer.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "ADMIN")
@PrimaryKeyJoinColumn(name = "ID")
public class Admin extends Person {

	public Admin(){}

	public Admin(long id, String name, String email, String password, String phone, String address) {
		super(id, name, email, password, phone, address);

	}

}
