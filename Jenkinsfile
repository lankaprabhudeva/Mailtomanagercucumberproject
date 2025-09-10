pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK17'
    }

    triggers {
    cron('H/30 6-18 * * 1-5')
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
                junit 'target/cucumber-reports/cucumber.xml'
                archiveArtifacts artifacts: 'target/cucumber-reports/*.*', fingerprint: true
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // 🐳 Build Docker image from Dockerfile in repo
                    bat 'docker build -t cucumber-mail-pipeline:latest .'
                }
            }
        }

        stage('Run Docker Image') {
            steps {
                script {
                    // 🐳 Run container & clean after exit
                    bat 'docker run --rm cucumber-mail-pipeline:latest'
                }
            }
        }
    }

    post {
        success {
            echo '✅ Build & Docker Run Successful'
        }
        failure {
            echo '❌ Build Failed'
        }
    }
}
