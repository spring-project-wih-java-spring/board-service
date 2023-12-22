package org.example.boardservice.repository;

import org.example.boardservice.config.JpaConfig;
import org.example.boardservice.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("SELECT 테스트")
    @Test
    void select_test() {
        // given
        // when
        List<Article> articles = articleRepository.findAll();
        // then
        assertThat(articles)
                .isNotNull()
                .hasSize(100);
    }

    @DisplayName("INSERT 테스트")
    @Test
    void insert_test() {
        // given
        long previousCount = articleRepository.count();
        Article article = Article.of("new article", "new content", "#spring");

        // when
        articleRepository.save(article);

        // then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }

    @DisplayName("UPDATE 테스트")
    @Test
    void update_test() {
        // given
        Article article = articleRepository.findById(1L).get();
        article.setHashtag("#springboot");

        // when
        Article saveArticle = articleRepository.save(article);

        // then
        assertThat(saveArticle).hasFieldOrPropertyWithValue("hashtag", "#springboot");
    }


}