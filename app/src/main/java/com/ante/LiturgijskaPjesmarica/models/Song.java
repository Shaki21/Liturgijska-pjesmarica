package com.ante.LiturgijskaPjesmarica.models;

public class Song {
    private String title;
    private String pdfPath;

    public Song(String title, String pdfPath) {
        this.title = title;
        this.pdfPath = pdfPath;
    }

    public String getTitle() {
        return title;
    }

    public String getPdfPath() {
        return pdfPath;
    }
}
