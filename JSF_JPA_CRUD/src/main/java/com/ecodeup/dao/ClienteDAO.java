package com.ecodeup.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;

import com.ecodeup.model.Cliente;
import com.ecodeup.model.JPAUtil;


public class ClienteDAO {
//	Para usar hibernate creamos un objeto de tipo entity manager, nos sirve para crear el CRUD
	EntityManager entity=JPAUtil.getEntityManagerFactory().createEntityManager();
	
//	Guardar cliente
	public void guardar(Cliente cliente) {
//		Vamos a empezar una transaccion
		entity.getTransaction().begin();
//		hace la persistencia y guarda en nuestra base de datos
		entity.persist(cliente);
//		Para finalizar hacemos un commit
		entity.getTransaction().commit();
//		cerramos la conexion
//		JPAUtil.shutdown();		
	}
	
	public void editar(Cliente cliente) {
//		Comenzamos la conexion(o transaccion)
		entity.getTransaction().begin();
//		Para actualizar el objeto en la DB
		entity.merge(cliente);
//		Commiteamos
		entity.getTransaction().commit();
//		Cerramos la conexion
//		JPAUtil.shutdown();
	}
	
//	Buscar cliente
	public Cliente buscar(Long id) {
//		Creamos un objeto para guardar el objeto que vamos a buscar el registro posteriormente
		Cliente c = new Cliente();
//		Permite encontrar un registro en la D
		c = entity.find(Cliente.class, id);
//		cerramos conexion
//		JPAUtil.shutdown();
//		Devolvemos el objeto
		return c;
	}
	
//	Eliminar cliente
	public void eliminar(Long id) {
//		Crear un nuevo cliente
		Cliente c = new Cliente();
//		Encuentra el registro en la DB
		c = entity.find(Cliente.class, id);
//		Empezamos la transaccion con la db
		entity.getTransaction().begin();
//		Eliminamos el objeto cliente seleccionado por la id
		entity.remove(c);
//		Commiteamos para guardar los cambios
		entity.getTransaction().commit();
		
	}
	
//	Obtener todos los clientes
	public List<Cliente> obtenerClientes(){
		List<Cliente> listaClientes= new ArrayList<>();
		
//		Usamos JQL
		Query q = entity.createQuery("SELECT c FROM Cliente c");
//		Para obtener todos los registros de tabla clientes
		listaClientes = q.getResultList();
		return listaClientes;
	}
		

	
	
}
