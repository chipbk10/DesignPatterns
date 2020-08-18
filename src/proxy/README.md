### Proxy Pattern

- providing a placeholder object (with the same interface), in order to control access to the original object.

### Applicability

- **Lazy initialization**: Instead of creating the object when the app launches, you can delay the object’s initialization to a time when it’s really needed.
- **Access control**: This is when you want only specific clients to be able to use the service object. The proxy can pass the request to the service object only if the client’s credentials match some criteria.
- **Logging request**: This is when you want to keep a history of requests to the service object.
- **Caching**: The proxy can implement caching for recurring requests that always yield the same results. The proxy may use the parameters of requests as the cache keys.
- **Smart Reference**: The proxy can keep track of clients that obtained a reference to the service object. From time to time, the proxy may go over the clients and check whether they are still active. If the client list gets empty, the proxy might dismiss the service object and free the underlying system resources.

### References

- [Proxy: Pros && Cons](https://bit.ly/3iWIREt)