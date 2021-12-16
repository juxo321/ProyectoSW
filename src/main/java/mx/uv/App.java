package mx.uv;

import spark.*;
import static spark.Spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.gson.*;

import java.util.HashMap;
import java.util.Map;

import javafx.stage.Window;
import mx.uv.db.*;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;


/**
 * Hello world!
 *
 */
public class App 
{
    private static Gson gson = new Gson();
    private static Map<Integer, Examen> examenes = new HashMap<>();
    private static Map<Integer, Pregunta> preguntas = new HashMap<>();
    private static Pregunta pregunta = new Pregunta();
    private static Examen examenActual = new Examen();

    public static void main( String[] args )
    {

        staticFileLocation("/public");

        staticFiles.externalLocation("/ProyectoSW/src/main/resources/public");

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        //Login

        post("/validarUsuario", (req, res) -> {
            // Validamos usuario
            String json = req.body();
            Usuario usuario = gson.fromJson(json, Usuario.class);
            
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            int resultado = usuarioDAO.validarUsuario(usuario);
            System.out.println(resultado);
            return resultado; 
        });

        post("/registrarUsuario", (req, res) -> {
            // Insertamos un nuevo usuario
            String json = req.body();
            System.out.println(json);
            Usuario usuario = gson.fromJson(json, Usuario.class);
            System.out.println(usuario.toString());

            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", usuarioDAO.insertarUsuario(usuario));
            return respuesta;
        });  
        
        //Index usuarios

        get("/maestro", (req, res) -> {
            before((req2, res2) -> res.type("text/html"));
            examenes.clear();
            ExamenDAO examenDAO = new ExamenDAO();
            List<Examen> listaExamenes = examenDAO.listaExamenes();
            System.out.println(listaExamenes.toString());
            for (Examen examen : listaExamenes) examenes.put(examen.getNoExamen(),examen);
            Map<String, Object> map = new HashMap<>();
            map.put("examenes", examenes.values());
            return new VelocityTemplateEngine().render(new ModelAndView(map, "templates/Maestro.vm"));
        });

        get("/estudiante", (req, res) -> {
            before((req2, res2) -> res.type("text/html"));
            examenes.clear();
            ExamenDAO examenDAO = new ExamenDAO();
            List<Examen> listaExamenes = examenDAO.listaExamenes();
            System.out.println(listaExamenes.toString());
            for (Examen examen : listaExamenes) examenes.put(examen.getNoExamen(),examen);
            Map<String, Object> map = new HashMap<>();
            map.put("examenes", examenes.values());
            return new VelocityTemplateEngine().render(new ModelAndView(map, "templates/Estudiante.vm"));
        });

        //CRUD EXAMEN

        post("/crearExamen", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            // Insertamos un nuevo examen
            String json = req.body();
            Examen examen = gson.fromJson(json, Examen.class);
            System.out.println(examen.toString());
            examenActual = examen;

            ExamenDAO examenDAO = new ExamenDAO();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", examenDAO.insertarExamen(examen));
            return respuesta;
    
            //Map<String, Object> map = new HashMap<>();
            //map.put("examenes", examenes.values());
            //return new VelocityTemplateEngine().render(new ModelAndView(map, "templates/crearExamen.vm"));
        });
        
        post("/borrarExamen", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            String json = req.body();
            Examen Examen = gson.fromJson(json, Examen.class);
            ExamenDAO examenDAO = new ExamenDAO();
            
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", examenDAO.borrarExamen(Examen.getNoExamen()));
            return respuesta;
        });

        //CRUD PREGUNTAS

        get("/crearPreguntas", (req, res) -> {
            before((req2, res2) -> res.type("text/html"));
            Map<String, Object> map = new HashMap<>();
            map.put("examenes", examenes.values());
            return new VelocityTemplateEngine().render(new ModelAndView(map, "templates/crearPreguntas.vm"));
        });
        
        post("/agregarPregunta", (req, res) -> {
            String json = req.body();
            System.out.println(json);
            pregunta = gson.fromJson(json, Pregunta.class);
            pregunta.setNoExamen(ultimoExamen());
            //System.out.println(pregunta.toString());
            

            PreguntaDAO preguntaDAO = new PreguntaDAO();
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", preguntaDAO.insertarPregunta(pregunta));
            return respuesta;
        }); 
  
        //Contestar examen

        post("/contestarExamen", (req, res) -> {
            before((req2, res2) -> res.type("application/json"));
            String json = req.body();
            Examen examen = gson.fromJson(json, Examen.class);
            System.out.println(examen.toString());

            PreguntaDAO preguntaDAO = new PreguntaDAO();
            List<Pregunta> listaPreguntasExamen = preguntaDAO.listaPreguntasExamen(examen.getNoExamen());

            for (Pregunta pregunta : listaPreguntasExamen) preguntas.put(pregunta.getNoPregunta(),pregunta);
            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("status", "listo");
            return respuesta;
        });

        get("/formularioExamen", (req, res) -> {
            before((req2, res2) -> res.type("text/html"));
            Map<String, Object> map = new HashMap<>();
            map.put("preguntas", preguntas.values());
            return new VelocityTemplateEngine().render(new ModelAndView(map, "templates/formularioExamen.vm"));
        });

        //crud respuestas

        post("/agregarRespuesta", (req, res) -> {
            String json = req.body();
            System.out.println(json);
            Respuesta respuesta = gson.fromJson(json, Respuesta.class);
            System.out.println(respuesta.toString());
            

            RespuestaDAO respuestaDAO = new RespuestaDAO();
            JsonObject respuesta1 = new JsonObject();
            respuesta1.addProperty("status", respuestaDAO.insertarRespuesta(respuesta));
            return respuesta1;
        }); 
  


        //Upload videos

        get("/", (req, res) ->
                  "<form method='post' enctype='multipart/form-data'>" // note the enctype
                + "    <input type='file' name='videoGrabado' accept='.png'>" // make sure to call getPart using the same "name" in the post
                + "    <button>Upload picture</button>"
                + "</form>"
        );

        post("/video", (req, res) -> {

            File uploadDir = new File("upload");
            uploadDir.mkdir(); 
            
            System.out.println(pregunta.toString());
            System.out.println(pregunta.getTipo());
            if(pregunta.getTipo().equals("Abierta")){
                Path tempFile = Files.createTempFile(uploadDir.toPath(), "video", ".mp4");

                req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
                try (InputStream input = req.raw().getPart("video1").getInputStream()) { // getPart needs to use same "name" as input field in form
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
                }
                File file = tempFile.toFile();
                File fileRenombrado = new File(uploadDir.toPath()+"/"+"videoExamen" + ultimoExamen()+ "Pregunta"+pregunta.getNoPregunta()+ ".mp4");
                file.renameTo(fileRenombrado);
            }else if (pregunta.getTipo().equals("Cerrada")){
                for(int i=1;i<6;i++){
                    Path tempFile = Files.createTempFile(uploadDir.toPath(), "video"+i, ".mp4");
    
                    req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
                    try (InputStream input = req.raw().getPart("video"+i).getInputStream()) { // getPart needs to use same "name" as input field in form
                    Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
                    }
                    if(i==1){
                        File file = tempFile.toFile();
                        File fileRenombrado = new File(uploadDir.toPath()+"/"+"videoExamen" + ultimoExamen()+ "Pregunta"+pregunta.getNoPregunta()+ ".mp4");
                        file.renameTo(fileRenombrado);  
                    }else if(i==2){
                        File file = tempFile.toFile();
                        File fileRenombrado = new File(uploadDir.toPath()+"/"+"videoExamen" + ultimoExamen()+ "Pregunta"+pregunta.getNoPregunta()+"opciona"+".mp4");
                        file.renameTo(fileRenombrado);
                    }else if(i==3){
                        File file = tempFile.toFile();
                        File fileRenombrado = new File(uploadDir.toPath()+"/"+"videoExamen" + ultimoExamen()+ "Pregunta"+pregunta.getNoPregunta()+"opcionb"+".mp4");
                        file.renameTo(fileRenombrado);
                    }else if(i==4){
                        File file = tempFile.toFile();
                        File fileRenombrado = new File(uploadDir.toPath()+"/"+"videoExamen" + ultimoExamen()+ "Pregunta"+pregunta.getNoPregunta()+"opcionc"+".mp4");
                        file.renameTo(fileRenombrado);
                    }else if(i==5){
                        File file = tempFile.toFile();
                        File fileRenombrado = new File(uploadDir.toPath()+"/"+"videoExamen" + ultimoExamen()+ "Pregunta"+pregunta.getNoPregunta()+"opciond"+".mp4");
                        file.renameTo(fileRenombrado);
                    }
                }
            }
            return "Hola";
        });

        post("/videoRespuesta", (req, res) -> {

            File uploadDir = new File("upload");
            uploadDir.mkdir(); 
            
            System.out.println(pregunta.toString());
            System.out.println(pregunta.getTipo());
            Path tempFile = Files.createTempFile(uploadDir.toPath(), "video", ".mp4");

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            try (InputStream input = req.raw().getPart("video1").getInputStream()) { // getPart needs to use same "name" as input field in form
            Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }
            File file = tempFile.toFile();
            File fileRenombrado = new File(uploadDir.toPath()+"/"+"videoExamen" + ultimoExamen()+ "Pregunta"+pregunta.getNoPregunta()+ ".mp4");
            file.renameTo(fileRenombrado);

            return "Hola";
        });

    
    }

    public static int ultimoExamen(){
        ExamenDAO examenDAO = new ExamenDAO();
        return examenDAO.ultimoExamen();
    }


}
