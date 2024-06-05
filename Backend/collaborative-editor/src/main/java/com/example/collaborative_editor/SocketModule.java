package com.example.collaborative_editor;

import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.collaborative_editor.data.Data;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SocketModule {

    private final SocketIOServer server;
    private final SocketService socketService;

    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.server = server;
        this.socketService = socketService;
        this.server.addConnectListener(this.onConnected());
        this.server.addDisconnectListener(this.onDisconnected());
        this.server.addEventListener("send-changes", Data.class, this.onChanges());
        this.server.addEventListener("receive-changes", Object.class, this.onReceiveChanges());
        this.server.addEventListener("get-document", Object.class, this.onGetDocument());
        this.server.addEventListener("save-document", Data.class, this.onSaveDocument());
    }

    private DataListener<Data> onChanges() {
        return (senderClient, data, ackSender) -> {
            log.info("Sender: " + senderClient.getSessionId().toString() + " -> Data: " +
                    data.toString());
            this.socketService.sendSocketChanges(senderClient, data);
        };
    }

    private DataListener<Object> onReceiveChanges() {
        return (senderClient, data, ackSender) -> {
            log.info("Receiver: " + senderClient.getSessionId().toString() + " -> Data: " + data.toString());
        };
    }

    private DataListener<Object> onGetDocument() {
        return (senderClient, data, ackSender) -> {
            String documentId = (String) data;
            this.socketService.getDocument(senderClient, documentId);
            log.info("Receiver: " + senderClient.getSessionId().toString() + " -> Document: " + documentId);
        };
    }

    private DataListener<Data> onSaveDocument() {
        return (senderClient, data, ackSender) -> {
            this.socketService.saveDocument(data);
            log.info("Receiver: " + senderClient.getSessionId().toString() + " -> Data: " + data.toString());
        };
    }

    private ConnectListener onConnected() {
        return (client) -> {
            log.info("Socket ID[{}] - Connected through", client.getSessionId().toString());
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            log.info("Socket ID[{}] - Disconnected through", client.getSessionId().toString());
        };
    }

}
