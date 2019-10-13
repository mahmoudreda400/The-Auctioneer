package edu.mum.cs.auctioneer.models;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@Entity
//@Table(name = "POST")
public class Post {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "TITLE")
	private String title;
	@Column(name = "DESC")
	private String desc;
	@Column(name = "EXPIRDATE")
	private LocalDate expirDate;
	@Column(name = "MINPRICE")
	private Integer minPrice;
	@Column(name = "INCRVALUE")
	private Integer incrValue;
	@Column(name = "CITY")
	private String city;
	@Column(name = "COUNTRY")
	private String country;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	@JsonIgnoreProperties(value = { "posts" })
	private User user;

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Bidding.class, mappedBy = "post")
	@JsonIgnoreProperties(value = { "post" })
	private Set<Bidding> biddings;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Photo.class, mappedBy = "post")
	@JsonIgnoreProperties(value = { "post" })
	private Set<Photo> photos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public LocalDate getExpirationDate() {
		return expirDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirDate = expirationDate;
	}

	public Integer getMinimumPrice() {
		return minPrice;
	}

	public void setMinimumPrice(Integer minimumPrice) {
		this.minPrice = minimumPrice;
	}

	public Integer getIncrementalValue() {
		return incrValue;
	}

	public void setIncrementalValue(Integer incrementalValue) {
		this.incrValue = incrementalValue;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Bidding> getBiddings() {
		return biddings;
	}

	public void setBiddings(Set<Bidding> biddings) {
		this.biddings = biddings;
	}

	public Set<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}

}