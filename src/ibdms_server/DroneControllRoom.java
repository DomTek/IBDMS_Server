package ibdms_server;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class DroneControllRoom extends JPanel {
    private Image backgroundImage;
    JTextArea messageOutputText = new JTextArea();

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
        
        //Draw a blue rectangle on the background image        
        g.setColor(Color.BLUE);
        g.fillRect(300, 200, 50, 25);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString("Drone 02", 300, 210);
        
        
        // Draw a blue rectangle on the background image
        g.setColor(Color.BLUE);
        g.fillRect(200, 100, 50, 25);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString("Drone 01", 200, 110);
        
        
        // Draw a red circle with text on the background image
        g.setColor(Color.RED);
        g.fillOval(350, 300, 50, 50);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Fire", 355, 330);        

        // Draw a blue rectangle on the background image
        g.setColor(Color.BLUE);
        g.fillRect(400, 300, 50, 25);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString("Drone 03", 400, 310);
    }

    private java.awt.Dimension getBackgroundImageSize() {
        return new java.awt.Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null));
    }

    public void appendMessage(String message) {
        messageOutputText.append(message + "\n");
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    public static void createAndShowGUI() {
               ArrayList<String> droneList = new ArrayList<String>();
        droneList.add("Drone 1");
        droneList.add("Drone 2");
        droneList.add("Drone 3");
        
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
        
        JComboBox<String> droneListDisplay = new JComboBox<String>(droneList.toArray(new String[0]));
        JComboBox<String> fireListDisplay = new JComboBox<String>(fireList.toArray(new String[0]));

               
        JFrame frame = new JFrame("Display Objects on Background");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a new JPanel for the left side panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.GRAY);
        leftPanel.setLayout(new GridBagLayout());
        
        
   
        // Add the left side panel and DroneControllRoom panel to the frame
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(new DroneControllRoom(), BorderLayout.CENTER);
        
        // Creating Controll interface  Laytout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        gbc.gridx=0;
        gbc.gridy=0;
        leftPanel.add(title, gbc);
        
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.anchor = GridBagConstraints.WEST;
        leftPanel.add(droneSelectLable, gbc); 
        
        gbc.gridx=1;
        gbc.gridy=1;
        leftPanel.add(CoordinatesLable, gbc);

        gbc.gridx=1;
        gbc.gridy=2;
        leftPanel.add(xLable, gbc);
        
        
        gbc.gridx=2;
        gbc.gridy=2;
        leftPanel.add(xCorTextField, gbc);
        xCorTextField.setColumns(2);
        
        gbc.gridx=1;
        gbc.gridy=3;
        leftPanel.add(yLable, gbc);
        
        gbc.gridx=2;
        gbc.gridy=3;
        leftPanel.add(yCorTextField, gbc);
        yCorTextField.setColumns(2);
        
        
        gbc.gridx=0;
        gbc.gridy=2;
        leftPanel.add(droneListDisplay, gbc);
        droneListDisplay.setPreferredSize(new Dimension(100, 20));
        
        gbc.gridx=0;
        gbc.gridy=4;               
        leftPanel.add(moveDroneB, gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        leftPanel.add(separator, gbc);
        separator.setPreferredSize(new Dimension(200, 5));
        
        
        gbc.gridx=0;
        gbc.gridy=6;
        leftPanel.add(fireSelectLable, gbc);
        
        
        gbc.gridx=0;
        gbc.gridy=7;
        leftPanel.add(fireListDisplay, gbc);
        
        gbc.gridx=1;
        gbc.gridy=7;
        leftPanel.add(removeFireB, gbc);
        
        
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        leftPanel.add(separator2, gbc);
        separator2.setPreferredSize(new Dimension(200, 5));
        
        gbc.gridx=0;
        gbc.gridy=9;
        leftPanel.add(message, gbc);
        
        
        gbc.gridx=0;
        gbc.gridy=10;
        leftPanel.add(messageOutputText, gbc);
        messageOutputText.setPreferredSize(new Dimension(200, 200));        
                       
        
        gbc.gridx=0;
        gbc.gridy=11;
        leftPanel.add(ReturnB, gbc);
        
        gbc.gridx=1;
        gbc.gridy=11;
        leftPanel.add(ShutDownB, gbc);
        
       

        DroneControllRoom droneControlRoomInstance = new DroneControllRoom();
        frame.add(droneControlRoomInstance, BorderLayout.CENTER);

        // ...

        frame.pack();
        frame.setVisible(true);

        try {
            int serverPort = 8888;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket, droneControlRoomInstance);
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
    DroneControllRoom droneControlRoom;

    public Connection(Socket aClientSocket, DroneControllRoom droneControlRoom) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.droneControlRoom = droneControlRoom;
            this.start();
        } catch (IOException e) {
            droneControlRoom.appendMessage("Connection:" + e.getMessage());
        }
    }

    public void run() {
        try {
            String data = in.readUTF();
            System.out.println(data);
            if(data.equals("New Drone")) {
                System.out.println("there is a new drone");
                ArrayList<Drone> droneList = new ArrayList<>();
                newDrone(droneList);
                        }
            else
                System.out.println("no new drone found");
            
            out.writeUTF("completed");
         //   droneControlRoom.appendMessage(data);            
        } catch (EOFException e) {
           droneControlRoom.appendMessage("EOF:" + e.getMessage());
        } catch (IOException e) {
            droneControlRoom.appendMessage("IO:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
            }
        }

    }
//
//    public static void newDrone() {
//        String droneName = JOptionPane.showInputDialog(null, "Please enter the drone name:", "Drone Name", JOptionPane.QUESTION_MESSAGE);
//        int droneID = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the drone ID:", "Drone ID", JOptionPane.QUESTION_MESSAGE));
//        int posX = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the drones X position:", "Drone ID", JOptionPane.QUESTION_MESSAGE));
//        int posY = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the drones Y position:", "Drone ID", JOptionPane.QUESTION_MESSAGE));
//        Drone droneOne = new Drone(droneID, droneName, posX, posY);
//    }
    
    
    // This methodes calls for the popups for the user to define the Drones ID, Name and current position. This is then Stored in an Array list
    public static void newDrone(ArrayList<Drone> droneList) {
        String droneName = JOptionPane.showInputDialog(null, "Please enter the drone name:", "Drone Name", JOptionPane.QUESTION_MESSAGE);
        int droneID = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the drone ID:", "Drone ID", JOptionPane.QUESTION_MESSAGE));
        int posX = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the drone's X position:", "Drone X Position", JOptionPane.QUESTION_MESSAGE));
        int posY = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the drone's Y position:", "Drone Y Position", JOptionPane.QUESTION_MESSAGE));
        Drone newDrone = new Drone(droneID, droneName, posX, posY);
        droneList.add(newDrone);
    }

}
