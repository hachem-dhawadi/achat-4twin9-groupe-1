import { BrowserModule } from '@angular/platform-browser';
<<<<<<< HEAD
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';

// 🔽 Logger
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';

// Tes composants
import { ProductsComponent } from './products/products.component';
=======

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { ProductsComponent } from './products/products.component';
import { FormsModule } from '@angular/forms';
import { NgbModal, NgbModalModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
>>>>>>> 7f0ebff (initial commit)
import { StockComponent } from './stock/stock.component';
import { ReglementComponent } from './reglement/reglement.component';
import { SecteurActiviteComponent } from './secteur-activite/secteur-activite.component';
import { OperateurComponent } from './operateur/operateur.component';
import { FactureComponent } from './facture/facture.component';
import { NavbarComponent } from './navbar/navbar.component';
<<<<<<< HEAD
=======
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';
>>>>>>> 7f0ebff (initial commit)

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    StockComponent,
    ReglementComponent,
    SecteurActiviteComponent,
    OperateurComponent,
    FactureComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModalModule,
<<<<<<< HEAD
    NgbModule,
    RouterModule,
    
    // ✅ Logger module correctement placé
    LoggerModule.forRoot({
      level: NgxLoggerLevel.DEBUG,
      serverLogLevel: NgxLoggerLevel.ERROR,
      serverLoggingUrl: 'SpringMVC/logs',
=======
    RouterModule,
    NgbModule,
    LoggerModule.forRoot({
      level: NgxLoggerLevel.DEBUG,
      serverLoggingUrl: 'SpringMVC/logs',
      disableConsoleLogging: false
>>>>>>> 7f0ebff (initial commit)
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
