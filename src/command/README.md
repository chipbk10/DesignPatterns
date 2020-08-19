### Command Pattern

- allows to convert the requests or simple operations into objects
- benefits: storing command history, allow undo, redo, queue, schedule, or remote executions

### Example

- A remote control can control many devices with on and off buttons.
- The remote control should not know of context of each device (how the device works).
- Encapsulate the on/off operation with the device's context in an object (a command) 