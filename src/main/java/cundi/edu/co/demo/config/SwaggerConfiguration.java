package cundi.edu.co.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.client.*;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.service.*;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

	public static final Contact DEFAULT_CONTACT = new Contact("Luz Velasquez", "https://www.ucundinamarca.edu.co",
			"lnvelasquez@ucundinamarca.edu.co");

	public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Documentacion Linea III", "Documentacion servicios",
			"1.0", "PREMIUM", DEFAULT_CONTACT, "Aeche 2.0", "www.licencia.com", new ArrayList<>());

	@Bean
	public Docket documentation() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO).useDefaultResponseMessages(false);
	}
	
	@Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }
}
