package com.nia.sovaultcraft;

public enum SovaultCraftMeta {

    id("sovaultcraft"),
    name("sovaultcraft"),
    version("0.1dev");

    private final String value;

    SovaultCraftMeta(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
