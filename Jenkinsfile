node {
	def app

	stage('pull') {
		sh "cd game-sync-rest; git checkout master; git pull"
	}
	
	stage('build'){
		sh "cd game-sync-rest; mvn clean install; cd docs/build; docker-compose -f game-sync.yml up --build"
	}
}
