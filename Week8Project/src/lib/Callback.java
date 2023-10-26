package lib;

/**
 * Callback interface
 */
public interface Callback<E> {
  /**
   * How the callback is invoked
   * @param result The data passed through
   */
  void invoke(E result);
}
