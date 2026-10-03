# Spring Framework Practice — Constructor Injection and Conditional Bean

## Build
Requires JDK 17.

```bash
./mvnw clean package
```

## Notifications ON
The dev profile sets app.notification.enabled=true.

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

Open http://localhost:8080/notification.
Expected response: Notifications are ON.

## Notifications OFF
Stop the application with Ctrl+C.
The test profile sets app.notification.enabled=false.

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=test
```

Open http://localhost:8080/notification.
Expected response: Notifications are OFF.

## Implementation
- HelloService receives AppProperties through its constructor.
- Service fields are private final.
- Services do not use field @Autowired.
- NotificationService uses @ConditionalOnProperty.
- NotificationController uses ObjectProvider to handle an absent bean.

## Other endpoints
- /hello/get/Sally
- /config