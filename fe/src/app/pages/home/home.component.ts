import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ProdDetailComponent } from 'src/app/components/prod-detail/prod-detail.component';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/service/product.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  public newProducts: Product[] = []
  public products: Product[] = []
  private actualDate: Date = new Date();
  constructor(
    private productService: ProductService,
    public dialog: MatDialog
  ){}
  ngAfterViewInit() {
    this.productService.getProducts().subscribe(res => {
      console.log(res)
      res.forEach(el =>{ 
        console.log(this.parseDate(el.createAt))
        console.log(this.actualDate)
        this.products.push(el)
        if((this.actualDate.getTime() - this.parseDate(el.createAt).getTime()) < (5 * 86400000) )
          this.newProducts.push(el)
      })
    });
  }
  openDetail(product: Product) {
    console.log(product)
      let dialogRef = this.dialog.open(ProdDetailComponent, {
        height: '80vh',
        width: '80vw',
        data: {product: product},
      });
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        //this.animal = result;
      });
    }    
    private parseDate(date: String): Date{
      let pasredDate: String[] = date.split("T");
      let year: number = Number.parseInt(pasredDate[0].split("-")[0]);
      let month: number = Number.parseInt(pasredDate[0].split("-")[1]);
      let day: number = Number.parseInt(pasredDate[0].split("-")[2]);
      let newDate: Date = new Date(year,month - 1,day);
      return newDate;
    }
}
