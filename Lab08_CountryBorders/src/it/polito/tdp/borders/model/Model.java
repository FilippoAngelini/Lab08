package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private UndirectedGraph <Country, Border> graph;
	private BordersDAO dao;

	public Model() {
		
		//graph = new SimpleGraph <Country, Border> (Border.class);
		dao = new BordersDAO();
	}

	public String doCalcolaConfini(String anno) {
		
		List<Border> borders = new ArrayList<Border>();
		
		borders = dao.getCountryPairs(Integer.parseInt(anno));
		
		generaGrafo(borders);
		
		String ris = stampaConfini();
		
		return ris;		
	}
	
	public void generaGrafo (List<Border> borders){
		
		graph = new SimpleGraph <Country, Border> (Border.class);
		
		for(Border b : borders){
			Country c1 = b.getC1();
			Country c2 = b.getC2();
			if(!graph.vertexSet().contains(c1))
				graph.addVertex(c1);
			if(!graph.vertexSet().contains(c2))
				graph.addVertex(c2);
			if(!graph.edgeSet().contains(b))
				graph.addEdge(c1, c2, b);
		}
	}
	
	public String stampaConfini(){
		
		String ris = "";
		
		ris += calcolaComponentiConnesse() + "\n";
		
		for(Country vertex : graph.vertexSet())
			ris += vertex.toString() + " Numero stati confinanti: " + graph.degreeOf(vertex) + "\n";
		
		return ris.trim();
	}
	
	public String calcolaComponentiConnesse(){
		
		String ris = "";
		
		ConnectivityInspector<Country,Border> ci = new ConnectivityInspector<Country,Border>(graph);
		
		int numComponentiConnesse = ci.connectedSets().size();
		
		ris += "Numero componenti connesse nel grafo: " + numComponentiConnesse;
		
		return ris;
	}

}
