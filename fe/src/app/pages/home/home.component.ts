import { Component } from '@angular/core';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/service/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  public products: Product[] = []
  constructor(private productService: ProductService){}
  ngAfterViewInit() {
    this.productService.getProducts().subscribe(res => {
      console.log(res)
      res.forEach(el => this.products.push(el))
    });
  }
}
