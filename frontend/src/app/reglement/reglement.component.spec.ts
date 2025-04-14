import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReglementComponent } from './reglement.component';
import { ReglementService } from '../shared/Service/Reglement.service';
import { NgbModal, NgbModalRef, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { of, throwError } from 'rxjs';
import { NGXLogger } from 'ngx-logger';

describe('ReglementComponent', () => {
  let component: ReglementComponent;
  let fixture: ComponentFixture<ReglementComponent>;
  let mockReglementService: any;
  let mockModalService: any;
  let mockLogger: any;

  beforeEach(async () => {
    mockReglementService = {
      getAllReglements: jasmine.createSpy('getAllReglements').and.returnValue(of([{ idReglement: 1 }])),
      addReglement: jasmine.createSpy('addReglement').and.returnValue(of({}))
    };

    mockModalService = {
      open: jasmine.createSpy('open').and.returnValue({
        result: Promise.resolve('closed')
      } as NgbModalRef)
    };

    mockLogger = {
      info: jasmine.createSpy('info'),
      error: jasmine.createSpy('error'),
      debug: jasmine.createSpy('debug'),
      warn: jasmine.createSpy('warn')
    };

    await TestBed.configureTestingModule({
      declarations: [ReglementComponent],
      providers: [
        { provide: ReglementService, useValue: mockReglementService },
        { provide: NgbModal, useValue: mockModalService },
        { provide: NGXLogger, useValue: mockLogger }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReglementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create component', () => {
    expect(component).toBeTruthy();
  });

  it('should call getAllReg on ngOnInit', () => {
    expect(mockReglementService.getAllReglements).toHaveBeenCalled();
    expect(component.listReglement).toEqual([{ idReglement: 1 }]);
  });

  it('should add a reglement and refresh the list', () => {
    const form = { montantPaye: 100 };
    component.addReglement(form);

    expect(mockReglementService.addReglement).toHaveBeenCalledWith(form);
    expect(mockReglementService.getAllReglements).toHaveBeenCalledTimes(2); // initial + after add
    expect(component.form).toBeFalse();
  });

  it('should log error if addReglement fails', () => {
    mockReglementService.addReglement.and.returnValue(throwError('add error'));

    component.addReglement({ montantPaye: 100 });

    expect(mockLogger.error).toHaveBeenCalledWith('Error adding reglement:', 'add error');
  });

  it('should open modal and log result', async () => {
    const fakeContent = 'modalContent';
    await component.open(fakeContent);

    expect(mockModalService.open).toHaveBeenCalledWith(fakeContent, { ariaLabelledBy: 'modal-basic-title' });
    expect(mockLogger.info).toHaveBeenCalledWith('Modal closed with result:', 'closed');
  });

  it('should handle modal dismiss reason ESC', () => {
    const reason = ModalDismissReasons.ESC;
    const result = component['getDismissReason'](reason);
    expect(result).toBe('by pressing ESC');
  });

  it('should cancel form', () => {
    component.form = true;
    component.cancel();
    expect(component.form).toBeFalse();
  });
});
