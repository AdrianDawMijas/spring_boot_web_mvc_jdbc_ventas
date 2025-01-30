package org.iesvdm.modelo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//La anotación @Data de lombok proporcionará el código de:
//getters/setters, toString, equals y hashCode
//propio de los objetos POJOS o tipo Beans
@Data
//Para generar un constructor con lombok con todos los args
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	
	private long id;

	@NotBlank(message = "{error.nombre}")
	@Size(max = 30, message = "{error.longitud.max}")
	private String nombre;

	@NotBlank(message = "{error.nombre}")
	@Size(max = 30, message = "{error.longitud.max}")
	private String apellido1;

	private String apellido2;

	@NotBlank(message = "{error.nombre}")
	@Size(max = 50, message = "{error.longitud.max}")
	private String ciudad;

	@NotNull(message = "{error.nombre}")
	@Min(value = 1000, message = "{error.tamano.min}")
	@Max(value = 10000, message = "{error.tamano.max}")
	private int categoria;

	@Email(message = "{error.email.regex}")
	private String email;

	public Cliente(long id, String nombre, String apellido1, String apellido2, String ciudad, int categoria) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.ciudad = ciudad;
		this.categoria = categoria;
	}
}
