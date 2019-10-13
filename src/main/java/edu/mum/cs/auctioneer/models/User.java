package edu.mum.cs.auctioneer.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "USER")
@PrimaryKeyJoinColumn(name = "ID")
public class User extends Person {

	@Column(name = "BLOCKED")
	private Boolean blocked = false;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Post.class)
	@JsonIgnoreProperties(value = { "user" })
	private Set<Post> posts;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", targetEntity = Bidding.class)
	@JsonIgnoreProperties(value = { "user" })
	private Set<Bidding> biddings;

//	 reports which he did it to other users
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reporter", targetEntity = Report.class)
	@JsonIgnoreProperties(value = { "reporter" })
	private Set<Report> makeReports;

	// reports which other users did it to him
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reported", targetEntity = Report.class)
	@JsonIgnoreProperties(value = { "reported" })
	private Set<Report> hasReports;

	public User() {

	}

	public Boolean getBlocked() {
		return blocked;
	}

	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

	public Set<Bidding> getBiddings() {
		return biddings;
	}

	public void setBiddings(Set<Bidding> biddings) {
		this.biddings = biddings;
	}

	public Set<Report> getMakeReports() {
		return makeReports;
	}

	public void setMakeReports(Set<Report> makeReports) {
		this.makeReports = makeReports;
	}

	public Set<Report> getHasReports() {
		return hasReports;
	}

	public void setHasReports(Set<Report> hasReports) {
		this.hasReports = hasReports;
	}

}
