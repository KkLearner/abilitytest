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
@Table(name="administrator")
public class Administrator implements Serializable {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
	
	@Column(name="account")
	private String account;
	
	@Column(name="password")
	private String password;
	
	@Column(name="type")
	private Integer type;
	
	@Column(name="if_del")
	private Integer if_del;
	
	@Column(name="buildtime")
	private Date buildtime;
	
	@Column(name="modifytime")
	private Date modifytime;

	public Administrator(Integer id, String account, String password, Integer type, Integer if_del, Date buildtime,
			Date modifytime) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.type = type;
		this.if_del = if_del;
		this.buildtime = buildtime;
		this.modifytime = modifytime;
	}

	public Administrator(String account, String password, Integer type, Integer if_del, Date buildtime,
			Date modifytime) {
		super();
		this.account = account;
		this.password = password;
		this.type = type;
		this.if_del = if_del;
		this.buildtime = buildtime;
		this.modifytime = modifytime;
	}

	public Administrator() {
		super();
	}

	
}
