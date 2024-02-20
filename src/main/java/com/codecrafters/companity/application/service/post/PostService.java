package com.codecrafters.companity.application.service.post;

import com.codecrafters.companity.application.in.usecase.PostUseCase;
import com.codecrafters.companity.application.out.utility.LocalDateTimeProvider;
import com.codecrafters.companity.application.out.persistance.PostRepository;
import com.codecrafters.companity.application.out.persistance.UserRepository;
import com.codecrafters.companity.domain.post.OrderType;
import com.codecrafters.companity.domain.post.Post;
import com.codecrafters.companity.domain.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Slf4j
@Service
public class PostService implements PostUseCase {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostFactory postFactory;

    private final LocalDateTimeProvider dateTimeProvider;

    @Override
    public Post add(Post post, Long userId) {
        User user = userRepository.getUserById(userId);
        Post newPost = postFactory.create(post, user, dateTimeProvider.getNow());
        return postRepository.add(newPost);
    }

    @Override
    public Post update(Long postId, Post post) {
        Post oldPost = postRepository.getById(postId);
        return postRepository.save(postFactory.update(oldPost, post));
    }

    @Override
    public List<Post> findByCriteria(Post criteria, OrderType orderType) {
        //TODO need to make pagination
        return postRepository.findBySportAndCityAndRecruitOrderByRecentDateOrFavorite(criteria.getSport(), criteria.getCity(), criteria.isRecruit(), orderType);
    }
}
