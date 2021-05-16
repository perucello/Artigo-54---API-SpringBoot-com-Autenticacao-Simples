package com.project.jpa.mysql.SpringBoot.JPA.Mysql.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.jpa.mysql.SpringBoot.JPA.Mysql.Model.Cliente;


public interface Clientes extends JpaRepository<Cliente, Long>  {

}
