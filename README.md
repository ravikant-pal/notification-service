# 📣 Notification Service - Multi-Channel User Notifications

A lightweight Spring Boot microservice designed to send **user notifications** across multiple channels:

- 📧 Email (Plain Text + HTML)
- 📱 SMS (Text Message)
- 💬 WhatsApp

Built for scalability and easy integration across microservices, this service enables real-time communication with end users using pluggable notification providers.

---

## 📌 Overview

This service allows other microservices to send notifications by simply calling an API. It abstracts the channel-specific logic and handles all formatting, templating, and delivery behind the scenes.

Supports:

- Single or multi-channel delivery
- Dynamic notification types (account alerts, OTPs, marketing, etc.)
- HTML templating for rich email content
- Message queuing or async processing (optional)

---

## 🚀 Features

✅ Send notifications via Email, SMS, WhatsApp  
✅ Supports both plain text and rich **HTML emails**  
✅ Unified notification request format  
✅ Extensible channel-specific handlers  
✅ Easy to integrate with other services via REST or message queue  
✅ Logging and error handling for failed deliveries

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3+
- JavaMail / SMTP for Email
- Twilio (SMS), WhatsApp Business API (or other providers)
- Thymeleaf / FreeMarker for HTML email templates
- RabbitMQ / Kafka (optional async support)
- Maven or Gradle

---

## 📦 Installation

```bash
git clone https://github.com/ravikant-pal/notification-service.git
cd notification-service
./mvnw spring-boot:run
```

Update `application.yml` with SMTP credentials and provider API keys.

---

## 📨 Notification API

### 🔁 Endpoint

| Method | Endpoint                  | Description                       |
|--------|---------------------------|-----------------------------------|
| POST   | `/api/v1/notifications/send` | Sends a notification to a user    |

---

### 📤 Request Payload

```json
{
  "to": "ravikant@example.com",
  "channel": ["EMAIL", "SMS", "WHATSAPP"],
  "subject": "Welcome to Our Platform",
  "template": "welcome",
  "params": {
    "username": "Ravikant",
    "supportEmail": "support@yourdomain.com"
  },
  "isHtml": true
}
```

- `to`: Receiver's identifier (email, phone, or WhatsApp number)
- `channel`: Channels to send through (`EMAIL`, `SMS`, `WHATSAPP`)
- `subject`: Subject for email (optional for SMS/WhatsApp)
- `template`: HTML template name (for email rendering)
- `params`: Dynamic values injected into the template
- `isHtml`: Indicates if the email should be rendered as HTML

---

## ✉️ HTML Email Templates

- Located in `/resources/templates/`
- Uses Thymeleaf or FreeMarker for dynamic content rendering

### Sample `welcome.html` Template

```html
<html>
<body>
    <h1>Welcome, [[${username}]]!</h1>
    <p>We’re glad to have you. If you need help, contact [[${supportEmail}]].</p>
</body>
</html>
```

---

## 📢 Multi-Channel Support

Each notification is routed through one or more of the following providers:

- **EMAIL**: SMTP or SendGrid with HTML support
- **SMS**: Twilio or any SMS gateway
- **WHATSAPP**: WhatsApp Business API, Cloud API or Twilio

---

## 🔧 Sample Integration from Another Service

```bash
curl -X POST http://localhost:8080/api/v1/notifications/send \
 -H "Content-Type: application/json" \
 -d '{
    "to": "ravikant@example.com",
    "channel": ["EMAIL"],
    "subject": "Login Alert",
    "template": "login-alert",
    "params": {
        "username": "Ravikant",
        "time": "10:45 PM"
    },
    "isHtml": true
}'
```

---

## 🧪 Testing

```bash
./mvnw test
```

Covers:
- Template rendering
- Email/SMS/WhatsApp sending
- Channel dispatching logic
- Error handling

---

## 🛡️ License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file.

---

## 👨‍💻 Author

**Ravikant Pal**  
Backend Developer | Notifications & Messaging | Microservices  
[LinkedIn](https://linkedin.com/in/ravikant-pal) • [GitHub](https://github.com/ravikant-pal)

---

## 🌟 Feedback / Contributions

Feel free to open an issue or PR:  
👉 [https://github.com/ravikant-pal/notification-service](https://github.com/ravikant-pal/notification-service)
