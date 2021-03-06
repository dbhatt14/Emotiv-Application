package server.sys;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import data.EmotivData;
import server.gui.panels.LogPanel;
import util.Constants;

/**
 * The purpose of this class is to provide the websocket logic for the different life
 * cycles and other static methods to expose the websocket since all of the interaction is
 * from the server outwards.
 * 
 * @author Group 1 #001 - #013
 * @version 1.0
 * @since 28MAR2018
 *
 */
@ServerEndpoint(value = Constants.ENDPOINT)
public class ServerWebSocket {
  public EmotivData emotivData = new EmotivData();

  static List<Session> clients = Collections.synchronizedList(new ArrayList<Session>());
  
  @OnOpen
  public void onOpen(Session session) {
    if (!clients.contains(session)) {
      clients.add(session);
      LogPanel.getConsolePanel().updateText(String.format("Welcome client: %s", session.getId()));
    }
  }

  @OnMessage
  public void onMesage(String emotivDataString, Session session) throws IOException {
  }

  @OnClose
  public void onClose(Session session) throws IOException {
    clients.remove(session);
    LogPanel.getConsolePanel().updateText(String.format("Session %s disconnected...", session.getId()));
  }

  @OnError
  public void onError(Session session, Throwable t) {
    LogPanel.getConsolePanel()
        .updateText(String.format("Error occurred (%s): \n%s", session.getId(), t.getMessage()));
  }

  public static void sendMessage(Session session, String message) throws IOException {
    if (clients.contains(session))
      session.getBasicRemote().sendText(message);
    else
      throw new NullPointerException();
  }

  public static List<Session> getClients() {
    return clients;
  }
}
