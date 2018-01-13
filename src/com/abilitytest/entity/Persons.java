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
@Table(name="persons")
public class Persons implements Serializable {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getDisability_type() {
		return disability_type;
	}

	public void setDisability_type(Integer disability_type) {
		this.disability_type = disability_type;
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
	
	@Column(name="id_number")
	private String id_number;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="sex")
	private Integer sex;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="disability_type")
	private Integer disability_type;
	
	@Column(name="type")
	private Integer type;
	
	@Column(name="if_del")
	private Integer if_del;
	
	@Column(name="buildtime")
	private Date buildtime;
	
	@Column(name="modifytime")
	private Date modifytime;

	public Persons() {
		super();
	}

	public Persons(Integer id, String id_number, String name, String password, Integer sex, String phone,
			Integer disability_type, Integer type, Integer if_del, Date buildtime, Date modifytime) {
		super();
		this.id = id;
		this.id_number = id_number;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.disability_type = disability_type;
		this.type = type;
		this.if_del = if_del;
		this.buildtime = buildtime;
		this.modifytime = modifytime;
	}

	public Persons(String id_number, String name, String password, Integer sex, String phone, Integer disability_type,
			Integer type, Integer if_del, Date buildtime, Date modifytime) {
		super();
		this.id_number = id_number;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.phone = phone;
		this.disability_type = disability_type;
		this.type = type;
		this.if_del = if_del;
		this.buildtime = buildtime;
		this.modifytime = modifytime;
	}
	
	
}
