package com.example.music_app.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String LOCATION = "D:/temp/"; // Thư mục tạm lưu file upload
    private static final long MAX_FILE_SIZE = 52428800; // 50MB
    private static final long MAX_REQUEST_SIZE = 209715200; // 200MB
    private static final int FILE_SIZE_THRESHOLD = 0;

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfiguration.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppConfiguration.class}; // THÊM DÒNG NÀY để Spring load cấu hình Thymeleaf, Multipart...
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
                LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD
        );
        registration.setMultipartConfig(multipartConfigElement);
    }
}
