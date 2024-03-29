package ui.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import persistencia.subcategoria.SubcategoriaModel;

/**
 * Clase para clasificar por colores las tablas de navegaci�n en la tienda
 * online
 * 
 * @author Moises
 *
 */
public class MiRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Object id = table.getValueAt(row, 0);
		String mensaje = (String) table.getValueAt(row, 4);
		SubcategoriaModel sModel = new SubcategoriaModel();

		// Si no es un producto
		if (id == null) {
			String nombre = (String) table.getValueAt(row, 1);

			// Si es una subcategoria
			if (!sModel.getSubcategoriasByNombre(nombre).isEmpty()) {
				setForeground(Color.BLACK);
				setBackground(new Color(94, 237, 158));
			} else { // Si es una categoria
				setForeground(Color.BLACK);
				setBackground(new Color(94, 208, 237));
			}
		} else { // Si es un producto
			setBackground(Color.LIGHT_GRAY);
			// Si el stock actual esta por debajo del stock minimo
			if (mensaje.startsWith("�Solo quedan") || mensaje.equals("�Agotado!")) { 
				setForeground(Color.RED);
			} else {
				setForeground(Color.BLACK);
			}

		}

		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

}
