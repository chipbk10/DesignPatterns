package command;

import static command.DeviceData.*;

public class CommandData {

    public interface ICommand {
        void execute();
        void undo();
    }

    public static class NoCommand implements ICommand {
        @Override
        public void execute() {}

        @Override
        public void undo() {}
    }

    public static class LightOnCommand implements ICommand {
        private Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.on();
        }

        @Override
        public void undo() {
            light.off();
        }
    }

    public static class LightOffCommand implements ICommand {
        private Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.off();
        }

        @Override
        public void undo() {
            light.on();
        }
    }

    public static class RadioOnCommand implements ICommand {
        private Radio radio;

        public RadioOnCommand(Radio radio) {
            this.radio = radio;
        }

        @Override
        public void execute() {
            radio.on();
            radio.setChannel("Favorite Channel");
            radio.setVolume(11);
        }

        @Override
        public void undo() {
            radio.off();
        }
    }

    public static class RadioOffCommand implements ICommand {
        private Radio radio;

        public RadioOffCommand(Radio radio) {
            this.radio = radio;
        }

        @Override
        public void execute() {
            radio.off();
        }

        @Override
        public void undo() {
            radio.on();
            radio.setChannel("Favorite Channel");
            radio.setVolume(11);
        }
    }

    public static class AirConditionerOnCommand implements ICommand {
        private AirConditioner airConditioner;

        public AirConditionerOnCommand(AirConditioner airConditioner) {
            this.airConditioner = airConditioner;
        }

        @Override
        public void execute() {
            airConditioner.on();
            airConditioner.turnOnFan();
            airConditioner.setTemperature(20);
        }

        @Override
        public void undo() {
            airConditioner.off();
        }
    }

    public static class AirConditionerOffCommand implements ICommand {
        private AirConditioner airConditioner;

        public AirConditionerOffCommand(AirConditioner airConditioner) {
            this.airConditioner = airConditioner;
        }

        @Override
        public void execute() {
            airConditioner.turnOffFan();
            airConditioner.off();
        }

        @Override
        public void undo() {
            airConditioner.on();
            airConditioner.turnOnFan();
            airConditioner.setTemperature(20);
        }
    }
}
