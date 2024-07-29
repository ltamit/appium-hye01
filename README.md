<img height="100" alt="hyperexecute_logo" src="https://user-images.githubusercontent.com/1688653/159473714-384e60ba-d830-435e-a33f-730df3c3ebc6.png">

HyperExecute is a smart test orchestration platform to run end-to-end tests at the fastest speed possible. HyperExecute lets you achieve an accelerated time to market by providing a test infrastructure that offers optimal speed, test orchestration, and detailed execution logs.

The overall experience helps teams test code and fix issues at a much faster pace. HyperExecute is configured using a YAML file. Instead of moving the Hub close to you, HyperExecute brings the test scripts close to the Hub!

* <b>HyperExecute HomePage</b>: https://www.lambdatest.com/hyperexecute
* <b>Lambdatest HomePage</b>: https://www.lambdatest.com
* <b>LambdaTest Support</b>: [support@lambdatest.com](mailto:support@lambdatest.com)

To know more about how HyperExecute does intelligent Test Orchestration, do check out [HyperExecute Getting Started Guide](https://www.lambdatest.com/support/docs/getting-started-with-hyperexecute/)

[<img alt="Try it now" width="200 px" align="center" src="images/Try it Now.svg" />](https://hyperexecute.lambdatest.com/hyperexecute/jobs)

# Steps to run Real Device Appium tests using HyperExecute

* [Pre-requisites](#pre-requisites)
   - [Download HyperExecute CLI](#download-hyperexecute-cli)
   - [Setup Environment Variables](#setup-environment-variable)
* [Step 1: Upload your Application](#step-1-upload-your-application)
* [Step 2: Configure your Test](#step-2-configure-your-test)
* [Step 3: Define your YAML file](#step-3-define-your-yaml-file)
* [Step 4: Trigger your Test](#step-4-trigger-your-test)

# Pre-requisites

- HyperExecute YAML file which contains all the necessary instructions.
- HyperExecute CLI to initiate a test execution Job.
- Your LambdaTest Username and Access Key
- Setup the Environmental Variable
- Ensure you have Appium’s Java client library installed.
- Access to the Android app (.apk or .aab file) or an iOS app (.ipa file).

## Download HyperExecute CLI

HyperExecute CLI is the CLI for interacting and running the tests on the HyperExecute Grid. The CLI provides a host of other useful features that accelerate test execution. In order to trigger tests using the CLI, you need to download the HyperExecute CLI binary corresponding to the platform (or OS) from where the tests are triggered:

Also, it is recommended to download the binary in the project's parent directory. Shown below is the location from where you can download the HyperExecute CLI binary:

| Platform	| HyperExecute CLI download URL |
|-----------|-------------------------------|
| Windows | https://downloads.lambdatest.com/hyperexecute/windows/hyperexecute.exe |
| macOS | https://downloads.lambdatest.com/hyperexecute/darwin/hyperexecute |
| Linux | https://downloads.lambdatest.com/hyperexecute/linux/hyperexecute |

## Setup Environment Variable
Export the environment variables *LT_USERNAME* and *LT_ACCESS_KEY* that are available in the [LambdaTest Profile page](https://accounts.lambdatest.com/detail/profile).
Run the below mentioned commands in the terminal to setup the CLI and the environment variables.

For Linux / macOS:

```bash
export LT_USERNAME=YOUR_LT_USERNAME
export LT_ACCESS_KEY=YOUR_LT_ACCESS_KEY
```

For Windows:

```bash
set LT_USERNAME=YOUR_LT_USERNAME
set LT_ACCESS_KEY=YOUR_LT_ACCESS_KEY
```

## Step 1: Upload your Application
Upload your Android or iOS application (.apk or .ipa file) to the LambdaTest servers using our REST API. You need to provide your Username and AccessKey in the format Username:AccessKey in the cURL command for authentication.

```bash
curl -u "USERNAME:ACCESS_KEY" -X POST "https://manual-api.lambdatest.com/app/upload/realDevice" -F "appFile=@"<YOUR_LOCAL_APP_PATH>"" -F "name="sampleApp""
```

## Step 2: Configure your Test
After running the above cURL command, you will receive an `AppURL` of the format `lt://APP123456789123456789` which you will update in your test scripts. Create a `.xml` file to run your test and define device capabilities.

## Step 3: Define your YAML file

```yaml
version: 0.2
globalTimeout: 150
testSuiteTimeout: 150
testSuiteStep: 150

runson: linux

concurrency: 5

autosplit: true

retryOnFailure: false
maxRetries: 1

pre:
  - mvn -Dmaven.repo.local=./.m2 dependency:resolve

appium: true
framework:
  name: maven/testng
  defaultReports: false
  discoveryType: xmltest
  flags: ["-Pandroid-parallel"]

jobLabel: ['HYP-RD', 'Android', 'Multiple Device']
```

## Step 4: Trigger your Test

> Note: In the case of MacOS, if you get a permission denied warning while executing CLI, simply run `chmod u+x ./hyperexecute` to allow permission. In case you get a security popup, allow it from your System Preferences → Security & Privacy → General tab.

Run the below command in your terminal at the root folder of the project:

```bash
./hyperexecute --user <your_username> --key <your_access_key> --config <path_of_yaml_file>
```

## LambdaTest Community :busts_in_silhouette:

The [LambdaTest Community](https://community.lambdatest.com/) allows people to interact with tech enthusiasts. Connect, ask questions, and learn from tech-savvy people. Discuss best practises in web development, testing, and DevOps with professionals from across the globe.

## Documentation & Resources :books:
      
If you want to learn more about the LambdaTest's features, setup, and usage, visit the [LambdaTest documentation](https://www.lambdatest.com/support/docs/). You can also find in-depth tutorials around test automation, mobile app testing, responsive testing, manual testing on [LambdaTest Blog](https://www.lambdatest.com/blog/) and [LambdaTest Learning Hub](https://www.lambdatest.com/learning-hub/).     
      
 ## About LambdaTest

[LambdaTest](https://www.lambdatest.com) is a leading test execution and orchestration platform that is fast, reliable, scalable, and secure. It allows users to run both manual and automated testing of web and mobile apps across 3000+ different browsers, operating systems, and real device combinations. Using LambdaTest, businesses can ensure quicker developer feedback and hence achieve faster go to market. Over 500 enterprises and 1 Million + users across 130+ countries rely on LambdaTest for their testing needs.

[<img height="70" src="https://user-images.githubusercontent.com/70570645/169649126-ed61f6de-49b5-4593-80cf-3391ca40d665.PNG">](https://accounts.lambdatest.com/register)
      
## We are here to help you :headphones:

* Got a query? we are available 24x7 to help. [Contact Us](mailto:support@lambdatest.com)
* For more info, visit - https://www.lambdatest.com
