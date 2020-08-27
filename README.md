# 29cm

## Contents
* [Specifications](#chapter-1)
* [How to run](#chapter-2)

### <a name="chapter-1"></a>Specifications 
````
 OpenJDK11
 Spring Boot 2.3.3.RELEASE
 Spring Data Jpa
 Gradle
 lombok
 MySql / h2
````

### <a name="chapter-2"></a>How to Run
```
Profile
- none(h2), dev(MySql) 

# Using IntelliJ
1. Sync gradle
2. Run Application

# test
1. @EventListener(ApplicationReadyEvent.class) -> ignore
2. test run

# MySql Database ddl
./scripts/database
```
