package efub.SweetMeback.domain.post.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import efub.SweetMeback.domain.post.dto.PostFilteringRequest;
import efub.SweetMeback.domain.post.entity.Category;
import efub.SweetMeback.domain.post.entity.Post;
import efub.SweetMeback.domain.post.entity.Sort;
import efub.SweetMeback.domain.post.repository.PostRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static efub.SweetMeback.domain.post.entity.QPost.post;

@Service
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> filteringAll(PostFilteringRequest filteringRequest) {
        List<Post> postList = new ArrayList<>();
        Sort sort = filteringRequest.getSort();

        OrderSpecifier<LocalDateTime> createdDateSpecifier = post.createdDate.desc();
        OrderSpecifier<Integer> viewSpecifier = post.view.desc();
        OrderSpecifier<Long> heartCountSpecifier = post.heartCount.desc();

        if (sort != null) {
            if (sort.equals(Sort.DATE)) {
                postList = filteringQuery(filteringRequest).orderBy(createdDateSpecifier).fetch();
            } else if (sort.equals(Sort.VIEW)) {
                postList = filteringQuery(filteringRequest).orderBy(viewSpecifier).fetch();
            } else if (sort.equals(Sort.HEART)) {
                postList = filteringQuery(filteringRequest).orderBy(heartCountSpecifier).fetch();
            }
        } else {
            postList = filteringQuery(filteringRequest).orderBy(createdDateSpecifier).fetch();
        }

        return postList;
    }

    private JPAQuery<Post> filteringQuery(PostFilteringRequest filteringRequest) {
        return jpaQueryFactory
                .selectFrom(post)
                .where(
                        recruitmentCondition(filteringRequest.isRecruitment()),
                        categoryCondition(filteringRequest.getCategory())
                )
                .groupBy(post);
    }

    private BooleanExpression recruitmentCondition(Boolean recruitment) {
        if (recruitment != null) {
            return post.recruitment.eq(recruitment);
        }
        return null;
    }

    private BooleanExpression categoryCondition(Category category) {
        if (category != null) {
            return post.category.eq(category);
        }
        return null;
    }



}
