package com.example.collaborative_editor;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;

@Service
public class SocketService {
    public void sendSocketChanges(SocketIOClient senderClient, Object data) {
        for (SocketIOClient client : senderClient.getNamespace().getAllClients()) {
            if (!client.getSessionId().toString().equals(senderClient.getSessionId().toString())) {
                client.sendEvent("receive-changes", data);
            }
        }
    }
}
