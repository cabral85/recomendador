pipeline {
  agent any
  stages {
    stage('pull') {
      steps {
        git(url: 'https://github.com/cabral85/recomendador.git', branch: 'master')
      }
    }
  }
}