package com.sun.vo;
import java.io.Serializable;
public class Action implements Serializable{
private Integer actid;//Ȩ�ޱ��
private Groups groups;//Ȩ��������Ȩ����
private String title;//Ȩ�޵�����
private String url;//·��
private Integer sflag;//������
public Action() {
}
public Action(Integer actid, Groups groups, String title, String url, Integer sflag) {
	super();
	this.actid = actid;
	this.groups = groups;
	this.title = title;
	this.url = url;
	this.sflag = sflag;
}
public Integer getActid() {
	return actid;
}
public void setActid(Integer actid) {
	this.actid = actid;
}
public Groups getGroups() {
	return groups;
}
public void setGroups(Groups groups) {
	this.groups = groups;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public Integer getSflag() {
	return sflag;
}
public void setSflag(Integer sflag) {
	this.sflag = sflag;
}
@Override
public String toString() {
	return "Action [actid=" + actid + ", groups=" + groups + ", title=" + title + ", url=" + url + ", sflag=" + sflag
			+ "]";
}

}
