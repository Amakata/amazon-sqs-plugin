/*
 * Copyright 2016 M-Way Solutions GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.relution.jenkins.amazonsqs;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

import io.relution.jenkins.amazonsqs.factories.SQSFactoryImpl;
import io.relution.jenkins.amazonsqs.factories.ThreadFactoryImpl;
import io.relution.jenkins.amazonsqs.interfaces.ExecutorProvider;
import io.relution.jenkins.amazonsqs.interfaces.SQSQueueProvider;
import io.relution.jenkins.amazonsqs.model.SQSQueueProviderImpl;
import io.relution.jenkins.amazonsqs.net.RequestFactory;
import io.relution.jenkins.amazonsqs.net.RequestFactoryImpl;


public class Context extends com.google.inject.AbstractModule {

    private static Injector injector;

    public synchronized static Injector injector() {
        if (injector == null) {
            injector = Guice.createInjector(new Context());
        }
        return injector;
    }

    @Override
    protected void configure() {
        this.bind(ThreadFactory.class)
                .to(ThreadFactoryImpl.class)
                .in(com.google.inject.Singleton.class);

        this.bind(io.relution.jenkins.amazonsqs.interfaces.ExecutorFactory.class)
                .to(io.relution.jenkins.amazonsqs.factories.ExecutorFactoryImpl.class)
                .in(com.google.inject.Singleton.class);

        this.bind(ExecutorProvider.class)
                .to(io.relution.jenkins.amazonsqs.threading.ExecutorProviderImpl.class)
                .in(com.google.inject.Singleton.class);

        this.bind(ExecutorService.class)
                .toProvider(ExecutorProvider.class)
                .in(com.google.inject.Singleton.class);

        this.bind(io.relution.jenkins.amazonsqs.interfaces.SQSFactory.class)
                .to(SQSFactoryImpl.class)
                .in(com.google.inject.Singleton.class);

        this.bind(RequestFactory.class)
                .to(RequestFactoryImpl.class)
                .in(com.google.inject.Singleton.class);

        this.bind(SQSQueueProvider.class)
                .to(SQSQueueProviderImpl.class)
                .in(com.google.inject.Singleton.class);

        this.bind(io.relution.jenkins.amazonsqs.interfaces.SQSQueueMonitorScheduler.class)
                .to(io.relution.jenkins.amazonsqs.threading.SQSQueueMonitorSchedulerImpl.class)
                .in(com.google.inject.Singleton.class);

    }
}
