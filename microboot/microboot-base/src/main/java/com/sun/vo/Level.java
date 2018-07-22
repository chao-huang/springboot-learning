package com.sun.vo;

import java.io.Serializable;

public class Level implements Serializable {
private Integer lid;
private String title;
private Double losal;
private Double hisal;

public Level() {
}
public Level(Integer lid, String tltle, Double losal, Double hisal) {
	super();
	this.lid = lid;
	this.title = tltle;
	this.losal = losal;
	this.hisal = hisal;
}
public Integer getLid() {
	return lid;
}
public void setLid(Integer lid) {
	this.lid = lid;
}
public String getTitle() {
	return title;
}
public void setTitle(String tltle) {
	this.title = tltle;
}
public Double getLosal() {
	return losal;
}
public void setLosal(Double losal) {
	this.losal = losal;
}
public Double getHisal() {
	return hisal;
}
public void setHisal(Double hisal) {
	this.hisal = hisal;
}
@Override
public String toString() {
	return "level [lid=" + lid + ", title=" + title + ", losal=" + losal + ", hisal=" + hisal + "]";
}

}
