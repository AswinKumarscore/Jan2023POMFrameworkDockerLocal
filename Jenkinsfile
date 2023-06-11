pipeline{
    
    agent any
    tools{
    maven 'maven'
    }
    
    stages{
        
        stage("Build"){
             steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        stage("Run UTs"){
            steps{
                echo("run unit tests")
            }
        }
        
        
        stage("Deploy to dev"){
            steps{
                echo("deploying to dev env")
            }
        }
        
        
        
        stage("Deploy to qa"){
            steps{
                echo("deploying to qa env")
            }
        }
        
        stage("Run regression automation test cases"){
             steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {              
                    git 'https://github.com/AswinKumarscore/Jan2023POMSeries.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/main/resources/testrunners/testng_regression.xml"
                    
                }
            }
        }
        
        
         stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
     
        stage("Deploy to stage"){
            steps{
                echo("deploying to stage env")
            }
        }
        
        stage("Run sanity automation test cases"){
            steps{
                echo("Run sanity automation test cases")
            }
        }
        
        stage("Deploy to prod"){
            steps{
                echo("deploying to prod env")
            }
        }
    
        
        
        
    }
    
    
    
}