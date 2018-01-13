package com.abilitytest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="questions")
public class Questions implements Serializable {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(Integer testNumber) {
		this.testNumber = testNumber;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getIf_del() {
		return if_del;
	}

	public void setIf_del(Integer if_del) {
		this.if_del = if_del;
	}

	public Date getBuildtime() {
		return buildtime;
	}

	public void setBuildtime(Date buildtime) {
		this.buildtime = buildtime;
	}

	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="testNumber")
	private Integer testNumber;
	
	@Column(name="order")
	private Integer order;
	
	@Column(name="if_del")
	private Integer if_del;
	
	@Column(name="buildtime")
	private Date buildtime;
	
	@Column(name="modifytime")
	private Date modifytime;

	public Questions() {
		super();
	}

	public Questions(Integer id, String title, Integer testNumber, Integer order, Integer if_del, Date buildtime,
			Date modifytime) {
		super();
		this.id = id;
		this.title = title;
		this.testNumber = testNumber;
		this.order = order;
		this.if_del = if_del;
		this.buildtime = buildtime;
		this.modifytime = modifytime;
	}

	public Questions(String title, Integer testNumber, Integer order, Integer if_del, Date buildtime, Date modifytime) {
		super();
		this.title = title;
		this.testNumber = testNumber;
		this.order = order;
		this.if_del = if_del;
		this.buildtime = buildtime;
		this.modifytime = modifytime;
	}
	
}
