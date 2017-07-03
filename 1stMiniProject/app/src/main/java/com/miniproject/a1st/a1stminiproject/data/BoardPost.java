package com.miniproject.a1st.a1stminiproject.data;

import java.util.ArrayList;

/**
 * Created by jh on 17. 6. 30.
 * 게시글 정보
 */

public class BoardPost {
    String title;               // 제목
    String time;                // 작성 시간
    String content;             // 내용
    String attachUrl;           // 첨부 이미지 URL
    ArrayList<String> comments; // 댓글 리스트
    int numFavorite;            // 좋아요 수
    int numView;                // 조회수

    public BoardPost(String title, String time, String content, String attachUrl, ArrayList<String> comments, int numFavorite, int numView) {
        this.title = title;
        this.time = time;
        this.content = content;
        this.attachUrl = attachUrl;
        this.comments = comments;
        this.numFavorite = numFavorite;
        this.numView = numView;
    }

    // 새로운 글 추가
    public BoardPost(String title, String time, String content, String attachUrl) {
        this(title, time, content, attachUrl, null, 0, 0);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String title) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String attachUrl) {
        this.attachUrl = attachUrl;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public int getNumFavorite() {
        return numFavorite;
    }

    public void setNumFavorite(int numFavorite) {
        this.numFavorite = numFavorite;
    }

    public int getNumView() {
        return numView;
    }

    public void setNumView(int numView) {
        this.numView = numView;
    }
}
