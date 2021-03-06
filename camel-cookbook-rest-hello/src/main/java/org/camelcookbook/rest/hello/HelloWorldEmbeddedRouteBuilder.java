/*
 * Copyright (C) Scott Cranton, Jakub Korab, and Christian Posta
 * https://github.com/CamelCookbook
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.camelcookbook.rest.hello;

import org.apache.camel.builder.RouteBuilder;

/**
 * Simple REST DSL example with embedded routes
 */
public class HelloWorldEmbeddedRouteBuilder extends RouteBuilder {
    private int port1;

    public HelloWorldEmbeddedRouteBuilder() {
    }

    public HelloWorldEmbeddedRouteBuilder(int port) {
        this.port1 = port;
    }

    public void setPort1(int port1) {
        this.port1 = port1;
    }

    @Override
    public void configure() throws Exception {
        restConfiguration()
            .component("undertow").host("localhost").port(port1);

        rest("/say")
            .get("/hello")
                .route()
                    .transform().constant("Hello World")
                .endRest()
            .post("/bye")
                .consumes("application/json").to("mock:update");
    }
}
