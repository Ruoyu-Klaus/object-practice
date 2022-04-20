package com.objectexercise.objectexercise.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class Config extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String getUserResumesPath = "/api/v1/resumes/**";
        String getUserApplicationsPath = "/api/v1/applications";
        String saveJobPath = "/api/v1/jobs/bookmarks/{jobId}";
        String getSavedJobsPath = "/api/v1/jobs/bookmarks";
        String confirmJobApplicationStatusPath = "/api/v1/jobs/{jobId}/applications/{applicationId}";
        String applyJobApplicationPath = "/api/v1/jobs/{jobId}/applications}";
        String createJobPath = "/api/v1/jobs";

        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .mvcMatchers("/api/v1/users/**").hasAnyAuthority("ADMIN")
                .mvcMatchers(HttpMethod.POST, createJobPath).hasAnyAuthority("RECRUITER")
                .mvcMatchers(HttpMethod.POST, applyJobApplicationPath).hasAnyAuthority( "APPLICANT")
                .mvcMatchers(HttpMethod.PATCH, confirmJobApplicationStatusPath).hasAnyAuthority( "RECRUITER")
                .mvcMatchers(HttpMethod.GET, getSavedJobsPath).hasAnyAuthority("APPLICANT")
                .mvcMatchers(HttpMethod.POST, saveJobPath).hasAnyAuthority("APPLICANT")
                .mvcMatchers(HttpMethod.GET, getUserApplicationsPath).hasAnyAuthority( "APPLICANT")
                .antMatchers(HttpMethod.POST, getUserResumesPath).hasAnyAuthority("APPLICANT")
                .and()
                .addFilter(new CustomAuthenticationFilter(authenticationManagerBean()))
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
