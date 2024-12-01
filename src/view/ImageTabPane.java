package view;

import model.Perspective;

import javax.swing.*;
import java.io.*;


public class ImageTabPane extends JTabbedPane implements Serializable {

    public boolean createTab(String imageName, byte[] imageData) {
        ImageTab newTab = new ImageTab(imageName, imageData);
        this.addTab(imageName, newTab);
        this.setSelectedComponent(newTab);
        return this.getTabCount() == 1;
    }

    public boolean deleteTab() {
        ImageTab currentTab = (ImageTab) this.getSelectedComponent();
        if (currentTab != null) {
            this.remove(currentTab);
        }
        return this.getTabCount() == 0;
    }

    public void writeObject(ObjectOutputStream out) throws IOException {
        for (int i = 0; i < getTabCount(); i++) {
            ImageTab tab = (ImageTab) getComponentAt(i);
            out.writeObject(tab.getTitle());
            out.writeObject(tab.getPerspective());
        }
    }


    public void addfromFile(File file) {
        this.removeAll();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    String title = (String) in.readObject();
                    System.out.println(title);
                    Perspective perspective = (Perspective) in.readObject();
                    ImageTab newTab = new ImageTab(title, perspective);
                    this.addTab(title, newTab);
                    this.setSelectedComponent(newTab);
                } catch (EOFException e) {
                    break;
                }
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

