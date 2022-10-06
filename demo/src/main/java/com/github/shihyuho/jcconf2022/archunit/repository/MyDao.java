package com.github.shihyuho.jcconf2022.archunit.repository;

import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;


@Component
public record MyDao() implements Repository<MyEntity, Long> {

}

record MyEntity() {

}
