import { EventEmitter, Injectable, Output } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppContextService {
  @Output() onEvent: EventEmitter<boolean> = new EventEmitter()
  @Output() onLoaded: EventEmitter<boolean> = new EventEmitter()
  constructor() { }
  onEventSub(){
    this.onEvent.emit(true);
  }
  onLoadedSub(){
    this.onLoaded.emit(true);
  }
}
