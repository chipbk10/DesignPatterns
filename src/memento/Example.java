package memento;

import static memento.Data.*;

public class Example {

    public static void run() {
        TextEditor textEditor = new TextEditor("Design Patterns:");
        EditorHistory history = new EditorHistory(textEditor);

        // (1)
        textEditor.append("Memento");
        textEditor.print();
        history.snapshot();

        // (2)
        textEditor.appendBold("pattern");
        textEditor.print();
        history.snapshot();

        // (3)
        textEditor.appendItalic("is awesome");
        textEditor.print();
        history.snapshot();

        history.undo();
        textEditor.print();

        // (3)
        textEditor.appendCapital("is so confused!!!");
        textEditor.rename("Memento Pattern");
        textEditor.print();
        history.snapshot();

        // (1)
        history.rollback(3);
        textEditor.print();

    }
}
