import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
// import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JList;

public class NoteTab extends TabFrameTemplate {
    private JTextField noteTitleField;
    private JTextArea noteContentsField;
    private JPanel notesListPanel, noteEditorPanel, noteControlsPanel, listButtons;

    private Font pageFont = new Font("Segoe UI", Font.PLAIN, 15);

    // private String savedNotes[] = {"Shopping List", "Sandwich Ideas", "Add-Ins For That One Salad", "Foods to try", "test5", "test6"};
    // private Note savedNotes[] = {new Note("Shopping List", " - cabbage\n - apples\n - a half gallon of milk"), new Note("Sandwich Ideas", ""), new Note("Add-Ins For That One Salad", ""), new Note("Foods to try", ""), new Note("test5", ""), new Note("test6", "")};
    private ArrayList<Note> savedNotes = new ArrayList<Note>(Arrays.asList(new Note("Shopping List", " - cabbage\n - apples\n - a half gallon of milk"), new Note("Sandwich Ideas", ""), new Note("Add-Ins For That One Salad", ""), new Note("Foods to try", ""), new Note("test5", ""), new Note("test6", "")));
    private Note currentNote;

    private JList<String> notesList = new JList<>();

    @Override
    public void initializeTabContents() {
        this.mainPanel.setLayout(new BorderLayout(10, 10));
        
        // list of notes to view
        notesListPanel = new JPanel();
        notesListPanel.setLayout(new BorderLayout());
        notesListPanel.setBorder(new TitledBorder("Notes"));
        
        // JList<String> notesList = new JList<String>(savedNotes);
        setNoteList(savedNotes);
        notesList.setFont(pageFont);
        // notesListPanel.add(notesList, BorderLayout.CENTER);
        JScrollPane scrollablenotesList = new JScrollPane(notesList); // make it scrollable
        notesListPanel.add(scrollablenotesList, BorderLayout.CENTER);
        
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
        noteContentsField.setFont(pageFont);
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

    public void setNoteList(ArrayList<Note> Notes) {
        ArrayList<String> temp = new ArrayList<>();
        for (Note note : Notes) {
            temp.add(note.title);
        }
        String noteTitles[] = temp.toArray(new String[temp.size()]);

        // notesList = new JList<String>(noteTitles);
        notesList.setListData(noteTitles);
    }

    public void saveNote() {
        // System.out.println("This is a test of the save function.");
        String title = noteTitleField.getText();
        String contents = noteContentsField.getText();

        currentNote = new Note(title, contents);
        System.out.println(currentNote.title + " - " + currentNote.contents);

        if (currentNote.isEmpty()) {
            System.out.println("Empty Note");
        } else {
            /* 
                check if the note is already in the list(compare objects? compare titles?) before saving it
                
                can't get it to work? it keeps saying that the objects and even strings are not equal
            */
            int foundNoteIndex = -1;

            for (int i = 0; i < savedNotes.size(); i++) {
                // System.out.println(savedNotes.get(i).title + " - " + currentNote.title);
                // System.out.println((currentNote.title == savedNotes.get(i).title));
                System.out.println((currentNote.title.length() == savedNotes.get(i).title.length()));
                // if(currentNote.equals(savedNotes.get(i))) {
                if(currentNote.title == savedNotes.get(i).title) {
                    foundNoteIndex = i;
                }
            }
    
            if (foundNoteIndex == -1) {
                savedNotes.add(currentNote);
                setNoteList(savedNotes);
            }
            else {
                savedNotes.set(foundNoteIndex, currentNote);
            }
        }
    }

    public void clearNote() {
        // System.out.println("This is a test of the clear function.");

        noteTitleField.setText("");
        noteContentsField.setText("");
        currentNote = new Note("", "");
    }

    public void deleteNote() {
        // System.out.println("This is a test of the delete note function.");
        int noteIndex = notesList.getSelectedIndex();
        String noteTitle = (String) notesList.getSelectedValue();
        System.out.println("Selected note index: " + noteIndex + " and title: " + noteTitle);

        savedNotes.remove(noteIndex);
        setNoteList(savedNotes);
    }

    public void openNote() {
        // System.out.println("This is a test of the open note function.");
        // https://examples.javacodegeeks.com/java-development/desktop-java/swing/jlist/get-selected-value-from-jlist/
        int noteIndex = notesList.getSelectedIndex();
        String noteTitle = (String) notesList.getSelectedValue();
        System.out.println("Selected note index: " + noteIndex + " and title: " + noteTitle);

        // currentNote = savedNotes[noteIndex];
        currentNote = savedNotes.get(noteIndex);
        noteTitleField.setText(currentNote.title);
        noteContentsField.setText(currentNote.contents);
    }

    public void newNote() {
        System.out.println("This is a test of the new note function.");
        clearNote();
    }
}
