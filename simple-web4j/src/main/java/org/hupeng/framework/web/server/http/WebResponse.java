package org.hupeng.framework.web.server.http;

public interface WebResponse {

    WebResponse setHeader(final String name, final String value);

    void sendError(final int status);

    void sendByte(final byte[] content);

    void sendString(final String content);
}
