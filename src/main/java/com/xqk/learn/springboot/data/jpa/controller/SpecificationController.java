package com.xqk.learn.springboot.data.jpa.controller;

import com.xqk.learn.springboot.common.ResponseMessage;
import com.xqk.learn.springboot.data.jpa.entity.User;
import com.xqk.learn.springboot.data.jpa.entity.UserDetail;
import com.xqk.learn.springboot.data.jpa.repository.UserJpaRepository;
import com.xqk.learn.springboot.data.jpa.vo.FindUser;
import com.xqk.learn.springboot.data.jpa.vo.UserRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 测试Specification接口
 *
 * @author 熊乾坤
 * @since 2019-11-23 19:33
 */
@RestController
@RequestMapping("/spec")
@Transactional(rollbackFor = Exception.class)
public class SpecificationController {

    private final UserJpaRepository userJpaRepository;
    private final EntityManager entityManager;

    public SpecificationController(UserJpaRepository userJpaRepository, EntityManager entityManager) {
        this.userJpaRepository = userJpaRepository;
        this.entityManager = entityManager;
    }

    @GetMapping("/findUser")
    public ResponseMessage findUser(UserRequest userRequest) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query1 = builder.createQuery(Long.class);
        CriteriaQuery<FindUser> query = builder.createQuery(FindUser.class);
        Root<User> userRoot = query1.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        Optional<UserRequest> optional = Optional.of(userRequest);

        optional.map(UserRequest::getName).ifPresent(name -> predicates.add(builder.like(userRoot.get("name"), "%" + name + "%")));
        Join<User, UserDetail> userDetailJoin = userRoot.join("userDetail", JoinType.INNER);

        Expression<Number> sumExpression = (Expression<Number>) builder.sum(userRoot.get("score")).alias("sumScore");
        Selection<String> groupConcatExpression = builder.function("group_concat", String.class, userRoot.get("address"));

        //分组+条件过滤
        //query.groupBy(userRoot.get("score"));
        //optional.map(UserRequest::getScoreSum).ifPresent(sumScore-> query.having(builder.ge((Expression<? extends Number>) sumExpression,userRequest.getScoreSum())));
        //query
        //        .multiselect(userRoot.get("name"), sumExpression , userDetailJoin.get("enrollmentDate"),groupConcatExpression)
        //        //.multiselect(userRoot.get("name"), sumExpression , userDetailJoin.get("enrollmentDate"),builder.literal("hello"))
        //        .where(predicates.toArray(new Predicate[0]))
        //        .orderBy(new OrderImpl(sumExpression,false));
        //TypedQuery typedQuery = entityManager.createQuery(query);

        CriteriaQuery<Long> query2 = builder.createQuery(Long.class);
        Subquery<Long> query3 = query2.subquery(Long.class);
        optional.map(UserRequest::getScoreSum).ifPresent(sumScore -> query3.having(builder.ge((Expression<? extends Number>) sumExpression, userRequest.getScoreSum())));
        query3
                .select(builder.count(query3.from(User.class)))
                //.multiselect(userRoot.get("name"), sumExpression , userDetailJoin.get("enrollmentDate"),builder.literal("hello"))
                .where(predicates.toArray(new Predicate[0]))
                .groupBy(userRoot.get("score"));
        query2.select(builder.count(query2.from(query3.getJavaType())));
        TypedQuery typedQuery = entityManager.createQuery(query2);
        return ResponseMessage.ok(typedQuery.getResultList());
    }
}
