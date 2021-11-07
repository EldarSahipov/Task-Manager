package models;

import java.awt.*;

public class Notification {

    public void displayTray() throws AWTException {
        //Obtain only one instance of the SystemTray object
//        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
//        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
//        Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

//        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
//        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
//        trayIcon.setToolTip("System tray icon demo");
//        tray.add(trayIcon);
//        Task task = new Task("Ужин", "Заказать пиццы", new Date(2021,11,3,15,0), "");

//        trayIcon.displayMessage("Задача", task.toString(), TrayIcon.MessageType.INFO);
    }
}
