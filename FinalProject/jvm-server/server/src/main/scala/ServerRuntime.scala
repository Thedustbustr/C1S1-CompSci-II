import util.extensions.*
import game.universegen.StarSystemManager

object ServerRuntime {
  // Default 60x speed 20tps
  var simulation = Simulation(60, 20)

  var serverTick = 0L

  private var started = false
  def init = {
    if (!started) {
      started = true
      StarSystemManager.generateStarSystem()
    }
  }
}

final case class Simulation(speed: Int, maxServerTickRate: Int) {
//  def realtimeSimulationSpeed = if {(speed < 0) 1f / maxServerTickRate} else {1f / maxServerTickRate * speed}
  def realtimeSimulationSpeed = (speed < 0) ? (1f / maxServerTickRate) | (1f / maxServerTickRate * speed)
}
