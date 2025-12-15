# Asset Management Automation Framework

A robust and scalable **Selenium automation framework** built using **Java, TestNG, Page Object Model (POM), Selenium Grid, Extent Reports**, and **Excel-based data-driven testing**.
This framework is designed for **parallel execution**, **cross-browser testing**, and **enterprise-level maintainability**.

---

## 🚀 Tech Stack

* **Language:** Java
* **Automation Tool:** Selenium WebDriver
* **Test Framework:** TestNG
* **Design Pattern:** Page Object Model (POM)
* **Reporting:** Extent Reports (Spark Reporter)
* **Data-Driven Testing:** Apache POI (Excel)
* **Parallel Execution:** TestNG + ThreadLocal
* **Grid Execution:** Selenium Grid 4
* **Build Tool:** Maven
* **Version Control:** Git & GitHub

---

```

---

## 🔑 Key Features

* ✅ **Page Object Model (POM)** for better maintainability
* ✅ **Thread-safe WebDriver management** using `ThreadLocal`
* ✅ **Parallel execution** using TestNG
* ✅ **Cross-browser testing** (Chrome, Firefox, Edge)
* ✅ **Selenium Grid integration** for distributed execution
* ✅ **Excel-driven data testing** using Apache POI
* ✅ **Rich HTML reports** with Extent Reports
* ✅ **Reusable utility & event layers**

---

## 📊 Reporting

* Execution reports are generated using **Extent Spark Reporter**
* Report location:

  ```
  test-output/ExtentReport.html
  ```
* Includes:

    * Test status (PASS / FAIL / SKIP)
    * Step-level logs
    * Environment details

---

## 🧪 Data-Driven Testing

* Test data is maintained in Excel:

  ```
  src/test/resources/TestData/BankFormData.xlsx
  ```
* Each row represents a test scenario
* Data is read as `Map<String, String>` for flexibility

---

## ⚙️ Configuration

All environment-related values are stored in:

```
src/test/resources/config.properties
```
The framework also supports **environment variables** via `.env`.

---

## ▶️ How to Run Tests

### Run from IDE

* Right-click on `testng.xml` or test class
* Select **Run**

### Run using Maven

```bash
mvn clean test
```

---

## 🌐 Parallel Execution & Selenium Grid

* Tests can be executed in parallel using TestNG
* Supports execution on **Selenium Grid**
* Browser selection is parameterized via `testng.xml`

---

## 🧠 Design Highlights

* **Event Layer:** Contains business logic and user actions
* **Object Layer:** Contains only WebElement locators
* **BaseTest:** Handles driver setup, teardown, grid lifecycle, and reporting
* **Utils:** Centralized reusable utilities

---

## 📌 Future Enhancements

* 🔹 Screenshot capture on failure
* 🔹 CI/CD integration (Jenkins / GitHub Actions)
* 🔹 Dockerized Selenium Grid
* 🔹 API testing integration

---

## 👤 Author

**Hemant Patil**
📧 Email: [hemantpatil0219@gmail.com](mailto:hemantpatil0219@gmail.com)
🔗 LinkedIn: [linkedin.com/in/hemantpatil0219](https://www.linkedin.com/in/hemantpatil0219)

---

⭐ *If you like this project, feel free to star the repository!*
