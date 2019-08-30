package com.backend.economundi.database.dao.entity;

public class Word {
    private Long id;
    private String name;
    private String description;
    private Long amountSearch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
        public Long getAmountSearch() {
        return amountSearch;
    }

    public void setAmountSearch(Long amountSearch) {
        this.amountSearch = amountSearch;
    }
}
