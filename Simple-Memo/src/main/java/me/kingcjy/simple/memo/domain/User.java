package me.kingcjy.simple.memo.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String uid;
    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Builder
    public User(String uid, String name, String password, SocialType socialType) {
        this.uid = uid;
        this.name = name;
        this.password = password;
        this.socialType = socialType;
    }
}
