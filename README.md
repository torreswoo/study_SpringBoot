# study_SpringBoot

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
	- @RequestBody로 Object로 받음 Setter/Getter설정이되어있음

- Json Parsing : Jackson / Gson
	
### Generic / Collection
