/**
 * (c) Copyright IBM Corporation 2018.
 * This is licensed under the following license.
 * The Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
 * U.S. Government Users Restricted Rights:  Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

package com.ibm.devops.connect.CRPipeline;

import org.apache.http.impl.client.DefaultHttpClient;

import hudson.AbortException;
import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.FilePath.FileCallable;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Descriptor.FormException;
import hudson.remoting.VirtualChannel;
import hudson.model.Hudson;
import hudson.model.Result;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import hudson.util.Secret;

import jenkins.tasks.SimpleBuildStep;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import net.sf.json.JSONObject;

import org.jenkinsci.remoting.RoleChecker;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import java.util.Map;

import com.ibm.devops.connect.Status.CrAction;

public class ContinuousReleaseProperties extends Builder implements SimpleBuildStep {

    private Map<String, String> properties;

    @DataBoundConstructor
    public ContinuousReleaseProperties(
            Map<String, String> properties) {
        this.properties = properties;
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    @Override
    public void perform(final Run<?, ?> build, FilePath workspace, Launcher launcher, final TaskListener listener)
            throws AbortException, InterruptedException, IOException {
        CrAction action = build.getAction(CrAction.class);

        if(action == null) {
            action = new CrAction();
            build.addAction(action);
        }

        if (properties != null) {
            action.updateCrProperties(properties);
        }
    }

    @Extension
    public static class ContinuousReleasePropertiesDescriptor extends BuildStepDescriptor<Builder> {

        public ContinuousReleasePropertiesDescriptor() {
            load();
        }

        @Override
        public String getHelpFile() {
            return "/plugin/ibm-ucdeploy-build-steps/publish.html";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            req.bindJSON(this, formData);
            save();
            return super.configure(req, formData);
        }

        /**
         * {@inheritDoc}
         *
         * @return {@inheritDoc}
         */
        @Override
        public String getDisplayName() {
            return "Pass Properties to Continuous Release Version";
        }

        @Override
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }
    }
}