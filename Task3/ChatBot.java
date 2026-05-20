package Task3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatBot {

    private static final String URL = "jdbc:mysql://localhost:3306/task3";
    private static final String USER = "root";
    private static final String PASS = "Bairo@123";

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("AI Chatbot Window");
        frame.setSize(450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Chat History Area
        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 15));
        chatArea.append("Bot: Hello! How can I help you today?\n");
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input Panel at the Bottom
        JPanel inputPanel = new JPanel(new BorderLayout());
        
        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 15));
        
        JButton sendButton = new JButton("Send");
        sendButton.setPreferredSize(new Dimension(80, 40)); // Explicitly size the button

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Force the window to lay everything out properly
        frame.revalidate();
        frame.repaint();

        ActionListener sendAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText().trim();
                if (userInput.isEmpty()) return;

                chatArea.append("\nYou: " + userInput);
                inputField.setText(""); 

                if (userInput.toLowerCase().equals("bye")) {
                    chatArea.append("\nBot: bye!");
                    return;
                }

                boolean found = false;
                try (Connection con = DriverManager.getConnection(URL, USER, PASS);
                     Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT keywords, answer FROM chatbot")) {

                    while (rs.next()) {
                        String keywords = rs.getString("keywords");

                        if (keywords != null) {
                            String[] keywordArray = keywords.split(",");

                            for (String word : keywordArray) {
                                if (userInput.toLowerCase().contains(word.trim().toLowerCase())) {
                                    chatArea.append("\nBot: " + rs.getString("answer"));
                                    found = true;
                                    break;
                                }
                            }
                        }
                        if (found) break;
                    }

                } catch (Exception ex) {
                    chatArea.append("\nSystem Error: " + ex.getMessage());
                }

                if (!found) {
                    chatArea.append("\nBot: Sorry, I don't understand.");
                }
            }
        };

        sendButton.addActionListener(sendAction);
        inputField.addActionListener(sendAction);

        // Center the window on your screen and open it
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}