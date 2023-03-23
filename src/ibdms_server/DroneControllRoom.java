
package ibdms_server;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DroneControllRoom extends JPanel {
    
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
        
        // Draw a blue rectangle on the background image
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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Display Objects on Background");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create a new JPanel for the left side panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE);
        leftPanel.add(new JLabel("Drone Control Room", JLabel.CENTER));
        
        // Add the left side panel and DroneControllRoom panel to the frame
        frame.add(leftPanel, BorderLayout.WEST);
        frame.add(new DroneControllRoom(), BorderLayout.CENTER);
        
        frame.pack();
        frame.setVisible(true);
    }
}