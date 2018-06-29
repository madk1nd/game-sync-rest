node {
	def app

	stage('pull') {
		sh "cd game-sync-rest; git checkout master; git pull"
	}
	
	stage('build'){
		sh "mvn clean install; cd docs/build; docker-compose -f game-sync.yml up --build"
	}
}
