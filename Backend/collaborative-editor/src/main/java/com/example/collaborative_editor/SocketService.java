package com.example.collaborative_editor;

import java.util.List;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;
import com.example.collaborative_editor.data.Data;
import com.example.collaborative_editor.data.DataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final DataService dataService;

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
        List<Data> data = dataService.findAllByDocumentID(documentId);
        System.out.println(data);
        client.sendEvent("load-document", data);
    }

    public void saveDocument(Data data) {
        dataService.save(data);
    }
}
