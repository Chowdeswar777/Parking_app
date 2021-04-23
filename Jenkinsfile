  
node('master') 

{ 

    

   stage('SCM Checkout') 

             { 

                  git 'https://github.com/mythribhay/parking_backend.git' 

              } 

        

   stage('Build')  

         { 

                  withSonarQubeEnv('sonar')  

                  { 

                        sh '/usr/share/maven/bin/mvn clean verify sonar:sonar -Dsonar.password=sonar -Dsonar.login=admin' 

                  } // SonarQube taskId is automatically attached to the pipeline context 

         } 

   

   stage("QG Check") 

         { 

                  timeout(time: 1, unit: 'HOURS')  

                  { // Just in case something goes wrong, pipeline will be killed after a timeout 

                        def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv 

                        if (qg.status != 'OK')  

                        { 

                            error "Pipeline aborted due to quality gate failure: ${qg.status}" 

                        } 

                  } 

         } 
}

stage('Deploy approval') 
        {
                          input "Deploy to prod?"
        }

node('master') 

{ 
      stage('SIT – Deployment ') 

        { 

             sh '/usr/share/maven/bin/mvn clean deploy -DaltDeploymentRepository=internal.repo::default::http://admin:admin123@3.21.127.130:8081/nexus/content/repositories/snapshots/' 

         } 
}


stage("Sending the notification to slack") 
{
    slackSend message: "Job Name:- ${env.JOB_NAME} Job Number:- ${env.BUILD_NUMBER} Build status :- ${currentBuild.currentResult}"
    
    }
