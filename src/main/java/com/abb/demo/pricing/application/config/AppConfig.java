package com.abb.demo.pricing.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.abb.demo.pricing.common.CommonConfig;
import com.abb.demo.pricing.domain.DomainConfig;
import com.abb.demo.pricing.repository.RepositoryConfig;
import com.abb.demo.pricing.web.WebConfig;

@Configuration
@Import({ WebConfig.class, DomainConfig.class, RepositoryConfig.class, CommonConfig.class })
public class AppConfig {

}
