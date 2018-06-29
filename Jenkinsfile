node {
	def app

	stage('pull') {
		sh "cd game-sync-rest; git init; git checkout master; git pull"
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
