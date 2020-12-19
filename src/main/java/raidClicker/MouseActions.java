package raidClicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import static java.awt.MouseInfo.getPointerInfo;

public final class MouseActions {

    private final Robot robot;
    private Point mouseLocation;

    public MouseActions() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException("No robot for you!");
        }
    }

    public MouseActions getMouseLocation() {
        mouseLocation = getPointerInfo().getLocation();
        return this;
    }

    public MouseActions doubleClick() {
        return doubleClick(25);
    }

    public MouseActions doubleClick(int milliToClick) {
        moveToMouseLocation();
        click();
        Timer timer = new Timer(milliToClick, null);
        timer.addActionListener(e -> {
            click();
            timer.stop();
        });
        timer.start();

        return this;
    }

    private void click() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private void moveToMouseLocation() {
        robot.mouseMove(mouseLocation.x, mouseLocation.y);
    }

    /*private final Point location;
    private final int secondsToWait;
    private final JLabel showElapsedTime;
    private final Robot robot;

    public MouseActions(Point location, int secondsToWait, JLabel showElapsedTime) {
        this.location = location;
        this.secondsToWait = secondsToWait;
        this.showElapsedTime = showElapsedTime;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickTheShitOutOfIt() {
        robot.mouseMove(location.x, location.y);

        Timer timer = new Timer(1000, null);

        waitTime = secondsToWait;

        ActionListener listener = e1 -> {

            if (!isOn) {
                timer.stop();
            }

            if (waitTime <= 1) {
                robot.mouseMove(location.x, location.y);
                click();
                click();
                waitTime = secondsToWait;
                if (isOn) {
                    showElapsedTime.setText("CLICK!!!");
                }
            } else {
                if (isOn) {
                    showElapsedTime.setText(format("Click > [%d s]!", waitTime - 1));
                }
                waitTime--;
            }

        };
        timer.addActionListener(listener);
        timer.start();
    }

    private void click() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }*/
}
