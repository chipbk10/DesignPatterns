### Chain Of Responsibility Pattern

- to avoid the `if else if else if else...` conditions checking

- allow to pass requests along a chain of handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.

### Chain Of Responsibility vs Decorator

- the difference being that for the decorator, all classes handle the request, while for the chain of responsibility, exactly one of the classes in the chain handles the request. 