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
 MySql / h2Database
````

### <a name="chapter-2"></a>How to Run
```
Profile
- dev(MySql), none(h2)

1. 실행
./gradlew bootrun

# Using IntelliJ
1. Sync gradle
2. Run Application

2. Test 
./gradlew test

3. MySql Database ddl
./scripts/database
```
