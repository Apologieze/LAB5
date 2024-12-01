package main;

import services.SaveService;
import view.ImageTab;
import view.ImageTabPane;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class ImageViewerApp extends JFrame {
    private ImageTabPane imageTabPane;
    private JMenuItem closeMenuItem;
    private JMenuItem duplicateMenuItem;
    private JMenuItem saveMenuItem;

    public ImageViewerApp() {
        setTitle("Image Pro Editor Premium Deluxe 121");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);

        createMenuBarAndTab();

        setLocationRelativeTo(null);

        getRootPane().registerKeyboardAction(
                e -> undoLastAction(),
                KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK),
                JComponent.WHEN_IN_FOCUSED_WINDOW
        );
    }

    private void createMenuBarAndTab() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fichierMenu = new JMenu("Fichier");
        JMenuItem openMenuItem = new JMenuItem("Ouvrir");
        this.closeMenuItem = new JMenuItem("Fermer");
        this.duplicateMenuItem = new JMenuItem("Nouvelle perspective");
        this.saveMenuItem = new JMenuItem("Sauvegarder");

        openMenuItem.addActionListener(e -> openImage());
        closeMenuItem.addActionListener(e -> {
            if (imageTabPane.deleteTab()) {
                setMenuItem(false);
            }
        });
        duplicateMenuItem.addActionListener(e -> {
            ImageTab currentTab = (ImageTab) imageTabPane.getSelectedComponent();
            if (currentTab != null) {
                imageTabPane.createTab("copy "+currentTab.getTitle(), currentTab.getPerspective().getImage().getData());
            }
        });

        saveMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save File");

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                SaveService.saveProject(imageTabPane, fileToSave.getAbsolutePath()+".imagepro");
            }
        });


        fichierMenu.add(openMenuItem);
        fichierMenu.add(closeMenuItem);
        fichierMenu.add(duplicateMenuItem);
        fichierMenu.add(saveMenuItem);

        setMenuItem(false);

        menuBar.add(fichierMenu);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem undoMenuItem = new JMenuItem("Undo");
        undoMenuItem.addActionListener(e -> undoLastAction());

        editMenu.add(undoMenuItem);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);
        imageTabPane = new ImageTabPane();
        add(imageTabPane);
    }


    private void openImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png", "gif", "bmp", "imagepro"));

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.getName().toLowerCase().endsWith(".imagepro")) {
                this.imageTabPane.addfromFile(selectedFile);
                setMenuItem(true);
                return;
            }
            try {
                byte[] imageData = Files.readAllBytes(selectedFile.toPath());

                if(imageTabPane.createTab(selectedFile.getName(), imageData)){
                    setMenuItem(true);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error loading image: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void undoLastAction() {
        ImageTab currentTab = (ImageTab) imageTabPane.getSelectedComponent();
        if (currentTab != null) {
            currentTab.undo();
        }
    }

    private void setMenuItem(boolean enabled) {
        closeMenuItem.setEnabled(enabled);
        duplicateMenuItem.setEnabled(enabled);
        saveMenuItem.setEnabled(enabled);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ImageViewerApp().setVisible(true);
        });
    }
}