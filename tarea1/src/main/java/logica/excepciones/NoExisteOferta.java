package main.java.logica.excepciones;

@SuppressWarnings("serial")
public class NoExisteOferta extends Exception {

  public NoExisteOferta(String msg) {
    super(msg);
  }
}
