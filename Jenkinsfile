pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Program Files\\Apache\\maven'
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.1'
        DOCKER_REGISTRY = 'docker.io'  // Docker Hub or your registry URL
        DOCKER_IMAGE = 'ajeetdocker002/petstore'  // Replace with your Docker image name
        DOCKER_TAG = 'latest'  // Tag for the Docker image
        DOCKER_USERNAME = 'ajeetdocker002'  // Your Docker username
        DOCKER_PASSWORD = 'Admin@2024'      // Your Docker password
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

        stage('Verify Dockerfile') {
            steps {
                echo 'Verifying Dockerfile existence...'
                bat 'dir'  // This will list the contents of the current directory to check if Dockerfile exists
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building the Docker image...'
                bat 'docker build -t %DOCKER_IMAGE%:%DOCKER_TAG% .'
            }
        }

        stage('Push Docker Image') {
            when {
                branch 'master'  // Only push to registry if the branch is 'master'
            }
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
