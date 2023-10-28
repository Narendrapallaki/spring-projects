package com.esr.ThymeleafService;


import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafConfig {

	public TemplateEngine getTemplateProperties() {

		TemplateEngine te = new TemplateEngine();

		te.setTemplateResolver(emailProperites());
		// te.process("email-template.html", null);

		return te;

	}

	public ITemplateResolver emailProperites() {
		ClassLoaderTemplateResolver ctr = new ClassLoaderTemplateResolver();

		ctr.setPrefix("templates/");
		ctr.setSuffix(".html");
		ctr.setCharacterEncoding("utf-8");
		ctr.setTemplateMode("HTML");

		return ctr;

	}

}
