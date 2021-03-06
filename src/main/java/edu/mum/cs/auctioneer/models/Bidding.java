package edu.mum.cs.auctioneer.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "BIDDING")
public class Bidding extends AbstractEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
//	@Column(name = "BID_DATE")
//	private LocalTime date;
	@Column(name = "PRICE")
	private Double price;

	@ManyToOne
	@JoinColumn(name = "POST_ID")
	@JsonIgnoreProperties(value = { "biddings" })
	private Post post;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	@JsonIgnoreProperties(value = { "biddings","posts", "makeReports","hasReports" })
	private User user;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public LocalTime getDate() {
//		return date;
//	}
//
//	public void setDate(LocalTime date) {
//		this.date = date;
//	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
