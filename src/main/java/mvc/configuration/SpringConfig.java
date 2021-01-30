package mvc.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.time.format.DateTimeFormatter;

/**
 * WebMvcConfigurationSupport
 * This is the main web configuration class with Spring in the web application.
 */
@Configuration
public class SpringConfig extends WebMvcConfigurationSupport {

    /**
     * Provides mapping mechanism User to UserDTO and inverse.
     */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    /**
     * Service interface for encoding passwords.
     * This current interface is temporary only for test.
     * Soon will be BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Conversion service.
     *
     * Resolve: Failed to convert value of type 'java.lang.String' to required type 'java.time.LocalDate';
     *          nested exception is org.springframework.core.convert.ConversionFailedException.
     */
    @Bean
    @Override
    public FormattingConversionService mvcConversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

        DateTimeFormatterRegistrar dateTimeFormatterRegistrar = new DateTimeFormatterRegistrar();
        dateTimeFormatterRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dateTimeFormatterRegistrar.registerFormatters(conversionService);

        DateFormatterRegistrar dateFormatterRegistrar = new DateFormatterRegistrar();
        dateFormatterRegistrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
        dateFormatterRegistrar.registerFormatters(conversionService);

        return conversionService;
    }

    /**
     * Provides the path to resources.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    /**
     * Sets path to resources.
     */
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/resources/",
            "classpath:/static/",
    };
}
