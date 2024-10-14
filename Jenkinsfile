pipeline {
    agent any

    environment {
        GRADLE_HOME = '/path/to/gradle'
        JAVA_HOME = '/path/to/java'
    }

    stages {
        stage('Checkout') {
            steps {
                // Github URL
                git url: 'https://github.com/your-repo/spring-boot-app.git', branch: 'main'
            }
        }

        stage('Clean') {
            steps {
                sh './gradlew clean'
            }
        }

        stage('Refresh Dependencies and Build') {
            steps {
                sh './gradlew --refresh-dependencies build'
            }
        }

        stage('To Deploy and select local profile') {
            steps {
                sh "./gradlew bootRun --args='--spring.profiles.active=local"
            }
        }

        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
