package command;

import static command.CommandData.*;
import static command.DeviceData.*;
import static command.RemoteControlData.RemoteControl;

public class Example {

    public static void run() {
        Light light = new Light();
        Radio radio = new Radio();
        AirConditioner airConditioner = new AirConditioner();

        ICommand lightOnCommand = new LightOnCommand(light);
        ICommand lightOffCommand = new LightOffCommand(light);

        ICommand radioOnCommand = new RadioOnCommand(radio);
        ICommand radioOffCommand = new RadioOffCommand(radio);

        ICommand airConditionerOnCommand = new AirConditionerOnCommand(airConditioner);
        ICommand airConditionerOffCommand = new AirConditionerOffCommand(airConditioner);

        RemoteControl remoteControl = new RemoteControl(7);
        remoteControl.setCommand(0, lightOnCommand, lightOffCommand);
        remoteControl.setCommand(1, radioOnCommand, radioOffCommand);
        remoteControl.setCommand(2, airConditionerOnCommand, airConditionerOffCommand);

        remoteControl.onButtonPressed(1);
        remoteControl.undoButtonPressed();

        remoteControl.offButtonPressed(2);
        remoteControl.undoButtonPressed();
    }
}
