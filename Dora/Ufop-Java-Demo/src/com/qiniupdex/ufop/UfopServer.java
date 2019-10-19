package com.qiniupdex.ufop;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

import java.io.IOException;

public class UfopServer {

    private Server mServer;

    public UfopServer() throws IOException {
        this.mServer = new Server(9100);

        ContextHandler handlerContext = new ContextHandler("/handler");
        handlerContext.setContextPath("/handler");
        handlerContext.setAllowNullPathInfo(true);
        handlerContext.setHandler(new UfopRequestHandler());

        ContextHandler healthContext = new ContextHandler("/health");
        healthContext.setContextPath("/health");
        healthContext.setAllowNullPathInfo(true);
        healthContext.setHandler(new UfopHealthHandler());

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[]{
                handlerContext, healthContext,
        });

        this.mServer.setHandler(contexts);
    }

    public void listen() {
        try {
            this.mServer.start();
            this.mServer.join();
        } catch (Exception ex) {
            System.err.printf("start ufop server exception, %s%n", ex.getMessage());
        }
    }

}
