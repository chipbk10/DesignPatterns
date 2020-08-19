package command;

import static command.CommandData.ICommand;
import static command.CommandData.NoCommand;
import static lib.Printer.println;

public class RemoteControlData {

    public static class RemoteControl {

        int maxDevices;
        ICommand[] onCommands;
        ICommand[] offCommands;
        ICommand curCommand;

        public RemoteControl(int maxDevices) {
            this.maxDevices = maxDevices;
            onCommands = new ICommand[maxDevices];
            offCommands = new ICommand[maxDevices];

            ICommand noCommand = new NoCommand();
            for (int i = 0; i < maxDevices; i++) {
                onCommands[i] = offCommands[i] = noCommand;
            }
        }

        public void setCommand(int slot, ICommand onCommand, ICommand offCommand) {
            if (slot < 0 || slot >= maxDevices) return;
            onCommands[slot] = onCommand;
            offCommands[slot] = offCommand;
        }

        public void onButtonPressed(int slot) {
            println("\nOn Button is pressed ... \n");
            if (slot < 0 || slot >= maxDevices) return;
            curCommand = onCommands[slot];
            curCommand.execute();
        }

        public void offButtonPressed(int slot) {
            println("\nOff Button is pressed ... \n");
            if (slot < 0 || slot >= maxDevices) return;
            curCommand = offCommands[slot];
            curCommand.execute();
        }

        public void undoButtonPressed() {
            println("\nUndo Button is pressed ... \n");
            curCommand.undo();
        }
    }

}
