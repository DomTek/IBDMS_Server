package ibdms_server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.*;

public class DroneControllRoom extends JPanel {

    // crating a styatic Vasriable as a static field so i can use the methodes from this class in the cennection class
    static DroneControllRoom droneControllRoom;

    //Creates an Object array to store the drone details for further usage in the app
    ArrayList<Drone> droneListArray = new ArrayList<Drone>();

    //methode to invoke pupups for the user to enter the conected drone details and then add them to the droneListArray. Then it follows by invoking the methode to update the JComboList in the GUI for the drones    
    public void newDrone(JComboBox<String> droneListDisplay) {
        String droneName = JOptionPane.showInputDialog(null, "Please enter the drone name:", "Drone Name", JOptionPane.QUESTION_MESSAGE);
        int droneID = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the drone ID:", "Drone ID", JOptionPane.QUESTION_MESSAGE));
        int posX = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the drone's X position:", "Drone X Position", JOptionPane.QUESTION_MESSAGE));
        int posY = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the drone's Y position:", "Drone Y Position", JOptionPane.QUESTION_MESSAGE));
        Drone newDrone = new Drone(droneID, droneName, posX, posY);
        droneListArray.add(newDrone);
        updateDroneListDisplay(droneListDisplay);
        repaint();
    }

    // this methode retrieves the drone names from the droneListArray so i can pass it tro the JComboBox
    public String[] getDroneNames() {
        String[] names = new String[droneListArray.size()];
        for (int i = 0; i < droneListArray.size(); i++) {
            names[i] = droneListArray.get(i).getName();
        }
        return names;
    }

    // Methode to update the list displayed in the drone JComboBox in the GUI
    public void updateDroneListDisplay(JComboBox<String> droneListDisplay) {
        droneListDisplay.removeAllItems();
        String[] droneNames = getDroneNames();
        for (String droneName : droneNames) {
            droneListDisplay.addItem(droneName);
        }
    }

// Add Drone objects to the ArrayList
    private Image backgroundImage;

    public DroneControllRoom() {
        // Load the background image
        backgroundImage = new ImageIcon("background.jpg").getImage();

        // Set the panel size to match the background image size
        setPreferredSize(getBackgroundImageSize());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, null);

        int index = 0;

        while (index < droneListArray.size()) {
            Drone drone = droneListArray.get(index);
            String name = drone.getName();
            int posX = drone.getPosX();
            int posY = drone.getPosY();

            g.setColor(Color.BLUE);
            g.fillRect(posX, posY, 50, 25);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 10));
            g.drawString(name, posX, posY + 15);

            index++;
        }

// Draw a red circle with text on the background image
        g.setColor(Color.RED);
        g.fillOval(350, 300, 50, 50);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Fire", 355, 330);

    }

    private java.awt.Dimension getBackgroundImageSize() {
        return new java.awt.Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null));
    }

    public static void main(String[] args) {

        createAndShowGUI();
    }

    public static void createAndShowGUI() {

        droneControllRoom = new DroneControllRoom();
        
        //Creates a dummy list of fires
        ArrayList<String> fireList = new ArrayList<String>();
        fireList.add("Fire1");
        fireList.add("Fire2");
        fireList.add("Fire3");

        // creating all button labels and input elements
        JLabel title = new JLabel("Controll Room");
        JLabel droneSelectLable = new JLabel("Select Drone");
        JLabel CoordinatesLable = new JLabel("Coordinates");
        JLabel xLable = new JLabel("X:");
        JLabel yLable = new JLabel("Y:");
        JLabel fireSelectLable = new JLabel("Select Fire");
        JLabel message = new JLabel("Message:");

        JButton moveDroneB = new JButton("Move Drone");
        JButton removeFireB = new JButton("Remove Fire");
        JButton ShutDownB = new JButton("Shut Down");
        JButton ReturnB = new JButton("Return All Drones");

        JTextField xCorTextField = new JTextField();
        JTextField yCorTextField = new JTextField();

        JTextArea messageOutputText = new JTextArea();

        JSeparator separator = new JSeparator();
        JSeparator separator2 = new JSeparator();

        JComboBox<String> droneListDisplay = new JComboBox<String>(droneControllRoom.getDroneNames());

        JComboBox<String> fireListDisplay = new JComboBox<String>(fireList.toArray(new String[0]));

        JFrame frame = new JFrame("Display Objects on Background");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a new JPanel for the left side panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setLayout(new GridBagLayout());

        // Add the left side panel and DroneControllRoom panel to the frame
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(droneControllRoom, BorderLayout.CENTER);

        // Creating Controll interface  Laytout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(title, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(droneSelectLable, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        leftPanel.add(CoordinatesLable, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        leftPanel.add(xLable, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        leftPanel.add(xCorTextField, gbc);
        xCorTextField.setColumns(2);

        gbc.gridx = 1;
        gbc.gridy = 3;
        leftPanel.add(yLable, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        leftPanel.add(yCorTextField, gbc);
        yCorTextField.setColumns(2);

        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(droneListDisplay, gbc);
        droneListDisplay.setPreferredSize(new Dimension(100, 20));

        gbc.gridx = 0;
        gbc.gridy = 4;
        leftPanel.add(moveDroneB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        leftPanel.add(separator, gbc);
        separator.setPreferredSize(new Dimension(200, 5));

        gbc.gridx = 0;
        gbc.gridy = 6;
        leftPanel.add(fireSelectLable, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        leftPanel.add(fireListDisplay, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        leftPanel.add(removeFireB, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        leftPanel.add(separator2, gbc);
        separator2.setPreferredSize(new Dimension(200, 5));

        gbc.gridx = 0;
        gbc.gridy = 9;
        leftPanel.add(message, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        leftPanel.add(messageOutputText, gbc);
        messageOutputText.setPreferredSize(new Dimension(200, 200));

        gbc.gridx = 0;
        gbc.gridy = 11;
        leftPanel.add(ReturnB, gbc);

        gbc.gridx = 1;
        gbc.gridy = 11;
        leftPanel.add(ShutDownB, gbc);

        frame.pack();
        frame.setVisible(true);

        // this code will update the Map every 10 seconds. currently only updating the drone locations using the updateDroneListDisplay methode
        int updateInterval = 10000; // 10 seconds intervall created for the update to run. This number is set as milli seconds
        ActionListener updateDroneListAction = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                droneControllRoom.updateDroneListDisplay(droneListDisplay);
            }
        };
        Timer updateDroneListTimer = new Timer(updateInterval, updateDroneListAction);
        updateDroneListTimer.start();

        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket, messageOutputText, droneControllRoom, droneListDisplay);
                System.out.printf("\nServer waiting on: %d for client from %d ",
                        listenSocket.getLocalPort(), clientSocket.getPort());
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }

    }

}

class Connection extends Thread {

    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    JTextArea messageOutputText;
    DroneControllRoom droneControllRoom;
    JComboBox<String> droneListDisplay;
    private boolean keepRunning;

    public Connection(Socket aClientSocket, JTextArea messageOutputText, DroneControllRoom droneControllRoom, JComboBox<String> droneListDisplay) {
        try {
            keepRunning = true;
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.messageOutputText = messageOutputText;
            this.droneControllRoom = droneControllRoom;
            this.droneListDisplay = droneListDisplay;
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            String data;
            while (keepRunning) {
                data = in.readUTF();
                if ("New Drone".equalsIgnoreCase(data)) {
                    out.writeUTF("Server received:" + data);
                    System.out.println(data);
                    addMessage(data + " Connected!");
                    droneControllRoom.newDrone(droneListDisplay);
                    droneControllRoom.repaint();

                    // retrieves the latest drone added to the list
                    Drone newDrone = droneControllRoom.droneListArray.get(droneControllRoom.droneListArray.size() - 1);

                    // Send the drone properties as individual messages
                    out.writeUTF("Drone Name: " + newDrone.getName());
                    System.out.println("Sending Drone Name: " + newDrone.getName());

                    out.writeInt(newDrone.getID());
                    System.out.println("Sending Drone ID: " + newDrone.getID());

                    out.writeInt(newDrone.getPosX());
                    System.out.println("Sending Drone Position X: " + newDrone.getPosX());

                    out.writeInt(newDrone.getPosY());
                    System.out.println("Sending Drone Position Y: " + newDrone.getPosY());

                    // Update the drone's position in the server and user interface map.
//                } else if (data.equalsIgnoreCase("Update Position")) {
//                    int id = in.readInt();
//                    int posX = in.readInt();
//                    int posY = in.readInt();
//                    System.out.println("Received position update for drone ID: " + id + ", Position: (" + posX + ", " + posY + ")");
//                    
//                }
                } else if (data.startsWith("DroneUpdate:")) {
                    String[] parts = data.substring("DroneUpdate:".length()).split(",");
                    int id = Integer.parseInt(parts[0].trim());
                    int posX = Integer.parseInt(parts[1].trim());
                    int posY = Integer.parseInt(parts[2].trim());
                    System.out.println("Received position update for drone ID: " + id + ", Position: (" + posX + ", " + posY + ")");
                    updateDronePosition(id, posX, posY);
                    droneControllRoom.repaint();
                    
                } else if ("shutdown".equalsIgnoreCase(data)) {
                    keepRunning = false;
                } else {
                    System.out.println("Message Received: " + data);
                    if (data.startsWith("DroneUpdate:")) {
                        String[] parts = data.substring("DroneUpdate:".length()).split(",");
                        int id = Integer.parseInt(parts[0].trim());
                        int posX = Integer.parseInt(parts[1].trim());
                        int posY = Integer.parseInt(parts[2].trim());
                        updateDronePosition(id, posX, posY);
                        droneControllRoom.repaint();
                    }
                }
            }

        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {

            }
        }
    }

    private void updateDronePosition(int id, int posX, int posY) {
        for (Drone drone : droneControllRoom.droneListArray) {
            if (drone.getID() == id) {
                drone.setPosX(posX);
                drone.setPosY(posY);
                break;
            }
        }
    }

    public void addMessage(String message) {
        synchronized (messageOutputText) {
            messageOutputText.append(message + "\n");
        }
    }
}
