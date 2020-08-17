### Observer pattern
- allows many listener objects to respond to changes of a particular object

### Event Bus pattern
- the subscribers do not know anything about publisher.
- the event bus encapsulates how objects communicate. It plays like a mediator.

### Example

- Producer-Consumer problem describes 2 processes:
- The producer’s job is to generate data, put it into the storage, and start again.
- At the same time, the consumer is consuming the data (i.e. removing it from the storage), one piece at a time.
- Note: To make sure that the producer won’t try to add data into the storage if it’s full and that the consumer won’t try to remove data from an empty storage.