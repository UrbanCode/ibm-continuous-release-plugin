# IBM Continuous Release

**Please go to https://wiki.jenkins-ci.org/display/JENKINS/IBM+Cloud+DevOps+Plugin for the latest instructions of this plugin**

---

With this Jenkins plugin, you can run Jenkins jobs as a part of a deployment plan in IBM Continuous Release (CR) and Composite Pipeline.  You can run jobs that will trigger the creation of a version in the Composite Pipeline as well as persist properties on that version that can be used as input properties in other Jenkins builds.  This plugin will pass along important data from Git to the Composite Pipeline.

# Detailed Functionality

* **Posting job metadata to your Continuous Release instance -** As you create and edit jobs, the metadata for the jobs will uploaded including the names of the jobs as well as the names of parameters.  This is done so that the jobs can be invoked from the Continuous Release service within the CR security model.

* **Invoke jobs from Continuous Release -** An authenticated, encrypted persistent connection is established with the CR service so that you can trigger off jobs and pipelines with no special firewall configuration.

* **Updates status of running jobs -** You will receive instant feedback in the CR service with links to the execution.

* **Job Executions can create versions in Composite Pipeline -** You will have the option to select Jenkins jobs as "input jobs" which will create a version with special properties that you can specify.

* **Detects quality data provided by IBM Deployment Risk Analytics -** If you use the capabilities found in the IBM Cloud DevOps plugin to provide data to IBM DRA, then this plugin will forward that data to your composite pipeline to visualize quality data across your whole suite of applications.

# Installation

## 1. Generate a Sync Id and Sync Token

Navigate to the [Getting Started page] of the Continuous Release service and at the bottom of the page is a (https://console.bluemix.net/devops/continuous-release/getting-started).

1. To create a toolchain, go to the [Create a Toolchain page](https://console.ng.bluemix.net/devops/create) and follow the instructions on that page.

2. After you create the toolchain, add DevOps Insights to it. For instructions, see the [DevOps Insights documentation](https://console.ng.bluemix.net/docs/services/DevOpsInsights/index.html).

## 2. (optional) Configure Jenkins jobs for Deployment Risk dashboard

If you would like to make use Deployment Risk dashboard, follow these steps.

After the plugin is installed, you can integrate DevOps Insights into your Jenkins project.


### General workflow

1. Open the configuration of any jobs that you have, such as build, test, or deployment.

2. Add a post-build action for the corresponding type:

   * For build jobs, use **Publish build information to IBM Cloud DevOps**.

   * For test jobs, use **Publish test result to IBM Cloud DevOps**.

   * For deployment jobs, use **Publish deployment information to IBM Cloud DevOps**.

3. Complete the required fields:

   * From the **Credentials**, select your Bluemix ID and password. If they are not saved in Jenkins, click **Add** to add and save them. Click **Test Connection** to test your connection with Bluemix.

   * In the **Build Job Name** field, specify your build job's name exactly as it is in Jenkins. If the build occurs with the test job, leave this field empty. If the build job occurs outside of Jenkins, select the **Builds are being done outside of Jenkins** check box and specify the build number and build URL.

   * For the environment, if the tests are running in build stage, select only the build environment. If the tests are running in the deployment stage, select the deploy environment and specify the environment name. Two values are supported: `STAGING` and `PRODUCTION`.

   * For the **Result File Location** field, specify the result file's location. If the test doesn't generate a result file, leave this field empty. The plugin uploads a default result file that is based on the status of current test job.

   **Example configurations**

   ![Upload Build Information](https://github.com/IBM/ibm-cloud-devops/blob/master/screenshots/Upload-Build-Info.png "Publish Build Information to DRA")

   ![Upload Test Result](https://github.com/IBM/ibm-cloud-devops/blob/master/screenshots/Upload-Test-Result.png "Publish Test Result to DRA")

   ![Upload Deployment Information](https://github.com/IBM/ibm-cloud-devops/blob/master/screenshots/Upload-Deployment-Info.png "Publish Deployment Information to DRA")

4. (Optional): If you want to use DevOps Insights policy gates to control a downstream deploy job, add a post build action, **IBM Cloud DevOps Gate**. Choose a policy and specify the scope of the test results. To allow the policy gates to prevent downstream deployments, select the **Fail the build based on the policy rules** check box. The following image shows an example configuration:

    ![DevOps Insights Gate](https://github.com/IBM/ibm-cloud-devops/blob/master/screenshots/DRA-Gate.png "DevOps Insights Gate")

5. Run your Jenkins Build job.

6. Go to the [IBM Bluemix DevOps](https://console.ng.bluemix.net/devops), select your toolchain and click on DevOps Insights card to view Deployment Risk dashboard.


## 3. (Optional) Configure Jenkins jobs to send notifications to tools in your toolchain (e.g., Slack, PagerDuty), and enable traceability

Configure your Jenkins jobs to send notifications to tools integrated to your toolchain (e.g., Slack, PagerDuty),
and use traceability to track code deployments through tags, labels, and comments in your Git repository (repo).

Both Freestyle projects and Pipeline are supported.

Detailed instructions are available in the [Bluemix Docs](https://console.ng.bluemix.net/docs/services/ContinuousDelivery/toolchains_integrations.html#jenkins).


   **Example configurations**
  * Configuring the IBM_CLOUD_DEVOPS_WEBHOOK_URL for job configurations: ![Set IBM_CLOUD_DEVOPS_WEBHOOK_URL Parameter](https://github.com/IBM/ibm-cloud-devops/blob/master/screenshots/Set-Parameterized-Webhook.png "Set Parameterized WebHook")
  * Configuring post-build actions for job notifications: ![Post-build Actions for WebHook notification](https://github.com/IBM/ibm-cloud-devops/blob/master/screenshots/PostBuild-WebHookNotification.png "Configure WebHook Notification in Post-build Actions")
  * Configuring post-build actions to track deployment of code changes: ![Post-build Actions to track deployment of code changes](https://github.com/IBM/ibm-cloud-devops/blob/master/screenshots/track-deployment-of-code-changes.png "Configure WebHook Notification in Post-build Actions")


## License

Copyright&copy; 2016, 2017 IBM Corporation

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
