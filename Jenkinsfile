pipeline {

    /*
    * Run everything on an existing agent configured with a label 'docker'.
    * This agent will need docker, git and a jdk installed at a minimum.
    */
    agent {
      node {
        label 'master'
      }
    }

    // using the Timestamper plugin we can add timestamps to the console log
    options {
      timestamps()
    }

    environment {
      //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
      IMAGE = readMavenPom().getArtifactId()
      VERSION = readMavenPom().getVersion()
    }

    stages {
      stage('Build') {
        agent {
          docker {
            /*
            * Reuse the workspace on the agent defined at top-level of Pipeline but run inside a container.
            * In this case we are running a container with maven so we don't have to install specific versions
            * of maven directly on the agent
            */
            reuseNode true
            image 'maven:3.5.0-jdk-8'
          }
        }
        steps {
          // using the Pipeline Maven plugin we can set maven configuration settings, publish test results, and annotate the Jenkins console
          withMaven(options: [findbugsPublisher(), junitPublisher(ignoreAttachments: false)]) {
            sh 'mvn clean findbugs:findbugs package'
          }
        }
        post {
          success {
            // we only worry about archiving the jar file if the build steps are successful
            archiveArtifacts(artifacts: '**/target/*.jar', allowEmptyArchive: true)
          }
        }
      }
  }

}