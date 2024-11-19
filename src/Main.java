import Gestion.GestionPreguntas;
import Modelo.Enum.Categoria;
import Modelo.Juego.Pregunta;
import Modelo.Juego.PreguntaMultipleChoice;
import Modelo.Juego.PreguntaVerdaderoOFalso;
import Modelo.Juego.Ronda;
import Modelo.Usuario.Jugador;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        PreguntaMultipleChoice entretenimientoMC4 = new PreguntaMultipleChoice(
                "En la saga The Legend of Zelda, ¿cómo se llama el reino donde ocurren la mayoría de las aventuras?",
                Categoria.ENTRETENIMIENTO,
                "Hyrule"
        );
        entretenimientoMC4.agregarOpcion("Lorule");
        entretenimientoMC4.agregarOpcion("Hyrule");
        entretenimientoMC4.agregarOpcion("Termina");
        entretenimientoMC4.agregarOpcion("Koholint");

        PreguntaMultipleChoice entretenimientoMC5 = new PreguntaMultipleChoice(
                "Albion Online es...",
                Categoria.ENTRETENIMIENTO,
                "un MMORPG no linel de tipo sandbox en el que eliges tu propio destino sin limitarte a un camino prefijado."
        );
        entretenimientoMC5.agregarOpcion("un libro de estadistica");
        entretenimientoMC5.agregarOpcion("un rpg lineal");
        entretenimientoMC5.agregarOpcion("un MMORPG no linel de tipo sandbox en el que eliges tu propio destino sin limitarte a un camino prefijado.");
        entretenimientoMC5.agregarOpcion("un MOBA");

        PreguntaVerdaderoOFalso entretenimientoVF4 = new PreguntaVerdaderoOFalso(
                "El primer videojuego de la historia fue Pong, desarrollado en 1972.",
                Categoria.ENTRETENIMIENTO,
                "Falso"
        );

        PreguntaVerdaderoOFalso entretenimientoVF5 = new PreguntaVerdaderoOFalso(
                "En los videojuegos, el género \"sandbox\" se caracteriza por ofrecer libertad para explorar y modificar el entorno.",
                Categoria.ENTRETENIMIENTO,
                "Verdadero"
        );

        PreguntaMultipleChoice historiaMC4 = new PreguntaMultipleChoice(
                "Que emperador romano le declaro la guerra al mar?",
                Categoria.HISTORIA,
                "Caligula"
        );
        historiaMC4.agregarOpcion("Julio Cesar");
        historiaMC4.agregarOpcion("Neron Claudio");
        historiaMC4.agregarOpcion("Caligula");
        historiaMC4.agregarOpcion("Constantino");

        PreguntaMultipleChoice historiaMC5 = new PreguntaMultipleChoice(
                "¿Quién fue el líder militar cartaginense que cruzó los Alpes para invadir Italia durante la Segunda Guerra Púnica?",
                Categoria.HISTORIA,
                "Anibal"
        );

        historiaMC5.agregarOpcion("Anibal");
        historiaMC5.agregarOpcion("Escipion el Africano");
        historiaMC5.agregarOpcion("Julio Cesar");
        historiaMC5.agregarOpcion("Cayo Mario");

        PreguntaVerdaderoOFalso historiaVF4 = new PreguntaVerdaderoOFalso(
                "¿La Revolución de Mayo de 1810 fue el primer paso hacia la independencia de Argentina del Imperio Español?",
                Categoria.HISTORIA, "Verdadero"
        );

        PreguntaVerdaderoOFalso historiaVF5 = new PreguntaVerdaderoOFalso(
                "Alejandro Magno nunca fue derrotado en batalla.",
                Categoria.HISTORIA, "Verdadero"
        );

        PreguntaMultipleChoice geografiaMC4 = new PreguntaMultipleChoice(
                "¿Qué océano se encuentra entre África, América y Europa?",
                Categoria.GEOGRAFIA,
                "Oceano Atlantico"
        );

        geografiaMC4.agregarOpcion("Oceano Pacifico");
        geografiaMC4.agregarOpcion("Oceano Atlantico");
        geografiaMC4.agregarOpcion("Oceano Indico");
        geografiaMC4.agregarOpcion("Oceano Artico");

        PreguntaMultipleChoice geografiaMC5 = new PreguntaMultipleChoice(
                "¿Cuál es el país más grande del mundo?",
                Categoria.GEOGRAFIA,
                "Rusia"
        );

        geografiaMC5.agregarOpcion("Argentina");
        geografiaMC5.agregarOpcion("Francia");
        geografiaMC5.agregarOpcion("Rusia");
        geografiaMC5.agregarOpcion("Chile");

        PreguntaVerdaderoOFalso geografiaVF4 = new PreguntaVerdaderoOFalso(
                "Japón está compuesto por más de 6,000 islas.",
                Categoria.GEOGRAFIA,"Verdadero"
        );
        PreguntaVerdaderoOFalso geografiaVF5 = new PreguntaVerdaderoOFalso(
                "La Gran Muralla China se extiende por más de 20,000 kilómetros.",
                Categoria.GEOGRAFIA, "Verdadero"
        );

        PreguntaMultipleChoice cienciaMC1 = new PreguntaMultipleChoice(
                "Quien invento el Foco de Luz?",
                Categoria.CIENCIA,
                "Thomas Edison"
        );

        cienciaMC1.agregarOpcion("Alfred Einstein");
        cienciaMC1.agregarOpcion("Issac Newtoon");
        cienciaMC1.agregarOpcion("Thomas Edison");
        cienciaMC1.agregarOpcion("Marie Curie");

        PreguntaMultipleChoice cienciaMC2 = new PreguntaMultipleChoice(
                "Cual es el Numero del Plomo en la Tabla Periodica?",
                Categoria.CIENCIA,
                "82"
        );
        cienciaMC2.agregarOpcion("75");
        cienciaMC2.agregarOpcion("85");
        cienciaMC2.agregarOpcion("82");
        cienciaMC2.agregarOpcion("80");

        PreguntaMultipleChoice cienciaMC3 = new PreguntaMultipleChoice(
                "Cual es el gas mas abundante de la atmosfera terrestre?",
                Categoria.CIENCIA,
                "Nitrogeno"
        );

        cienciaMC3.agregarOpcion("Oxigeno");
        cienciaMC3.agregarOpcion("Elio");
        cienciaMC3.agregarOpcion("Nitrogeno");
        cienciaMC3.agregarOpcion("Argon");

        PreguntaMultipleChoice cienciaMC4 = new PreguntaMultipleChoice(
                "Que tipo de celula no tiene nucleo?",
                Categoria.CIENCIA,
                "Procariota"
        );

        cienciaMC4.agregarOpcion("Vegetal");
        cienciaMC4.agregarOpcion("Animal");
        cienciaMC4.agregarOpcion("Procariota");
        cienciaMC4.agregarOpcion("Fungica");

        PreguntaMultipleChoice cienciaMC5 = new PreguntaMultipleChoice(
                "Que cientifico propuso la teoria de la relatividad?",
                Categoria.CIENCIA,
                "Albert Einstein"
        );

        cienciaMC5.agregarOpcion("Isaac Newton");
        cienciaMC5.agregarOpcion("Nikola Tesla");
        cienciaMC5.agregarOpcion("Albert Einstein");
        cienciaMC5.agregarOpcion("Galileo Galilei");

        PreguntaVerdaderoOFalso cienciaVF1 = new PreguntaVerdaderoOFalso(
                "La velocidad de la luz en el vacío es aproximadamente 300,000 kilómetros por segundo.",
                Categoria.CIENCIA,
                "Verdadero"
        );

        PreguntaVerdaderoOFalso cienciaVF2 = new PreguntaVerdaderoOFalso(
                "Los humanos tienen un total de 206 huesos en el cuerpo adulto.",
                Categoria.CIENCIA,
                "Verdadero"
        );

        PreguntaVerdaderoOFalso cienciaVF3 = new PreguntaVerdaderoOFalso(
                "El ADN está compuesto por cuatro bases nitrogenadas: adenina, citosina, guanina y uracilo.",
                Categoria.CIENCIA,
                "Falso"
        );

        PreguntaVerdaderoOFalso cienciaVF4 = new PreguntaVerdaderoOFalso(
                "La Tierra es el planeta con mas cantidad de agua en el Sistema Solar.",
                Categoria.CIENCIA,
                "Falso"
        );

        PreguntaVerdaderoOFalso cienciaVF5 = new PreguntaVerdaderoOFalso(
                "El sonido se desplaza mas rapido en el agua que en el aire",
                Categoria.CIENCIA,
                "Verdadero"
        );

        PreguntaMultipleChoice tecnologiaMC1 = new PreguntaMultipleChoice(
                "¿Quién desarrolló el lenguaje de programación C, que se convirtió en la base de muchos otros lenguajes modernos?",
                Categoria.TECNOLOGIA,
                "Dennis Ritchie"
        );

        tecnologiaMC1.agregarOpcion("Alan Turing");
        tecnologiaMC1.agregarOpcion("Dennis Ritchie");
        tecnologiaMC1.agregarOpcion("John McCarthy");
        tecnologiaMC1.agregarOpcion("Ken Thompson");

        PreguntaMultipleChoice tecnologiaMC2 = new PreguntaMultipleChoice(
                "¿Cuál fue el propósito principal de la máquina creada por Ada Lovelace y Charles Babbage, conocida como la Máquina Analítica?",
                Categoria.TECNOLOGIA,
                "Ser un ordenador mecánico programable"
        );

        tecnologiaMC2.agregarOpcion("Resolver ecuaciones diferenciales");
        tecnologiaMC2.agregarOpcion("Generar gráficos matemáticos");
        tecnologiaMC2.agregarOpcion("Ser un ordenador mecánico programable");
        tecnologiaMC2.agregarOpcion("Calcular trayectorias balísticas");

        PreguntaMultipleChoice tecnologiaMC3 = new PreguntaMultipleChoice(
                "En Java, ¿cuál de los siguientes modificadores de acceso permite que una clase o miembro sea accesible únicamente dentro del mismo paquete?",
                Categoria.TECNOLOGIA,
                "Sin modificador (por defecto)"
        );

        tecnologiaMC3.agregarOpcion("public");
        tecnologiaMC3.agregarOpcion("private");
        tecnologiaMC3.agregarOpcion("protected");
        tecnologiaMC3.agregarOpcion("Sin modificar (por defecto)");

        PreguntaMultipleChoice tecnologiaMC4 = new PreguntaMultipleChoice(
                "En programación concurrente, ¿qué significa una condición de carrera (race condition)?",
                Categoria.TECNOLOGIA,
                "Un error que ocurre cuando dos o más hilos compiten por acceso al mismo recurso compartido."
        );

        tecnologiaMC4.agregarOpcion("Un error que ocurre cuando dos o más hilos compiten por acceso al mismo recurso compartido.");
        tecnologiaMC4.agregarOpcion("Un proceso que finaliza más rápido de lo esperado.");
        tecnologiaMC4.agregarOpcion("Un algoritmo que utiliza múltiples hilos para mejorar el rendimiento.");
        tecnologiaMC4.agregarOpcion("Un fallo que ocurre por un límite de memoria excedido.");


        PreguntaMultipleChoice tecnologiaMC5 = new PreguntaMultipleChoice(
                "¿Qué significa \"POO\" en programación?",
                Categoria.TECNOLOGIA,
                "Programacion Orientada a Objetos"
        );

        tecnologiaMC5.agregarOpcion("Politica Opcional Optativa");
        tecnologiaMC5.agregarOpcion("Programacion Orientada a Objetos");
        tecnologiaMC5.agregarOpcion("Programacion Orientada a Ovnis");
        tecnologiaMC5.agregarOpcion("Programacion Orientada a Obstaculos");

        PreguntaVerdaderoOFalso tecnologiaVF1 = new PreguntaVerdaderoOFalso(
                "El primer lenguaje de programación fue Fortran, creado en 1957.",
                Categoria.TECNOLOGIA,
                "Verdadero"
        );

        PreguntaVerdaderoOFalso tecnologiaVF2 = new PreguntaVerdaderoOFalso(
                "El creador de Linux, Linus Torvalds, desarrolló el sistema operativo mientras trabajaba en Microsoft.",
                Categoria.TECNOLOGIA,
                "Falso"
        );

        PreguntaVerdaderoOFalso tecnologiaVF3 = new PreguntaVerdaderoOFalso(
                "El término 'bug' para referirse a un error en programación proviene de un insecto real encontrado en una computadora.",
                Categoria.TECNOLOGIA,
                "Verdadero"
        );

        PreguntaVerdaderoOFalso tecnologiaVF4 = new PreguntaVerdaderoOFalso(
                "HTML es un lenguaje de programación completo que permite realizar cálculos matemáticos avanzados.",
                Categoria.TECNOLOGIA,
                "Falso"
        );

        PreguntaVerdaderoOFalso tecnologiaVF5 = new PreguntaVerdaderoOFalso(
                "La Ley de Moore predice que la cantidad de transistores en un microchip se duplica aproximadamente cada dos años.",
                Categoria.TECNOLOGIA,
                "Verdadero"
        );

        PreguntaMultipleChoice deporteMC1 = new PreguntaMultipleChoice(
                "Cuál es considerada la Copa mas antígua del Fútbol?",
                Categoria.DEPORTE,
                "FA-Cup (Football Association Challenge)"
        );

        deporteMC1.agregarOpcion("Copa Mundial de la FIFA");
        deporteMC1.agregarOpcion("Copa America");
        deporteMC1.agregarOpcion("FA-Cup (Football Association Challenge)");
        deporteMC1.agregarOpcion("Copa MercoSur");



        PreguntaMultipleChoice deporteMC2 = new PreguntaMultipleChoice(
                "¿Cuál es el torneo de tenis más antiguo del mundo?",
                Categoria.DEPORTE,
                "Wimbledon"
        );
        deporteMC2.agregarOpcion("Roland Garros");
        deporteMC2.agregarOpcion("Wimbledon");
        deporteMC2.agregarOpcion("US Open");
        deporteMC2.agregarOpcion("Australian Open");

        PreguntaMultipleChoice deporteMC3 = new PreguntaMultipleChoice(
                "¿En qué deporte se utiliza una red alta y un volante (shuttlecock)?",
                Categoria.DEPORTE,
                "Bádminton"
        );
        deporteMC3.agregarOpcion("Bádminton");
        deporteMC3.agregarOpcion("Voleibol");
        deporteMC3.agregarOpcion("Tenis de mesa");
        deporteMC3.agregarOpcion("Cricket");


        PreguntaMultipleChoice deporteMC4 = new PreguntaMultipleChoice(
                "¿Cuántos puntos vale un tiro libre en baloncesto?",
                Categoria.DEPORTE,
                "1"
        );
        deporteMC4.agregarOpcion("1");
        deporteMC4.agregarOpcion("2");
        deporteMC4.agregarOpcion("3");
        deporteMC4.agregarOpcion("4");


        PreguntaMultipleChoice deporteMC5 = new PreguntaMultipleChoice(
                "¿En qué año se celebraron los primeros Juegos Olímpicos modernos?",
                Categoria.DEPORTE,
                "1896"
        );
        deporteMC5.agregarOpcion("1892");
        deporteMC5.agregarOpcion("1896");
        deporteMC5.agregarOpcion("1900");
        deporteMC5.agregarOpcion("1904");

        PreguntaVerdaderoOFalso deporteVF1 = new PreguntaVerdaderoOFalso(
                "Valtteri Bottas tiene el récord de la mejor salida en la historia de la Fórmula 1, con una reacción de 0.041 segundos en el Gran Premio de Alemania 2019.",
                Categoria.DEPORTE,
                "Verdadero"
        );

        PreguntaVerdaderoOFalso deporteVF2 = new PreguntaVerdaderoOFalso(
                "El único deporte que ha sido parte de todas las ediciones de los Juegos Olímpicos modernos es el boxeo.",
                Categoria.DEPORTE,
                "Falso"
        );

        PreguntaVerdaderoOFalso deporteVF3 = new PreguntaVerdaderoOFalso(
                "La Copa del Mundo de la FIFA se celebró por primera vez en 1930 en Uruguay.",
                Categoria.DEPORTE,
                "Verdadero"
        );

        PreguntaVerdaderoOFalso deporteVF4 = new PreguntaVerdaderoOFalso(
                "La natación sincronizada fue un deporte olímpico por primera vez en los Juegos Olímpicos de Londres 1908.",
                Categoria.DEPORTE,
                "Falso"
        );

        PreguntaVerdaderoOFalso deporteVF5 = new PreguntaVerdaderoOFalso(
                "El tenis de mesa es un deporte olímpico.",
                Categoria.DEPORTE,
                "Verdadero"
        );

    }
}