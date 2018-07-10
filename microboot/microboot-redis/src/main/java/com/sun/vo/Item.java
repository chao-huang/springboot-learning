package com.sun.vo;
import java.io.Serializable;
public class Item implements Serializable{
private Integer iid;
private String title;
private String content;
public Integer getIid() {
	return iid;
}
public void setIid(Integer iid) {
	this.iid = iid;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
@Override
public String toString() {
	return "Item [iid=" + iid + ", title=" + title + ", content=" + content + "]";
}


}
