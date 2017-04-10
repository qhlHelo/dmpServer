package com.chinagreentown.dmp.pojo.precisionmarketing;

/**
 * Created by admin on 2017/3/28.
 * 购物行为
 */
public class Shopping {
    // 购物金额/月
    private String amount;
    // 购物频率/月
    private String frequency;

    public Shopping(String amount, String frequency) {
        this.amount = amount;
        this.frequency = frequency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
