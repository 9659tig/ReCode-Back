package com.example.recode.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String password;    // 비밀번호

    private String nickname;    // 닉네임

    @Enumerated(EnumType.STRING)
    private MembershipLevel membershipLevel;    // 멤버쉽 등급

    private String imageUrl;    // 프로필이미지URL

    private int heart;          // 받은 하트 수

    @OneToMany(mappedBy = "groupLeader", cascade = CascadeType.ALL)
    private List<Group> groups = new ArrayList<>();

    @OneToMany(mappedBy = "groupMember", cascade = CascadeType.ALL)
    private List<User_Group> user_groups = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Algorithm> algorithms = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Folder> folders = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Problem> problems = new ArrayList<>();

    @OneToMany(mappedBy = "bestCoder", cascade = CascadeType.ALL)
    private List<Problem> bestCodeProblems = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "voter", cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();
}
