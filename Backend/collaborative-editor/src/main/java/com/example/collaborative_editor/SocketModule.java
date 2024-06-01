package com.example.collaborative_editor;

import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SocketModule {

    public SocketModule(SocketIOServer server) {
        server.addConnectListener(this.onConnected());
        server.addDisconnectListener(this.onDisconnected());
    }

    private ConnectListener onConnected() {
        return (client) -> {
            // var params = client.getHandshakeData().getUrlParams();
            // String room = params.get("room").stream().collect(Collectors.joining());
            // String username =
            // params.get("username").stream().collect(Collectors.joining());
            // client.joinRoom(room);
            log.info("Socket ID[{}] - Connected through", client.getSessionId().toString());
        };

    }

    private DisconnectListener onDisconnected() {
        return client -> {
            // var params = client.getHandshakeData().getUrlParams();
            // String room = params.get("room").stream().collect(Collectors.joining());
            // String username =
            // params.get("username").stream().collect(Collectors.joining());
            // socketService.saveInfoMessage(client,
            // String.format(Constants.DISCONNECT_MESSAGE, username), room);
            log.info("Socket ID[{}] - Disconnected through", client.getSessionId().toString());
        };
    }

}
