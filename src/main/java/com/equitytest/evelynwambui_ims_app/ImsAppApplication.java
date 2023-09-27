package com.equitytest.evelynwambui_ims_app;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info =
		@Info(
				title = "EvelynIMS",
				description = "Inventory Management System",
				version = "1.0.0",
				termsOfService = "Terms of service will be updated soon.",
				contact = @Contact(name = "EVELYN WAMBUI", email = "mailto:wambuievelyn50@gmail.com"),
				license =
				@License(
						name = "Apache Licence 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0")),
		tags = @Tag(
				name = "EvelynIMS",
				description = "Application to manage inventory"),
		externalDocs =
		@ExternalDocumentation(
				url = "https://github.com/EvelynKin/evelynwambui-ims-app",
				description = "Opensource Documentation"))

@SpringBootApplication
public class ImsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImsAppApplication.class, args);
	}

}
