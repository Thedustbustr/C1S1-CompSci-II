package lib;

/**
 * Callback interface
 */
public interface Callback<T> {
  /**
   * How the callback is invoked
   * @param result The data passed through
   */
  void invoke(T result);
}
