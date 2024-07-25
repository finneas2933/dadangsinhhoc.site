package com.example.dadangsinhhoc.exception;

// Xử lý ngoại lệ không tìm thấy bản ghi
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}