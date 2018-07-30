package io.relution.jenkins.amazonsqs.util;

import hudson.model.AbstractProject;
import hudson.model.Job;
import jenkins.model.ParameterizedJobMixIn;

/**
 * Job utility class.
 */
public class JobInfoHelpers {

    private JobInfoHelpers() {
        throw new IllegalAccessError("Do not instantiate it");
    }

    /**
     * This method converts any child class of {@link Job} (ie, {@link AbstractProject}
     * to {@link ParameterizedJobMixIn} to use it in workflow
     *
     * @param job to wrap
     * @param <T> any child type of Job
     *
     * @return ParameterizedJobMixIn
     */
    public static <T extends Job> ParameterizedJobMixIn asParameterizedJobMixIn(final T job) {
        return new ParameterizedJobMixIn() {
            @Override
            protected Job asJob() {
                return job;
            }
        };
    }
}
