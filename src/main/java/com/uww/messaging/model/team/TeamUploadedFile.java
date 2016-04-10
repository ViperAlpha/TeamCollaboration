package com.uww.messaging.model.team;

import javax.persistence.*;

/**
 * Created by horvste on 4/9/16.
 */
@Entity
public class TeamUploadedFile {
    private int teamUploadedFileKey;

    public TeamUploadedFile() {
    }

    public TeamUploadedFile(int userId, String fileName, String filePath, Integer teamMessageChatId) {
        this.userId = userId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.teamMessageChatId = teamMessageChatId;
    }

    @Id
    @GeneratedValue
    @Column(name = "teamUploadedFileKey", nullable = false)
    public int getTeamUploadedFileKey() {
        return teamUploadedFileKey;
    }

    public void setTeamUploadedFileKey(int teamUploadedFileKey) {
        this.teamUploadedFileKey = teamUploadedFileKey;
    }

    private int userId;

    @Basic
    @Column(name = "userId", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private String fileName;

    @Basic
    @Column(name = "fileName", nullable = true, length = 100)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String filePath;

    @Basic
    @Column(name = "filePath", nullable = true, length = 500)
    public String getFilePath() {
        return filePath;
    }


    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    private Integer teamMessageChatId;

    @Basic
    @Column(name = "teamMessageChatId", nullable = true)
    public Integer getTeamMessageChatId() {
        return teamMessageChatId;
    }

    public void setTeamMessageChatId(Integer teamMessageChatId) {
        this.teamMessageChatId = teamMessageChatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamUploadedFile that = (TeamUploadedFile) o;

        if (teamUploadedFileKey != that.teamUploadedFileKey) return false;
        if (userId != that.userId) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (filePath != null ? !filePath.equals(that.filePath) : that.filePath != null) return false;
        if (teamMessageChatId != null ? !teamMessageChatId.equals(that.teamMessageChatId) : that.teamMessageChatId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = teamUploadedFileKey;
        result = 31 * result + userId;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        result = 31 * result + (teamMessageChatId != null ? teamMessageChatId.hashCode() : 0);
        return result;
    }
}
