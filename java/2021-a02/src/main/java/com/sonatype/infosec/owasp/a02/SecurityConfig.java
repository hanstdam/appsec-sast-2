package com.sonatype.infosec.owasp.a02;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final ApiKeyFilter apiKeyFilter;
	
	public SecurityConfig() {
		apiKeyFilter = new ApiKeyFilter();
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// Enabled CORS
    	http = http.cors().and();
    	
    	// Set session management to stateless
        http = http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and();
        
        // Set unauthorized requests exception handler
        http = http
            .exceptionHandling()
            .authenticationEntryPoint(
                (request, response, ex) -> {
                    response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        "Authorization failed"
                    );
                }
            )
            .and();
        
        // Set permissions on endpoints
        http.authorizeRequests()
            // Our public endpoints
            .antMatchers("/public/**").permitAll()
            // Our private endpoints
            .anyRequest().authenticated();
    	
        // Add api key token filter
        http.addFilterBefore(
    		apiKeyFilter,
            UsernamePasswordAuthenticationFilter.class
        );
    }
    
    // Used by spring security if CORS is enabled.
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
