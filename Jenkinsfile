pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Program Files\\Apache\\maven' // Update with your Maven installation path
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.1' // Update to match your Java version
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
                 
        }
    }
      stage('Publish Extent Report') {
            steps {
                publishHTML([
                    reportName: 'Extent Report',
                    reportDir: 'petStoreProject/reports',  // The directory containing your report
                    reportFiles: 'Test-Report--01-25-2025/index.html',  // The specific HTML report file
                    alwaysLinkToLastBuild: true,
                    keepAll: true
                ])
            }

    post {
        always {
            echo 'Pipeline completed.'
        }
        success {
            echo 'Build and tests ran successfully.'
        }
        failure {
            echo 'Pipeline failed. Check the logs for errors.'
        }
    }
}
