package com.example.android.miwok;

/**
 * Created by Maura on 5/17/2017.
 */

public class Word {

    private final int NO_RESOURCE_PROVIDED = -1;

    private String miwok;
    private String english;
    private int imgResourceId;
    private int audioResourceId;

    public Word(String miwok, String english) {
        this.miwok = miwok;
        this.english = english;
        this.imgResourceId = NO_RESOURCE_PROVIDED;
        this.audioResourceId = NO_RESOURCE_PROVIDED;
    }

    public Word(String miwok, String english, int audioResourceId) {
        this.miwok = miwok;
        this.english = english;
        this.audioResourceId = audioResourceId;
        this.imgResourceId = NO_RESOURCE_PROVIDED;
    }

    public Word(String miwok, String english, int imgResourceId, int audioResourceId) {
        this.miwok = miwok;
        this.english = english;
        this.imgResourceId = imgResourceId;
        this.audioResourceId = audioResourceId;
    }

    public String getMiwok() {
        return miwok;
    }

    public void setMiwok(String miwok) {
        this.miwok = miwok;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }

    public void setImgResourceId(int imgResourceId) {
        this.imgResourceId = imgResourceId;
    }

    public boolean hasImage() {
        return imgResourceId != NO_RESOURCE_PROVIDED;
    }

    public int getAudioResourceId() {
        return audioResourceId;
    }

    public void setAudioResourceId(int audioResourceId) {
        this.audioResourceId = audioResourceId;
    }

    public boolean hasAudio() {
        return audioResourceId != NO_RESOURCE_PROVIDED;
    }
}
