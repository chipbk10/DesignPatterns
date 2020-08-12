### Singleton Pattern

- The Singleton design pattern ensures that only one instance exists for a given class and that there’s a global access point to that instance. It usually uses lazy loading to create the single instance when it’s needed the first time.

- The most common reason why singleton’s are problematic is testing. If you have state being stored in a global object like a singleton then order of tests can matter, and it can be painful to mock them. Both of these reasons make testing a pain.