package org.rabbit.purchasing.models;

public class Item {

    private Long id;
    private Order order;
    private String product;
    private double price;
    private int quantity;

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public String getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
