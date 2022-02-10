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
        }
}
