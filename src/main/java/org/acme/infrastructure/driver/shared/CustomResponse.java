package org.acme.infrastructure.driver.shared;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.Response;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CustomResponse{
    private static final Logger LOGGER = Logger.getLogger(CustomResponse.class.getName());
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //! Singleton para ObjectMapper para evitar la creacion repetida.
    private CustomResponse() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Construye una respuesta de éxito con validación de entrada
     *
     * @param entity  Entidad a serializar
     * @param message Mensaje descriptivo
     * @return Respuesta HTTP
     */

    public static Response buildSuccessResponse(Object entity, String message) {
        try{
           //! Validacion de parametros de entrada.
            validateInputs(entity, message);

            CustomPresenter presenter = createPresenter(
                    Response.Status.CREATED,
                    message,
                    sanitizeEntity(entity)
            );

            return Response
                    .status(Response.Status.CREATED)
                    .entity(presenter)
                    .build();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al construir respuesta de exito", e);
            return buildErrorResponse(
              Response.Status.INTERNAL_SERVER_ERROR,
                    "Error interno al procesar la solicitud"
            );
        }
    }

    /**
     * Validación de parámetros de entrada
     */
    private static void validateInputs(Object entity, String message) {
        if(message == null || message.trim().isEmpty()){
            throw new IllegalArgumentException("El mensaje no puede ser nulo o vacio");
        }
    }
    /**
     * Construye una respuesta de error con validación
     *
     * @param status  Estado HTTP
     * @param message Mensaje de error
     * @return Respuesta HTTP de error
     */

    public static Response buildErrorResponse(Response.Status status, String message) {
        //? validacion de parametros.
        status = Optional.ofNullable(status)
                .orElse(Response.Status.INTERNAL_SERVER_ERROR);

        message = Optional.ofNullable(message)
                .orElse("Error desconocido");

        CustomPresenter presenter = CustomPresenter.init();
        presenter.setReason(status.getReasonPhrase());
        presenter.setStatusCode(status.getStatusCode());
        presenter.setMessage(sanitizeMessage(message));

        return Response
                .status(status)
                .entity(presenter)
                .build();
    }


    /**
     * Crea un presentador con validaciones de seguridad
     */

    private static CustomPresenter createPresenter(
            Response.Status status,
            String message,
            Object entity
    ) throws JsonProcessingException {
        CustomPresenter presenter = CustomPresenter.init();

        presenter.setReason(status.getReasonPhrase());
        presenter.setStatusCode(status.getStatusCode());
        presenter.setMessage(sanitizeMessage(message));

        //! Serializacion segura con manejo de excepciones.
        if(entity != null){
            try {
                presenter.setData(MAPPER.writeValueAsString(entity));
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Error al serializar entidad", e);
                presenter.setData("{}"); //? Valor por defecto seguro.
            }
        }
        return presenter;
    }

    /**
     * Sanitización de mensaje para prevenir inyección
     */
    private static String sanitizeMessage(String message){
        return message == null ? "" :
                message.replaceAll("[<>&'\"]", "")
                        .trim();
    }

    /**
     * Sanitización de entidad para prevenir exposición de datos sensibles
     */
    private static Object sanitizeEntity(Object object){
        // Implementar lógica de sanitización según el tipo de entidad
        return object;
    }
}
