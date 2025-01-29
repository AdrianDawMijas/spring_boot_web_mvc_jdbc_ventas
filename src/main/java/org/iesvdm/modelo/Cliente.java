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

	@NotBlank(message = "Por favor introduzca nombre")
	@Size(max = 30, message = "Maximo 30 caracteres")
	private String nombre;

	@NotBlank(message = "Por favor introduzca nombre")
	@Size(max = 30, message = "Maximo 30 caracteres")
	private String apellido1;

	private String apellido2;

	@NotBlank(message = "Por favor introduzca nombre")
	@Size(max = 50, message = "Maximo 50 caracteres")
	private String ciudad;

	@NotNull(message = "Debe introducir una categoría")
	@Min(value = 1000, message = "El valor mínimo permitido es 1000")
	@Max(value = 10000, message = "El valor máximo permitido es 10000")
	private int categoria;

	@Email(message = "Formato de email incorrecto. Ha introducido '${validatedValue}'", regexp="^[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,5}")
	//@NotBlank(message = "Por favor, introduzca email.")
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
