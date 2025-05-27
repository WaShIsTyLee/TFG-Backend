package com.example.flypark1.Model;

import jakarta.persistence.*;

    @Entity
    @Table(name = "usuarios")
    public class Usuario {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id_usuario;

        @Column(name = "nombre")
        private String name;

        @Column(name = "email")
        private String email;

        @Column(name = "telefono")
        private String phone;

        @Column(name = "contraseña")
        private String password;

        @Column (name = "monedero")
        private double monedero;

        public double getMonedero() {
            return monedero;
        }

        public void setMonedero(double monedero) {
            this.monedero = monedero;
        }

        @Lob
        @Column(name = "foto", columnDefinition = "LONGTEXT")
        private String foto;

    public Usuario(int id_usuario, String name, String email, String phone, String password, String foto) {
        this.id_usuario = id_usuario;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.foto = foto;
    }

    public Usuario() {}

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id) {
        this.id_usuario = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
