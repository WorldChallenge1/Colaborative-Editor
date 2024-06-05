import { Component, OnInit, inject } from '@angular/core';
import Quill from 'quill';
import { TextEditorService } from '../text-editor.service';
import { ActivatedRoute } from '@angular/router';

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

const SAVE_INTERVAL_MS = 2000

@Component({
  selector: 'app-text-editor',
  standalone: true,
  imports: [],
  templateUrl: './text-editor.component.html',
  styleUrl: './text-editor.component.css',
})
export class TextEditorComponent implements OnInit {

  private textEditorService = inject(TextEditorService)
  private route = inject(ActivatedRoute)

  ngOnInit() {

    this.textEditorService.connect()

    const documentId = this.route.snapshot.paramMap.get('id')

    this.textEditorService.once("load-document", (document) => {
      const content: any = {
        ops: document[0]["ops"]
      }
      quill.setContents(content)
      quill.enable()
    })

    this.textEditorService.emit('get-document', documentId)

    const container = document.querySelector('.container')
    const newDiv = document.createElement('div')

    container?.appendChild(newDiv)

    const quill = new Quill(newDiv, {
      modules: {
        toolbar: TOOLBAR_OPTIONS
      },
      theme: 'snow', // or 'bubble'
    })


    quill.on('text-change', (delta: any, oldDelta, source) => {
      if (source !== 'user') {
        return
      }
      delta["documentID"] = documentId
      this.textEditorService.emit('send-changes', delta)

      console.log(JSON.stringify(quill.getContents()))

    })

    quill.disable()
    quill.setText('Loading...')

    this.textEditorService.on('receive-changes', (delta) => {
      quill.updateContents(delta)
    })


    const interval = setInterval(() => {
      const quillContents = quill.getContents()
      let data: any = quillContents
      data["documentID"] = documentId
      data["id"] = documentId
      this.textEditorService.emit("save-document", data)
    }, SAVE_INTERVAL_MS)

  }

  ngOnDestroy(): void {
    this.textEditorService.disconnect()
  }
}
