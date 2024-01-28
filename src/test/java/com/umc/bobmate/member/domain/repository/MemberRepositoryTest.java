package com.umc.bobmate.member.domain.repository;

import static com.umc.bobmate.content.domain.ContentType.VIDEO;
import static org.junit.jupiter.api.Assertions.*;

import com.umc.bobmate.content.domain.Content;
import com.umc.bobmate.content.domain.ContentType;
import com.umc.bobmate.content.domain.repository.ContentRepository;
import com.umc.bobmate.member.domain.Member;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

}