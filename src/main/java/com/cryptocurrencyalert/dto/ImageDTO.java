package com.cryptocurrencyalert.dto;


public class ImageDTO {
    private int id;

    private String name;

    private String originalFileName;

    private Long size;

    private String contentType;

    private byte [] bytes;

    public ImageDTO(){}

    public ImageDTO(int id, String name, String originalFileName, Long size, String contentType, byte[] bytes) {
        this.id = id;
        this.name = name;
        this.originalFileName = originalFileName;
        this.size = size;
        this.contentType = contentType;
        this.bytes = bytes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
