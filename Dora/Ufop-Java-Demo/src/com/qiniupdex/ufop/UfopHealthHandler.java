package com.qiniupdex.ufop;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UfopHealthHandler extends AbstractHandler {
    
    @Override
    public void handle(String s, Request request,
                       HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) throws IOException, ServletException {
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        PrintWriter respWriter = httpServletResponse.getWriter();
        respWriter.write("OK");
        respWriter.flush();
        respWriter.close();
    }
    
}
