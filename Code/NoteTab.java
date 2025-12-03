import note.Note;

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
import java.util.List;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class NoteTab extends TabFrameTemplate {
    private JTextField noteTitleField;
    private JTextArea noteContentsField;
    private JPanel notesListPanel, noteEditorPanel, noteControlsPanel, listButtons;

    private Font pageFont = new Font("Segoe UI", Font.PLAIN, 15);

    // private String savedNotes[] = {"Shopping List", "Sandwich Ideas", "Add-Ins For That One Salad", "Foods to try", "test5", "test6"};
    // private note.Note savedNotes[] = {new note.Note("Shopping List", " - cabbage\n - apples\n - a half gallon of milk"), new note.Note("Sandwich Ideas", ""), new note.Note("Add-Ins For That One Salad", ""), new note.Note("Foods to try", ""), new note.Note("test5", ""), new note.Note("test6", "")};
    private List<Note> savedNotes = new ArrayList<Note>(Arrays.asList(new Note("Shopping List", " - cabbage\n - apples\n - a half gallon of milk"), new Note("Sandwich Ideas", ""), new Note("Add-Ins For That One Salad", ""), new Note("Foods to try", ""), new Note("test5", ""), new Note("test6", "")));
    private Note currentNote;

    private JList<String> notesList = new JList<>();

    @Override
    public void initializeTabContents() {
        mainPanel.setLayout(new BorderLayout(10, 10));
        
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
        
        listButtons.add(openNoteButton);
        listButtons.add(newNoteButton);
        listButtons.add(deleteNoteButton);

        notesListPanel.add(listButtons, BorderLayout.SOUTH);
        
        mainPanel.add(notesListPanel, BorderLayout.WEST);
        
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
        
        mainPanel.add(noteEditorPanel, BorderLayout.CENTER);

        // binding the buttons to actions
        saveButton.addActionListener(e -> saveNote());
        clearButton.addActionListener(e -> clearNote());
        openNoteButton.addActionListener(e -> openNote());
        deleteNoteButton.addActionListener(e -> deleteNote());
        newNoteButton.addActionListener(e -> newNote());
    }

    public void setNoteList(List<Note> Notes) {
        savedNotes = Notes;
        // take in an list of notes, put their titles in a list display it on the page
        List<String> temp = new ArrayList<>();
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
        // System.out.println(currentNote.title + " - " + currentNote.contents);

        if (currentNote.isEmpty()) {
            // System.out.println("Empty note.Note");
            JOptionPane.showMessageDialog(mainPanel, "Empty Note.");
        } else {
            // System.out.println(currentNote.title + " - " + currentNote.title.length());

            /* 
                check if the note is already in the list(compare objects? compare titles?) before saving it
                
                can't get it to work? it keeps saying that the objects and even strings are not equal
            */
            int foundNoteIndex = -1;
            for (int i = 0; i < savedNotes.size(); i++) {
                // System.out.println(savedNotes.get(i).title + " - " + currentNote.title);
                // if(currentNote.equals(savedNotes.get(i))) {
                if(currentNote.title.equals(savedNotes.get(i).title)) {
                    foundNoteIndex = i;
                }
            }
    
            if (foundNoteIndex == -1) { // if we didn't find a note with the same name, save the note
                savedNotes.add(currentNote);
                setNoteList(savedNotes);
            }
            // else if (!currentNote.contents.equals(savedNotes.get(foundNoteIndex).contents)) {
            //     // if we found a note with the same name, but different contents, save with number added
            // }
            else { // if we find a note with the same name, overwrite its contents with the current one
                savedNotes.set(foundNoteIndex, currentNote);
            }
        }
    }

    public void clearNote() {
        // set the note fields to an empty string
        noteTitleField.setText("");
        noteContentsField.setText("");
        currentNote = new Note("", "");
    }

    public void deleteNote() {
        // delete the note selected from the list and update the displayed list
        int noteIndex = notesList.getSelectedIndex();
        // String noteTitle = (String) notesList.getSelectedValue();
        // System.out.println("Selected note index: " + noteIndex + " and title: " + noteTitle);

        if (noteIndex == -1) {
            JOptionPane.showMessageDialog(mainPanel, "Please select a note.");
        }
        else {
            savedNotes.remove(noteIndex);
            setNoteList(savedNotes);
        }
    }

    public void openNote() {
        // System.out.println("This is a test of the open note function.");
        // https://examples.javacodegeeks.com/java-development/desktop-java/swing/jlist/get-selected-value-from-jlist/
        int noteIndex = notesList.getSelectedIndex();
        // String noteTitle = (String) notesList.getSelectedValue();
        // System.out.println("Selected note index: " + noteIndex + " and title: " + noteTitle);

        if (noteIndex == -1) {
            JOptionPane.showMessageDialog(mainPanel, "Please select a note.");
        }
        else {
            // currentNote = savedNotes[noteIndex];
            currentNote = savedNotes.get(noteIndex);
            noteTitleField.setText(currentNote.title);
            noteContentsField.setText(currentNote.contents);
        }
    }

    public void newNote() {
        // System.out.println("This is a test of the new note function.");
        clearNote();
    }
}
