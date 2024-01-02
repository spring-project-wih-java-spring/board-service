package org.example.boardservice.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.example.boardservice.domain.Article;
import org.example.boardservice.domain.ArticleComment;
import org.example.boardservice.domain.QArticle;
import org.example.boardservice.domain.QArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticleComment>
{

    List<ArticleComment> findByArticle_Id(Long articleId);
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        // 필드를 선택적으로 검색하게끔 하는것
        bindings.excludeUnlistedProperties(true);

        // 해당 include 있는 필드만 검색가능
        bindings.including( root.content, root.createdAt, root.createdBy);

        // like '%${value}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); // 날짜 검색 (시,분,초)
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}