package main.java.presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import main.java.logica.datatypes.DataEmpresa;
import main.java.logica.datatypes.DataOfertaLaboral;
import main.java.logica.datatypes.DataPostulacion;
import main.java.logica.datatypes.DataPostulante;
import main.java.logica.datatypes.DataTipoPublicacion;
import main.java.logica.enums.StatusOfertaLaboral;
import main.java.logica.excepciones.NoExisteEmpresa;
import main.java.logica.excepciones.NoExisteKeyword;
import main.java.logica.excepciones.NoExisteOferta;
import main.java.logica.excepciones.NoExistePaquete;
import main.java.logica.excepciones.NoExistePostulante;
import main.java.logica.excepciones.NoExisteTipoPaquete;
import main.java.logica.excepciones.NoExisteTipoPublicacion;
import main.java.logica.excepciones.NoSuficienteTipoPubliEnPaquete;
import main.java.logica.excepciones.OfertaLaboralNoVigente;
import main.java.logica.excepciones.RemuneracionNegativa;
import main.java.logica.excepciones.YaExisteKeyword;
import main.java.logica.excepciones.YaExisteOferta;
import main.java.logica.excepciones.YaExistePaquete;
import main.java.logica.excepciones.YaExistePostulacion;
import main.java.logica.excepciones.YaExisteTipoEnPaquete;
import main.java.logica.excepciones.YaExisteTipoPublicacion;
import main.java.logica.excepciones.YaExisteUsuario;
import main.java.logica.interfaces.Fabrica;
import main.java.logica.interfaces.IOferta;
import main.java.logica.interfaces.IUsuario;

public class IngresarDatosAutomatico implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent event) {
    IUsuario iUsuario = Fabrica.getInstance().getIusuario();
    IOferta iOferta = Fabrica.getInstance().getIOferta();

    DataPostulante usu1 = new DataPostulante("lgarcia", "Lucía", "García", "lgarcia85@gmail.com",
        LocalDate.parse("1985-03-15"), "Uruguaya", "U1.jpg");
    DataPostulante usu2 = new DataPostulante("matilo", "Matías", "López",
        "matias.lopez90@hotmail.com", LocalDate.parse("1990-08-21"), "Argentina", "U2.jpg");
    DataPostulante usu3 = new DataPostulante("maro", "María", "Rodríguez", "marrod@gmail.com",
        LocalDate.parse("1988-11-10"), "Uruguaya", "U3.jpg");
    DataPostulante usu4 = new DataPostulante("javierf", "Javier", "Fernández",
        "javierf93@yahoo.com", LocalDate.parse("1993-06-05"), "Mexicana", "U4.jpg");
    DataPostulante usu5 = new DataPostulante("valen25", "Valentina", "Martínez", "vale87@gmail.com",
        LocalDate.parse("1987-02-25"), "Uruguaya", "U5.jpg");
    DataPostulante usu6 = new DataPostulante("andpe12", "Andrés", "Pérez", "anpe92@hotmail.com",
        LocalDate.parse("1992-04-12"), "Chilena", "U6");
    DataPostulante usu7 = new DataPostulante("sicam", "Camila", "Silva", "camilasilva89@gmail.com",
        LocalDate.parse("1989-09-30"), "Uruguaya", "U7.jpg");
    DataPostulante usu8 = new DataPostulante("sebgon", "Sebastián", "González", "gonza95@yahoo.com",
        LocalDate.parse("1995-01-18"), "Colombiana", "U8.jpg");
    DataPostulante usu9 = new DataPostulante("isabel", "Isabella", "López", "loisa@gmail.com",
        LocalDate.parse("1991-07-07"), "Uruguaya", "U9.jpg");
    DataPostulante usu10 = new DataPostulante("marram02", "Martín", "Ramírez", "marram@hotmail.com",
        LocalDate.parse("1986-12-02"), "Argentina", "U10.jpg");

    DataEmpresa usu11 = new DataEmpresa("EcoTech", "Sophia", "Johnson", "info@EcoTech.com",
        "EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles."
            + " Nuestro enfoque se centra en desarrollar y comercializar productos y servicios"
            + " que aborden los desafíos ambientales más apremiantes de nuestro tiempo."
            + " Desde sistemas de energía renovable y dispositivos de monitorización ambiental"
            + " hasta soluciones de gestión de residuos inteligentes, nuestra misión es"
            + " proporcionar herramientas que permitan a las empresas y comunidades adoptar"
            + " prácticas más ecológicas sin comprometer la eficiencia. Creemos en la"
            + " convergencia armoniosa entre la tecnología y la naturaleza, y trabajamos"
            + " incansablemente para impulsar un futuro más limpio y sostenible",
        "http://www.EcoTechInnovations.com", "U11");
    DataEmpresa usu12 = new DataEmpresa("FusionTech", "William", "Smith", "contacto@FusionTech.net",
        "FusionTech Dynamics es una empresa pionera en el ámbito de la inteligencia artificial y"
            + " la automatización avanzada. Nuestro eqiupo multidisciplinario de ingenieros,"
            + " científicos de datos y desarrolladores crea soluciones innovadoras que aprovechan"
            + " la potencia de la IA para transformar industrias. Desde la optimización de procesos"
            + " industriales hasta la creación de asistentes virtuales altamente personalizados,"
            + " nuestro objetivo es revolucionar la forma en que las empresas operan y se"
            + " conectan con sus clientes. Creemos en la sinergia entre la mente humana y las"
            + " capacidades de la IA, y trabajamos para constriur un mundo donde la tecnología"
            + " mejore y amplíe nuestras capacidades innatas.",
        "http://www.FusionTechDynamics.net", "U12.jpg");
    DataEmpresa usu13 = new DataEmpresa("GlobalHealth", "Isabella", "Brown", "jobs@GlobalHealth.uy",
        "GlobalHealth Dynamics es una empresa comprometida con el avance de la atenció"
            + " médica a nivel mundial. Como líderes en el campo de la salud digital, desarrollamos"
            + " plataformas y herramientas que permiten a los profesionales de la salud"
            + " ofrecer diagnósticos más precisos, tratamientos personalizados y"
            + " segiumiento continuo de los pacientes. Nuestra visión es crear un ecosistema"
            + " de salud conectado en el que los datos médicos se utilicen de manera ética y"
            + " segura para mejorar la calidad de vida de las personas. A través de la"
            + " innovación constante y la colaboración con expertos médicos, estamos dando"
            + " forma al futuro de la atención médica, donde la tecnología y la compasión"
            + " se unen para salvar vidas y mejorar el bienestar en todo el mundo.",
        "http://www.GlobalHealthDynamics.uy/info", "U13.jpg");
    DataEmpresa usu14 = new DataEmpresa("ANTEL", "Washington", "Rocha", "jarrington@ANTEL.com.uy",
        "En Antel te brindamos servicios de vanguardia en tecnología de comunicación en"
            + " Telefonía Móvil, Fija, Banda Ancha y Datos",
        "ANTEL.com.uy", "U14.jpg");
    DataEmpresa usu15 = new DataEmpresa("MIEM", "Pablo", "Bengoechea", "eldiez@MIEM.org.uy",
        "Balance Energético Nacional (BEN). La Dirección Nacional de Energía (DNE) del"
            + " Ministerio de Industria, Energía y Minería (MIEM) presenta anualmente el BEN.;",
        "MIEM.com.uy", "U15.jpg");
    DataEmpresa usu16 =
        new DataEmpresa("TechSolutions", "Mercedes", "Venn", "Mercedes@TechSolutions.com.uy",
            "\"TechSolutions Inc.\" es una empresa líder en el sector de tecnología de la"
                + " información y el software. Se especializa en el desarrollo de"
                + " soluciones de software personalizadas para empresas de diversos"
                + " tamaños y sectores. Su enfoque se centra en la creación de aplicaciones"
                + " empresariales innovadoras que optimizan procesos, mejoran la eficiencia"
                + " y brindan una ventaja competitiva a sus clientes.",
            "TechSolutions.com", "U16.jpg");

    // Tipos de publicacion
    DataTipoPublicacion tp1 = new DataTipoPublicacion("Premium", "Obtén máxima visibilidad", 1, 30,
        4000, LocalDate.of(2023, 8, 10));
    DataTipoPublicacion tp2 = new DataTipoPublicacion("Destacada", "Destaca tu anuncio", 2, 15, 500,
        LocalDate.of(2023, 8, 5));
    DataTipoPublicacion tp3 = new DataTipoPublicacion("Estándar",
        "Mejora la posición de tu anuncio", 3, 20, 150, LocalDate.of(2023, 8, 15));
    DataTipoPublicacion tp4 = new DataTipoPublicacion("Básica",
        "Publica de forma sencilla en la lista de ofertas", 4, 7, 50, LocalDate.of(2023, 8, 7));

    // Keywords
    String keyword1 = "Tiempo completo";
    String keyword2 = "Medio tiempo";
    String keyword3 = "Remoto";
    String keyword4 = "Freelance";
    String keyword5 = "Temporal";
    String keyword6 = "Permanente";
    String keyword7 = "Computación";
    String keyword8 = "Administración";
    String keyword9 = "Logísitica";
    String keyword10 = "Contabilidad";
    
    /*DataTipoPublicacionPaquete dtpp1 = new DataTipoPublicacionPaquete(1, tp1);
    DataTipoPublicacionPaquete dtpp2 = new DataTipoPublicacionPaquete(1, tp2);
    DataTipoPublicacionPaquete dtpp3 = new DataTipoPublicacionPaquete(1, tp3);
    DataTipoPublicacionPaquete dtpp4 = new DataTipoPublicacionPaquete(1, tp4);
    DataTipoPublicacionPaquete dtpp5 = new DataTipoPublicacionPaquete(2, tp1);
    DataTipoPublicacionPaquete dtpp6 = new DataTipoPublicacionPaquete(2, tp3);
    DataTipoPublicacionPaquete dtpp7 = new DataTipoPublicacionPaquete(2, tp2);
    
    
    DataPaquete dp1 = new DataPaquete("Básico ", "Publica ofertas laborales en nuestra plataforma por un período de 30 días", 30, 20, LocalDate.of(2023, 8, 16), new HashSet<>(Arrays.asList(dtpp1, dtpp2, dtpp3)));
    DataPaquete dp2 = new DataPaquete("Destacado",
            "Publica ofertas laborales destacadas que se mostrará en la parte superior de los resultados de búsqueda por 45 días",
            45, 
            10, 
            LocalDate.of(2023, 8, 15), 
            new HashSet<DataTipoPublicacionPaquete>());
    DataPaquete dp3 = new DataPaquete("Premium",
            "Publica ofertas laborales premium que incluye promoción en nuestras redes sociales y listado en la sección destacada por 60 días",
            60, 
            15, 
            LocalDate.of(2023, 8, 14), 
            new HashSet<DataTipoPublicacionPaquete>());

	DataPaquete dp4 = new DataPaquete("Express",
	            "Publica ofertas laborales urgentes resaltada en color y se mostrará en la sección de urgente por 15 días.",
	            15, 
	            5, 
	            LocalDate.of(2023, 10, 1), 
	            new HashSet<DataTipoPublicacionPaquete>());*/
    
    
    // Ofertas Laborales
    DataOfertaLaboral oferta1 = new DataOfertaLaboral("Desarrollador Frontend",
        "Únete a nuestro equipo de desarrollo frontend y crea experiencias"
            + " de usuario excepcionales.",
        90000, "09:00 - 18:00", "Montevideo", "Montevideo", LocalDate.of(2023, 8, 14), tp1,
        new HashSet<>(Arrays.asList(keyword1, keyword2, keyword3, keyword4, keyword5, keyword6)),
        StatusOfertaLaboral.Confirmada, "https://blfw.short.gy/dCL8Rs\r\n", usu11, "Básico");

    DataOfertaLaboral oferta2 = new DataOfertaLaboral("Estratega de Negocios",
        "Forma parte de nuestro equipo de estrategia y contribuye al"
            + " crecimiento de las empresas clientes.",
        80000, "08:00 - 17:00", "Maldonado", "Punta del Este", LocalDate.of(2023, 8, 14), tp3,
        new HashSet<>(Arrays.asList(keyword5)), StatusOfertaLaboral.Confirmada, "", usu13, null);

    DataOfertaLaboral oferta3 = new DataOfertaLaboral("Diseñador UX/UI",
        "Trabaja en colaboración con nuestro talentoso equipo de diseño"
            + " para crear soluciones impactantes.",
        65000, "14:00 - 18:00", "Colonia", "Rosario", LocalDate.of(2023, 8, 13), tp3,
        new HashSet<>(Arrays.asList(keyword2, keyword3, keyword6)), StatusOfertaLaboral.Confirmada,
        "", usu14, null);

    DataOfertaLaboral oferta4 = new DataOfertaLaboral("Analista de Datos",
        "Ayuda a nuestros clientes a tomar decisiones informadas basadas"
            + " en análisis y visualizaciones de datos.",
        40000, "09:00 - 13:00", "Maldonado", "Maldonado", LocalDate.of(2023, 8, 11), tp1,
        new HashSet<>(Arrays.asList(keyword2)), StatusOfertaLaboral.Ingresada, "", usu11, null);

    DataOfertaLaboral oferta5 = new DataOfertaLaboral("Content Manager",
        "Gestiona y crea contenido persuasivo y relevante para impulsar"
            + " la presencia en línea de nuestros clientes.",
        10000, "18:00 - 22:00", "Montevideo", "Montevideo", LocalDate.of(2023, 8, 20), tp2,
        new HashSet<>(Arrays.asList(keyword4)), StatusOfertaLaboral.Ingresada, "", usu15, null);

    DataOfertaLaboral oferta6 = new DataOfertaLaboral("Soporte Técnico",
        "Ofrece un excelente servicio de soporte técnico a nuestros clientes,"
            + " resolviendo problemas y brindando soluciones.",
        30000, "09:00 - 18:00", "Lavalleja", "Minas", LocalDate.of(2023, 8, 23), tp4,
        new HashSet<>(Arrays.asList(keyword1)), StatusOfertaLaboral.Confirmada, "", usu11, "Destacado");

    DataOfertaLaboral oferta7 = new DataOfertaLaboral("A. de Marketing Digital",
        "Únete a nuestro equipo de marketing y trabaja en estrategias digitales innovadoras.",
        80000, "10:00 - 19:00", "Flores", "Flores", LocalDate.of(2023, 8, 15), tp1, new HashSet<>(),
        StatusOfertaLaboral.Confirmada, "", usu11, null);

    DataOfertaLaboral oferta8 = new DataOfertaLaboral("Contador Senior",
        "Únete a nuestro equipo contable y ayuda en la gestión financiera de la empresa.", 100000,
        "08:30 - 17:30", "Colonia", "Colonia Suiza", LocalDate.of(2023, 8, 16), tp2,
        new HashSet<>(), StatusOfertaLaboral.Rechazada, "", usu13, null);

    DataOfertaLaboral oferta9 = new DataOfertaLaboral("Tecnico/a Basico Red",
        "REGIMEN DE CONTRATO EN FUNCION PUBLICA EN UN TODO DE ACUERDO A LA"
            + "NORMATIVA VIGENTE (LEY 16.127, DE 7 DE AGOSTO DE 1990, ART. 1°, LITERAL A) Y B) "
            + "CON LA MODIFICACI ´ON INTRODUCIDA POR EL ART. 11 DE LA LEY 17.930 DE 19 DE DICIEM"
            + "BRE DE 2005)",
        40000, "09:00 - 17:00", "Paysandu", "Paysandu", LocalDate.of(2023, 9, 29), tp1,
        new HashSet<>(Arrays.asList(keyword5)), StatusOfertaLaboral.Confirmada, "", usu14, null);

    DataOfertaLaboral oferta10 = new DataOfertaLaboral("Desarrollador de Software " + "Senior",
        "Unete a nuestro equipo y lidera proyectos de desar"
            + "rollo de software sostenible y ecologico. Impulsa la"
            + "innovacion y contribuye a un futuro mas verde.",
        123000, "09:00 - 16:00", "Montevideo", "Montevideo", LocalDate.of(2023, 10, 2), tp2,
        new HashSet<>(Arrays.asList(keyword1, keyword6, keyword9)), StatusOfertaLaboral.Ingresada,
        "", usu11, "Básico");

    DataOfertaLaboral oferta11 =
        new DataOfertaLaboral("Desarrollador de Software Full Stack Senior",
            "Unete a nuestro equipo para crear soluciones de soft"
                + "ware personalizadas de extremo a extremo. Colabora "
                + "en proyectos emocionantes y desafiantes.",
            135000, "04:00 - 13:00", "Rio Negro", "Fray Bentos", LocalDate.of(2023, 9, 25), tp1,
            new HashSet<>(Arrays.asList(keyword3)), StatusOfertaLaboral.Ingresada, "", usu16, null);

    DataOfertaLaboral oferta12 = new DataOfertaLaboral("Gerente de Proyecto",
        "Unete a nuestro equipo de gesti´on de proyectos y lid"
            + "era la entrega exitosa de soluciones de software per"
            + "sonalizadas. Colabora con equipos multidisciplinarios y clientes exigentes.",
        230000, "04:00 - 12:00", "Montevideo", "Montevideo", LocalDate.of(2023, 10, 2), tp2,
        new HashSet<>(Arrays.asList(keyword3, keyword6)), StatusOfertaLaboral.Confirmada, "",
        usu16, null);

    DataOfertaLaboral oferta13 = new DataOfertaLaboral("Ingeniero de Calidad de Software",
        "Asegura la calidad de nuestros productos de software "
            + "sostenibles. Unete a nosotros para garantizar un im"
            + "pacto positivo en el medio ambiente",
        60000, "14:00 - 18:00", "Montevideo", "Montevideo", LocalDate.of(2023, 10, 1), tp1,
        new HashSet<>(Arrays.asList(keyword1, keyword10)), StatusOfertaLaboral.Ingresada, "",
        usu11, null);

    // Postulaciones
    DataPostulacion pos1 = new DataPostulacion(LocalDate.of(2023, 8, 16),
        "Licenciada en Administración, experiencia en gestión de equipos y proyectos."
            + " Conocimientos en Microsoft Office.",
        "Estoy emocionada por la oportunidad de formar parte de un equipo dinámico y"
            + " contribuir con mis habilidades de liderazgo.",
        oferta1, usu1);
    DataPostulacion pos2 = new DataPostulacion(LocalDate.of(2023, 8, 23),
        "Estudiante de Comunicación, habilidades en redacción y manejo de redes sociales."
            + " Experiencia en prácticas en medios locales.",
        "Me encantaría formar parte de un equipo que me permita desarrollar mis habilidades"
            + " en comunicación y marketing.",
        oferta2, usu2);
    DataPostulacion pos3 = new DataPostulacion(LocalDate.of(2023, 8, 23),
        "Ingeniero en Sistemas, experiencia en desarrollo web y aplicaciones móviles."
            + " Conocimientos en JavaScript y React.",
        "Me entusiasma la posibilidad de trabajar en proyectos desafiantes y seguir"
            + " creciendo como profesional en el campo de la tecnología.",
        oferta1, usu3);
    DataPostulacion pos4 = new DataPostulacion(LocalDate.of(2023, 8, 23),
        "Técnico en Electricidad, experiencia en mantenimiento industrial."
            + " Conocimientos en lectura de planos eléctricos.",
        "Estoy interesado en formar parte de un equipo que me permita aplicar mis habilidades"
            + " técnicas y contribuir al mantenimiento eficiente.",
        oferta3, usu4);
    DataPostulacion pos5 = new DataPostulacion(LocalDate.of(2023, 8, 23),
        "Músico profesional, experiencia en espectáculos en vivo. Habilidades en canto y guitarra.",
        "Me gustaría combinar mi pasión por la música con una oportunidad laboral que me"
            + " permita seguir creciendo como artista.",
        oferta2, usu5);
    DataPostulacion pos6 = new DataPostulacion(LocalDate.of(2023, 8, 16),
        "Licenciada en Administración, me considero genia, experiencia en gestión"
            + " de equipos y proyectos." + " Conocimientos en Microsoft Office.",
        "Estoy emocionada por la oportunidad de formar parte de un equipo dinámico"
            + " y contribuir con mis habilidades de liderazgo.",
        oferta2, usu1);

    try {
      iUsuario.alta(usu1, "awdrg543");
      iUsuario.alta(usu2, "edrft543");
      iUsuario.alta(usu3, "r5t6y7u8");
      iUsuario.alta(usu4, "45idgaf67");
      iUsuario.alta(usu5, "poiuy987");
      iUsuario.alta(usu6, "xdrgb657");
      iUsuario.alta(usu7, "mnjkiu89");
      iUsuario.alta(usu8, "ytrewq10");
      iUsuario.alta(usu9, "sbsplol1");
      iUsuario.alta(usu10, "okmnji98");
      iUsuario.alta(usu11, "qsxcdw43");
      iUsuario.alta(usu12, "qpwoei586");
      iUsuario.alta(usu13, "asdfg654");
      iUsuario.alta(usu14, "2nru096");
      iUsuario.alta(usu15, "ibii4xo");
      iUsuario.alta(usu16, "1ngs03p");

      iOferta.nomKeyword(keyword1);
      iOferta.nomKeyword(keyword2);
      iOferta.nomKeyword(keyword3);
      iOferta.nomKeyword(keyword4);
      iOferta.nomKeyword(keyword5);
      iOferta.nomKeyword(keyword6);
      iOferta.nomKeyword(keyword7);
      iOferta.nomKeyword(keyword8);
      iOferta.nomKeyword(keyword9);
      iOferta.nomKeyword(keyword10);

      iOferta.insertarTipoPublicacion(tp1);
      iOferta.insertarTipoPublicacion(tp2);
      iOferta.insertarTipoPublicacion(tp3);
      iOferta.insertarTipoPublicacion(tp4);
      
      iOferta.insertarDatosPaquete("Básico",
				"Publica ofertas laborales en nuestra plataforma por un período de 30 días", 30, (float) 20,
				LocalDate.of(2023, 10, 8), "paq1.jpg");
		iOferta.insertarDatosPaquete("Destacado",
				"Publica ofertas laborales destacadas que se mostrará en la parte superior de los resultados de búsqueda por 45 días",
				45, (float) 10, LocalDate.of(2023, 8, 15), "paq2.jpg");
		iOferta.insertarDatosPaquete("Premium",
				"Publica ofertas laborales premium que incluye promoción en nuestras redes sociales y listado en la sección destacada por 60 días",
				60, (float) 15, LocalDate.of(2023, 8, 14), "paq3.jpg");
		iOferta.insertarDatosPaquete("Express",
				"Publica ofertas laborales urgentes resaltada en color y se mostrará en la sección de urgente por 15 días.",
				15, (float) 5, LocalDate.of(2023, 10, 1), "paq4.jpg");
		
		iOferta.agregarTipo("Básico", "Premium", 1);
        iOferta.agregarTipo("Básico", "Destacada", 1);
        iOferta.agregarTipo("Básico", "Estándar", 1);
        iOferta.agregarTipo("Destacado", "Premium", 2);
        iOferta.agregarTipo("Destacado", "Básica", 1);
        iOferta.agregarTipo("Premium", "Premium", 2);
        iOferta.agregarTipo("Premium", "Estándar", 2);
        iOferta.agregarTipo("Express", "Destacada", 2);
        
        iUsuario.agregarPaquete("Básico", "EcoTech");
        iUsuario.agregarPaquete("Destacado", "TechSolutions");
        iUsuario.agregarPaquete("Premium", "EcoTech");
        iUsuario.agregarPaquete("Destacado", "FusionTech");
        iUsuario.agregarPaquete("Express", "EcoTech");

      try {
    	//iOferta.agregarTipo(nomPaquete, tipoPaquete, cant);
          
    	  
        iOferta.insertarOfertaLaboral(oferta1, usu11);
        iOferta.insertarOfertaLaboral(oferta2, usu13);
        iOferta.insertarOfertaLaboral(oferta3, usu12);
        iOferta.insertarOfertaLaboral(oferta4, usu14);
        iOferta.insertarOfertaLaboral(oferta5, usu15);
        iOferta.insertarOfertaLaboral(oferta6, usu16);
        iOferta.insertarOfertaLaboral(oferta7, usu11);
        iOferta.insertarOfertaLaboral(oferta8, usu13);
        iOferta.insertarOfertaLaboral(oferta9, usu14);
        iOferta.insertarOfertaLaboral(oferta10, usu11);
        iOferta.insertarOfertaLaboral(oferta11, usu16);
        iOferta.insertarOfertaLaboral(oferta12, usu16);
        iOferta.insertarOfertaLaboral(oferta13, usu11);
      } catch (RemuneracionNegativa e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      } catch (NoExistePaquete e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuficienteTipoPubliEnPaquete e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      

      iOferta.postulacionOfertaLaboral(oferta1.getNombre(), usu1.getNickname(), pos1.getcV(),
          pos2.getMotivacion(), pos2.getFecPost());
      iOferta.postulacionOfertaLaboral(oferta2.getNombre(), usu2.getNickname(), pos2.getcV(),
          pos3.getMotivacion(), pos3.getFecPost());
      iOferta.postulacionOfertaLaboral(oferta1.getNombre(), usu3.getNickname(), pos3.getcV(),
          pos4.getMotivacion(), pos4.getFecPost());
      iOferta.postulacionOfertaLaboral(oferta3.getNombre(), usu4.getNickname(), pos4.getcV(),
          pos5.getMotivacion(), pos5.getFecPost());
      iOferta.postulacionOfertaLaboral(oferta2.getNombre(), usu5.getNickname(), pos5.getcV(),
          pos6.getMotivacion(), pos6.getFecPost());
      iOferta.postulacionOfertaLaboral(oferta2.getNombre(), usu1.getNickname(), pos6.getcV(),
          pos6.getMotivacion(), pos6.getFecPost());

    } catch (YaExisteOferta | NoExisteTipoPublicacion | NoExisteOferta | NoExistePostulante
        | YaExistePostulacion | YaExisteKeyword | NoExisteKeyword | YaExisteUsuario
        | NoExisteEmpresa | YaExisteTipoPublicacion | OfertaLaboralNoVigente e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    } catch (YaExistePaquete e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (YaExisteTipoEnPaquete e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoExisteTipoPaquete e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoExistePaquete e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
  }
}
