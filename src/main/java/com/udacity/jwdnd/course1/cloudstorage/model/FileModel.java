package com.udacity.jwdnd.course1.cloudstorage.model;

public class FileModel {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private byte[] fileData;
    private String base64FileData;
    private Integer userId;

    public FileModel() {
    }
    public FileModel(Integer fileId, String fileName, String contentType, String fileSize, byte[] fileData, String base64FileData, Integer userId) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
        this.base64FileData = base64FileData;
        this.userId = userId;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getBase64FileData() {
        return base64FileData;
    }

    public void setBase64FileData(String base64FileData) {
        this.base64FileData = base64FileData;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
