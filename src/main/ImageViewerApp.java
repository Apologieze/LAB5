package main;

import view.ImageTab;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class ImageViewerApp extends JFrame {
    private JTabbedPane tabbedPane;
    private JMenuItem closeMenuItem;
    private JMenuItem duplicateMenuItem;

    public ImageViewerApp() {
        setTitle("Image Pro Editor Premium Deluxe 121");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);

        createMenuBarAndTab();

        setLocationRelativeTo(null);
    }

    private void createMenuBarAndTab() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fichierMenu = new JMenu("Fichier");
        JMenuItem openMenuItem = new JMenuItem("Ouvrir");
        this.closeMenuItem = new JMenuItem("Fermer");
        this.duplicateMenuItem = new JMenuItem("Nouvelle perspective");

        openMenuItem.addActionListener(e -> openImage());
        closeMenuItem.addActionListener(e -> {
            deleteTab();
        });
        duplicateMenuItem.addActionListener(e -> {
            ImageTab currentTab = (ImageTab) tabbedPane.getSelectedComponent();
            if (currentTab != null) {
                createTab("copy "+currentTab.getTitle(), currentTab.getPerspective().getImage().getData());
            }
        });


        fichierMenu.add(openMenuItem);
        fichierMenu.add(closeMenuItem);
        fichierMenu.add(duplicateMenuItem);

        closeMenuItem.setEnabled(false);
        duplicateMenuItem.setEnabled(false);

        menuBar.add(fichierMenu);

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

                createTab(selectedFile.getName(), imageData);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error loading image: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void createTab(String imageName, byte[] imageData) {
        if (tabbedPane.getTabCount() == 0) {
            closeMenuItem.setEnabled(true);
            duplicateMenuItem.setEnabled(true);
        }
        ImageTab newTab = new ImageTab(imageName, imageData);
        tabbedPane.addTab(imageName, newTab);
        tabbedPane.setSelectedComponent(newTab);
    }

    private void deleteTab() {
        if (tabbedPane.getTabCount() == 1) {
            closeMenuItem.setEnabled(false);
            duplicateMenuItem.setEnabled(false);
        }
        ImageTab currentTab = (ImageTab) tabbedPane.getSelectedComponent();
        if (currentTab != null) {
            tabbedPane.remove(currentTab);
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