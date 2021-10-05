package com.kr.parking_project.user;


import com.kr.parking_project.common.CustomQuerydslRepositorySupport;
import com.querydsl.core.types.dsl.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static com.kr.parking_project.user.QUser.user;


public class UserRepositoryImpl extends CustomQuerydslRepositorySupport implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(JPAQueryFactory queryFactory) {
        super(User.class);
        this.queryFactory = queryFactory;
    }


    @Override
    public Optional<User> findUserByPhoneNumber(String phoneNumber) {
        final User fetchUser = queryFactory
                .selectFrom(user)
                .where(eq(user.phoneNumber, phoneNumber))
                .fetchOne();

        return Optional.ofNullable(fetchUser);
    }

    @Override
    public boolean existsUserByPhoneNumber(String phoneNumber) {
        final Integer fetchFirstUser = queryFactory
                .selectOne()
                .from(user)
                .where(eq(user.phoneNumber, phoneNumber))
                .fetchFirst();

        return Optional.ofNullable(fetchFirstUser).orElse(0) > 0;
    }







    /**
     * WHERE 절 조건
     */
    // Long
    public static BooleanExpression eq(NumberPath<Long> domainValue, Long value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    public static BooleanExpression in(NumberPath<Long> domainValue, List<Long> value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.in(value);
    }

    // String
    public static BooleanExpression eq(StringPath domainValue, String value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    public static BooleanExpression like(StringPath domainValue, String value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.likeIgnoreCase("%" + value + "%");
    }

    // Boolean
    private static BooleanExpression eq(BooleanPath domainValue, Boolean value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    // Enum
    private static <T extends Enum<T>> BooleanExpression eq(EnumPath<T> domainValue, T value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }


}