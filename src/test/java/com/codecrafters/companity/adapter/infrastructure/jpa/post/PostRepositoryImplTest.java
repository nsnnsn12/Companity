package com.codecrafters.companity.adapter.infrastructure.jpa.post;

import com.codecrafters.companity.config.QuerydslConfig;
import com.codecrafters.companity.domain.enums.City;
import com.codecrafters.companity.domain.enums.Sport;
import com.codecrafters.companity.domain.post.OrderType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({QuerydslConfig.class, PostRepositoryImpl.class})
class PostRepositoryImplTest {
    PostRepositoryImpl postRepository;
    @Autowired
    PostRepositoryImplTest(PostJPARepository postJPARepository, JPAQueryFactory jpaQueryFactory){
        postRepository = new PostRepositoryImpl(postJPARepository, jpaQueryFactory);
    }

    @Test
    void findBySportAndCityAndRecruitOrderByRecentDateOrFavorite() {
        postRepository.findBySportAndCityAndRecruitOrderByRecentDateOrFavorite(Sport.Badminton, City.Seoul, false, OrderType.Favorite);
    }
}