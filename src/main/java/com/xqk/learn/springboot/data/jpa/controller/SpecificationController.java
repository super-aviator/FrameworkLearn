package com.xqk.learn.springboot.data.jpa.controller;

import com.xqk.learn.springboot.common.ResponseMessage;
import com.xqk.learn.springboot.data.jpa.repository.UserJpaRepository;
import com.xqk.learn.springboot.data.jpa.vo.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

/**
 * 测试Specification接口
 *
 * @author 熊乾坤
 * @since 2019-11-23 19:33
 */
@Slf4j
@RestController
@Profile("default")
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
        // CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        // CriteriaQuery<Long> query1 = builder.createQuery(Long.class);
        // //CriteriaQuery<FindUser> query = builder.createQuery(FindUser.class);
        // Root<User> userRoot = query1.from(User.class);
        // //
        // List<Predicate> predicates = new ArrayList<>();
        // Optional<UserRequest> optional = Optional.of(userRequest);
        //
        // optional.map(UserRequest::getName).ifPresent(name -> predicates.add(builder.like(userRoot.get("name"), "%" + name + "%")));
        // Join<User, UserDetail> userDetailJoin = userRoot.join("userDetail", JoinType.INNER);
        //
        //Expression<Number> sumExpression = (Expression<Number>) builder.sum(userRoot.get("score")).alias("sumScore");
        //Selection<String> groupConcatExpression = builder.function("group_concat", String.class, userRoot.get("address"));

        //分组+条件过滤
        //query.groupBy(userRoot.get("score"));
        //optional.map(UserRequest::getScoreSum).ifPresent(sumScore-> query.having(builder.ge((Expression<? extends Number>) sumExpression,userRequest.getScoreSum())));
        //query
        //        .multiselect(userRoot.get("name"), sumExpression , userDetailJoin.get("enrollmentDate"),groupConcatExpression)
        //        //.multiselect(userRoot.get("name"), sumExpression , userDetailJoin.get("enrollmentDate"),builder.literal("hello"))
        //        .where(predicates.toArray(new Predicate[0]))
        //        .orderBy(new OrderImpl(sumExpression,false));
        //TypedQuery typedQuery = entityManager.createQuery(query);


        //optional.map(UserRequest::getScoreSum).ifPresent(sumScore -> query2.having(builder.ge(sumExpression, userRequest.getScoreSum())));
        // log.error(predicates.toString());
        // Predicate likePrecate = builder.like(userRoot.get("name"), "%" + "xqk" + "%");
        // predicates.add(likePrecate);
        // predicates.remove(likePrecate);
        // log.error(predicates.toString());
        //
        // query1
        //         .select(builder.count(userRoot))
        //         .where(predicates.toArray(new Predicate[0]))
        //         .groupBy(userRoot.get("score"));
        // TypedQuery typedQuery = entityManager.createQuery(query1);
        // return ResponseMessage.ok(typedQuery.getResultList().size());

/*        Long sum=userJpaRepository.count((root,query,builder)->{
            List<Predicate> predicates = new ArrayList<>();
            Optional<UserRequest> optional = Optional.of(userRequest);
            optional.map(UserRequest::getName).ifPresent(name -> predicates.add(builder.like(root.get("name"), "%" + name + "%")));
            Expression<Number> sumExpression = (Expression<Number>) builder.sum(root.get("score")).alias("sumScore");
            query.groupBy(root.get("score"));
            optional.map(UserRequest::getScoreSum).ifPresent(sumScore-> query.having(builder.ge( sumExpression,userRequest.getScoreSum())));
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        });
        return ResponseMessage.ok();
        */
        return null;
    }
}
