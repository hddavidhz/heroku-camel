
package org.example.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonAutoDetect
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User {

    @JsonProperty("id_cliente")
    private int id_cliente;

    @JsonProperty("nombre")
    private String nombre;

    @JsonProperty("apellido")
    private String apellido;

    @JsonProperty("telefono")
    private String telefono;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("prestamos")
    private Prestamo[] prestamos;


}
