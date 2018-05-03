package org.rabbit.purchasing.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collection;
import java.util.LinkedHashSet;

@Document
public class Order {

    @Id
    private String id;

    @Field("client")
    private String customer;

    private String type;

    private Collection<Item> items = new LinkedHashSet<Item>();

    public String getCustomer() {
        return customer;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }
}
