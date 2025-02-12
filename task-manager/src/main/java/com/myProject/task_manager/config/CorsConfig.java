// package com.myProject.task_manager.config;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class CorsConfig {

//     @Bean
//     public WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(CorsRegistry registry) {
//                 registry.addMapping("/**")
//                         .allowedOrigins("http://localhost:3000") // React'in adresini buraya ekle
//                         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                         .allowedHeaders("*")
//                         .exposedHeaders("Set-Cookie") // Çerezlerin gözükmesini sağlar
//                         .allowCredentials(true) // ÇEREZLERİN GÖNDERİLMESİNİ SAĞLAR
//                         .maxAge(3600);
//             }
//         };
//     }
// }