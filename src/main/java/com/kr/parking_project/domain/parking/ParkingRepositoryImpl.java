package com.kr.parking_project.domain.parking;


import com.kr.parking_project.common.CustomQuerydslRepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.ObjectUtils;


import java.util.Optional;

import static com.kr.parking_project.domain.parking.QParking.parking;

public class ParkingRepositoryImpl extends CustomQuerydslRepositorySupport implements ParkingRepositoryCustom{


    private final JPAQueryFactory queryFactory;

    public ParkingRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Parking.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public boolean existsParkingByNumber(String number) {
        final Integer existsParkingByNumber = queryFactory
                .selectOne()
                .from(parking)
                .where(eq(parking.number,number))
                .fetchFirst();

        return Optional.ofNullable(existsParkingByNumber).orElse(0) > 0;
    }

    /**
     * WHERE 절 조건
     */
    // String
    public static BooleanExpression eq(StringPath domainValue, String value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }
}
