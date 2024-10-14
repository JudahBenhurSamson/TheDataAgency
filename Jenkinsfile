pipeline {
    agent any
    
    environment {
        GRADLE_HOME = 'C:\\Program Files\\Gradle\\gradle-5.0-all'
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-17'
    }

    stages {
        stage('Checkout') {
            steps {
                // Github URL
                git url: 'https://github.com/JudahBenhurSamson/TheDataAgency.git', branch: 'main'
            }
        }
        
        stage('Clean') {
            steps {
                // Run the Gradle 'clean' task.
                bat '.\\gradlew.bat clean'
            }
        }
        
        stage('Refresh Dependencies and Build') {
            steps {
                // Run the Gradle build with the --refresh-dependencies flag.
                bat '.\\gradlew.bat --refresh-dependencies build'
            }
        }
        
        stage('To Deploy and select local profile') {
            steps {
                // Run the application with the Spring profile set to 'local'.
                bat ".\\gradlew.bat bootRun --args='--spring.profiles.active=local'"
            }
        }
        
        stage('Test') {
            steps {
                // Execute the Gradle test task.
                bat '.\\gradlew.bat test'
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
