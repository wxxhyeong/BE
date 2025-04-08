pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'wxxhyeong/be:latest'
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Gradle Build') {
            steps {
                sh 'chmod +x ./gradlew'
                sh './gradlew clean build' // 또는 필요한 태스크
            }
        }

        stage('Docker Build') {
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([string(credentialsId: 'docker-hub-token', variable: 'DOCKER_TOKEN')]) {
                    sh "echo $DOCKER_TOKEN | docker login -u wxxhyeong --password-stdin"
                    sh "docker push ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy to EC2') {
            steps {
                sh '''
                ssh -o StrictHostKeyChecking=no -i ~/.ssh/crossfit-backend.pem ubuntu@54.85.46.9 << EOF
                docker stop backend || true
                docker rm backend || true
                docker pull wxxhyeong/be:latest
                docker run -d --name backend -p 8080:8080 wxxhyeong/be:latest
                EOF
                '''
            }
        }
    }
}
