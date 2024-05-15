package main.java.logica.excepciones;

@SuppressWarnings("serial")
public class YaExisteKeyword extends Exception {

  public YaExisteKeyword(String msg) {
    super(msg);
  }
}
