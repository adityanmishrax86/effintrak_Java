package com.azaxxc.effintrakj.effinTrak.globalcomponents.mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.function.Function;


public class PageResponseMapper {

    public static <T,R> Page<R>  mapPageable(Page<T> pageObjectOf, Pageable pageable,
                                             Function<T,R> mapper) {
        List<R> response = pageObjectOf.getContent().stream()
                .map(mapper)
                .toList();

        return PageableExecutionUtils.getPage(
                response,
                pageable,
                pageObjectOf::getTotalElements
        );
    }
}
