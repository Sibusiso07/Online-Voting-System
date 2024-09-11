import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VotingSystem extends JFrame {
    // Define text fields for user input
    private JTextField nameField, phoneField, idField;
    // Define radio buttons for selecting parties
    private JRadioButton genZButton, millennialsButton, genAlphaButton;
    // Define buttons for submitting the vote and checking results
    private JButton submitButton, checkResultsButton;
    // Initialize vote counters for each party
    private int genZVotes = 0, millennialsVotes = 0, genAlphaVotes = 0;

    // Constructor to set up the GUI components
    public VotingSystem() {
        setTitle("Online Voting System");
        setSize(500, 400); // Set the size of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application when the JFrame is closed
        setLayout(new BorderLayout(10, 10)); // Border layout with padding between components

        // Panel for user information fields
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 5, 5)); // 3 rows, 2 columns, with 5-pixel padding
        infoPanel.add(new JLabel("Name:")); // Add label for Name
        nameField = new JTextField(); // Text field for Name
        infoPanel.add(nameField);

        infoPanel.add(new JLabel("Phone Number:")); // Add label for Phone Number
        phoneField = new JTextField(); // Text field for Phone Number
        infoPanel.add(phoneField);

        infoPanel.add(new JLabel("ID Number:")); // Add label for ID Number
        idField = new JTextField(); // Text field for ID Number
        infoPanel.add(idField);

        add(infoPanel, BorderLayout.NORTH); // Add the info panel to the top of the main frame

        // Panel for radio buttons
        JPanel radioPanel = new JPanel(new GridLayout(3, 1, 1, 1)); // 3 rows, 1 column, minimal padding
        genZButton = new JRadioButton("Generation Z"); // Radio button for Generation Z
        millennialsButton = new JRadioButton("Millennials"); // Radio button for Millennials
        genAlphaButton = new JRadioButton("Generation Alpha"); // Radio button for Generation Alpha

        // Group the radio buttons to allow only one selection at a time
        ButtonGroup partyGroup = new ButtonGroup();
        partyGroup.add(genZButton);
        partyGroup.add(millennialsButton);
        partyGroup.add(genAlphaButton);

        // Add radio buttons to the panel
        radioPanel.add(genZButton);
        radioPanel.add(millennialsButton);
        radioPanel.add(genAlphaButton);
        add(radioPanel, BorderLayout.CENTER); // Add the radio button panel to the center of the main frame

        // Panel for action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Flow layout with horizontal and vertical padding
        submitButton = new JButton("Submit Your Vote"); // Button for submitting the vote
        submitButton.addActionListener(new SubmitButtonListener()); // Add action listener to handle submit button click
        buttonPanel.add(submitButton); // Add submit button to button panel

        checkResultsButton = new JButton("Check Results"); // Button for checking results
        checkResultsButton.addActionListener(new CheckResultsButtonListener()); // Add action listener to handle check results button click
        buttonPanel.add(checkResultsButton); // Add check results button to button panel
        add(buttonPanel, BorderLayout.SOUTH); // Add the button panel to the bottom of the main frame
    }

    // Inner class to handle Submit button clicks
    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Check if any party is selected; show a warning message if none are selected
            if (!genZButton.isSelected() && !millennialsButton.isSelected() && !genAlphaButton.isSelected()) {
                JOptionPane.showMessageDialog(null, "Select a Party");
                return;
            }

            // Increment the vote count based on the selected party
            if (genZButton.isSelected()) {
                genZVotes++;
            } else if (millennialsButton.isSelected()) {
                millennialsVotes++;
            } else if (genAlphaButton.isSelected()) {
                genAlphaVotes++;
            }

            // Show success message
            JOptionPane.showMessageDialog(null, "Vote Submitted Successfully!");
            clearFields(); // Clear the input fields after submission
        }
    }

    // Inner class to handle Check Results button clicks
    private class CheckResultsButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Prepare the results message
            String resultMessage = String.format(
                    "Generation Z: %d votes\nMillennials: %d votes\nGeneration Alpha: %d votes\n",
                    genZVotes, millennialsVotes, genAlphaVotes);

            // Determine the party with the most votes
            int maxVotes = Math.max(genZVotes, Math.max(millennialsVotes, genAlphaVotes));
            if (maxVotes == genZVotes) {
                resultMessage += "\nParty with the most votes: Generation Z";
            } else if (maxVotes == millennialsVotes) {
                resultMessage += "\nParty with the most votes: Millennials";
            } else {
                resultMessage += "\nParty with the most votes: Generation Alpha";
            }

            // Show the results in a dialog box
            JOptionPane.showMessageDialog(null, resultMessage);
        }
    }

    // Method to clear input fields after a vote is submitted
    private void clearFields() {
        nameField.setText(""); // Clear name field
        phoneField.setText(""); // Clear phone field
        idField.setText(""); // Clear ID field
        genZButton.setSelected(false); // Deselect Generation Z radio button
        millennialsButton.setSelected(false); // Deselect Millennials radio button
        genAlphaButton.setSelected(false); // Deselect Generation Alpha radio button
    }

    // Main method to launch the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VotingSystem frame = new VotingSystem();
            frame.setVisible(true); // Make the frame visible
        });
    }
}
