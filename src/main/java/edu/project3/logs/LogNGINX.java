package edu.project3.logs;

import java.time.OffsetDateTime;

public record LogNGINX(String remoteAddress,
                       String remoteUser,
                       OffsetDateTime time,
                       RequestEnum request,
                       String resource,
                       int status,
                       int bytesSent,
                       String referer,
                       String userAgent) {
    public enum RequestEnum {
        GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH
    }
}
