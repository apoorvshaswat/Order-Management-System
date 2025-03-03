pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "my-java-app"
        DOCKER_REGISTRY = "localhost:5000"
    }

    stages {
        stage('Build') {
            steps {
                script {
                    sh 'docker-compose -f docker-compose.yml build'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    echo 'Running tests...'
                    sh './gradlew test'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    sh 'docker-compose -f docker-compose.yml up -d'
                }
            }
        }
    }

    post {
        always {
            sh 'docker-compose -f docker-compose.yml down'
        }
    }
}
