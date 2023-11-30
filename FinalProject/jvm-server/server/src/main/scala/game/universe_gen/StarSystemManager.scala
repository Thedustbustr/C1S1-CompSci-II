// package Server
// package UniverseGeneration

// import scala.collection.mutable.ListBuffer

// object StarSystemManager {
//   private var SpawnSystem: ServerStar = _
//   private val StarSystems = ListBuffer[ServerStar]()
//   private val LoadedStarSystems = ListBuffer[ServerStar]()

//   def generateStarSystem(): Unit = {
//     val isSpawnSystem = false
//     cleanUp()

//     val star = new StarBuilder().build
//     StarSystems += star
//     LoadedStarSystems += star

//     if (!isSpawnSystem) {
//       SpawnSystem = star
//     }
//   }

//   private def cleanUp(): Unit = {
//     if (LoadedStarSystems.nonEmpty) {
//       val toRemove = ListBuffer[ServerStar]()

//       LoadedStarSystems.foreach(loadedStarSystem => {
//         loadedStarSystem.unload()
//         toRemove += loadedStarSystem
//       })

//       toRemove.foreach(starSystemToRemove => {
//         LoadedStarSystems -= starSystemToRemove
//       })
//     }
//   }
// }
