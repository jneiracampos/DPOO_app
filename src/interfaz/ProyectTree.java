
package interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import modelo.Paquete;
import modelo.Proyecto;
import modelo.Tarea;

public class ProyectTree extends JFrame
{
    private JTree tree;
    private TreePath ruta;
    private DefaultMutableTreeNode paquete;
    private DefaultMutableTreeNode paquete1;
    private DefaultMutableTreeNode tarea;
    private HashMap<String,Paquete> paquetesPaquete;
    private HashMap<String,Tarea> tareasPaquete;
    private HashMap<String,Paquete> paquetesProyecto = Enrutador.getInstance().getProyecto().getPaquetes();
    
    public ProyectTree()
    {
        //create the root node
    	Set<String> nombresPaquetes = paquetesProyecto.keySet();    	
    	int n = nombresPaquetes.size();
        ArrayList<String> nombresPaquetesA = new ArrayList<String>(n);
        for (String x : nombresPaquetes)
        	nombresPaquetesA.add(x);
        
        DefaultMutableTreeNode proyecto = new DefaultMutableTreeNode("Proyecto");
    	for (int i = 0; i < nombresPaquetesA.size(); i++) {
			String nombre = nombresPaquetesA.get(i);
			
	        //create paquetes
			paquete = new DefaultMutableTreeNode(nombre);
			
			if (Enrutador.getInstance().getProyecto().getPaquete(nombre).isEmptyPaquetes() == false) {
				
				paquetesPaquete = Enrutador.getInstance().getProyecto().getPaquete(nombre).getPaquetes();
				
				//create the child nodes of paquetes
				Set<String> nombresPaquetes1 = paquetesPaquete.keySet();    	
		    	int n1 = nombresPaquetes1.size();
		        ArrayList<String> nombresPaquetesA1 = new ArrayList<String>(n1);
		        for (String x : nombresPaquetes1)
		        	nombresPaquetesA1.add(x);
		      
		    	for (int j = 0; j < nombresPaquetesA1.size(); j++) {
					String nombre1 = nombresPaquetesA1.get(j);
					System.out.println(nombre1);
			        //create paquetes
					paquete1 = new DefaultMutableTreeNode(nombre1);
					
			        //add the child nodes to paquetes
			        paquete.add(paquete1);
		        
		    	}
		        
			} 

			if (Enrutador.getInstance().getProyecto().getPaquete(nombre).isEmptyTareas() == false) {
				tareasPaquete = Enrutador.getInstance().getProyecto().getPaquete(nombre).getTareas();
				
				//create the child nodes of paquetes
				Set<String> nombresTareas = tareasPaquete.keySet();    	
		    	int nt = nombresTareas.size();
		        ArrayList<String> nombresTareasA = new ArrayList<String>(nt);
		        for (String x : nombresTareas)
		        	nombresTareasA.add(x);
		      
		    	for (int j = 0; j < nombresTareasA.size(); j++) {
					String nombreT = nombresTareasA.get(j);
					System.out.println(nombreT);
			        //create paquetes
					tarea = new DefaultMutableTreeNode(nombreT);
					
			        //add the child nodes to paquetes
			        paquete.add(tarea);
		    	}
			}

	        //add paquetes a proyecto
	        proyecto.add(paquete);
	    	
		}
    	
    	//create the tree by passing in the root node
    	tree = new JTree(proyecto);
        add(tree);
        
        this.setTitle("Proyecto"); 
        this.setSize(500,500);
        this.setVisible(true);
        //tree.setRootVisible(false);
        
        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                ruta = e.getPath();
                System.out.println(ruta);
                setVisible(false);
            }
        });
        
    }
    
    public TreePath getRuta() {
    	return ruta;
    }
    
    
}

