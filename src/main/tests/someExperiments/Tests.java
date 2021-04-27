package someExperiments;

import org.testng.annotations.Test;
import raidClicker.ColorMatrixGenerator;
import raidClicker.MouseActions;

public class Tests {

    @Test
    public void test1() {
        wait(5);
        MouseActions actions = new MouseActions();
        actions.takeMouseLocationFromScreen();
        ColorMatrixGenerator generator = new ColorMatrixGenerator(actions.getMouseLocation());
        generator.takeFocusedPixelMatrix();
        System.out.println("Mouse there: " + generator.isOneOfThePixelMatrixesVisible());
        System.out.println("Mouse not there: " + generator.isOneOfThePixelMatrixesVisible());

    }

    void wait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException exception) {
        }
    }
}
