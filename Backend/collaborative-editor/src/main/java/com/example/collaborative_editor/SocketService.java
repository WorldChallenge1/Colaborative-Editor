package com.example.collaborative_editor;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;

@Service
public class SocketService {
    public void sendSocketChanges(SocketIOClient senderClient, Data data) {
        // senderClient.getNamespace().getBroadcastOperations().sendEvent("receive-changes",
        // senderClient, data);

        String documentId = data.getDocumentID();

        for (SocketIOClient client : senderClient.getNamespace().getRoomOperations(documentId).getClients()) {
            if (!client.getSessionId().equals(senderClient.getSessionId())) {
                client.sendEvent("receive-changes", data);
            }
        }

    }

    public void joinDocumentRoom(SocketIOClient client, String documentId) {
        client.joinRoom(documentId);
    }

    public void getDocument(SocketIOClient client, String documentId) {
        this.joinDocumentRoom(client, documentId);
        client.sendEvent("load-document", "");
    }
}
