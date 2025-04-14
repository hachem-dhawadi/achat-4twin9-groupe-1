import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { OperateurService } from './Operateur.service';

describe('OperateurService', () => {
  let service: OperateurService;
  let httpMock: HttpTestingController;
  const API_URL = 'http://localhost:8089/SpringMVC/operateur';

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [OperateurService]
    });
    service = TestBed.inject(OperateurService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should retrieve all operateurs', () => {
    const mockOperateurs = [{ idOperateur: 1, nom: 'John' }];

    service.getAllOperateurs().subscribe(operateurs => {
      expect(operateurs).toEqual(mockOperateurs);
    });

    const req = httpMock.expectOne(`${API_URL}/retrieve-all-operateurs`);
    expect(req.request.method).toBe('GET');
    req.flush(mockOperateurs);
  });

  it('should add an operateur', () => {
    const mockOperateur = { nom: 'John', prenom: 'Doe' };

    service.addOperateur(mockOperateur).subscribe(response => {
      expect(response).toEqual(mockOperateur);
    });

    const req = httpMock.expectOne(`${API_URL}/add-operateur`);
    expect(req.request.method).toBe('POST');
    req.flush(mockOperateur);
  });

  it('should update an operateur', () => {
    const mockOperateur = { idOperateur: 1, nom: 'Updated' };

    service.editOperateur(mockOperateur).subscribe(response => {
      expect(response).toEqual(mockOperateur);
    });

    const req = httpMock.expectOne(`${API_URL}/modify-operateur`);
    expect(req.request.method).toBe('PUT');
    req.flush(mockOperateur);
  });

  it('should delete an operateur', () => {
    const operateurId = 1;

    service.deleteOperateur(operateurId).subscribe();

    const req = httpMock.expectOne(`${API_URL}/remove-operateur/${operateurId}`);
    expect(req.request.method).toBe('DELETE');
    req.flush({});
  });

  afterEach(() => {
    httpMock.verify();
  });
});
