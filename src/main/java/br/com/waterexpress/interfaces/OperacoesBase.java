package br.com.waterexpress.interfaces;

import java.util.List;

public interface OperacoesBase<Object>{
		
		//Create
		public void insert(Object register);
		//Read
		public Object getById(int id); 
		//ReadAll
		public  List<Object> listAll() throws Exception;
		//Update
		public void update(Object register);
		//Delete
		public void delete(int id );
		
	
		
	

	}
	
	

