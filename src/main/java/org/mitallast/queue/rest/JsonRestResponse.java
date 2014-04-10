package org.mitallast.queue.rest;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.mitallast.queue.transport.TransportResponse;

public class JsonRestResponse extends TransportResponse {
    public JsonRestResponse(HttpResponseStatus status) {
        setResponseStatus(status);
    }
}