package br.com.waterexpress.interfaces;

import java.util.List;


@SuppressWarnings("hiding")
public interface Operacoes<Object>{
		
		//Create
		public void insert(Object register);
		//Read
		public  List<Object> allList();
		//Update
		public void update(Object register);
		//Delete
		public void delete(int id );
		
		
	

	}
	
	

