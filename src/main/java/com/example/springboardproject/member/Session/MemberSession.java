package com.example.springboardproject.member.Session;

public class MemberSession {
    private Long memberId;
    private String role;

    public MemberSession(Long memberId, String role) {
        this.memberId = memberId;
        this.role = role;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getRole() {
        return role;
    }
}
