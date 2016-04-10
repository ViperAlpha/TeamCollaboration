package com.uww.messaging.model.wiki;

import java.sql.Timestamp;

import javax.persistence.*;

/**
 * Created on 4/9/16
 *
 * @author reinaldo
 */
/**
 * Created by horvste on 4/9/16.
 */

@Entity
public class Wiki {
	private int wikiId;
	private int userId;
	private String content;
	private Timestamp editTime;

	@Id
	@Column(name = "wikiId", nullable = false)
	public int getWikiId() {
		return wikiId;
	}

	public void setWikiId(int wikiId) {
		this.wikiId = wikiId;
	}

	@Basic
	@Column(name = "userId", nullable = false)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Basic
	@Column(name = "content", nullable = true, length = -1)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Basic
	@Column(name = "editTime", nullable = false)
	public Timestamp getEditTime() {
		return editTime;
	}

	public void setEditTime(Timestamp editTime) {
		this.editTime = editTime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Wiki wiki = (Wiki) o;

		if (wikiId != wiki.wikiId) return false;
		if (userId != wiki.userId) return false;
		if (content != null ? !content.equals(wiki.content) : wiki.content != null) return false;
		if (editTime != null ? !editTime.equals(wiki.editTime) : wiki.editTime != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = wikiId;
		result = 31 * result + userId;
		result = 31 * result + (content != null ? content.hashCode() : 0);
		result = 31 * result + (editTime != null ? editTime.hashCode() : 0);
		return result;
	}
}
