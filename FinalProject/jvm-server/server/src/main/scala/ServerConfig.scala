/**
  * Test
  *
  * @param serverTick
  * @param maxServerTickRate
  * @param realtimeSimulationSpeed
  * @param simulationSpeed
  */
final case class ServerConfig(
    serverTick: Long,
    maxServerTickRate: Int,
    realtimeSimulationSpeed: Float,
    simulationSpeed: Float
)
