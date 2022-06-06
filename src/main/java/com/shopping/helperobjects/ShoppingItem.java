package com.shopping.helperobjects;

import static java.lang.Math.max;

public class ShoppingItem {
    public String name;
    public String category;
    public Integer amount;

    public ShoppingItem(String name, String category, Integer amount)
    {
        this.name = name;
        this.category = category;
        this.amount = amount;
    }

    public void increase_amount()
    {
        this.amount+=1;
    }

    public void decrease_amount()
    {
        this.amount=max(0, this.amount-1);
    }

}
