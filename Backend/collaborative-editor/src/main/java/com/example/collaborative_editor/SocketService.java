package com.example.collaborative_editor;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;

@Service
public class SocketService {
    public void sendSocketChanges(SocketIOClient senderClient, Object data) {
        senderClient.getNamespace().getBroadcastOperations().sendEvent("receive-changes", senderClient, data);
    }
}
