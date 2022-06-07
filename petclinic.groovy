pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/g0t4/jgsu-spring-petclinic'
            }
        }
       
        stage('Test') {
            steps {
                sh "false"
                // bat 'mvnw.cmd test'
            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        
        stage('Package') {
            steps {
                bat 'mvnw.cmd package'
            }

            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
