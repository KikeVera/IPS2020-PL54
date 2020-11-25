package ui.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import persistencia.subcategoria.SubcategoriaModel;

/**
 * Clase para clasificar por colores las tablas de navegación en la tienda online 
 * @author Moises
 *
 */
public class MiRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		 
        Object id  = table.getValueAt(row, 0);
        SubcategoriaModel sModel = new SubcategoriaModel(); 
 
        if (id == null) {
        	String nombre = (String) table.getValueAt(row, 1);
        	if(!sModel.getSubcategoriasByNombre(nombre).isEmpty()) {
            	setForeground(Color.BLACK);
            	setBackground(new Color(94, 237, 158));
        	}
        	else {
            	setForeground(Color.BLACK);
            	setBackground(new Color(94, 208, 237));
        	}  
        }
        else {
        	setForeground(Color.BLACK);
        	setBackground(Color.LIGHT_GRAY);
        }
 
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}
