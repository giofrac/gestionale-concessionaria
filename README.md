# Gestionale Concessionaria

Sistema di gestione per una concessionaria auto — progetto di studio end-to-end con Spring Boot 4 e Java 21: modellazione del dominio, persistenza, sicurezza JWT, testing su più livelli.

## Stack tecnologico

- **Java 21**
- **Spring Boot 4.1.1** / Spring Framework 7
- **Spring Data JPA** + Hibernate 7
- **Spring Security 7** — autenticazione JWT stateless
- **H2 Database** (file-based in sviluppo)
- **Bean Validation** (Jakarta)
- **JJWT 0.12.6** — generazione e verifica token
- **JUnit 5 + Mockito** — testing
- **Maven**

## Funzionalità

- CRUD veicoli con gerarchia di ereditarietà (`Automobile`, `Motocicletta`, `AutoElettrica`)
- CRUD clienti, con validazione (`@NotBlank`, `@Email`)
- Gestione vendite: collega veicolo e cliente, con vincolo di business (un veicolo non può essere venduto due volte)
- Autenticazione JWT: login stateless, ruoli (`USER`/`ADMIN`), endpoint protetti per ruolo
- Gestione errori centralizzata (`@RestControllerAdvice`) con risposte `ProblemDetail` (RFC 7807)
- Console H2 per ispezione diretta del database in sviluppo

## Avvio del progetto

**Prerequisiti**: Java 21, Maven

```bash
mvn spring-boot:run
```

App disponibile su `http://localhost:8081`.

Imposta la variabile d'ambiente `JWT_SECRET` (stringa di almeno 32 caratteri) in produzione — in sviluppo locale è presente un valore di default in `application.properties`.

### Console database

Con l'app in esecuzione: `http://localhost:8081/h2-console`
JDBC URL: `jdbc:h2:file:./data/concessionaria`

## Endpoint principali

| Metodo | Path | Autenticazione | Descrizione |
|---|---|---|---|
| GET | `/api/veicoli` | Pubblico | Lista veicoli |
| POST | `/api/veicoli/automobili` | Autenticato | Registra una nuova automobile |
| GET | `/api/clienti` | Autenticato | Lista clienti |
| POST | `/api/clienti` | Autenticato | Registra un nuovo cliente |
| POST | `/api/vendite` | Autenticato | Registra una vendita |
| POST | `/api/auth/login` | Pubblico | Login, restituisce un token JWT |
| GET | `/api/admin/report` | Ruolo ADMIN | Endpoint riservato di esempio |

### Autenticazione

```bash
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"adminpass"}'
```

Utenti demo disponibili (in-memory): `mario` / `password123` (ruolo USER), `admin` / `adminpass` (ruolo ADMIN).

Usa il token restituito nell'header `Authorization: Bearer <token>` per le richieste successive.

## Testing

```bash
mvn test
```

Tre livelli:
- **Unit** (`AutomobileTest`, `MotociclettaTest`, `VenditaTest`...) — logica di dominio isolata
- **Mockito** (`VeicoloServiceTest`) — service layer senza database
- **Integrazione** (`VeicoloControllerIT`) — `@SpringBootTest` + `MockMvc`, incluse richieste autenticate con token JWT reali, su un database H2 in memoria isolato da quello di sviluppo

## Profili

- `dev` — `application-dev.properties`
- `prod` — `application-prod.properties`

Attivazione: `--spring.profiles.active=dev`