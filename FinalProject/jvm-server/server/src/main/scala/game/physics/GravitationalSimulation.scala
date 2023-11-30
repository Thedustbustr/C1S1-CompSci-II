// package Server
// package Physics

// object GravitationalSimulation {
//   def physicsUpdate(): Unit = {
//     val bodies = ServerUniverseGeneration.ServerUniverseGenerationHandler.requestAllBodies()
//     for (i <- 0 until bodies.length) {
//       if (!bodies(i).locked) {
//         val progression = (ServerRuntime.instance.serverTick / (bodies(i).orbitalParameters.orbitalPeriod * ServerRuntime.instance.maxServerTickRate)) * ServerRuntime.instance.simulationSpeed
//         bodies(i).position = PhysicsEngine.DeterministicPhysics.calculateDeterministicPhysics(bodies(i), bodies(i).centralObject, progression)
//       }
//     }
//   }
// }
