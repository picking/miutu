package com.andrew.miutu.entity;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;

@Table(name = "loveImg")
public class ImageDetial implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id(column = "id")
	private String id;
	private String desc;
	private String fromPageTitle;
	private String column;
	private String date;
    @Column(column = "downloadUrl")
	private String downloadUrl;
    @Column(column = "imageUrl")
	private String imageUrl;
    @Column(column = "imageWidth")
	private int imageWidth;
    @Column(column = "imageHeight")
	private int imageHeight;
    @Column(column = "thumbnailUrl")
	private String thumbnailUrl;
    @Column(column = "thumbnailWidth")
	private int thumbnailWidth;
    @Column(column = "thumbnailHeight")
	private int thumbnailHeight;
    @Column(column = "thumbLargeUrl")
	private String thumbLargeUrl;
    @Column(column = "thumbLargeWidth")
	private int thumbLargeWidth;
    @Column(column = "thumbLargeHeight")
	private int thumbLargeHeight;
    @Column(column = "thumbLargeTnUrl")
	private String thumbLargeTnUrl;
    @Column(column = "thumbLargeTnWidth")
	private int thumbLargeTnWidth;
    @Column(column = "thumbLargeTnHeight")
	private int thumbLargeTnHeight;
	private String objTag;
    @Column(column = "title")
	private String title;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFromPageTitle() {
		return fromPageTitle;
	}

	public void setFromPageTitle(String fromPageTitle) {
		this.fromPageTitle = fromPageTitle;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public int getThumbnailWidth() {
		return thumbnailWidth;
	}

	public void setThumbnailWidth(int thumbnailWidth) {
		this.thumbnailWidth = thumbnailWidth;
	}

	public int getThumbnailHeight() {
		return thumbnailHeight;
	}

	public void setThumbnailHeight(int thumbnailHeight) {
		this.thumbnailHeight = thumbnailHeight;
	}

	public String getThumbLargeUrl() {
		return thumbLargeUrl;
	}

	public void setThumbLargeUrl(String thumbLargeUrl) {
		this.thumbLargeUrl = thumbLargeUrl;
	}

	public int getThumbLargeWidth() {
		return thumbLargeWidth;
	}

	public void setThumbLargeWidth(int thumbLargeWidth) {
		this.thumbLargeWidth = thumbLargeWidth;
	}

	public int getThumbLargeHeight() {
		return thumbLargeHeight;
	}

	public void setThumbLargeHeight(int thumbLargeHeight) {
		this.thumbLargeHeight = thumbLargeHeight;
	}

	public String getThumbLargeTnUrl() {
		return thumbLargeTnUrl;
	}

	public void setThumbLargeTnUrl(String thumbLargeTnUrl) {
		this.thumbLargeTnUrl = thumbLargeTnUrl;
	}

	public int getThumbLargeTnWidth() {
		return thumbLargeTnWidth;
	}

	public void setThumbLargeTnWidth(int thumbLargeTnWidth) {
		this.thumbLargeTnWidth = thumbLargeTnWidth;
	}

	public int getThumbLargeTnHeight() {
		return thumbLargeTnHeight;
	}

	public void setThumbLargeTnHeight(int thumbLargeTnHeight) {
		this.thumbLargeTnHeight = thumbLargeTnHeight;
	}

	public String getObjTag() {
		return objTag;
	}

	public void setObjTag(String objTag) {
		this.objTag = objTag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
