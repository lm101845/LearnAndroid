package com.dongnaoedu.chapter07_client.util;

public class ImageInfo {
    public long id; // 图片编号
    public String name; // 图片标题
    public long size; // 文件大小
    public String path; // 文件路径

    @Override
    public String toString() {
        return "ImageInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", path='" + path + '\'' +
                '}';
    }
}
