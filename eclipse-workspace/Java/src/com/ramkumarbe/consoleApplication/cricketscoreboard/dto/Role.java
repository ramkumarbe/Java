package com.ramkumarbe.consoleApplication.cricketscoreboard.dto;

public enum Role {
    BATSMAN("Batsman"),
    BOWLER("Bowler"),
    ALLROUNDER("All-Rounder");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

