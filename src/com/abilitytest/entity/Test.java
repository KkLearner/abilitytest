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
@Table(name="test")
public class Test implements Serializable {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="money")
	private Double money;
	
	@Column(name="uDate")
	private Date uDate;

	
	public Test() {
		super();
	}

	public Test(Integer id, String name, Double money, Date uDate) {
		super();
		this.id = id;
		this.name = name;
		this.money = money;
		this.uDate = uDate;
	}

	public Test(String name, Double money, Date uDate) {
		super();
		this.name = name;
		this.money = money;
		this.uDate = uDate;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Date getuDate() {
		return uDate;
	}

	public void setuDate(Date uDate) {
		this.uDate = uDate;
	}
	
	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + ", money=" + money + ", uDate=" + uDate + "]";
	}
	
	
}
