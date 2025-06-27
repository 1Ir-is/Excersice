package com.example.book_rental.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Aspect
@Component
public class LoggerAspect {

    private int visitorCount = 0;
    private static final String LOG_FILE = "src/main/resources/library_logs.txt";

    // Pointcut cho các hành động thay đổi trạng thái sách
    @Pointcut("execution(* com.example.book_rental.services.BookService.borrowBook(..))")
    public void borrowAction() {
    }

    @Pointcut("execution(* com.example.book_rental.services.BookService.returnBook(..))")
    public void returnAction() {
    }

    @Pointcut("execution(* com.example.book_rental.services.BookService.saveBook(..)) || " +
            "execution(* com.example.book_rental.services.BookService.updateBook(..)) || " +
            "execution(* com.example.book_rental.services.BookService.deleteById(..))")
    public void otherStateChangeActions() {
    }

    @Pointcut("execution(* com.example.book_rental.controllers.*.*(..))")
    public void visitorActions() {
    }
    
    @After("borrowAction()")
    public void logBorrowAction() {
        String logMessage = LocalDateTime.now() + " - Sách đã được thuê vào lúc: " + LocalDateTime.now();
        appendLogToFile(logMessage);
    }

    @After("returnAction()")
    public void logReturnAction() {
        String logMessage = LocalDateTime.now() + " - Sách đã được trả vào lúc: " + LocalDateTime.now();
        appendLogToFile(logMessage);
    }

    @After("otherStateChangeActions()")
    public void logStateChangeActions() {
        String logMessage = LocalDateTime.now() + " - Trạng thái sách trong thư viện đã thay đổi vào lúc: " + LocalDateTime.now();
        appendLogToFile(logMessage);
    }

    @Before("visitorActions()")
    public void logVisitorCount() {
        visitorCount++;
        String logMessage = LocalDateTime.now() + " - Đã có người truy cập vào hệ thống thư viện. Tổng lượt truy cập: " + visitorCount;
        appendLogToFile(logMessage);
    }

    private void appendLogToFile(String logMessage) {
        try {
            File logFile = new File(LOG_FILE);
            if (!logFile.exists()) {
                logFile.getParentFile().mkdirs();
                logFile.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                writer.write(logMessage);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Không thể ghi log vào file: " + e.getMessage());
        }
    }
}