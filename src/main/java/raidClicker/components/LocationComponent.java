package raidClicker.components;

import raidClicker.exceptions.LocationNotParsable;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public final class LocationComponent extends JPanel {
    private final JTextField xLocation;
    private final JTextField yLocation;

    public LocationComponent() {
        xLocation = new JTextField();
        yLocation = new JTextField();
        this.setLayout(new GridLayout(1, 2));
        this.add(createPanelForLocationPart("X", xLocation, Color.BLUE));
        this.add(createPanelForLocationPart("Y", yLocation, Color.RED));
    }

    private JPanel createPanelForLocationPart(String part, JTextField textField, Color labelColor) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(labelInMiddle(part, labelColor));
        panel.add(textField);
        panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        return panel;
    }

    private JLabel labelInMiddle(String labelText, Color labelColor) {
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(labelColor);
        return label;
    }

    public Point getMouseLocation() {
        return new Point(parseLocationPart(xLocation), parseLocationPart(yLocation));
    }

    public void setMouseLocation(Point mouseLocation) {
        xLocation.setText(String.valueOf(mouseLocation.x));
        yLocation.setText(String.valueOf(mouseLocation.y));
        this.validate();
    }

    private int parseLocationPart(JTextField part) {
        try {
            return Integer.parseInt(part.getText());
        } catch (NumberFormatException ex) {
            throw new LocationNotParsable(ex);
        }
    }
}
