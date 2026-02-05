# E3R Project: Price Management API

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen?style=for-the-badge&logo=springboot)
![Build](https://img.shields.io/badge/Build-Maven-red?style=for-the-badge&logo=apachemaven)
![Coverage](https://img.shields.io/badge/Coverage-100%25-blueviolet?style=for-the-badge&logo=jacoco)

## Key Features

- **Strict Validation**: Robust input verification using `spring-boot-starter-validation`.
- **Integrated H2 Console**: In-memory database for rapid development and testing.
- **Docker Ready**: Includes `spring-boot-docker-compose` support for containerized environments.
- **Full Test Coverage**: Comprehensive integration tests achieving 100% coverage across business logic.

## Tech Stack

- **Core:** Spring Boot 4.0.2 & Java 21.
- **Data:** Spring Data JPA & H2 Database.
- **Testing:** JUnit 5, AssertJ, and MockMvc.
- **Reporting:** JaCoCo for Code Coverage.
- **Utilities:** Lombok & Spring Configuration Processor.

---

## Notes

- **The test cases are in the PriceControllerTest.java File.**
- **For testing purposes the application.properties is added.**
---

## Model DB

Brand ID,Start Date,End Date,Price List,Product ID,Priority,Price,Currency
1,2020-06-14-00.00.00,2020-12-31-23.59.59,1,35455,0,35.50,EUR
1,2020-06-14-15.00.00,2020-06-14-18.30.00,2,35455,1,25.45,EUR
1,2020-06-15-00.00.00,2020-06-15-11.00.00,3,35455,1,30.50,EUR
1,2020-06-15-16.00.00,2020-12-31-23.59.59,4,35455,1,38.95,EUR

---

## API Reference

### Find Applicable Price

Returns the price record with the highest priority for a given product, brand, and date.

**Endpoint:** `GET /api/prices/find`

**Query Parameters:**

| Parameter | Type | Example | Description |
| :--- | :--- | :--- | :--- |
|  `applicationDate` | String | `2020-06-14-10.00.00` | Target date/time (Format: `yyyy-MM-dd-HH.mm.ss`) |
| `productId` | Long | `35455` | Unique product identifier |
| `brandId` | Long | `1` | Retail chain identifier |

**Success Response (200 OK):**

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14-00.00.00",
  "endDate": "2020-12-31-23.59.59",
  "priceAmount": 35.50,
  "currency": "EUR"
}