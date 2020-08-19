package command;

import static lib.Printer.println;

public class DeviceData {

    public static class Light {
        public void on() { println("Light is on"); }
        public void off() { println("Light is off"); }
    }

    public static class Radio {
        public void on() { println("Radio is on"); }
        public void off() { println("Radio is off"); }
        public void setChannel(String channelName) { println("Switch to channel " + channelName); }
        public void setVolume(Integer volume) { println("Set volume = " + volume); }
    }

    public static class AirConditioner {
        public void on() { println("Air Conditioner is on"); }
        public void off() { println("Air Conditioner is off"); }
        public void turnOnFan() { println("Fan is turned on"); }
        public void turnOffFan() { println("Fan is turned off"); }
        public void setTemperature(int temperature) { println("Set temperature = " + temperature); }
    }
}
