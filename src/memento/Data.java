package memento;

import java.util.Stack;

import static lib.Printer.newLine;
import static lib.Printer.println;

public class Data {

    public interface Editor {
        Memento save();
        void restore(Memento memento);
    }

    public static class TextEditor implements Editor {

        private static final String EMPTY = "", SPACE = " ";
        private State state;

        public TextEditor(String editorName) {
            state = new State(editorName, EMPTY);
        }

        public void append(String text) {
            state.content += SPACE + text;
        }

        public void appendBold(String text) {
            state.content += SPACE + "<b>" + text + "</b>";
        }

        public void appendItalic(String text) {
            state.content += SPACE + "<i>" + text + "</i>";
        }

        public void appendCapital(String text) {
            state.content += SPACE + text.toUpperCase();
        }

        public void rename(String editorName) {
            state.editorName = editorName;
        }

        public void print() {
            println("Editor: " + state.editorName);
            println(state.content);
            newLine();
        }

        @Override
        public Memento save() {
            return new Memento(state.clone());
        }

        @Override
        public void restore(Memento memento) {
            state = (State)memento.getSnapshot();
        }

        private class State {
            private String editorName;
            private String content;

            public State(String name, String content) {
                this.editorName = name;
                this.content = content;
            }

            public State clone() {
                return new State(editorName, content);
            }
        }
    }

    public static class Memento<T> {

        private T snapshot;

        public Memento(T snapshot) {
            this.snapshot = snapshot;
        }

        public T getSnapshot() {
            return snapshot;
        }
    }

    public static class EditorHistory {

        private Editor editor;
        private Stack<Memento> stack = new Stack<>();

        public EditorHistory(Editor editor) {
            this.editor = editor;
        }

        public void snapshot() {
            Memento memento = editor.save();
            stack.push(memento);
        }

        public void rollback(int i) {
            while (!stack.isEmpty() && i-- >= 0) {
                Memento memento = stack.pop();
                editor.restore(memento);
            }
        }

        public void undo() {
            rollback(1);
        }
    }
}
