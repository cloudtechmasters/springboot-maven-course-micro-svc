pipeline{
    environment {
    account = "${environment}" 
    eks_cluster_name = "eks-${account}" 
    artifacts_dir = "${env.WORKSPACE}/artifacts"
    aws_region = "${params.aws_region}"
    job_root_dir="${env.WORKSPACE}"
    }
    tools { 
        maven 'maven-3.8.1' 
       
    }
    agent {
        label 'master'
        }
        stages{
            stage('Initialize workspace') {
        steps {
        // Make sure the directory is clean
        dir("${artifacts_dir}") {
            deleteDir()
        }
        sh(script: "mkdir -p ${artifacts_dir}", label: 'Create artifacts directory')
        }
    }
            stage('git stage'){
                steps{
                    git branch: 'main', url: 'https://github.com/cloudtechmasters/springboot-maven-course-micro-svc.git'
                }
            }
            stage('build maven project '){
                steps{
                   sh 'mvn clean package'
                }
            }
		stage('Generate kubeconfig for the cluster') {
        steps {
        script {
            env.KUBECONFIG = "${artifacts_dir}/${eks_cluster_name}-kubeconfig"
            sh 'chmod +x ${WORKSPACE}/generate_kubeconfig_eks.sh'
        }
        sh(script: '${WORKSPACE}/generate_kubeconfig_eks.sh', label: 'Generate kubeconfig file')
        }
    }
    
    stage('Get the cluster details') {
        steps {
        script {
            sh '''kubectl apply -f deployment.yml 
                  kubectl apply -f service.yml
                  kubectl get all
                '''
        }
        }
    }
        }
    post {
	    cleanup {
	          cleanWs(cleanWhenFailure: false)
	    }
	    always {  
             echo 'This will always run'  
         }  
         success {  
             echo 'This will run only if successful' 
			 mail bcc: '', body: "<b>Example</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: 'cloudtechmasters@gmail.com', mimeType: 'text/html', replyTo: 'cloudtechmasters@gmail.com', subject: "SUCCESS CI: Project name -> ${env.JOB_NAME}", to: "cloudtechmasters@gmail.com"; 
         }  
         failure {  
             mail bcc: '', body: "<b>Example</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: 'cloudtechmasters@gmail.com', mimeType: 'text/html', replyTo: 'cloudtechmasters@gmail.com', subject: "ERROR CI: Project name -> ${env.JOB_NAME}", to: "cloudtechmasters@gmail.com";  
         }  
         unstable {  
             echo 'This will run only if the run was marked as unstable'  
         }  
         changed {  
             echo 'This will run only if the state of the Pipeline has changed'  
             echo 'For example, if the Pipeline was previously failing but is now successful'  
         }
    }
}
