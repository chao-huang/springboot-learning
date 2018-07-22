package com.sun.vo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Dept implements Serializable {
private Integer did; //���ŵı��
private String title;//���ŵ�����
private Integer sflag;//������
private List<Emp> allEmps = new ArrayList<Emp>();//�����µ�Ա��
private List<Groups> allGroups;//���Ŷ�Ӧ��Ȩ������Ϣ
public Dept() {
}
public Dept(Integer did, String title, Integer sflag, List<Emp> allEmps, List<Groups> allGroups) {
	this.did = did;
	this.title = title;
	this.sflag = sflag;
	this.allEmps = allEmps;
	this.allGroups = allGroups;
}
public Integer getDid() {
	return did;
}
public void setDid(Integer did) {
	this.did = did;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public Integer getSflag() {
	return sflag;
}
public void setSflag(Integer sflag) {
	this.sflag = sflag;
}
public List<Emp> getAllEmps() {
	return allEmps;
}
public void setAllEmps(List<Emp> allEmps) {
	this.allEmps = allEmps;
}
public List<Groups> getAllGroups() {
	return allGroups;
}
public void setAllGroups(List<Groups> allGroups) {
	this.allGroups = allGroups;
}
@Override
public String toString() {
	return "Dept [did=" + did + ", title=" + title + ", sflag=" + sflag + ", allEmps=" + allEmps + ", allGroups="
			+ allGroups + "]";
}

}
