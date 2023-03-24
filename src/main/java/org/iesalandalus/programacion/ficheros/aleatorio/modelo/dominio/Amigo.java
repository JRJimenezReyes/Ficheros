package org.iesalandalus.programacion.ficheros.aleatorio.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Amigo {
	
	private static final String ER_TELEFONO = "\\d{9}";
	private static final String ER_CORREO = "\\w+(?:\\.\\w+)*+@\\w+\\.\\w{2,5}";
	private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	protected String nombre;
	protected String telefono;
	protected String correo;
	protected String direccion;
	protected LocalDate fechaNacimiento;
	protected double peso;		
	protected int altura;		
	
	public Amigo() {
	}
	
	public Amigo(String nombre, String telefono, String correo, String direccion, LocalDate fechaNacimiento, double peso, int altura) {
		setNombre(nombre);
		setTelefono(telefono);
		setCorreo(correo);
		setDireccion(direccion);
		setFechaNacimiento(fechaNacimiento);
		setPeso(peso);
		setAltura(altura);
	}
	
	public Amigo(String nombre, String telefono, String correo, String direccion, String fechaNacimiento, double peso, int altura) {
		setNombre(nombre);
		setTelefono(telefono);
		setCorreo(correo);
		setDireccion(direccion);
		setFechaNacimiento(fechaNacimiento);
		setPeso(peso);
		setAltura(altura);
	}
	
	public Amigo(Amigo amigo) {
		if (amigo == null) {
			throw new NullPointerException("El amigo a copiar no puede ser nulo.");
		}
		nombre = amigo.getNombre();
		telefono = amigo.getTelefono();
		correo = amigo.getCorreo();
		direccion = amigo.getDireccion();
		fechaNacimiento = amigo.getFechaNacimiento();
		peso = amigo.getPeso();
		altura = amigo.getAltura();
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("El nombre no puede ser nulo.");
		}
		if (nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre no puede estar vacío.");
		}
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null) {
			throw new NullPointerException("El teléfono no puede ser nulo.");
		}
		if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("El formato del teléfono no es correcto.");
		}
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("El correo no puede ser nulo.");
		}
		if (!correo.matches(ER_CORREO)) {
			throw new IllegalArgumentException("El formato del correo no es correcto.");
		}
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		if (direccion == null) {
			throw new NullPointerException("La dirección no puede ser nula.");
		}
		if (direccion.isBlank()) {
			throw new IllegalArgumentException("La dirección no puede estar vacía.");
		}
		this.direccion = direccion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public String getFechaNacimientoStr() {
		return FORMATO_DIA.format(fechaNacimiento);
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if (fechaNacimiento == null) {
			throw new NullPointerException("La fecha de nacimiento no puede ser nula.");
		}
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public void setFechaNacimiento(String fechaNacimiento) {
		if (fechaNacimiento == null) {
			throw new NullPointerException("La fecha de nacimiento no puede ser nula.");
		}
		try {
			this.fechaNacimiento = LocalDate.parse(fechaNacimiento, FORMATO_DIA);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Formato de la fecha de nacimiento incorrecto.");
		}
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		if (peso <= 0) {
			throw new IllegalArgumentException("El peso debe ser mayor que cero.");
		}
		this.peso = peso;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		if (altura <= 0) {
			throw new IllegalArgumentException("La altura debe ser mayor que cero.");
		}
		this.altura = altura;
	}
	
	public int compareTo(Amigo amigo) {
		return this.getNombre().compareTo(amigo.getNombre());
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Amigo)) {
			return false;
		}
		Amigo other = (Amigo) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return String.format(
				"Amigo [nombre=%s, telefono=%s, correo=%s, direccion=%s, fechaNacimiento=%s, peso=%s, altura=%s]",
				nombre, telefono, correo, direccion, fechaNacimiento.format(FORMATO_DIA), peso, altura);
	}
	
}
