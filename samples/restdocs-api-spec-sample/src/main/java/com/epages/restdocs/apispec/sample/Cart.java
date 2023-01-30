package com.epages.restdocs.apispec.sample;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.math.BigDecimal.ZERO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    @JsonIgnore
    private boolean ordered;

    public Long getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public BigDecimal getTotal() {
        return products.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal::add)
                .orElse(ZERO);
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }
}
