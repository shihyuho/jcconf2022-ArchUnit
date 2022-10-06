package com.github.shihyuho.jcconf2022.archunit.service;

import com.github.shihyuho.jcconf2022.archunit.repository.MyDao;
import org.springframework.stereotype.Service;

@Service
public record MyService(MyDao repository) {
}
