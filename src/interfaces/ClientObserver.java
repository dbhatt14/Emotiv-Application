package interfaces;

import data.EmotivData;

/**
 * Implementation of Observer Interface
 * 
 * @author Group 1 #001 - #013
 * @version 1.0
 * 
 */
public interface ClientObserver {

  /**
   * Method that updates the observers.
   * 
   * @param data - the data received from server that has to passed to the observer.
   */
  void updateObserver(EmotivData data);
}
