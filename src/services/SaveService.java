package services;

import model.Perspective;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveService {
    public void savePerspective(Perspective perspective, String filePath) {
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(perspective.getImage().getData());
            System.out.println("Image saved successfully to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}