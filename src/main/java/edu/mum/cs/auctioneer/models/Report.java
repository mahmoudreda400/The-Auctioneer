package edu.mum.cs.auctioneer.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
//@Table(name = "REPORT")
public class Report {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "REPORT_DATE")
	private LocalDate date;
	@Column(name = "DESC")
	private String desc;

	@ManyToOne
	@JoinColumn(name = "REPORTER_ID")
	private User reporter;

	@ManyToOne
	@JoinColumn(name = "REPORTED_ID")
	private User reported;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public User getReported() {
		return reported;
	}

	public void setReported(User reported) {
		this.reported = reported;
	}

}
