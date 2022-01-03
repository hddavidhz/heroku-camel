
package org.example.pojo;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
@JsonAutoDetect
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Prestamo implements Comparable<Prestamo>{

    @JsonProperty("id_prestamo")
    private int id_prestamo;

    @JsonProperty("cantidad")
    private String cantidad;

    @JsonProperty("interes")
    private int interes;

    @JsonProperty("n_cuotas")
    private Integer n_cuotas;

    @JsonProperty("fecha_inicio")
    private String fecha_inicio;


    @Override
    public int compareTo(Prestamo o) {
        return n_cuotas.compareTo( o.getN_cuotas() );
    }
}
