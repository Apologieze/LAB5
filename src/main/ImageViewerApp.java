package main;

import view.ImageTab;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ImageViewerApp extends JFrame {
    private JTabbedPane tabbedPane;

    public ImageViewerApp() {
        setTitle("Image Pro Editor Premium Deluxe 121");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);


        createMenuBarAndTab();

        setLocationRelativeTo(null);
    }

    private void createMenuBarAndTab() {
        JMenuBar menuBar = new JMenuBar();

        // Fichier Menu
        JMenu fichierMenu = new JMenu("Fichier");
        JMenuItem openMenuItem = new JMenuItem("Ouvrir");
        JMenuItem closeMenuItem = new JMenuItem("Fermer");
        openMenuItem.addActionListener(e -> openImage());
        closeMenuItem.addActionListener(e -> {
            ImageTab currentTab = (ImageTab) tabbedPane.getSelectedComponent();
            if (currentTab != null) {
                tabbedPane.remove(currentTab);
            }
        });

        fichierMenu.add(openMenuItem);
        fichierMenu.add(closeMenuItem);
        menuBar.add(fichierMenu);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem undoMenuItem = new JMenuItem("Undo");
        undoMenuItem.addActionListener(e -> undoLastAction());

        editMenu.add(undoMenuItem);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);
        tabbedPane = new JTabbedPane();
        add(tabbedPane);
    }


    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png", "gif", "bmp"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                byte[] imageData = Files.readAllBytes(selectedFile.toPath());

                // Create a new tab for this image
                ImageTab newTab = new ImageTab(selectedFile.getName(), imageData);
                tabbedPane.addTab(selectedFile.getName(), newTab);
                tabbedPane.setSelectedComponent(newTab);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error loading image: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void undoLastAction() {
        ImageTab currentTab = (ImageTab) tabbedPane.getSelectedComponent();
        if (currentTab != null) {
            currentTab.undo();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ImageViewerApp().setVisible(true);
        });
    }
}