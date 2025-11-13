import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
// import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;

public class NoteTab extends TabFrameTemplate {
    private JTextField noteTitleField;
    private JTextArea noteContentsField;
    private JPanel notesListPanel, noteEditorPanel, noteControlsPanel, listButtons;

    private Font pageFont = new Font("Segoe UI", Font.PLAIN, 13);

    private String savedNotes[] = {"Shopping List", "Sandwich Ideas", "Add-Ins For That One Salad", "Foods to try", "test5", "test6"};
    // private Note savedNotes[] = {new Note("Shopping List", ""), new Note("Sandwich Ideas", ""), new Note("Add-Ins For That One Salad", ""), new Note("Foods to try", ""), new Note("test5", ""), new Note("test6", "")};
    private Note currentNote;

    @Override
    public void initializeTabContents() {
        this.mainPanel.setLayout(new BorderLayout(10, 10));
        
        // list of notes to view
        notesListPanel = new JPanel();
        notesListPanel.setLayout(new BorderLayout());
        notesListPanel.setBorder(new TitledBorder("Notes"));
        // notesListPanel.setMinimumSize(new Dimension(300, 100));
        
        // searchResults
        // JPanel listPanel = new JPanel();
        // listPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        /*
        ArrayList<String> temp = new ArrayList<>();
        for (Note note : savedNotes) {
            temp.add(note.title);
        }

        String noteTitles[] = temp.toArray();
        JList<String> notesList = new JList<String>(noteTitles);
        */
        
        JList<String> notesList = new JList<String>(savedNotes);
        notesList.setFont(pageFont);
        notesListPanel.add(notesList, BorderLayout.CENTER);
        
        // buttons for deleting, opening
        listButtons = new JPanel();

        JButton openNoteButton = new JButton("Open");
        JButton newNoteButton = new JButton("New");
        JButton deleteNoteButton = new JButton("Delete");
        
        listButtons.add(openNoteButton, BorderLayout.SOUTH);
        listButtons.add(newNoteButton, BorderLayout.SOUTH);
        listButtons.add(deleteNoteButton, BorderLayout.SOUTH);

        notesListPanel.add(listButtons, BorderLayout.SOUTH);
        
        this.mainPanel.add(notesListPanel, BorderLayout.WEST);
        
        // note editing/viewing interface
        noteEditorPanel = new JPanel();
        noteEditorPanel.setLayout(new BorderLayout(10, 10));
        noteEditorPanel.setBorder(new TitledBorder("Current Note"));
        
        // title
        noteTitleField = new JTextField();
        noteTitleField.setFont(pageFont);
        noteTitleField.setToolTipText("Note Title");
        noteEditorPanel.add(noteTitleField, BorderLayout.NORTH);

        // contents
        noteContentsField = new JTextArea();
        // noteContentsField.setEditable(false);
        noteEditorPanel.add(noteContentsField, BorderLayout.CENTER);

        // save, cancel, etc. buttons
        noteControlsPanel = new JPanel(new FlowLayout());

        JButton saveButton = new JButton("Save Note");
        JButton clearButton = new JButton("Clear");

        noteControlsPanel.add(saveButton);
        noteControlsPanel.add(clearButton);
        
        noteEditorPanel.add(noteControlsPanel, BorderLayout.SOUTH);
        
        this.mainPanel.add(noteEditorPanel, BorderLayout.CENTER);

        // binding the buttons to actions
        saveButton.addActionListener(e -> saveNote());
        clearButton.addActionListener(e -> clearNote());
        openNoteButton.addActionListener(e -> openNote());
        deleteNoteButton.addActionListener(e -> deleteNote());
        newNoteButton.addActionListener(e -> newNote());
    }

    public void saveNote() {
        System.out.println("This is a test of the save function.");
    }

    public void clearNote() {
        System.out.println("This is a test of the clear function.");
    }

    public void deleteNote() {
        System.out.println("This is a test of the delete note function.");
    }

    public void openNote() {
        System.out.println("This is a test of the open note function.");
    }

    public void newNote() {
        System.out.println("This is a test of the new note function.");
    }

    public class Note {
        public String title = "";
        public String contents = "";

        public Note(String title, String contents) {
            this.title = title;
            this.contents =contents;
        }
    }
}
