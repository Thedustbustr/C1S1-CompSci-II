package game.universegen

import game.universegen.solarsystem.star.*

import scala.collection.mutable.ListBuffer

object StarSystemManager {
  var SpawnSystem: Star = _
  val StarSystems = ListBuffer[Star]()
  val LoadedStarSystems = ListBuffer[Star]()

  def generateStarSystem(): Unit = {
    val isSpawnSystem = false
    //cleanUp()

    val star = StarBuilder.build()
    StarSystems += star
    LoadedStarSystems += star

    if (!isSpawnSystem) {
      SpawnSystem = star
    }
  }

  // private def cleanUp(): Unit = {
  //   if (LoadedStarSystems.nonEmpty) {
  //     val toRemove = ListBuffer[Star]()

  //     LoadedStarSystems.foreach(loadedStarSystem => {
  //       loadedStarSystem.unload()
  //       toRemove += loadedStarSystem
  //     })

  //     toRemove.foreach(starSystemToRemove => {
  //       LoadedStarSystems -= starSystemToRemove
  //     })
  //   }
  // }
}
