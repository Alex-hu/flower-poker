package com.bolegame.flowerpoker.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Hu Zhongyuan
 * @version 2013-11-03
 */
public class ServerHandler extends IoHandlerAdapter {

    private final Logger log = LoggerFactory.getLogger(ServerHandler.class);
    public static Set sessions = Collections.synchronizedSet(new HashSet());


    public ServerHandler() {
    }

    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        session.close(true);
        log.debug("session occured exception, so close it." + cause.getMessage());
    }

    public void sessionCreated(IoSession session) {
        log.debug("remote client [" + session.getRemoteAddress() + "] connected.");
        session.write("Welcome");
        sessions.add(session);
    }

    public void messageReceived(IoSession session, Object message)
            throws Exception {
        String str = message.toString();
        log.debug("Message written..." + str);
        session.setAttribute("type", message);
        String remoteAddress = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
        log.debug("address:" + remoteAddress);
        session.setAttribute("ip", remoteAddress);
    }

    public void sessionClosed(IoSession session) throws Exception {

        log.debug("sessionClosed.");
        System.out.println("sessionClosed.");
        sessions.remove(session);
    }

    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        log.debug("session idle, so disconnecting......");
        session.close();
        log.debug("disconnected.");
    }

    public void messageSent(IoSession session, Object message)
            throws Exception {
        log.debug("messageSent.");
        System.out.println("messageSent.");
    }

    public void sessionOpened(IoSession session)
            throws Exception {
        log.debug("sessionOpened.");
        System.out.println("sessionOpened.");
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 300);
    }


}
