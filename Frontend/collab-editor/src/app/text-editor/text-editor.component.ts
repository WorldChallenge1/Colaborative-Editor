import { Component, OnInit } from '@angular/core';
import Quill from 'quill';

const TOOLBAR_OPTIONS = [
  [{ header: [1, 2, 3, 4, 5, 6, false] }],
  [{ font: [] }],
  [{ list: "ordered" }, { list: "bullet" }],
  ["bold", "italic", "underline"],
  [{ color: [] }, { background: [] }],
  [{ script: "sub" }, { script: "super" }],
  [{ align: [] }],
  ["image", "blockquote", "code-block"],
  ["clean"],
]

@Component({
  selector: 'app-text-editor',
  standalone: true,
  imports: [],
  templateUrl: './text-editor.component.html',
  styleUrl: './text-editor.component.css',
})
export class TextEditorComponent implements OnInit {
  ngOnInit() {
    const container = document.querySelector('.container');
    const newDiv = document.createElement('div');
    container?.appendChild(newDiv);
    const quill = new Quill(newDiv, {
      modules: {
        toolbar: TOOLBAR_OPTIONS
      },
      theme: 'snow', // or 'bubble'
    });
  }
}
