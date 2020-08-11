package com.lams1989.rest.exercise.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
@Configuration
public class Config {
		@Bean
		public LocaleResolver localeResolver() {
			AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
			
			localeResolver.setDefaultLocale(Locale.US);
			
			return localeResolver;
			
		}
}
