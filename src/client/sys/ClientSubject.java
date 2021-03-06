package client.sys;

import java.util.ArrayList;

import data.EmotivData;
import interfaces.ClientObservable;
import interfaces.ClientObserver;

/**
 * Observable - This class is the subject that is observed to get data from the server.
 * Uses Singleton pattern so that the same instance is used by websocket and the
 * observers.
 * 
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 2018-04-03
 */
public class ClientSubject implements ClientObservable {

  ArrayList<ClientObserver> observerList = new ArrayList<>();
  EmotivData emotivData;
  private static ClientSubject subjectInstance;

  public void updateObservers(EmotivData data) {
    System.out.println("Are we here then: " + data.getExcitementLongTerm());
    emotivData = data;
    notifyObserver(observerList);
  }

  private ClientSubject() {
  }

  /**
   * @return the singleton instance of the class
   */
  public static ClientSubject getInstance() {
    if (subjectInstance == null) {
      subjectInstance = new ClientSubject();
    }
    return subjectInstance;
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.ClientObservable#addObserver(interfaces.ClientObserver)
   */
  @Override
  public void addObserver(ClientObserver observer) {
    observerList.add(observer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.ClientObservable#removeObserver(interfaces.ClientObserver)
   */
  @Override
  public void removeObserver(ClientObserver observer) {
    observerList.remove(observer);

  }

  /*
   * (non-Javadoc)
   * 
   * @see interfaces.ClientObservable#notifyObserver(java.util.ArrayList)
   */
  @Override
  public void notifyObserver(ArrayList<ClientObserver> observers) {
    for (ClientObserver obs : observers) {
      obs.updateObserver(emotivData);
    }
  }
}
