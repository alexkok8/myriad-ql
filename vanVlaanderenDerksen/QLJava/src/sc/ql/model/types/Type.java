package sc.ql.model.types;

import javax.swing.JPanel;

import sc.ql.gui.widgets.Widget;
import sc.ql.model.Node;

public class Type extends Node {
	
	public String toString() {
		return "undefined type";
	}
	
	public Boolean isNumericType() {
		return false;
	}
	
	public Boolean isBooleanType() {
		return false;
	}
	
	public Boolean isStringType() {
		return false;
	}
	
	public Boolean isCompatibleWith(Type type) {
		return false;
	};
	
	public Boolean isCompatibleWith(BooleanType type) {
		return false;
	}
	
	public Boolean isCompatibleWith(IntegerType type) {
		return false;
	}
	
	public Boolean isCompatibleWith(MoneyType type) {
		return false;
	}
	
	public Boolean isCompatibleWith(StringType type) {
		return false;
	}
	
	public Widget getWidget(JPanel panel) {
		return null;
	}
	
}
