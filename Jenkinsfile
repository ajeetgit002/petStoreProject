pipeline {
    agent any

    environment {
        MAVEN_HOME = '/usr/share/maven' // Update with your Maven installation path
        JAVA_HOME = '/usr/lib/jvm/java-11-openjdk-amd64' // Update to match your Java version
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
                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean compile'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Archive Test Results') {
            steps {
                echo 'Archiving test results...'
                junit 'target/surefire-reports/*.xml'
            }
        }
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
