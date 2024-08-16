package com.springsecuirty.SpringSecurity.SecurityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {
    //Adding Users and Roles
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails user1= User.builder()
//                .username("akash")
//                .password("{noop}test@123")
//                .roles("STUDENT")
//                .build();
//        UserDetails user2= User.builder()
//                .username("sonali")
//                .password("{noop}test@123")
//                .roles("STUDENT","TEACHER")
//                .build();
//        UserDetails user3= User.builder()
//                .username("suvendu")
//                .password("{noop}test@123")
//                .roles("STUDENT","TEACHER","ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user1,user2,user3);
//    }

    //*********************************Role Structure and Endpoints Accessibility********************************
    /** STUDENT-----> /students     <------GET
                    /students/{studentID}   <----GET
        TEACHER -----> /students   <---- POST
                        /students  <-----PUT
        ADMIN -------> /students/{studentID}  <----DELETE
     **/

    //***************************************** spring-security-demo-database-plain text ***********************************************************
    //Adding Support for JDBC connections-----> will fetch users from database.
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }

    //***************************************** spring-security-demo-database-bcrypt ***************************************************

    //****************************** spring-security-demo-database-bcrypt-custom-table-names ***********************************
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager=new JdbcUserDetailsManager(dataSource);

        //// define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery("select * from members where user_id=?");
        //// define query to retrieve the roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select * from roles where user_id=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET,"/students").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.GET,"/students/**").hasRole("STUDENT")
                        .requestMatchers(HttpMethod.POST,"/students").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.PUT, "/students").hasRole("TEACHER")
                        .requestMatchers(HttpMethod.DELETE,"/students/**").hasRole("ADMIN")
                );
        //Use http basic authentication
        http.httpBasic(Customizer.withDefaults());
        //Disable CSRF (Cross Site Request Forgery)---> mainly used with web Forms not used with Crud APIs.
        http.csrf(csrf->csrf.disable());
        return http.build();
    }


}
