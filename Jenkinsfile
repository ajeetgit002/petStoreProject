pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Program Files\\Apache\\maven'
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.1'
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_IMAGE = 'ajeetdocker002/petstore'
        DOCKER_TAG = 'latest'
        DOCKER_USERNAME = 'ajeetdocker002'
        DOCKER_PASSWORD = 'Admin@2024'
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
                bat 'mvn clean install'  // Run clean install to generate the JAR file
            }
        }

        stage('Verify JAR File') {
            steps {
                echo 'Verifying if the JAR file exists...'
                bat 'if exist target\\PetStoreAutomation-0.0.1-SNAPSHOT.jar (echo JAR file exists) else (echo JAR file not found)'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building the Docker image...'
                bat 'docker build -t %DOCKER_IMAGE%:%DOCKER_TAG% .'
            }
        }

        stage('Push Docker Image') {
            
            steps {
                echo 'Pushing Docker image to registry...'
                bat '''
                    echo %DOCKER_PASSWORD% | docker login -u %DOCKER_USERNAME% --password-stdin
                    docker push %DOCKER_IMAGE%:%DOCKER_TAG%
                '''
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
        }
        failure {
            echo 'Pipeline failed. Check the logs for errors.'
        }
    }
}
