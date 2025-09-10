pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/lankaprabhudeva/Mailtomanagercucumberproject.git', branch: 'main'
            }
        }

        stage('Build Project') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Archive Reports') {
            steps {
                // ✅ Now cucumber.xml will actually exist
                junit 'target/cucumber-reports/cucumber.xml'
                archiveArtifacts artifacts: 'target/cucumber-reports/*.*', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Build Successful'
        }
        failure {
            echo 'Build Failed'
        }
    }
}
