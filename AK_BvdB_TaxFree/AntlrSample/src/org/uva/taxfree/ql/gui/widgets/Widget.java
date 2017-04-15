package org.uva.taxfree.ql.gui.widgets;

import org.uva.taxfree.ql.gui.FormListener;
import org.uva.taxfree.ql.model.environment.SymbolTable;
import org.uva.taxfree.ql.model.values.Value;
import org.uva.taxfree.qls.QlsStyle;

import javax.swing.*;

public abstract class Widget {
    private final JPanel mPanel;
    private final JLabel mLabel;

    public Widget(String label, String id) {
        mLabel = new JLabel(label);
        mPanel = createPanel(id);
    }

    private JPanel createPanel(String name) {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setName(name);
        widgetPanel.add(mLabel);
        widgetPanel.setVisible(true);
        return widgetPanel;
    }

    public void registerToPanel(JPanel widgetPanel) {
        fillPanel(mPanel);
        widgetPanel.add(mPanel);
    }

    protected abstract void fillPanel(JPanel widgetPanel);

    public abstract Value resolveValue();

    public abstract void callOnUpdate(FormListener listener);

    public void updateVisibility(SymbolTable symbolTable) {
        mPanel.setVisible(symbolTable.isVisible(getId()));
    }

    public abstract void updateValues(SymbolTable symbolTable);

    protected void writeToTable(SymbolTable symbolTable) {
        symbolTable.updateValue(getId(), resolveValue());
    }

    protected String readFromTable(SymbolTable symbolTable) {
        return symbolTable.resolveValue(getId()).toString();
    }

    public void updateStyle(QlsStyle qlsStyle) {
        applyStyle(mPanel, mLabel, qlsStyle);
    }

    protected void applyStyle(JPanel panel, JLabel label, QlsStyle qlsStyle) {
        // Intentionally left blank
    }

    public String getId() {
        return mPanel.getName();
    }

}
