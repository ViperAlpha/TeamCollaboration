package com.uww.messaging.model.user;

import javax.persistence.*;

/**
 * Created by horvste on 3/19/16.
 */
@Entity
public class UserUploadedFile {
    private int userUploadedFileKey;
    private int userId;
    private String fileName;
    private String filePath;
    private int chatId;

    public UserUploadedFile() {
    }

    public UserUploadedFile(int userId, String fileName, String filePath, int chatId) {
        this.userId = userId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.chatId = chatId;
    }

    @Id
    @GeneratedValue
    @Column(name = "userUploadedFileKey", nullable = false)
    public int getUserUploadedFileKey() {
        return userUploadedFileKey;
    }

    public void setUserUploadedFileKey(int userUploadedFileKey) {
        this.userUploadedFileKey = userUploadedFileKey;
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
    @Column(name = "chatId", nullable = false)
    public int getChatId() {
        return chatId;
    }



    public void setChatId(int chatId) {
        this.chatId = chatId;
    }


    @Basic
    @Column(name = "fileName", nullable = false, length = 100)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "filePath", nullable = false, length = 500)
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserUploadedFile that = (UserUploadedFile) o;

        if (userUploadedFileKey != that.userUploadedFileKey) return false;
        if (userId != that.userId) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (filePath != null ? !filePath.equals(that.filePath) : that.filePath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userUploadedFileKey;
        result = 31 * result + userId;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
        return result;
    }
}
