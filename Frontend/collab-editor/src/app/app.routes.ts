import { Routes } from '@angular/router';
import { v4 as uuidv4 } from 'uuid';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'document/' + uuidv4(),
    pathMatch: 'full'
  },
  {
    path: 'document/:id',
    loadComponent: () => import('./text-editor/text-editor.component').then(m => m.TextEditorComponent)
  }
];
