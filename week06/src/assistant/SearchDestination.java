package assistant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Tests the functionality of class FHmapSC and class WordAssistant.
 * Specifically checks for implementation of containsKey() function to return an object
 * associated with a given key input.
 *
 * @author Foothill College, Clara Manolache
 */
public class SearchDestination
{
    public final static String DEFAULT_RESULT_MESSAGE = "[No results found.]";
    public final static String DEFAULT_INPUT_MESSAGE = "[Waiting for input.]";

    /**
     * Instantiates a simple GUI that enables the user to search for a word.
     * Uses Swing to create a JFrame where the user can enter their search.
     * The result of the search is displayed as a list of descriptions.
     * @param args not used
     */
    public static void main(String [] args)
    {
        // NOTE: Make sure to use *relative* path instead of specifying the entire path.
        //       Otherwise, your program will result in run time errors when the instructor
        //       tests your implementation.
        final String FILEPATH = "resources/destinations.txt";
        // String FILEPATH = "resources/friendsAndPlaces.txt";
        FHlinkedList<Word> words = FileReader.readWords(FILEPATH);

        WordAssistant assistant = new WordAssistant(words);

        // Creates GUI to take user input
        JFrame frame = new JFrame("Word Assistant");
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(400,new JTextField().getPreferredSize().height));
        JLabel labelForPrompt01 = new JLabel("Type in the text you want to search for.");
        JLabel labelForPrompt02 = new JLabel("Then hit the return key.");
        labelForPrompt01.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelForPrompt02.setAlignmentX(Component.LEFT_ALIGNMENT);

        StringBuffer typedWord = new StringBuffer();
        JLabel labelForTyped = new JLabel();
        labelForTyped.setAlignmentX(Component.LEFT_ALIGNMENT);
        labelForTyped.setText(DEFAULT_INPUT_MESSAGE);

        // Find the typed sequence in the table
        FHlinkedList<String> result  = new FHlinkedList<>();
        JTextArea textAreaForSearchResult = new JTextArea();
        textAreaForSearchResult.setEnabled(false);
        textAreaForSearchResult.setAlignmentX(Component.LEFT_ALIGNMENT);
        textAreaForSearchResult.setText(DEFAULT_RESULT_MESSAGE);

        JButton buttonForClear = new JButton("Clear");
        buttonForClear.addActionListener(e -> {
            typedWord.delete(0,typedWord.length());
            textField.setText("");
            textAreaForSearchResult.setText(DEFAULT_RESULT_MESSAGE);
            labelForTyped.setText(DEFAULT_INPUT_MESSAGE);
        });

        pane.add(Box.createRigidArea(new Dimension(10,10)));
        pane.add(labelForPrompt01);
        pane.add(labelForPrompt02);
        pane.add(Box.createRigidArea(new Dimension(10,10)));
        JPanel searchPane = new JPanel();
        searchPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPane.setLayout(new BoxLayout(searchPane, 0));
        searchPane.add(textField);
        searchPane.add(buttonForClear);
        pane.add(searchPane);
        pane.add(Box.createRigidArea(new Dimension(10,10)));
        pane.add(labelForTyped);
        pane.add(Box.createRigidArea(new Dimension(10,10)));
        pane.add(textAreaForSearchResult);
        pane.add(Box.createRigidArea(new Dimension(10,10)));

        //Display the window.
        frame.add(pane);
        frame.setVisible(true);

        // Adding the keyListener
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e)
            {
                char typed = e.getKeyChar();
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    e.consume();
                }
                else if (Character.isLetterOrDigit(typed) || Character.isSpaceChar(typed)) {

                    String typedStr = new String("" + e.getKeyChar());
                    typedWord.append(typedStr.toLowerCase());
                    labelForTyped.setText("");
                }
            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.isActionKey()) System.exit(0);
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                {
                    char released = e.getKeyChar();
                    if (typedWord.length() > 0) {
                        typedWord.deleteCharAt(typedWord.length() - 1);
                        labelForTyped.setText("");
                        String message = processSearch(typedWord.toString(), assistant);
                        System.out.println(message);
                        textAreaForSearchResult.setText(message.toString());
                    }
                    else
                        labelForTyped.setText(DEFAULT_INPUT_MESSAGE);
                } // process delete key
            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    char released = e.getKeyChar();
                    e.consume();
                    String sequence = typedWord.toString().strip();
                    String message = processSearch(sequence, assistant);
                    System.out.println(message);
                    textAreaForSearchResult.setText(message.toString());
                } // process return key
            }
        });
    }

    private static String processSearch(String requestedSequence, WordAssistant assistant)
    {
        ArrayList<Word> searchResult = assistant.findSequenceInTable(requestedSequence);
        StringBuffer message = new StringBuffer();
        if (requestedSequence.length() > 0 && searchResult != null)
        {
            for (Word currentWord : searchResult)
                message.append(currentWord + "\n");
        }
        else
        {
            message = new StringBuffer(DEFAULT_RESULT_MESSAGE);
        }
        return message.toString();
    }
}
