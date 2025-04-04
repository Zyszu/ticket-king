# ✈️ Ticket King

**Ticket King** is a Java Spring-based web application built as a learning project.  
It allows small airline companies to register flights, and enables users to browse and book tickets for those flights — with built-in role-based access, internationalization, and secure booking workflows.


---

## 🧠 About the Project

> 🚀 A **Java Spring learning project** focused on transport-related services  
> ✈️ Booking airplane tickets | 🧾 Issuing tickets | 🔐 Secure multi-role access

---

## 🛠️ Tech Stack

- **Spring Framework**
- **Spring Security**
- **JPA / Hibernate**
- **Maven**
- **JSP & Bootstrap**
- **Jakarta Validation**
- **i18n** (EN / PL)
- **PDF generation**
- **Email & Token-based activation**
- **CAPTCHA integration**

---

## 🌍 Features

- 🌐 **Internationalization** – English & Spanish
- 🛂 **Role-based Access**
    - **ROLE_USER** – can buy tickets, view flights, access wallet, and manage personal data & address
    - **ROLE_PROPRIETOR** – can initiate company verification to become a flight provider
    - **ROLE_NOT_VERIFIED** – indicates company verification is in progress
    - **ROLE_VERIFIED** – company verification approved; can add aircraft and flights
    - **ROLE_SUPPORT** – can access the verification panel and approve/reject companies
- 🔐 **Secure registration** with email token activation
- 🧠 **Backend validation** for key forms
- 🧾 **PDF tickets** for paid tickets
- ⏳ **Auto-cancellation** of unpaid tickets (scheduled task)
- 📦 **REST endpoint** with XML support
- 🧩 **All types of relationships** in ORM model
- 🤖 **CAPTCHA** on registration forms
- 🎨 **Responsive frontend** using JSP & Bootstrap

---

> 📝 **Reminder:** Before running the project, make sure to fill in the `project.properties` file.  
> Use the provided `project.properties.example` as a reference.


## 🔧 Run Locally

```bash
git clone https://github.com/zyszu/ticket-king.git
# import as a Maven project
# configure DB and run on Tomcat/Jetty

