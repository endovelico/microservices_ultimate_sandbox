Based on your `docker-compose.yml`, all services are exposed on your **local machine (localhost)** using the mapped ports.

Here are the access endpoints:

---

## 🟠 Kafka (broker, not a web UI)

* Apache Kafka
* **Bootstrap server (for apps/clients):**

> ⚠️ Kafka does **not have a built-in web interface**. You access it via producers/consumers or tools like Kafka UI.

---

## 🔎 Elasticsearch

* Elasticsearch
* **API endpoint:**
  [Elasticsearch API](http://localhost:9200?utm_source=chatgpt.com)

You can test it in browser or curl:

```bash
curl http://localhost:9200
```

---

## 📊 Kibana

* Kibana
* **Web UI:**
  [Kibana UI](http://localhost:5601?utm_source=chatgpt.com)

This is your main dashboard for logs, metrics, and visualizations.

---

## 🔧 Logstash

* Logstash
* **Input endpoint (TCP/HTTP depending on your config):**
  [Logstash Input](http://localhost:5000?utm_source=chatgpt.com)

> ⚠️ Logstash is **not a UI service**. Port `5000` is typically used for receiving logs/events (e.g., Beats, HTTP input plugin).

---

## 🧩 Summary Table

| Service       | Access URL                                     |
| ------------- | ---------------------------------------------- |
| Kafka         | localhost:9092 (broker)                        |
| Elasticsearch | [http://localhost:9200](http://localhost:9200) |
| Kibana        | [http://localhost:5601](http://localhost:5601) |
| Logstash      | [http://localhost:5000](http://localhost:5000) |

---

If you want, I can also help you add a **Kafka UI (like AKHQ or Kafka UI)** so you can actually browse topics and messages in the browser.
