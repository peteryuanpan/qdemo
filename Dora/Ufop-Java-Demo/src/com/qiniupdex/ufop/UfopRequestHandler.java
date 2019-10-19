package com.qiniupdex.ufop;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.IOException;

public class UfopRequestHandler extends AbstractHandler {
    
    public UfopRequestHandler() {
    }
    
    public void handle(String s, Request request,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) throws IOException, ServletException {
        
        System.out.println(request.getRequestURL());
        
        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(httpServletResponse.getOutputStream());
            outputStream.write("HelloWorld!! ufop-java-demo!".getBytes());
            outputStream.flush();
        } catch (IOException ex) {
            System.err.printf("exception=%s\n", ex.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception ex) {
            }
        }
    }
    
}
