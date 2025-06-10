package com.example.springboardproject.member.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "members")
// 멤버 객체에 멤버 한명의 데이터가 담기기 때문에 단수로 사용
public class Member {

    //속
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "member_name", nullable = false, length = 50)
    private String memberName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "create_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @LastModifiedDate
    private LocalDateTime updateAt;

    //생
    /**
     * JPA가 사용하기 위한 기본 생성자
     * 다른 코드에서 호출 할 위험을 막기 위해 protect로 변경
     */

    protected Member(){}
    //기

    /**
     * MemberService.createMemberService
     * MemberCreatRequestDto 데이터를 받아 Entity로 사용
     */
    private Member(String memberName, String password) {
        this.memberName = memberName;
        this.password = password;
    }
    // 팩토리메서드 패턴
    public static Member createFromMemberCreateRequestDto(String memberName, String password){
        return new Member(memberName, password);
    }

    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }
}
