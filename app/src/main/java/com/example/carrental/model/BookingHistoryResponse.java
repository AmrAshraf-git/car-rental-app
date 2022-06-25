package com.example.carrental.model;

import java.util.List;

public class BookingHistoryResponse {
    private String message;
    private List<BookingHistory> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<BookingHistory> getData() {
        return data;
    }

    public void setData(List<BookingHistory> data) {
        this.data = data;
    }
}
