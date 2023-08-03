package com.capstone.capstoneorder;

public enum Admin {
    TRUE(true),
    FALSE(false);

    private final boolean value;

    Admin(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}