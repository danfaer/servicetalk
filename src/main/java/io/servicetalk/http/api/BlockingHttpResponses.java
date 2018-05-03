/*
 * Copyright © 2018 Apple Inc. and the ServiceTalk project authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.servicetalk.http.api;

import io.servicetalk.concurrent.api.Executor;
import io.servicetalk.concurrent.api.Executors;
import io.servicetalk.concurrent.api.Publisher;

import static io.servicetalk.concurrent.api.Publisher.empty;
import static io.servicetalk.concurrent.api.Publisher.from;
import static io.servicetalk.concurrent.api.Publisher.just;
import static io.servicetalk.http.api.DefaultHttpHeadersFactory.INSTANCE;
import static io.servicetalk.http.api.HttpProtocolVersions.HTTP_1_1;

/**
 * Factory methods for creating {@link BlockingHttpResponse}s.
 */
public final class BlockingHttpResponses {
    private BlockingHttpResponses() {
        // no instances
    }

    /**
     * Create a new instance using HTTP 1.1 with empty payload body and headers.
     *
     * @param status the {@link HttpResponseStatus} of the response.
     * @param executor The {@link Executor} used to consume the empty payload. Note this is typically
     * consumed by ServiceTalk so if there are any blocking transformations (e.g. filters) and you are unsure if
     * ServiceTalk is consuming {@code payloadBody} it is wise to avoid {@link Executors#immediate()}.
     * @param <O> Type of the content of the response.
     * @return a new {@link BlockingHttpResponse}.
     */
    public static <O> BlockingHttpResponse<O> newResponse(final HttpResponseStatus status,
                                                          final Executor executor) {
        return newResponse(HTTP_1_1, status, executor);
    }

    /**
     * Create a new instance with empty payload body and headers.
     *
     * @param version the {@link HttpProtocolVersion} of the response.
     * @param status the {@link HttpResponseStatus} of the response.
     * @param executor The {@link Executor} used to consume the empty payload. Note this is typically
     * consumed by ServiceTalk so if there are any blocking transformations (e.g. filters) and you are unsure if
     * ServiceTalk is consuming {@code payloadBody} it is wise to avoid {@link Executors#immediate()}.
     * @param <O> Type of the content of the response.
     * @return a new {@link BlockingHttpResponse}.
     */
    public static <O> BlockingHttpResponse<O> newResponse(final HttpProtocolVersion version,
                                                          final HttpResponseStatus status,
                                                          final Executor executor) {
        return newResponse(version, status, empty(executor));
    }

    /**
     * Create a new instance using HTTP 1.1 with empty headers.
     *
     * @param status the {@link HttpResponseStatus} of the response.
     * @param payloadBody the payload body of the response.
     * @param executor The {@link Executor} used to consume data from {@code payloadBody}. Note this is typically
     * consumed by ServiceTalk so if there are any blocking transformations (e.g. filters) and you are unsure if
     * ServiceTalk is consuming {@code payloadBody} it is wise to avoid {@link Executors#immediate()}.
     * @param <O> Type of the content of the response.
     * @return a new {@link BlockingHttpResponse}.
     */
    public static <O> BlockingHttpResponse<O> newResponse(final HttpResponseStatus status,
                                                          final O payloadBody,
                                                          final Executor executor) {
        return newResponse(HTTP_1_1, status, payloadBody, executor);
    }

    /**
     * Create a new instance with empty headers.
     *
     * @param version the {@link HttpProtocolVersion} of the response.
     * @param status the {@link HttpResponseStatus} of the response.
     * @param payloadBody the payload body of the response.
     * @param executor The {@link Executor} used to consume data from {@code payloadBody}. Note this is typically
     * consumed by ServiceTalk so if there are any blocking transformations (e.g. filters) and you are unsure if
     * ServiceTalk is consuming {@code payloadBody} it is wise to avoid {@link Executors#immediate()}.
     * @param <O> Type of the content of the response.
     * @return a new {@link BlockingHttpResponse}.
     */
    public static <O> BlockingHttpResponse<O> newResponse(final HttpProtocolVersion version,
                                                          final HttpResponseStatus status,
                                                          final O payloadBody,
                                                          final Executor executor) {
        return newResponse(version, status, just(payloadBody, executor));
    }

    /**
     * Create a new instance using HTTP 1.1 with empty headers.
     *
     * @param status the {@link HttpResponseStatus} of the response.
     * @param payloadBody a {@link Iterable} of the payload body of the response.
     * @param executor The {@link Executor} used to consume data from {@code payloadBody}. Note this is typically
     * consumed by ServiceTalk so if there are any blocking transformations (e.g. filters) and you are unsure if
     * ServiceTalk is consuming {@code payloadBody} it is wise to avoid {@link Executors#immediate()}.
     * @param <O> Type of the content of the response.
     * @return a new {@link BlockingHttpResponse}.
     */
    public static <O> BlockingHttpResponse<O> newResponse(final HttpResponseStatus status,
                                                          final Iterable<O> payloadBody,
                                                          final Executor executor) {
        return newResponse(HTTP_1_1, status, payloadBody, executor);
    }

    /**
     * Create a new instance with empty headers.
     *
     * @param status the {@link HttpResponseStatus} of the response.
     * @param version the {@link HttpProtocolVersion} of the response.
     * @param payloadBody a {@link Iterable} of the payload body of the response.
     * @param executor The {@link Executor} used to consume data from {@code payloadBody}. Note this is typically
     * consumed by ServiceTalk so if there are any blocking transformations (e.g. filters) and you are unsure if
     * ServiceTalk is consuming {@code payloadBody} it is wise to avoid {@link Executors#immediate()}.
     * @param <O> Type of the content of the response.
     * @return a new {@link BlockingHttpResponse}.
     */
    public static <O> BlockingHttpResponse<O> newResponse(final HttpProtocolVersion version,
                                                          final HttpResponseStatus status,
                                                          final Iterable<O> payloadBody,
                                                          final Executor executor) {
        return newResponse(version, status, payloadBody, executor, INSTANCE.newHeaders());
    }

    /**
     * Create a new instance.
     *
     * @param version the {@link HttpProtocolVersion} of the response.
     * @param status the {@link HttpResponseStatus} of the response.
     * @param payloadBody a {@link Iterable} of the payload body of the response.
     * @param executor The {@link Executor} used to consume data from {@code payloadBody}. Note this is typically
     * consumed by ServiceTalk so if there are any blocking transformations (e.g. filters) and you are unsure if
     * ServiceTalk is consuming {@code payloadBody} it is wise to avoid {@link Executors#immediate()}.
     * @param headers the {@link HttpHeaders} of the response.
     * @param <O> Type of the content of the response.
     * @return a new {@link BlockingHttpResponse}.
     */
    public static <O> BlockingHttpResponse<O> newResponse(final HttpProtocolVersion version,
                                                          final HttpResponseStatus status,
                                                          final Iterable<O> payloadBody,
                                                          final Executor executor,
                                                          final HttpHeaders headers) {
        return newResponse(version, status, from(executor, payloadBody), headers);
    }

    private static <O> BlockingHttpResponse<O> newResponse(final HttpProtocolVersion version,
                                                           final HttpResponseStatus status,
                                                           final Publisher<O> payloadBody) {
        return newResponse(version, status, payloadBody, INSTANCE.newHeaders());
    }

    private static <O> BlockingHttpResponse<O> newResponse(final HttpProtocolVersion version,
                                                           final HttpResponseStatus status,
                                                           final Publisher<O> payloadBody,
                                                           final HttpHeaders headers) {
        return new DefaultBlockingHttpResponse<>(HttpResponses.newResponse(version, status, payloadBody, headers));
    }
}
