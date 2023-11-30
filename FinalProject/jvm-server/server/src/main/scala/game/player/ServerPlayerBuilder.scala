// package Server
// package Player

// import ServerUniverseGeneration._

// class ServerPlayerBuilder {
//   var position: Vector3 = _
//   var GameObject = ???
//   var currentStarSystem: ServerStar = _

//   def setPosition(position: Vector3): ServerPlayerBuilder = {
//     this.position = position
//     this
//   }

//   def build: ServerPlayerInstance = {
//     this.currentStarSystem = StarSystemManager.spawnSystem
//     this.playerObject = new GameObject
//     this.playerObject.transform.position = this.position
//     new ServerPlayerInstance(this)
//   }
// }
