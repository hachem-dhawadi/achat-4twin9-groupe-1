<<<<<<< HEAD
import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Reglement } from '../shared/Model/Reglement';
import { ReglementService } from '../shared/Service/Reglement.service';
import { NGXLogger } from 'ngx-logger';
=======
import {Component, OnInit} from '@angular/core';
import {Reglement} from '../shared/Model/Reglement';
import {ReglementService} from '../shared/Service/Reglement.service';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
>>>>>>> 7f0ebff (initial commit)

@Component({
  selector: 'app-reglement',
  templateUrl: './reglement.component.html',
  styleUrls: ['./reglement.component.css']
})
export class ReglementComponent implements OnInit {

<<<<<<< HEAD
=======

>>>>>>> 7f0ebff (initial commit)
  listReglement: any;
  form: boolean = false;
  reglement!: Reglement;
  closeResult!: string;

<<<<<<< HEAD
  constructor(private reglementService: ReglementService, private modalService: NgbModal, private logger: NGXLogger) {
    this.logger.info('ReglementComponent initialized');
  }

  ngOnInit(): void {
    this.logger.info('Component initialized. Fetching all reglements...');
    this.getAllReg();

    this.reglement = {
      idReglement: null,
      dateReglement: null,
      payee: null,
      montantRestant: null,
      montantPaye: null
    };
  }

  getAllReg() {
    this.logger.info('Fetching all reglements...');
    this.reglementService.getAllReglements().subscribe(
        res => {
          this.logger.info('Reglements fetched successfully:', res);
          this.listReglement = res;
        },
        error => {
          this.logger.error('Error fetching reglements:', error);
        }
    );
  }

  addReglement(f: any) {
    this.logger.info('Adding new reglement:', f);
    this.reglementService.addReglement(f).subscribe(
        () => {
          this.logger.info('Reglement added successfully');
          this.getAllReg();
          this.form = false;
        },
        error => {
          this.logger.error('Error adding reglement:', error);
        }
    );
  }

  open(content: any) {
    this.logger.info('Opening modal...');
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title' }).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
      this.logger.info('Modal closed with result:', result);
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
      this.logger.info('Modal dismissed with reason:', reason);
=======
  constructor(private reglementService: ReglementService, private modalService: NgbModal) {
  }

  ngOnInit(): void {
    this.getAllReg();
    this.reglement = {
      idReglement:null,
      dateReglement:null,
      payee:null,
      montantRestant:null,
      montantPaye:null
    }
  }

  getAllReg() {
    this.reglementService.getAllReglements().subscribe(res => this.listReglement = res)
  }

  addReglement(f: any) {
    this.reglementService.addReglement(f).subscribe(() => {
      this.getAllReg();
      this.form = false;
    });
  }

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
>>>>>>> 7f0ebff (initial commit)
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
<<<<<<< HEAD
    this.logger.info('Cancelling form...');
=======
>>>>>>> 7f0ebff (initial commit)
    this.form = false;
  }
}
