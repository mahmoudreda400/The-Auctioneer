package edu.mum.cs.auctioneer.models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "ADMIN")
@PrimaryKeyJoinColumn(name="ID") 
public class Admin extends Person{


	
	
}
