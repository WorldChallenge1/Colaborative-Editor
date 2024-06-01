import { Injectable } from '@angular/core';
import io from "socket.io-client";


@Injectable({
  providedIn: 'root'
})
export class TextEditorService {
  private socket = io('http://localhost:3000');

  constructor() { }

  disconnect() {
    this.socket.disconnect();
  }
}
