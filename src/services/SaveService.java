package services;

import model.Perspective;
import view.ImageTab;
import view.ImageTabPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveService {
    public static void saveProject(ImageTabPane project, String filePath) {
        try  {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            project.writeObject(out);
            out.close();
            fileOut.close();
            System.out.println("Project saved successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}