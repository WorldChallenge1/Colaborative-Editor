import { Injectable } from '@angular/core';
import io, { Socket } from "socket.io-client";


@Injectable({
  providedIn: 'root'
})
export class TextEditorService {
  private socket: Socket | undefined;

  constructor() {}

  connect() {
    this.socket = io('http://localhost:3000');
  }

  disconnect() {
    if (!this.socket) return;
    this.socket.disconnect();
  }

  emit(event: string, data: any) {
    if (!this.socket) return;
    console.log(event, data);
    this.socket.emit(event, data);
  }

  on(event: string, callback: (data: any) => void) {
    if (!this.socket) return;
    this.socket.on(event, callback);
  }
}
