package com.demo.model;

public class CardDomain {
    private String cardId;
    private int userId;
    private int func_bmp;

    public int getUserId() {
        return this.userId;
    }
    public  String getCardId()
    {
        return this.cardId;
    }
    public int getFunc_bmp()
    {
        return this.func_bmp;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }
    public void setCardId(String cardId)
    {
        this.cardId = cardId;
    }
    public void setFunc_bmp(int func_bmp)
    {
        this.func_bmp = func_bmp;
    }
    @Override
    public String toString()
    {
        return "userid:"+this.userId+" "+"cardid:"+this.cardId+" "+"func_bmp:"+this.func_bmp+" ";
    }
}
