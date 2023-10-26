package lib;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The callback manager manages any callback created in the program
 * 
 * Info on thread safe map:
 * https://www.baeldung.com/java-concurrent-map
 */
public class CallbackManager {
  /**
   * The callback manager singleton
   */
  public static final CallbackManager INSTANCE = new CallbackManager();

  /** 
   * Returns instance of the callback manager singleton
   * @return CallbackManager
   */
  public static CallbackManager getInstance() {
    return INSTANCE;
  }

  /**
  * Constructor
  */
  private CallbackManager() {
    //
  }

  private Map<UUID, Callback<?>> activeCallbacks = new ConcurrentHashMap<>();

  /** 
   * Generates unique UUID
   * @return UUID
   */
  private UUID getUUID() {
    UUID id = UUID.randomUUID();

    if (activeCallbacks.containsKey(id)) {
      return getUUID();
    } else {
      return id;
    }
  }

  /** 
   * Registers a new callback to be tracked
   * @param callback The callback to be added
   * @param <E> Allows callbacks to be generic types
   * @return UUID
   */
  public <E> UUID registerCallback(Callback<E> callback) {
    UUID uuid = getUUID();
    activeCallbacks.put(uuid, callback);

    return uuid;
  }

  /** 
   * Removes callback from callback manager
   * @param uuid The UUID of the callback
   */
  public void removeCallback(UUID uuid) {
    activeCallbacks.remove(uuid);
  }

  /** 
   * Executes the callback
   * @param uuid The UUID of the callback
   * @param data The data to be passed to the callback
   * @param <E> Allows callbacks to be generic types
   */
  public <E> void executeCallback(UUID uuid, E data) {
    Callback<E> callback = (Callback<E>) activeCallbacks.get(uuid); // I know this is not good practice but I've been working on this for hours
    if (callback != null) {
      callback.invoke(data);
      removeCallback(uuid);
    }
  }
}