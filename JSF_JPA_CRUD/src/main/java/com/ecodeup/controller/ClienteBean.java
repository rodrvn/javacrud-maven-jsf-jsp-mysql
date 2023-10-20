package com.ecodeup.controller;

//import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.ecodeup.dao.ClienteDAO;
import com.ecodeup.model.Cliente;

// Para que jsf reconozca como maganerbean
// usamos el name para que al instanciar utilicemos el nobmre que le asignemos
@ManagedBean(name="clienteBean")
// Fijamos el alcance o ambito de este Magament Bean para que sea de peticion
@RequestScoped
public class ClienteBean {
	
//	Redirige a la pagina para agregar a un nuevo cliente
	public String nuevo() {
//		Colocamos los siguientes para que al entrar a la pagina de guardar llegue un objeto cliente vacio al que posteriormente al guardar
//		se le asignen los datos y se pueda efectivizar la consulta
		Cliente c = new Cliente();
//		coleccion tipo mapeo que persiste durante la ejecucion de la aplicacion
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("cliente", c);	
		
		return "/faces/nuevo.xhtml";
	}
	
//	Guardar cliente
	public String guardarCliente(Cliente cliente) {
		//guarda la fecha de registro
		Date fechaActual= new Date();
		cliente.setFregistro(new java.sql.Date(fechaActual.getTime()));
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.guardar(cliente);
		return "/faces/index.xhtml";
		
	}
	
	public List<Cliente> obtenerClientes(){
		
		ClienteDAO clienteDAO= new ClienteDAO();
		
//		List<Cliente> listaClientes = new ArrayList<>();
//		Cliente c1= new Cliente();
//		c1.setId(1L);
//		c1.setNombres("Rodrigo");
//		c1.setApellidos("Vallejos");
//		c1.setDireccion("asun");
//		
//		Cliente c2= new Cliente();
//		c2.setId(2L);
//		c2.setNombres("Victoria");
//		c2.setApellidos("Iriarte");
//		c2.setDireccion("cde");
//		
//		listaClientes.add(c1);
//		listaClientes.add(c2);
//		 
//		return listaClientes;
		
		return clienteDAO.obtenerClientes();
		  
	}
	
//	Muestra los datos en la vista editar
	public String editar(Long id) {
		
		ClienteDAO clienteDAO = new ClienteDAO();
		
		Cliente c = new Cliente();
		c = clienteDAO.buscar(id);
//		System.out.println("***********************");
//		System.out.println(c.getNombres());
//		System.out.println(c);
//		System.out.println("***********************");
		
//		coleccion tipo mapeo que persiste durante la ejecucion de la aplicacion
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap.put("cliente", c);		
		
//		Faces porque en nuestro archivo web.xml indicamos que las paginas se van a presentar con el sufijo /faces
		return "/faces/editar.xhtml";
		
	}
	
//	Actualiza los datos de la vista editar
	public String actualizar(Cliente cliente) {
		//guarda la fecha de actualizacion
		Date fechaActual= new Date();
		cliente.setFactualizar(new java.sql.Date(fechaActual.getTime()));
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.editar(cliente);
		
//		Volvemos al index
		return "/faces/index.xhtml";

	}
	
//	Eliminar un cliente
	public String eliminar(Long id) {
//		creamos un nuevo objeto
		ClienteDAO clienteDAO = new ClienteDAO();
//		Llamamos el metodo que creamos en clientedao
		clienteDAO.eliminar(id);
//		para confirmar
		System.out.println("Eliminanding");
		
//		Volvemos al index
		return "/faces/index.html";
		
	}
	
	
	
	
}
