package com.APIProject.apiProject.domain.business;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="Guerrilla")
public class GuerrillaUsuario {

    //Declaracion de variable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID_guerrilla;

    @Column(name = "Email")
    @Size(max = 50)
    @Email
    private String Email;

    @NotNull
    @Column(name = "Faccion")
    @Size(max = 100)
    private String Faccion;

    @NotNull
    @Size(max = 50)
    @Column(name = "Nombre_usuario")
    private String Nombre;

    @NotNull
    @Column(name = "Recurso_humano")
    private Integer Recurso_humano;

    @NotNull
    @Column(name = "Petroleo")
    private Double Petroleo;

    @NotNull
    @Column(name = "Dinero")
    private Double Dinero;

    @NotNull
    @Column(name = "Puntaje")
    private Double Puntaje;

    @NotNull
    @Column(name = "Indice_defensa")
    private Double Indice_defensa;

    @NotNull
    @Column(name = "Indice_ataque")
    private Double Indice_ataque;

    @OneToMany(mappedBy = "guerrilla", fetch = FetchType.LAZY)
    private List<UnidadBatalla> unidad_batallas;

    //Getters and setters
    public String getEmail() { return Email; }

    public void setEmail(String email) { Email = email; }

    public Integer getID_Guerrilla_Usuario() { return ID_guerrilla; }

    public void setID_Guerrilla_Usuario(Integer ID_Guerrilla_Usuario) { this.ID_guerrilla = ID_Guerrilla_Usuario; }

    public String getFaccion() { return Faccion; }

    public void setFaccion(String faccion) { Faccion = faccion; }

    public String getNombre() { return Nombre; }

    public void setNombre(String nombre) { Nombre = nombre; }

    public Integer getRecurso_humano() { return Recurso_humano; }

    public void setRecurso_humano(Integer recurso_humano) { Recurso_humano = recurso_humano; }

    public Double getPetroleo() { return Petroleo; }

    public void setPetroleo(Double petroleo) { Petroleo = petroleo; }

    public Double getDinero() { return Dinero; }

    public void setDinero(Double dinero) { Dinero = dinero; }

    public Double getPuntaje() { return Puntaje; }

    public void setPuntaje(Double puntaje) { Puntaje = puntaje; }

    public Double getIndice_defensa() { return Indice_defensa; }

    public void setIndice_defensa(Double indice_defensa) { Indice_defensa = indice_defensa; }

    public Double getIndice_ataque() { return Indice_ataque; }

    public void setIndice_ataque(Double indice_ataque) { Indice_ataque = indice_ataque; }

    public List<UnidadBatalla> getUnidad_batallas() { return unidad_batallas; }

    public void setUnidad_batallas(List<UnidadBatalla> unidad_batallas) { this.unidad_batallas = unidad_batallas; }

    //toString
    @Override
    public String toString() {
        return "GuerrillaUsuario{" +
                "Email='" + Email + '\'' +
                ", ID_Guerrilla_Usuario=" + ID_guerrilla +
                ", Direccion='" + Faccion + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Recurso_humano=" + Recurso_humano +
                ", Petroleo=" + Petroleo +
                ", Dinero=" + Dinero +
                ", Puntaje=" + Puntaje +
                ", Indice_defensa=" + Indice_defensa +
                ", Indice_ataque=" + Indice_ataque +
                '}';
    }
}

