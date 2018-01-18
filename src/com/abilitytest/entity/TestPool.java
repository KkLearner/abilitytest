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
@Table(name="testpool")
public class TestPool implements Serializable {
	
	public Integer getIf_del() {
		return if_del;
	}

	public void setIf_del(Integer if_del) {
		this.if_del = if_del;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPerson_id() {
		return person_id;
	}

	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
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
	
	@Column(name="person_id")
	private Integer person_id;
	
	@Column(name="test")
	private String test;
	
	@Column(name="if_del")
	private Integer if_del;	
	
	@Column(name="buildtime")
	private Date buildtime;
	
	@Column(name="modifytime")
	private Date modifytime;

	
	public TestPool(Integer id, Integer person_id, String test, Integer if_del, Date buildtime, Date modifytime) {
		super();
		this.id = id;
		this.person_id = person_id;
		this.test = test;
		this.if_del = if_del;
		this.buildtime = buildtime;
		this.modifytime = modifytime;
	}

	public TestPool() {
		super();
	}

	public TestPool(Integer person_id, String test, Integer if_del, Date buildtime, Date modifytime) {
		super();
		this.person_id = person_id;
		this.test = test;
		this.if_del = if_del;
		this.buildtime = buildtime;
		this.modifytime = modifytime;
	}
	
	
}
