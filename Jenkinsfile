pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Program Files\\Apache\\maven'
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.1'
    }

    stages {
        stage('Clone Repository') {
            steps {
                echo 'Cloning the repository...'
                git branch: 'master', url: 'https://github.com/ajeetgit002/petStoreProject.git'
            }
        }

        stage('Set Up Environment') {
            steps {
                echo 'Setting up the environment...'
                bat 'java -version'
                bat 'mvn -version'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running tests...'
                bat 'mvn test'
            }
        }

        stage('Archive Test Results') {
            steps {
                echo 'Archiving test results...'
                archiveArtifacts artifacts: '**/test-output/reports/Report.html', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Build and tests ran successfully.'
            emailext(
                to: 'ajeet.testingqa@gmail.com',
                subject: 'TestNG Results - Success',
                body: 'The tests completed successfully. Please find the report attached.',
                attachmentsPattern: '**/test-output/reports/Report.html'
            )
        }
        failure {
            echo 'Pipeline failed. Check the logs for errors.'
            emailext(
                to: 'ajeet.testingqa@gmail.com',
                subject: 'TestNG Results - Failure',
                body: 'The pipeline failed. Please check the logs and attached report.',
                attachmentsPattern: '**/test-output/reports/Report.html'
            )
        }
    }
}
