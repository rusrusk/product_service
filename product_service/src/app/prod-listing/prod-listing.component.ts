import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';



@Component({
  selector: 'app-prod-listing',
  templateUrl: './prod-listing.component.html',
  styleUrls: ['./prod-listing.component.css']
})
export class ProdListingComponent implements OnInit {
  productList: any;
  constructor(private productService: ProductService) {}

  ngOnInit(): void {
      this.productService.findAllProducts().subscribe(content => {
        this.productList = content;
        console.log("PRODUCTLIST === ", this.productList); 
      }) 
  }
}
