import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProductsComponent } from './products.component';
import { ProductService } from '../shared/Service/Product.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

// ✅ Import LoggerModule and config
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';

describe('ProductsComponent', () => {
  let component: ProductsComponent;
  let fixture: ComponentFixture<ProductsComponent>;
  let productService: ProductService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProductsComponent],
      imports: [
        HttpClientTestingModule,

        // ✅ Add the logger module with configuration
        LoggerModule.forRoot({
          level: NgxLoggerLevel.DEBUG,
          serverLogLevel: NgxLoggerLevel.ERROR
        })
      ],
      providers: [ProductService]
    }).compileComponents();

    fixture = TestBed.createComponent(ProductsComponent);
    component = fixture.componentInstance;
    productService = TestBed.inject(ProductService);
  });

  it('should create the ProductsComponent', () => {
    expect(component).toBeTruthy();
  });

  it('should load all products on init', () => {
    const mockProducts = [
      { idProduit: 1, codeProduit: 'PR01', libelleProduit: 'Produit 1' }
    ];
    spyOn(productService, 'getAllProducts').and.returnValue(of(mockProducts));

    component.getAllProducts();
    expect(component.listProducts).toEqual(mockProducts);
  });
});
