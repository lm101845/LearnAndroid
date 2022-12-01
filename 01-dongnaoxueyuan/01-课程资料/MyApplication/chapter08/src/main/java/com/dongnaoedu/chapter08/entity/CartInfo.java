package com.dongnaoedu.chapter08.entity;

//购物车信息
public class CartInfo {
    public int id;
    // 商品编号
    public int goodsId;
    // 商品数量
    public int count;
    // 商品信息
    public GoodsInfo goods;

    public CartInfo(){}

    public CartInfo(int id, int goodsId, int count) {
        this.id = id;
        this.goodsId = goodsId;
        this.count = count;
        this.goods = new GoodsInfo();
    }
}
