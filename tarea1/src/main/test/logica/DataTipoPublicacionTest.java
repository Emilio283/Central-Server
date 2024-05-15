package main.test.logica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import main.java.logica.datatypes.DataTipoPublicacion;

class DataTipoPublicacionTest {

  private LocalDate fecha1 = LocalDate.of(1234, 12, 12);
  private LocalDate fecha2 = LocalDate.of(1234, 8, 12);

  private DataTipoPublicacion dtp1 = new DataTipoPublicacion("Nombre", "Descripcion", 0, 0, 0, fecha1);
  private DataTipoPublicacion dtp2 =
      new DataTipoPublicacion(new String("Nombre"), "Descripcion", 0, 0, 0, fecha1);
  private DataTipoPublicacion dtp3 = new DataTipoPublicacion("Distinta", "Otra desc", 5, 2, 4, fecha2);

  @Test
  void reflexiva() {
    assertEquals(dtp1, dtp1);
  }

  @Test
  void simetrica() {
    assertEquals(dtp1, dtp2);
    assertEquals(dtp2, dtp1);

    assertNotEquals(dtp1, dtp3);
    assertNotEquals(dtp3, dtp1);
  }

  @Test
  void testHashCode() {
    assertEquals(dtp1.hashCode(), dtp2.hashCode());

    assertNotEquals(dtp1.hashCode(), dtp3.hashCode());
  }

}
