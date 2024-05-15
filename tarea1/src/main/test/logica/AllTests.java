package main.test.logica;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ ControladorOfertaTest.class, ControladorUsuarioTest.class, DataEmpresaTest.class,
		DataPostulanteTest.class, EmpresaTest.class, ManejadorUsuariosTest.class, PostulanteTest.class,
		DataTipoPublicacionTest.class })
public class AllTests {

}
