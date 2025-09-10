pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
        jdk 'JDK17'
    }

    triggers {
        // Run every 30 minutes, Mon–Fri, between 6 AM and 6:59 PM
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
                // Build project but skip tests (we run them separately in next stage)
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                // Run tests on Jenkins (reports + email)
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
                    // 🐳 Build Docker image without tests
                    bat 'docker build -t cucumber-mail-pipeline:latest .'
                }
            }
        }

        // ❌ Remove this stage unless you really want to run app.jar in Docker
        // stage('Run Docker Image') {
        //     steps {
        //         script {
        //             bat 'docker run --rm cucumber-mail-pipeline:latest'
        //         }
        //     }
        // }
    }

    post {
        success {
            echo '✅ Build & Tests Successful, Docker image created'
        }
        failure {
            echo '❌ Build or Tests Failed'
        }
    }
}
