# Cryptocurrencyalert

"Сryptocurrencyalert" is an application that sends an email to the user when the rate of a cryptocurrency goes up or down relative to a given price.

The application performs the following functions:
- The user creates a new notification, selects the desired ticker, price, terms, email.
- You can upload photos to the database, one random photo will be in the email, for fun.
- Launch alert. The application checks the exchange rate of Binance once per minute. If the price of the selected ticker went down or up relative to your specified conditions, the application will send an email notification.
- The notification comes to the email only once.

## Technology stack

- Java Core,
- Spring Boot,
- Spring Data,
- Hibernate,
- PostgreSQL,
- REST API,
- Slf4j,
- HTML.

## Web

- API GET "/start" Starts an alert.
- API GET "/stop" Stops an alert.

- API GET "/images/save" returns a form to upload a new image.
- API POST "/images/save" save a new image.
- API GET "/images/{id}" returns image by id.
- API DELETE "/images/{id}" removes image by id.

- API GET "/api" returns all alerts.
- API GET "/api/reg" returns a form to upload a new alert.
- API POST "/api/reg" save a new alert.
- API GET "/api/{id}" returns alert by id.
- API DELETE "/api/{id}" removes alert by id.
- API GET "/api/{id}/edit" returns form to update alert.
- API POST "/api/{id}/edit" update alert.

## Set up before starting

- "application.properties" file. Set datasource properties, set mail properties.












