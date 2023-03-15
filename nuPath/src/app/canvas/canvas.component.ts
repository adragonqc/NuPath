import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-canvas',
  templateUrl: './canvas.component.html',
  styleUrls: ['./canvas.component.css']
})
export class CanvasComponent implements OnInit {
  @ViewChild('canvas', { static: true })
  canvas!: ElementRef<HTMLCanvasElement>;

  context!: CanvasRenderingContext2D | null;
  isDrawing = false;
  color = 'black';

  ngOnInit(): void {
    this.context = this.canvas.nativeElement.getContext('2d');
    this.canvas.nativeElement.width = this.canvas.nativeElement.offsetWidth;
    this.canvas.nativeElement.height = this.canvas.nativeElement.offsetHeight;
  }

  updateCanvasSize(): void {
    this.canvas.nativeElement.width = this.canvas.nativeElement.offsetWidth;
    this.canvas.nativeElement.height = this.canvas.nativeElement.offsetHeight;
  }

  startDrawing(event: MouseEvent): void {
    this.isDrawing = true;
    if (this.context != null) {
      this.context.beginPath();
      this.context.moveTo(event.offsetX, event.offsetY);
    }
  }

  draw(event: MouseEvent): void {
    if (!this.isDrawing) {
      return;
    }
    if (this.context != null) {
      this.context.strokeStyle = this.color;
      this.context.lineTo(event.offsetX, event.offsetY);
      this.context.stroke();
    }
  }

  stopDrawing(): void {
    this.isDrawing = false;
  }

  setColor(color: string): void {
    this.color = color;
  }

  clearCanvas(): void {
    if(this.context != null) {
      this.context.clearRect(0, 0, this.canvas.nativeElement.width, this.canvas.nativeElement.height);
    }
  }
}
