package main.java.logica.excepciones;

@SuppressWarnings("serial")
public class NoExistePostulante extends Exception {

  public NoExistePostulante(String msg) {
    super(msg);
  }
}
