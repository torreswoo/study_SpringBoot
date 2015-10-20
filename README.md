# study_SpringBoot

---

### 실행 
- localhost:8080/

### SpringBoot: Controller / Service / Configuration
- @RestController : 웹애플리케이션의 요청을 받아들이는 컨트롤러클래스임을 나타
- @RequestMapping : HTTP요청을 받아들이는 메서드
- @EnableAutoConfiguration : 다양한 설정이 자동으로 수행되고 기존의 설정파일들이 필요없게

- 스프링부트 
	- @SpringBootApplication
		- ApplicationContext ctx = SpringApplication.run(TestSpringBootApplication.class, args);
		- @Configuration / @EnableAutoConfiguration / @ComponentScan 

- 스케줄링
	- @EnableScheduling : 설정에서 
	- @Scheduled(cron = "....")
	
- Controller컨트롤러	
	- @RestController : spring 4.2  @Controller + @ResponseBody 를 대신해준다 
	- @Controller
	- @RequestMapping : value
		- @ResponseBody : 응답하는 body를 결정 
		- @PathVariable : path에 추가적으로, 선택적으로 
		- @RequestParam : required 파라미터를 들어오는것  
		- @RequestBody : 클라이언트에서 jsonObject로 전송되는 파라미터를 자동으로 javaClass 형식으로 변환한다. (UserBean Class 는 클라이언트에서 받을 파라미터를 get, set 으로 작성하면, bean에 등록된 massageConverters에서 자동으로 javaClass 형식에 맞게 컨버팅
	
- Configuration Bean설정 
	- @Configuration
	- @Bean : 새로운 빈객체를 제공할때 사용됨 
	- @Autowired : 사용시에 

- Service 서비스 : 비즈니스로직
	- @Service

---

### lombok 
- https://www.lesstif.com/display/JAVA/Lombok+-+Java+annotation+library
-1 lombok설치
-2 build.gradle에 추가
	- compile 'org.projectlombok:lombok:1.16.6'
-3 Gradle -> Refresh All

- 주요 애노태이션
	- @AllArgsConstructor
	- @Setter / @Getter
	- @Data
	- @ToString
	- @EqualsAndHashCode
	
	
### Json
- send 
	- http://babolsk.tistory.com/1067
	- Content-Type : application/json;charset=UTF-8
	- ex) { "_index" : "host-raw", "_type" : "healthcheck-raw", "host" : "localhost", "logObject" : 1 }
	
- springBoot에서 받기 
	- @RequestBody로 Object로 받음 Setter/Getter설정이 되어있음

- Json Parsing : Jackson / Gson
	

### Generic / Collection
#### Generic 타입 
- 모든 자바객체는 Object로 저장되고 관리되어져, collection에 저장할수있다. 문제는 이런 컬랙션이 어떤것이 담아져있는지 확인하기위한 방법이 실행시에 알수 다는 것이 문제가 될수있다.
- Generic타입 이란것은 프로그램의 안전성을 컴파일 단계에서부터 제시하여 <>사이에 선언된 객체 자료형이 아닌 다른 객체형이 저장되는 것을 허용하지 않게한다.

- Parameterized Type 파라미터타입 : <>
	- <T> / <E> / <K> / <V>
- Wildcard Type 와일드카드 타입 : <>>
	- <?> : 객체내부의 모든 제네릭타입은 Object로 인식한다.
	- <? super 객체자료형> : 
	- <? extends 객체자료형> : 명시된 객체자료형 하위 객체의 타입으로 제한한다.

#### Java Collection Framework
- 객체들을 한곳에 모아 관리하고 그것을 편하게 사용하기위해 제공되는 환경.
	- interfaces : 컬랙션들이 가져야하는 조직 / 기능을 추상적으로 표현
	- Implementations : 위의 인터페이스를 구체적으로 구현한 클래스를 의미한다. 
	- Algorithms : 구현한 객체들의 검색과 정렬과같은 유용한 동작, 즉 메서드 
	
- Collection<E>
	- List<E> : 순서가있는 데이터
		- ArrayList / Vector / Stack / LinkedList
	- Queue<E> : 여러쓰레드에 들어오는 작업을 순차적으로 처리할때 사용 
	- Set<E> : 순서에 상관없이 어떤데이터가 존재하는지 확인하기위한 용도
		- HashSet / TreeSet / LinkedHashSet
- Map<K,V> : Key-value쌍을 이루는 데이터
	- HashMap / TreeMap / LinkedHashMap / HashTable

---

### ElasticSearch Java API
- https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/client.html
- gradle추가 & Refresh All
```
compile 'org.elasticsearch:elasticsearch:1.5.2'
```

#### Client / Index / CRUD(GET, DELETE, UPDATE) / SEARCH / Aggregation
- Client
	- Node Client
	- Transport Client
	
- Index API
	- 
- Get API







