import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Product } from '../shared/Model/Product';
import { ProductService } from '../shared/Service/Product.service';
import { NGXLogger } from 'ngx-logger'; // ✅ Logging module

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  listProducts: any;
  form: boolean = false;
  product!: Product;
  closeResult!: string;

  constructor(
    private productService: ProductService,
    private modalService: NgbModal,
    private logger: NGXLogger // ✅ Inject logger
  ) {}

  ngOnInit(): void {
    this.logger.info('🔄 Initializing ProductsComponent...');
    this.getAllProducts();

    this.product = {
      idProduit: null,
      codeProduit: null,
      libelleProduit: null,
      prix: null,
      dateCreation: null,
      dateDerniereModification: null
    }
  }

  getAllProducts() {
    this.logger.debug('📦 Fetching all products...');
    this.productService.getAllProducts().subscribe(res => {
      if (Array.isArray(res)) {
        this.listProducts = res;
        this.logger.info(`✅ Fetched ${res.length} products`);
      } else {
        this.logger.error('❌ Expected an array of products');
      }
    }, error => {
      this.logger.error('❌ Failed to fetch products', error);
    });
  }

  addProduct(p: any) {
    this.logger.info('➕ Adding new product:', p);
    this.productService.addProduct(p).subscribe(() => {
      this.logger.info('✅ Product added successfully');
      this.getAllProducts();
      this.form = false;
    }, error => {
      this.logger.error('❌ Failed to add product', error);
    });
  }

  editProduct(product: Product) {
    this.logger.info('✏️ Editing product:', product);
    this.productService.editProduct(product).subscribe(() => {
      this.logger.info('✅ Product updated');
    }, error => {
      this.logger.error('❌ Error updating product', error);
    });
  }

  deleteProduct(idProduct: any) {
    this.logger.warn(`🗑️ Deleting product with ID ${idProduct}`);
    this.productService.deleteProduct(idProduct).subscribe(() => {
      this.logger.info('✅ Product deleted');
      this.getAllProducts();
    }, error => {
      this.logger.error('❌ Error deleting product', error);
    });
  }

  open(content: any, action: any) {
    this.logger.debug('📂 Opening modal');
    if (action != null) {
      this.logger.debug('📝 Editing existing product');
      this.product = action;
    } else {
      this.logger.debug('🆕 Adding new product');
      this.product = new Product();
    }

    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      this.logger.debug(this.closeResult);
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      this.logger.debug(this.closeResult);
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  cancel() {
    this.logger.debug('❌ Cancelled form');
    this.form = false;
  }
}
