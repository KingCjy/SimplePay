package me.kingcjy.simple.simplepay.domain;

public enum SocialType {
    SIMPLE("simple");

    private String name;

    SocialType(String name) {
        this.name = name;
    }

    public String getRoleType() {
        return "ROLE_" + name.toUpperCase();
    }

    public String getValue() {
        return name;
    }

    public boolean isEquals(String authority) {
        return this.getRoleType().equals(authority);
    }
}

