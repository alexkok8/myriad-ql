package org.uva.taxfree.ql.gui;

import oracle.jrockit.jfr.JFR;
import org.uva.taxfree.ql.gui.widgets.Widget;
import org.uva.taxfree.ql.model.environment.SymbolTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuestionForm implements FormListener {
    private final JFrame mFrame;
    private final JPanel mWidgetPanel;
    private final List<Widget> mWidgets;
    private final SymbolTable mSymbolTable;

    public QuestionForm(String caption, SymbolTable symbolTable) {
        mFrame = createFrame(caption);
        mWidgetPanel = createWidgetPanel();
        mFrame.add(mWidgetPanel);
        mWidgets = new ArrayList<>();
        mSymbolTable = symbolTable;
    }

    private JFrame createFrame(String caption) {
        JFrame frame = new JFrame(caption);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    mSymbolTable.exportData(caption + ".txdata");
                } catch (IOException error) {
                    MessageWindow.showMessage("(QuestionForm.java:createFrame): Unable to write results to file:\r\n" + error.getMessage());
                }
            }
        });
        return frame;
    }

    private JPanel createWidgetPanel() {
        JPanel widgetPanel = new JPanel();
        widgetPanel.setLayout(new BoxLayout(widgetPanel, BoxLayout.Y_AXIS));
        widgetPanel.setVisible(true);
        return widgetPanel;
    }

    public void show() {
        setDimensions();
        generatePanelContent();
        updateForm();
    }

    public void addWidget(Widget widget) {
        mWidgets.add(widget);
    }

    public void updateForm() {
        for (Widget widget : mWidgets) {
            widget.updateValues(mSymbolTable);
        }

        for (Widget widget : mWidgets) {
            widget.updateVisibility(mSymbolTable);
        }
    }

    private void setDimensions() {
        mFrame.setLocationRelativeTo(null);
        mFrame.setVisible(true);
        mFrame.setPreferredSize(new Dimension(640, 480));
        mFrame.pack();
        mFrame.setLocationRelativeTo(null);
    }

    private void generatePanelContent() {
        mWidgetPanel.removeAll();
        for (Widget widget : mWidgets) {
            registerWidget(widget);
            widget.setListener(this);
        }
        registerToPanel(mWidgetPanel);
    }

    protected void registerWidget(Widget widget) {
        widget.registerToPanel(mWidgetPanel);
    }

    protected void registerToPanel(JPanel panel) {
        // intentionally left blank
    }

}
