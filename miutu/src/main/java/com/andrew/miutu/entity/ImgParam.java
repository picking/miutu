package com.andrew.miutu.entity;

import com.andrew.miutu.utils.UrlToolUilts;

/**
 * Created by Andrew on 2015/4/21.
 */

//请求路径http://image.baidu.com/data/imgs?col=美女&tag=小清新&sort=0&tag3=&pn=1&rn=60&p=channel&from=1
public class ImgParam {
    // 图片总标签
    private String col;
    // 图片子标签
    private String tag;
    // 从那一条数据开始拿
    private int pn;
    // 拿多少条
    private int rn;

    public String getCol() {
        return col;
    }

    public int getPn() {
        return pn;
    }

    public int getRn() {
        return rn;
    }

    public String getTag() {
        return tag;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public void setPn(int pn) {
        this.pn = pn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return UrlToolUilts.BASE_URL + "col="
                + UrlToolUilts.EncodeString(getCol()) + "&tag="
                + UrlToolUilts.EncodeString(getTag()) + "&sort=0&tag3=&pn="
                + getPn() + "&rn=" + getRn() + "&p=channel&from=1";
    }
}
