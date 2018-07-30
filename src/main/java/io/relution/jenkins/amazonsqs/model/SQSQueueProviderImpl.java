
package io.relution.jenkins.amazonsqs.model;

import java.util.List;

import io.relution.jenkins.amazonsqs.interfaces.SQSQueueProvider;


public class SQSQueueProviderImpl implements SQSQueueProvider {

    @Override
    public List<? extends io.relution.jenkins.amazonsqs.interfaces.SQSQueue> getSqsQueues() {
        final io.relution.jenkins.amazonsqs.SQSTrigger.DescriptorImpl descriptor = io.relution.jenkins.amazonsqs.SQSTrigger.DescriptorImpl.get();
        return descriptor.getSqsQueues();
    }

    @Override
    public io.relution.jenkins.amazonsqs.interfaces.SQSQueue getSqsQueue(final String uuid) {
        final io.relution.jenkins.amazonsqs.SQSTrigger.DescriptorImpl descriptor = io.relution.jenkins.amazonsqs.SQSTrigger.DescriptorImpl.get();
        return descriptor.getSqsQueue(uuid);
    }
}
