pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M2"
    }
    stages{
        stage("Check out code"){
            steps{
                git branch: 'master', url: 'https://github.com/anand40090/springboot-maven-course-micro-svc.git'
            }
        }
        stage("Build code"){
            steps{
                sh 'mvn clean package'
            }
        }
        stage("Sonar Deploy"){
            steps{
                withSonarQubeEnv(installationName: 'sonarqube-9', credentialsId: 'jenkins-sonar-token') {
                    sh 'mvn clean verify sonar:sonar'
                      }
            }   
        }
        stage('ImageBuild'){
            steps{
                script{
                    app = docker.build("mvc")
                }
            }
        }
        stage('Test'){
            steps{
                echo 'Empty'
            }
        }
        stage('Deploy'){
            steps{
                script{
                    docker.withRegistry('https://193740069471.dkr.ecr.ap-south-1.amazonaws.com/mvc', 'ecr:ap-south-1:jenkins-ecr-credentials') {
                        app.push("${env.BUILD_NUMBER}")
                        app.push("latest")
                    }
                }
            }
        }
    }

    }
