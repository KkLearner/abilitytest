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
@Table(name="answers")
public class Answers implements Serializable {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
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
	
	@Column(name="question_id")
	private Integer question_id;
	
	@Column(name="answer")
	private String answer;
	
	@Column(name="if_del")
	private Integer if_del;
	
	@Column(name="buildtime")
	private Date buildtime;
	
	@Column(name="modifytime")
	private Date modifytime;

	public Answers() {
		super();
	}

	public Answers(Integer id, Integer question_id, String answer, Integer if_del, Date buildtime, Date modifytime) {
		super();
		this.id = id;
		this.question_id = question_id;
		this.answer = answer;
		this.if_del = if_del;
		this.buildtime = buildtime;
		this.modifytime = modifytime;
	}

	public Answers(Integer question_id, String answer, Integer if_del, Date buildtime, Date modifytime) {
		super();
		this.question_id = question_id;
		this.answer = answer;
		this.if_del = if_del;
		this.buildtime = buildtime;
		this.modifytime = modifytime;
	}
	
	
}
