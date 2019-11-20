# Spring Boot Rest API
REST API that returns a list of dogs from an embedded H2 in memory database.

Spring Boot 2 REST API Controller. In Spring, a controller class, which is capable of serving REST API requests, is called rest controller. It should be annotated with **@RestController annotation**. The resource uris are specified in **@RequestMapping** annotations. It can be applied at class level and method level both.

## Spring Security
Spring Security is a framework providing an almost declarative security services for Spring-based applications. Security is mostly about __authentication__, i.e. the verification of the identity, and __authorization__, the grant of access rights to resources. Spring security supports a huge range of authentication models, either provided by third parties or implemented natively.

```
 <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
 </dependency>
 <dependency>
      <groupId>org.springframework.security</groupId>
      <artifactId>spring-security-test</artifactId>
      <scope>test</scope>
</dependency>
```

##  Swagger
Documentation is one important aspect for any application. Especially for REST API, good documentation is very important — even instrumental for external adoption or partners acquisitions. The documentation must be up-to-date, every change in the API should be simultaneously described in the reference documentation. Like good code, good documentation is difficult and time consuming to write. Accomplishing this manually is (very) tedious, and as developer we love to automate it.
**Swagger** is a specification for documenting REST API. It specifies the format (URL, method, and representation) to describe REST web services.
```
<dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>2.9.2</version>
</dependency>
<dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>2.9.2</version>
      <scope>compile</scope>
</dependency>
```
## Unit Test

```
 @Test
    @WithMockUser(username = "admin", password="password", roles={"USER"})
    public void getDogBreeds() throws Exception {
        mockMvc.perform(get("/dogs/breed/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogBreed();
    }
```

## Integration Test
```
@Test
    public void getAllDogs() {
        restTemplate = new TestRestTemplate("admin", "password");
        ResponseEntity<List> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/dogs/", List.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
```

## Packaging
![Packaging](https://github.com/deretou/Rest-api-Spring-Boot/blob/master/packaging.JPG)




