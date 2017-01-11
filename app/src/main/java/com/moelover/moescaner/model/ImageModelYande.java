package com.moelover.moescaner.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Administrator on 2015/12/6.
 */
public class ImageModelYande implements Serializable {
//    private int id;//"//: 339694,
//    private String tags;//"//: "aria_(okuda08808008) cleavage graf_zeppelin_(kancolle) kantai_collection pantyhose sweater",
//    private long created_at;//"//: 1449410682,
//    private int creator_id;//"//: 25882,
//    private String author;//"//: "Mr_GT",
//    private long change;//"//: 1791131,
//    private String source;//"//: "",
//    private int score;//"//: 5,
//    private String md5;//"//: "56b5f45d531cf561809715e10c586a07",
//    private long file_size;//"//: 2371089,
//    private String file_url;//"//: "https://files.yande.re/image/56b5f45d531cf561809715e10c586a07/yande.re%20339694%20aria_%28okuda08808008%29%20cleavage%20graf_zeppelin_%28kancolle%29%20kantai_collection%20pantyhose%20sweater.png",
//    private boolean is_shown_in_index;//"//: true,
//    private String preview_url;//"//: "https//://assets.yande.re/data/preview/56/b5/56b5f45d531cf561809715e10c586a07.jpg",
//    private int preview_width;//"//: 109,
//    private int preview_height;//"//: 150,
//    private int actual_preview_width;//"//: 217,
//    private int actual_preview_height;//"//: 300,
//    private String sample_url;//"//: "https//://files.yande.re/sample/56b5f45d531cf561809715e10c586a07/yande.re%20339694%20sample%20aria_%28okuda08808008%29%20cleavage%20graf_zeppelin_%28kancolle%29%20kantai_collection%20pantyhose%20sweater.jpg",
//    private int sample_width;//"//: 1087,
//    private int sample_height;//"//: 1500,
//    private int sample_file_size;//"//: 264481,
//    private String jpeg_url;//"//: "https//://files.yande.re/jpeg/56b5f45d531cf561809715e10c586a07/yande.re%20339694%20aria_%28okuda08808008%29%20cleavage%20graf_zeppelin_%28kancolle%29%20kantai_collection%20pantyhose%20sweater.jpg",
//    private int jpeg_width;//"//: 1482,
//    private int jpeg_height;//"//: 2046,
//    private long jpeg_file_size;//"//: 438727,
//    private String rating;//"//: "q",
//    private boolean has_children;//"//: true,
//    private int parent_id;//"//: null,
//    private String status;//"//: "active",
//    private int width;//"//: 1482,
//    private int height;//"//: 2046,
//    private boolean is_held;//"//: false,
//    private String frames_pending_string;//"//: "",
//    private String frames_string;//"//: "",


    private long id; // 377355,
    private String tags; // "girls_und_panzer nishizumi_miho sweater tomiya7112",
    private long created_at; // 1481654588,
    private long updated_at; // 1481654596,
    private int creator_id;
    private int approver_id;// null,
    private String author;// "Mr_GT",
    private long change;// 1996218,
    private String source;// "https;////i1.pixiv.net/img-original/img/2016/12/13/21/09/20/60363504_p0.jpg",
    private int score;// 0,
    private String md5;// "190e616fa00f5c40615cc4e1e6f6757c",
    private long file_size;// 7797634,
    private String file_ext;// "jpg",
    private String file_url;// "https;////files.yande.re/image/190e616fa00f5c40615cc4e1e6f6757c/yande.re%20377355%20girls_und_panzer%20nishizumi_miho%20sweater%20tomiya7112.jpg",
    private boolean is_shown_in_index;// true,
    private String preview_url;// "https;////assets.yande.re/data/preview/19/0e/190e616fa00f5c40615cc4e1e6f6757c.jpg",
    private int preview_width;// 112,
    private int preview_height;// 150,
    private int actual_preview_width;// 223,
    private int actual_preview_height;// 300,
    private String sample_url;// "https;////files.yande.re/sample/190e616fa00f5c40615cc4e1e6f6757c/yande.re%20377355%20sample%20girls_und_panzer%20nishizumi_miho%20sweater%20tomiya7112.jpg",
    private int sample_width;// 1116,
    private int sample_height;// 1500,
    private long sample_file_size;// 420288,
    private String jpeg_url;// "https;////files.yande.re/image/190e616fa00f5c40615cc4e1e6f6757c/yande.re%20377355%20girls_und_panzer%20nishizumi_miho%20sweater%20tomiya7112.jpg",
    private int jpeg_width;// 3200,
    private int jpeg_height;// 4300,
    private long jpeg_file_size;// 0,
    private String rating;// "s",
    private boolean is_rating_locked;// false,
    private boolean has_children;// false,
    private long parent_id;// null,
    private String status;// "active",
    private boolean is_pending;// false,
    private int width;// 3200,
    private int height;// 4300,
    private boolean is_held;// false,
    private String frames_pending_string;// "",
   // private String frames_pending;// [],
    private String frames_string;// "",
   // private String frames;// [],
    private boolean is_note_locked;// false,
    private int last_noted_at;// 0,
    private int last_commented_at;// 0


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public int getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(int approver_id) {
        this.approver_id = approver_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getChange() {
        return change;
    }

    public void setChange(long change) {
        this.change = change;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public String getFile_ext() {
        return file_ext;
    }

    public void setFile_ext(String file_ext) {
        this.file_ext = file_ext;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    public boolean is_shown_in_index() {
        return is_shown_in_index;
    }

    public void setIs_shown_in_index(boolean is_shown_in_index) {
        this.is_shown_in_index = is_shown_in_index;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }

    public int getPreview_width() {
        return preview_width;
    }

    public void setPreview_width(int preview_width) {
        this.preview_width = preview_width;
    }

    public int getPreview_height() {
        return preview_height;
    }

    public void setPreview_height(int preview_height) {
        this.preview_height = preview_height;
    }

    public int getActual_preview_width() {
        return actual_preview_width;
    }

    public void setActual_preview_width(int actual_preview_width) {
        this.actual_preview_width = actual_preview_width;
    }

    public int getActual_preview_height() {
        return actual_preview_height;
    }

    public void setActual_preview_height(int actual_preview_height) {
        this.actual_preview_height = actual_preview_height;
    }

    public String getSample_url() {
        return sample_url;
    }

    public void setSample_url(String sample_url) {
        this.sample_url = sample_url;
    }

    public int getSample_width() {
        return sample_width;
    }

    public void setSample_width(int sample_width) {
        this.sample_width = sample_width;
    }

    public int getSample_height() {
        return sample_height;
    }

    public void setSample_height(int sample_height) {
        this.sample_height = sample_height;
    }

    public long getSample_file_size() {
        return sample_file_size;
    }

    public void setSample_file_size(long sample_file_size) {
        this.sample_file_size = sample_file_size;
    }

    public String getJpeg_url() {
        return jpeg_url;
    }

    public void setJpeg_url(String jpeg_url) {
        this.jpeg_url = jpeg_url;
    }

    public int getJpeg_width() {
        return jpeg_width;
    }

    public void setJpeg_width(int jpeg_width) {
        this.jpeg_width = jpeg_width;
    }

    public int getJpeg_height() {
        return jpeg_height;
    }

    public void setJpeg_height(int jpeg_height) {
        this.jpeg_height = jpeg_height;
    }

    public long getJpeg_file_size() {
        return jpeg_file_size;
    }

    public void setJpeg_file_size(long jpeg_file_size) {
        this.jpeg_file_size = jpeg_file_size;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public boolean is_rating_locked() {
        return is_rating_locked;
    }

    public void setIs_rating_locked(boolean is_rating_locked) {
        this.is_rating_locked = is_rating_locked;
    }

    public boolean isHas_children() {
        return has_children;
    }

    public void setHas_children(boolean has_children) {
        this.has_children = has_children;
    }

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean is_pending() {
        return is_pending;
    }

    public void setIs_pending(boolean is_pending) {
        this.is_pending = is_pending;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean is_held() {
        return is_held;
    }

    public void setIs_held(boolean is_held) {
        this.is_held = is_held;
    }

    public String getFrames_pending_string() {
        return frames_pending_string;
    }

    public void setFrames_pending_string(String frames_pending_string) {
        this.frames_pending_string = frames_pending_string;
    }

//    public String getFrames_pending() {
//        return frames_pending;
//    }
//
//    public void setFrames_pending(String frames_pending) {
//        this.frames_pending = frames_pending;
//    }

    public String getFrames_string() {
        return frames_string;
    }

    public void setFrames_string(String frames_string) {
        this.frames_string = frames_string;
    }

//    public String getFrames() {
//        return frames;
//    }
//
//    public void setFrames(String frames) {
//        this.frames = frames;
//    }

    public boolean is_note_locked() {
        return is_note_locked;
    }

    public void setIs_note_locked(boolean is_note_locked) {
        this.is_note_locked = is_note_locked;
    }

    public int getLast_noted_at() {
        return last_noted_at;
    }

    public void setLast_noted_at(int last_noted_at) {
        this.last_noted_at = last_noted_at;
    }

    public int getLast_commented_at() {
        return last_commented_at;
    }

    public void setLast_commented_at(int last_commented_at) {
        this.last_commented_at = last_commented_at;
    }

    public String getFileName() {
        return "yande.re "+getId()+"."+file_ext;
    }
}
