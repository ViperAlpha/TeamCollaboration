package com.uww.messaging.model;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created on 4/9/16
 *
 * @author reinaldo
 */
public class Wiki {
	private int wikiId;
	private int userId;
	private String content;
	private Timestamp editTime;

	@Id
	@GeneratedValue
	@Column(name = "wikiId", nullable = false)
	public int getWikiId() {
		return wikiId;
	}

	public void setWikiId(final int wikiId) {
		this.wikiId = wikiId;
	}

	@Basic
	@Column(name = "userId", nullable = false)
	public int getUserId() {
		return userId;
	}

	public void setUserId(final int userId) {
		this.userId = userId;
	}

	@Basic
	@Column(name = "content", nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	@Basic
	@Column(name = "editTime", nullable = false)
	public Timestamp getEditTime() {
		return editTime;
	}

	public void setEditTime(final Timestamp editTime) {
		this.editTime = editTime;
	}
}
