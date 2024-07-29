import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Product } from 'src/app/models/product.model';

@Component({
  selector: 'app-prod-detail',
  templateUrl: './prod-detail.component.html',
  styleUrl: './prod-detail.component.scss'
})
export class ProdDetailComponent {
  public product: Product = new Product();
  constructor(
    public dialogRef: MatDialogRef<ProdDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ){}

  ngOnInit(){
    console.log(this.data)
    this.product = this.data.product;
  }
}
