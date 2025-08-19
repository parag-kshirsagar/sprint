package com.tcs.ilp.catering_mgmt.enums;

public enum MenuType {
    VEG("Veg"),
    NON_VEG("Non-Veg");

    private final String displayName;

    MenuType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

