# Next Stuff

## Testing!
- Practice TDD
- E2E TestContainers

## Reactivity
- Study if my teams endpoint can be reactive
- Make mongo reactive
- Make my endpoint reactive
- Measure the performance of both the reactive and non-reactive approach

# Messaging
- Then create the other endpoint
- the other endpoint consumes the kafka

# Stablize
- Links from the Landing page are not working
- Add new service for Players
- Add comuunication between the two
- Add Logging
- Integrate Logging with ELK
- Redis
- MongoDB

## Destabilizar
- Try switching the proxy type to JDK (from CGLI)

# Document!
- Document the flow when adding a new microservice, including everything needed
- for testing
- for reactivity
- for kafka
- for event driven stuff
- for the microservice itself
- for elk

# Observability
- Make sure elastic is OK
- Make sure Logstash is OK
- Make sure Kibana is OK

# How To Start
- rebuild script
- Both API GAteway and Service should appear

# Checking errors
- docker ps
- Get the id
- docker logs [id]
- Grep it if needed



=========================================================================

To make your repository **reactive** in Spring, you need to switch from the traditional **blocking `MongoRepository`** to **`ReactiveMongoRepository`** (if you’re using MongoDB). Here’s exactly what you need to do:

---

### **1️⃣ Update your Maven/Gradle dependencies**

Make sure you have **Spring Data Reactive MongoDB**:

**Maven:**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb-reactive</artifactId>
</dependency>
```

**Gradle:**

```gradle
implementation 'org.springframework.boot:spring-boot-starter-data-mongodb-reactive'
```

> ⚠️ Do **not** use the normal `spring-boot-starter-data-mongodb` if you want reactive behavior — that one is blocking.

---

### **2️⃣ Extend `ReactiveMongoRepository`**

Your current repository likely looks like this:

```java
public interface TeamRepository extends MongoRepository<Team, String> {}
```

Change it to:

```java
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TeamRepository extends ReactiveMongoRepository<Team, String> {
    // you can define custom queries too
    // Flux<Team> findByName(String name);
}
```

* `ReactiveMongoRepository<Team, String>` replaces `MongoRepository<Team, String>`.
* Methods like `findAll()`, `findById()`, `save()`, etc., now return **reactive types** (`Flux<Team>` or `Mono<Team>`).

---

### **3️⃣ Update service code**

Before (blocking):

```java
List<Team> allTeams = repository.findAll();
return teamMapper.toDtoList(allTeams);
```

After (reactive):

```java
Flux<TeamDTO> allTeams = repository.findAll()
                                   .map(teamMapper::toDto);
```

Or if you want a `Mono<List<TeamDTO>>`:

```java
Mono<List<TeamDTO>> allTeams = repository.findAll()
                                         .map(teamMapper::toDto)
                                         .collectList();
```

---

### **4️⃣ MongoDB Configuration**

* Reactive MongoDB uses **`ReactiveMongoTemplate`** under the hood.
* If you already use `spring-boot-starter-data-mongodb-reactive`, Spring Boot **auto-configures** it.
* You don’t need a blocking `MongoTemplate`. Make sure your MongoDB connection string is correct.

---

### **5️⃣ Notes / Gotchas**

* **Custom queries**: Use `@Query` or derived query methods — return `Flux`/`Mono`, not `List`/`Optional`.
* **Don’t mix reactive and blocking calls**: Don’t call `repository.findAll().collectList().block()` unless absolutely necessary — that defeats non-blocking benefits.
* **Logging / side effects**: Use `doOnNext`, `doOnComplete`, etc., instead of logging inline.

---

💡 **Summary**:

1. Use `spring-boot-starter-data-mongodb-reactive`.
2. Extend `ReactiveMongoRepository`.
3. Update service methods to return `Flux` or `Mono`.
4. Don’t block; use Reactor operators (`map`, `flatMap`, `collectList`) for transformations.

---

If you want, I can **rewrite your `getAllTeams()` method fully reactive**, including event publishing, logging, and mapping — exactly how it would look in a real Spring WebFlux service.

Do you want me to do that?

================================================================================

Yes! You can definitely rewrite your `getAllTeams()` method in a **Project Reactor / reactive style** using **Spring WebFlux** and **Reactive Repositories**. The key changes are:

1. Use `Flux<Team>` instead of `List<Team>` when fetching from the repository.
2. Return a `Flux<TeamDTO>` or `Mono<List<TeamDTO>>` depending on what your API wants.
3. Use `.map()` for mapping entities to DTOs.
4. Use `doOnNext`, `doOnSubscribe`, `doOnComplete`, etc., for logging or side effects.
5. Publish events reactively if needed (or just use `doOnSubscribe` to trigger it).

Here’s a reactive version of your code:

```java
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public Flux<TeamDTO> getAllTeams() {

    logger.info("Executing TeamService.getAllTeams...");
    logger.debug("Publishing event GetAllTeamsEvent.");

    // Publish the event once when subscription happens
    eventPublisher.publishEvent(new GetAllTeamsEvent());

    return repository.findAll() // repository should be ReactiveMongoRepository<Team, String>
            .doOnNext(team -> logger.debug("Retrieved team entity: " + team))
            .map(teamMapper::toDto) // convert each entity to DTO
            .doOnComplete(() -> logger.debug("All teams retrieved and mapped to DTOs."));
}
```

### ✅ Notes:

1. **Repository type**: Your repository must extend `ReactiveMongoRepository<Team, String>` instead of `MongoRepository`. Example:

```java
public interface TeamRepository extends ReactiveMongoRepository<Team, String> {}
```

2. **Mapping List vs. Flux**:

    * If you want a single `List<TeamDTO>` instead of `Flux<TeamDTO>`:

```java
public Mono<List<TeamDTO>> getAllTeams() {
    eventPublisher.publishEvent(new GetAllTeamsEvent());
    return repository.findAll()
                     .map(teamMapper::toDto)
                     .collectList()
                     .doOnSuccess(list -> logger.debug("All teams retrieved: " + list));
}
```

3. **Logging**: In reactive programming, logging is usually done with `doOnNext`, `doOnSubscribe`, or `doOnComplete` instead of logging inline, to preserve the reactive flow.

---

If you want, I can rewrite your **entire service method fully reactive, including event publishing in a fully non-blocking way**, so it’s *pure Reactor style* with proper backpressure handling.

Do you want me to do that?
