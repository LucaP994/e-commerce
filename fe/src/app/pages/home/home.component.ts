import { Component } from '@angular/core';
import { ProductService } from 'src/app/service/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  constructor(private productService: ProductService){}
  ngAfterViewInit() {
    this.productService.getProducts().subscribe(res => console.log(res));
  }
}
