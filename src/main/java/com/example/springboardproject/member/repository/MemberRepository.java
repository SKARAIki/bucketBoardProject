package com.example.springboardproject.member.repository;

import com.example.springboardproject.member.entity.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
