-- board 생성
create table board (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시글 식별자',
                       member_id BIGINT COMMENT '사용자 식별자',
                       member_name VARCHAR(50) NOT NULL COMMENT '사용자 이름',
                       title VARCHAR(100) NOT NULL COMMENT '게시글 제목',
                       password VARCHAR(255) NOT NULL COMMENT '게시글 비밀번호',
                       content TEXT COMMENT '게시글 내용',
                       image VARCHAR(255) COMMENT '게시글에 업로드 된 이미지',
                       create_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '게시글 생성시간',
                       update_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '게시글 수정시간'
);

-- member 생성

CREATE TABLE members (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 식별자',
                         member_name VARCHAR(50) NOT NULL COMMENT '사용자 이름',
                         password VARCHAR(255) NOT NULL COMMENT '사용자 비밀번호',
                         create_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '사용자 정보 생성 시간',
                         update_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '사용자 정보 수정 시간'
);
-- 외래키 작성 / fk_참조테이블_대상테이블
ALTER TABLE board
    ADD CONSTRAINT fk_members_board FOREIGN KEY (member_id) REFERENCES members(id);