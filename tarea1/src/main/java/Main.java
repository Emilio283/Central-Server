package main.java;

import java.awt.EventQueue;

import main.java.presentacion.App;

/** The main class. */
public class Main {

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        App frame = new App();
        frame.setVisible(true);
        frame.setSize(800, 600);
      }
    });
  }
}
