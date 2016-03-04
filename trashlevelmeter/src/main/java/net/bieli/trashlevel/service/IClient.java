package net.bieli.trashlevel.service;


public interface IClient {
    public void connect(String host);
    public boolean publish(String topicEndpoint, String messageBody);
    public String subscribe(String topicEndpoint);

    void disconnect();
}
