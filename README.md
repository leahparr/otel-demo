# otel-demo
Playing around with the combination of kotlin, spring boot 3.1.0, spring cloud gateway, webflux, coroutines and their observability with micrometer and opentelemetry.

# Get started
1. Start the observability BE
1.1 `cd ./otel-be`
1.2 `docker compose up`
2. Start the app
3. Got to http://localhost:3000
4. Enjoy the dashboards



Curls
```
curl --location 'http://localhost:8085/world/hello' \
  --header 'baggage: accountId=zukrzukrzkzrukuzr' \
```

```
curl --location 'http://localhost:8085/hello' \
  --header 'baggage: accountId=zukrzukrzkzrukuzr' \
  ```
# Issues/TODOs
- No logging sent over otlp
- Some attributes are not correlated correctly