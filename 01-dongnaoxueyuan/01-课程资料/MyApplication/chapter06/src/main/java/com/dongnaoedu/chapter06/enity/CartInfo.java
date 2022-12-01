package com.dongnaoedu.chapter06.enity;

//购物车信息
public class CartInfo {
    public int id;
    // 商品编号
    public int goodsId;
    // 商品数量
    public int count;

    public CartInfo(){}

    public CartInfo(int id, int goodsId, int count) {
        this.id = id;
        this.goodsId = goodsId;
        this.count = count;
    }
}
