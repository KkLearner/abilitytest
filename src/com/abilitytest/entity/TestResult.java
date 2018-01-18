package com.abilitytest.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="testresult")
public class TestResult implements Serializable {

	public Integer getTestpool_id() {
		return testpool_id;
	}

	public void setTestpool_id(Integer testpool_id) {
		this.testpool_id = testpool_id;
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

	public Integer getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(Integer testNumber) {
		this.testNumber = testNumber;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getIf_del() {
		return if_del;
	}

	public void setIf_del(Integer if_del) {
		this.if_del = if_del;
	}

	public Time getUsetime() {
		return usetime;
	}

	public void setUsetime(Time usetime) {
		this.usetime = usetime;
	}

	public Date getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="person_id")
	private Integer person_id;
	
	@Column(name="testpool_id")
	private Integer testpool_id;
	
	@Column(name="testNumber")
	private Integer testNumber;
	
	@Column(name="answer")
	private String answer;
	
	@Column(name="if_del")
	private Integer if_del;
	
	@Column(name="usetime")
	private Time usetime;
	
	@Column(name="finishtime")
	private Date finishtime;

	

	public TestResult(Integer person_id, Integer testpool_id, Integer testNumber, String answer, Integer if_del,
			Time usetime, Date finishtime) {
		super();
		this.person_id = person_id;
		this.testpool_id = testpool_id;
		this.testNumber = testNumber;
		this.answer = answer;
		this.if_del = if_del;
		this.usetime = usetime;
		this.finishtime = finishtime;
	}

	public TestResult(Integer id, Integer person_id, Integer testpool_id, Integer testNumber, String answer,
			Integer if_del, Time usetime, Date finishtime) {
		super();
		this.id = id;
		this.person_id = person_id;
		this.testpool_id = testpool_id;
		this.testNumber = testNumber;
		this.answer = answer;
		this.if_del = if_del;
		this.usetime = usetime;
		this.finishtime = finishtime;
	}

	public TestResult() {
		super();
	}
	
	
	
}
