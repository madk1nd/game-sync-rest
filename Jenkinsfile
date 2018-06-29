node {
	def app

	stage('pull') {
		sh "git clone https://github.com/madk1nd/game-sync-rest.git; git checkout master; git pull"
	}
	
	stage('build'){
		step1{
			sh "mvn clean install"
		}
		step2{
			sh "cd docs/build"
		}
		step3{
			sh "docker-compose -f game-sync.yml up --build"
		}
	}
}
